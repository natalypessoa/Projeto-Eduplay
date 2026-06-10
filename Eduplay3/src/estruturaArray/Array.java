package estruturaArray;

import modelo.*;
import aula.*;
import cordenacao.RegistroContato;
import sistemadenotas.*;

public class Array {

    // Crianças
    private Crianca[] criancas = new Crianca[10];
    private int contCriancas = 0;

    // Responsáveis
    private Responsavel[] responsaveis = new Responsavel[10];
    private int contResponsaveis = 0;

    // Professores
    private Professor[] professores = new Professor[10];
    private int contProfessores = 0;

    // Aulas
    private Aula[] aulas = new Aula[10];
    private int contAulas = 0;

    // Registros de Contato (Coordenação)
    private RegistroContato[] registros = new RegistroContato[20];
    private int contRegistros = 0;

    // Provas
    private Prova[] provas = new Prova[20];
    private int contProvas = 0;

    // Notas
    private Nota[] notas = new Nota[50];
    private int contNotas = 0;

    // ==================== GETTERS ====================

    public Crianca[] getCriancas() { return criancas; }
    public int getContCriancas() { return contCriancas; }

    public Responsavel[] getResponsaveis() { return responsaveis; }
    public int getContResponsaveis() { return contResponsaveis; }

    public Professor[] getProfessores() { return professores; }
    public int getContProfessores() { return contProfessores; }

    public Aula[] getAulas() { return aulas; }
    public int getContAulas() { return contAulas; }

    public Prova[] getProvas() { return provas; }
    public int getContProvas() { return contProvas; }

    public Nota[] getNotas() { return notas; }
    public int getContNotas() { return contNotas; }

    public RegistroContato[] getRegistros() { return registros; }
    public int getContRegistros() { return contRegistros; }

    // ==================== VALIDAÇÃO CPF DUPLICADO ====================

    public boolean cpfJaCadastrado(String cpf) {
        for (int i = 0; i < contCriancas; i++) {
            if (criancas[i].getCpf().equals(cpf)) return true;
        }
        for (int i = 0; i < contResponsaveis; i++) {
            if (responsaveis[i].getCpf().equals(cpf)) return true;
        }
        for (int i = 0; i < contProfessores; i++) {
            if (professores[i].getCpf().equals(cpf)) return true;
        }
        return false;
    }

    // ==================== CRIANÇAS ====================

    public boolean inserirCrianca(Crianca crianca) {
        if (cpfJaCadastrado(crianca.getCpf())) {
            System.out.println("❌ CPF já cadastrado no sistema! Verifique os dados.");
            return false;
        }
        if (contCriancas == criancas.length) {
            criancas = novoArrayCriancas();
        }
        criancas[contCriancas++] = crianca;
        return true;
    }

    private Crianca[] novoArrayCriancas() {
        Crianca[] novo = new Crianca[criancas.length + (criancas.length / 2)];
        System.arraycopy(criancas, 0, novo, 0, criancas.length);
        return novo;
    }

    public void exibirCriancas() {
        if (contCriancas == 0) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        for (int i = 0; i < contCriancas; i++) {
            System.out.println(criancas[i]);
            System.out.println("------------------------------------------------------------------------------");
        }
    }

    public boolean atualizarAluno(int matricula, String nome, String cpf, int idade,
                                   String email, String genero, String turma, String turno, String curso) {
        for (int i = 0; i < contCriancas; i++) {
            if (criancas[i].getMatricula() == matricula) {
                criancas[i].setNome(nome);
                criancas[i].setCpf(cpf);
                criancas[i].setIdade(idade);
                criancas[i].setEmail(email);
                criancas[i].setGenero(genero);
                criancas[i].setTurma(turma);
                criancas[i].setTurno(turno);
                criancas[i].setCurso(curso);
                return true;
            }
        }
        return false;
    }

    public boolean excluirAluno(int matriculaAluno) {
        for (int i = 0; i < contCriancas; i++) {
            if (criancas[i].getMatricula() == matriculaAluno) {
                Responsavel resp = criancas[i].getResponsavel();
                if (resp != null) {
                    for (int j = 0; j < contResponsaveis; j++) {
                        if (responsaveis[j] == resp) {
                            for (int k = j; k < contResponsaveis - 1; k++) {
                                responsaveis[k] = responsaveis[k + 1];
                            }
                            responsaveis[contResponsaveis - 1] = null;
                            contResponsaveis--;
                            break;
                        }
                    }
                }
                for (int j = i; j < contCriancas - 1; j++) {
                    criancas[j] = criancas[j + 1];
                }
                criancas[contCriancas - 1] = null;
                contCriancas--;
                return true;
            }
        }
        return false;
    }

