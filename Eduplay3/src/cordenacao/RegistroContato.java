package cordenacao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import modelo.*;
import aula.*;
        

public class RegistroContato {
    private static int contador = 0;
    private int id;
    private String dataHora;
    private String tipo;       // "Ligação" | "Email" | "Evasão" | "Observação"
    private String descricao;
    private String alunoNome;
    private int alunoMatricula;

    public RegistroContato(String tipo, String descricao, String alunoNome, int alunoMatricula) {
        this.id = ++contador;
        this.tipo = tipo;
        this.descricao = descricao;
        this.alunoNome = alunoNome;
        this.alunoMatricula = alunoMatricula;
        // Data e hora geradas automaticamente
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.dataHora = LocalDateTime.now().format(fmt);
    }

    public int getId() { return id; }
    public String getDataHora() { return dataHora; }
    public String getTipo() { return tipo; }
    public String getDescricao() { return descricao; }
    public String getAlunoNome() { return alunoNome; }
    public int getAlunoMatricula() { return alunoMatricula; }

    @Override
    public String toString() {
        return "=== REGISTRO DE CONTATO ===\n" +
               "ID do Registro: " + id + "\n" +
               "Data/Hora: " + dataHora + "\n" +
               "Tipo: " + tipo + "\n" +
               "Aluno: " + alunoNome + " (ID: " + alunoMatricula + ")\n" +
               "Descrição: " + descricao;
    }
}
