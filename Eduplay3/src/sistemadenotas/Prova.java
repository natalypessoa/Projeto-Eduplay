package sistemadenotas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import modelo.*;

public class Prova {
    private static int contador = 0;
    private int id;
    private String nomeProva;
    private String curso;
    private String dataAplicacao;

    public Prova(String nomeProva, String curso) {
        this.id = ++contador;
        this.nomeProva = nomeProva;
        this.curso = curso;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.dataAplicacao = LocalDateTime.now().format(fmt);
    }

    public int getId() { return id; }
    public String getNomeProva() { return nomeProva; }
    public void setNomeProva(String nomeProva) { this.nomeProva = nomeProva; }
    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
    public String getDataAplicacao() { return dataAplicacao; }

    @Override
    public String toString() {
        return "=== PROVA ===\n" +
               "ID da Prova: " + id + "\n" +
               "Nome: " + nomeProva + "\n" +
               "Curso: " + curso + "\n" +
               "Data de Aplicação: " + dataAplicacao;
    }
}