    // ==================== RESPONSÁVEIS ====================

    public boolean inserirResponsavel(Responsavel responsavel) {
        if (cpfJaCadastrado(responsavel.getCpf())) {
            System.out.println("❌ CPF já cadastrado no sistema! Verifique os dados.");
            return false;
        }
        if (contResponsaveis == responsaveis.length) {
            responsaveis = novoArrayResponsaveis();
        }
        responsaveis[contResponsaveis++] = responsavel;
        return true;
    }

    private Responsavel[] novoArrayResponsaveis() {
        Responsavel[] novo = new Responsavel[responsaveis.length + (responsaveis.length / 2)];
        System.arraycopy(responsaveis, 0, novo, 0, responsaveis.length);
        return novo;
    }

    public void exibirResponsaveis() {
        if (contResponsaveis == 0) {
            System.out.println("Nenhum responsável cadastrado.");
            return;
        }
        for (int i = 0; i < contResponsaveis; i++) {
            System.out.println(responsaveis[i]);
            System.out.println("------------------------------------------------------------------------------");
        }
    }

    public void exibirResponsaveisPorAluno() {
        if (contCriancas == 0) {
            System.out.println("Nenhum aluno cadastrado até o momento.");
            return;
        }
        for (int i = 0; i < contCriancas; i++) {
            System.out.println(criancas[i]);
            if (criancas[i].getResponsavel() != null) {
                System.out.println(criancas[i].getResponsavel());
            } else {
                System.out.println("Responsável: Não vinculado.");
            }
            System.out.println("------------------------------------------------------------------------------");
        }
    }

    public boolean atualizarResponsavel(int matriculaAluno, String nome, String cpf,
                                         int idade, String email, String genero, String telefone) {
        for (int i = 0; i < contCriancas; i++) {
            if (criancas[i].getMatricula() == matriculaAluno) {
                Responsavel resp = criancas[i].getResponsavel();
                if (resp == null) return false;
                resp.setNome(nome);
                resp.setCpf(cpf);
                resp.setIdade(idade);
                resp.setEmail(email);
                resp.setGenero(genero);
                resp.setTelefone(telefone);
                return true;
            }
        }
        return false;
    }

    // ==================== BUSCA DE ALUNOS ====================

    public void buscarAlunoPorCpf(String cpfBusca) {
        for (int i = 0; i < contCriancas; i++) {
            if (criancas[i].getCpf().equals(cpfBusca)) {
                System.out.println("\n[Aluno Encontrado!]");
                System.out.println(criancas[i]);
                if (criancas[i].getResponsavel() != null) {
                    System.out.println(criancas[i].getResponsavel());
                }
                return;
            }
        }
        System.out.println("Aluno com o CPF " + cpfBusca + " não foi encontrado.");
    }

    public void buscarAlunoPorId(int idBusca) {
        for (int i = 0; i < contCriancas; i++) {
            if (criancas[i].getMatricula() == idBusca) {
                System.out.println("\n[Aluno Encontrado!]");
                System.out.println(criancas[i]);
                if (criancas[i].getResponsavel() != null) {
                    System.out.println(criancas[i].getResponsavel());
                }
                return;
            }
        }
        System.out.println("Aluno com o ID " + idBusca + " não foi encontrado.");
    }

    // Retorna objeto Crianca por ID (para uso interno)
    public Crianca encontrarAlunoPorId(int id) {
        for (int i = 0; i < contCriancas; i++) {
            if (criancas[i].getMatricula() == id) return criancas[i];
        }
        return null;
    }

    // ==================== PROFESSORES ====================

    public boolean inserirProfessor(Professor professor) {
        if (cpfJaCadastrado(professor.getCpf())) {
            System.out.println("❌ CPF já cadastrado no sistema! Verifique os dados.");
            return false;
        }
        if (contProfessores == professores.length) {
            professores = novoArrayProfessores();
        }
        professores[contProfessores++] = professor;
        return true;
    }

    private Professor[] novoArrayProfessores() {
        Professor[] novo = new Professor[professores.length + (professores.length / 2)];
        System.arraycopy(professores, 0, novo, 0, professores.length);
        return novo;
    }

    public void exibirProfessores() {
        if (contProfessores == 0) {
            System.out.println("Nenhum professor voluntário cadastrado.");
            return;
        }
        for (int i = 0; i < contProfessores; i++) {
            System.out.println(professores[i]);
            System.out.println("------------------------------------------------------------------------------");
        }
    }

    public boolean atualizarProfessor(int id, String nome, String cpf, int idade,
                                       String email, String genero, String especialidade) {
        for (int i = 0; i < contProfessores; i++) {
            if (professores[i].getid() == id) {
                professores[i].setNome(nome);
                professores[i].setCpf(cpf);
                professores[i].setIdade(idade);
                professores[i].setEmail(email);
                professores[i].setGenero(genero);
                professores[i].setEspecialidade(especialidade);
                return true;
            }
        }
        return false;
    }

