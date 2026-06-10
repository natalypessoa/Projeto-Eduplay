package sistemadenotas;
import modelo.*;
import aula.*;


public class Nota {
    private static int contador = 0;
    private int id;
    private int provaId;
    private String provaNome;
    private String curso;
    private int alunoMatricula;
    private String alunoNome;
    private double valor;  // 0.0 a 10.0

    public Nota(int provaId, String provaNome, String curso, int alunoMatricula, String alunoNome, double valor) {
        this.id = ++contador;
        this.provaId = provaId;
        this.provaNome = provaNome;
        this.curso = curso;
        this.alunoMatricula = alunoMatricula;
        this.alunoNome = alunoNome;
        this.valor = valor;
    }

    public int getId() { return id; }
    public int getProvaId() { return provaId; }
    public String getProvaNome() { return provaNome; }
    public String getCurso() { return curso; }
    public int getAlunoMatricula() { return alunoMatricula; }
    public String getAlunoNome() { return alunoNome; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    @Override
    public String toString() {
        return "Prova: " + provaNome + " (ID: " + provaId + ")" + "\n" +
               "Nota: " + String.format("%.1f", valor);
    }
}
