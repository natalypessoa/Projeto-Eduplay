
package modelo;

public class Crianca extends Pessoa {

    private static int contador = 0;
    private int matricula;
    private String turma;
    private String turno;
    private String curso;
    private Responsavel responsavel;

    // Frequência
    private int presencas = 0;
    private int totalAulas = 0;
    private boolean assistiuAulaPratica = false;

    // Status
    private boolean evasao = false;

    public Crianca(String nome, String cpf, int idade, String email, String genero,
                   String turma, String turno, String curso, Responsavel responsavel) {
        super(nome, cpf, idade, email, genero);
        this.matricula = ++contador;
        this.turma = turma;
        this.turno = turno;
        this.curso = curso;
        this.responsavel = responsavel;
    }

    public int getMatricula() { return matricula; }

    public String getTurma() { return turma; }
    public void setTurma(String turma) { this.turma = turma; }

    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public Responsavel getResponsavel() { return responsavel; }
    public void setResponsavel(Responsavel responsavel) { this.responsavel = responsavel; }

    public int getPresencas() { return presencas; }
    public void setPresencas(int presencas) { this.presencas = presencas; }

    public int getTotalAulas() { return totalAulas; }
    public void setTotalAulas(int totalAulas) { this.totalAulas = totalAulas; }

    public boolean isAssistiuAulaPratica() { return assistiuAulaPratica; }
    public void setAssistiuAulaPratica(boolean assistiu) { this.assistiuAulaPratica = assistiu; }

    public boolean isEvasao() { return evasao; }
    public void setEvasao(boolean evasao) { this.evasao = evasao; }

    public void registrarPresenca(boolean presente, boolean ehPratica) {
        this.totalAulas++;
        if (presente) {
            this.presencas++;
            if (ehPratica) {
                this.assistiuAulaPratica = true;
            }
        }
    }

    public double getFrequencia() {
        if (totalAulas == 0) return 100.0;
        return ((double) presencas / totalAulas) * 100.0;
    }

    private String statusFrequencia() {
        if (totalAulas == 0) return "";
        double freq = getFrequencia();
        if (freq < 75.0) return "  ⚠️  ALERTA: Frequência abaixo de 75%! Risco de reprovação por falta.";
        return "";
    }

    @Override
    public String toString() {
        String status = evasao ? "Status: EVADIDO ⚠️" : "Status: Ativo ✅";
        String alerta = statusFrequencia();
        return "=== DADOS DO ALUNO ===\n" +
               super.toString() + "\n" +
               "ID/Matrícula: " + matricula + "\n" +
               "Curso: " + curso + "\n" +
               "Turno: " + turno + "\n" +
               "Turma: " + turma + "\n" +
               "Total de Aulas: " + totalAulas + "\n" +
               "Presenças: " + presencas + "\n" +
               "Frequência: " + String.format("%.1f", getFrequencia()) + "%\n" +
               "Assistiu Aula Prática: " + (assistiuAulaPratica ? "Sim" : "Não") + "\n" +
               status +
               (alerta.isEmpty() ? "" : "\n" + alerta);
    }
}