    public boolean excluirProfessor(int id) {
        for (int i = 0; i < contProfessores; i++) {
            if (professores[i].getid() == id) {
                for (int j = 0; j < contAulas; j++) {
                    if (aulas[j].getProfessor() == professores[i]) {
                        aulas[j].setProfessor(null);
                    }
                }
                for (int j = i; j < contProfessores - 1; j++) {
                    professores[j] = professores[j + 1];
                }
                professores[contProfessores - 1] = null;
                contProfessores--;
                return true;
            }
        }
        return false;
    }

    public void buscarProfessorPorCpf(String cpfBusca) {
        for (int i = 0; i < contProfessores; i++) {
            if (professores[i].getCpf().equals(cpfBusca)) {
                System.out.println("\n[Professor Encontrado!]");
                System.out.println(professores[i]);
                return;
            }
        }
        System.out.println("Professor com o CPF " + cpfBusca + " não foi encontrado.");
    }

    public void buscarProfessorPorId(int idBusca) {
        for (int i = 0; i < contProfessores; i++) {
            if (professores[i].getid() == idBusca) {
                System.out.println("\n[Professor Encontrado!]");
                System.out.println(professores[i]);
                return;
            }
        }
        System.out.println("Professor com o ID " + idBusca + " não foi encontrado.");
    }

    // ==================== AULAS ====================

    public void inserirAula(Aula aula) {
        if (contAulas == aulas.length) {
            aulas = novoArrayAulas();
        }
        aulas[contAulas++] = aula;
    }

    private Aula[] novoArrayAulas() {
        Aula[] novo = new Aula[aulas.length + (aulas.length / 2)];
        System.arraycopy(aulas, 0, novo, 0, aulas.length);
        return novo;
    }

    public void exibirAulas() {
        if (contAulas == 0) {
            System.out.println("Nenhuma aula cadastrada.");
            return;
        }
        for (int i = 0; i < contAulas; i++) {
            System.out.println(aulas[i]);
            System.out.println("------------------------------------------------------------------------------");
        }
    }

    // ==================== COORDENAÇÃO — REGISTROS ====================

    public void inserirRegistro(RegistroContato r) {
        if (contRegistros == registros.length) {
            RegistroContato[] novo = new RegistroContato[registros.length + 10];
            System.arraycopy(registros, 0, novo, 0, registros.length);
            registros = novo;
        }
        registros[contRegistros++] = r;
    }

    public void exibirRegistrosPorAluno(int matricula) {
        boolean encontrou = false;
        for (int i = 0; i < contRegistros; i++) {
            if (registros[i].getAlunoMatricula() == matricula) {
                System.out.println(registros[i]);
                System.out.println("------------------------------------------------------------------------------");
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum registro de contato para este aluno.");
    }

    public void exibirTodosRegistros() {
        if (contRegistros == 0) {
            System.out.println("Nenhum registro de contato cadastrado.");
            return;
        }
        for (int i = 0; i < contRegistros; i++) {
            System.out.println(registros[i]);
            System.out.println("------------------------------------------------------------------------------");
        }
    }

    public void exibirAlunosEvadidos() {
        boolean encontrou = false;
        for (int i = 0; i < contCriancas; i++) {
            if (criancas[i].isEvasao()) {
                System.out.println(criancas[i]);
                System.out.println("------------------------------------------------------------------------------");
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum aluno evadido registrado.");
    }

    public void exibirAlunosEmRisco() {
        boolean encontrou = false;
        System.out.println("=== ALUNOS EM SITUAÇÃO DE RISCO ===");
        for (int i = 0; i < contCriancas; i++) {
            Crianca c = criancas[i];
            boolean freqBaixa = c.getTotalAulas() > 0 && c.getFrequencia() < 75.0;
            boolean notaBaixa = calcularMediaAluno(c.getMatricula()) < 6.0
                                && contNotasDoAluno(c.getMatricula()) > 0;
            boolean evadido = c.isEvasao();

            if (freqBaixa || notaBaixa || evadido) {
                System.out.println(c);
                if (freqBaixa)  System.out.println("  ⚠️  Frequência abaixo de 75%");
                if (notaBaixa)  System.out.println("  ⚠️  Média abaixo de 6.0 (risco de reprovação)");
                if (evadido)    System.out.println("  ⚠️  Aluno marcado como EVADIDO");
                System.out.println("------------------------------------------------------------------------------");
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("✅ Nenhum aluno em situação de risco no momento.");
    }

    // ==================== PROVAS ====================

    public void inserirProva(Prova prova) {
        if (contProvas == provas.length) {
            Prova[] novo = new Prova[provas.length + 10];
            System.arraycopy(provas, 0, novo, 0, provas.length);
            provas = novo;
        }
        provas[contProvas++] = prova;
    }

    public void exibirProvas() {
        if (contProvas == 0) {
            System.out.println("Nenhuma prova cadastrada.");
            return;
        }
        for (int i = 0; i < contProvas; i++) {
            System.out.println(provas[i]);
            System.out.println("------------------------------------------------------------------------------");
        }
    }

    public Prova encontrarProvaPorId(int id) {
        for (int i = 0; i < contProvas; i++) {
            if (provas[i].getId() == id) return provas[i];
        }
        return null;
    }

    // ==================== NOTAS ====================

    public boolean inserirNota(Nota nota) {
        // Bloquear aluno evadido
        Crianca aluno = encontrarAlunoPorId(nota.getAlunoMatricula());
        if (aluno != null && aluno.isEvasao()) {
            System.out.println("❌ Não é possível lançar nota para aluno evadido.");
            return false;
        }
        // Verificar se já existe nota deste aluno nesta prova
        for (int i = 0; i < contNotas; i++) {
            if (notas[i].getAlunoMatricula() == nota.getAlunoMatricula()
                    && notas[i].getProvaId() == nota.getProvaId()) {
                System.out.println("⚠️  Já existe nota lançada para este aluno nesta prova. Use atualizar.");
                return false;
            }
        }
        if (contNotas == notas.length) {
            Nota[] novo = new Nota[notas.length + 20];
            System.arraycopy(notas, 0, novo, 0, notas.length);
            notas = novo;
        }
        notas[contNotas++] = nota;
        return true;
    }

    public double calcularMediaAluno(int matricula) {
        double soma = 0;
        int count = 0;
        for (int i = 0; i < contNotas; i++) {
            if (notas[i].getAlunoMatricula() == matricula) {
                soma += notas[i].getValor();
                count++;
            }
        }
        if (count == 0) return 0.0;
        return soma / count;
    }

    private int contNotasDoAluno(int matricula) {
        int count = 0;
        for (int i = 0; i < contNotas; i++) {
            if (notas[i].getAlunoMatricula() == matricula) count++;
        }
        return count;
    }

    public void exibirBoletimAluno(int matricula) {
        Crianca aluno = encontrarAlunoPorId(matricula);
        if (aluno == null) {
            System.out.println("Aluno com ID " + matricula + " não encontrado.");
            return;
        }
        System.out.println("=== BOLETIM — " + aluno.getNome() + " (ID: " + matricula + ") ===");
        System.out.println("Curso: " + aluno.getCurso());
        System.out.println("------------------------------------------------------------------------------");

        boolean temNota = false;
        for (int i = 0; i < contNotas; i++) {
            if (notas[i].getAlunoMatricula() == matricula) {
                System.out.println(notas[i]);
                temNota = true;
            }
        }

        if (!temNota) {
            System.out.println("Nenhuma nota lançada para este aluno.");
        } else {
            double media = calcularMediaAluno(matricula);
            System.out.println("------------------------------------------------------------------------------");
            System.out.printf("Média Final: %.1f%n", media);
            System.out.println("Situação: " + (media >= 6.0 ? "APROVADO ✅" : "REPROVADO ❌"));
        }
        System.out.println("Frequência: " + String.format("%.1f", aluno.getFrequencia()) + "%");
        if (aluno.getTotalAulas() > 0 && aluno.getFrequencia() < 75.0) {
            System.out.println("⚠️  Frequência abaixo de 75%! Risco de reprovação por falta.");
        }
    }

    public void exibirBoletimCompleto() {
        if (contCriancas == 0) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        for (int i = 0; i < contCriancas; i++) {
            exibirBoletimAluno(criancas[i].getMatricula());
            System.out.println("==============================================================================");
        }
    }

    // ==================== RELATÓRIO DE AULAS POR CURSO ====================

    public void exibirRelatorioAulasPorCurso(String[] cursosDisponiveis) {
        System.out.println("=== STATUS DAS AULAS PRÁTICAS POR CURSO ===");
        for (String curso : cursosDisponiveis) {
            boolean temPratica = false;
            for (int i = 0; i < contAulas; i++) {
                if (aulas[i].getCurso().equals(curso) && aulas[i] instanceof AulaPratica) {
                    temPratica = true;
                    break;
                }
            }
            if (temPratica) {
                System.out.println("✅ [OK] " + curso);
            } else {
                System.out.println("⚠️  [ALERTA] " + curso + " — NÃO possui aula prática cadastrada!");
            }
        }
    }
}