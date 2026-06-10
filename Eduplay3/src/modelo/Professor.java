package modelo;

public class Professor extends Pessoa {
    private String especialidade;

    public Professor(String nome, String cpf, int idade, String email, String genero, String especialidade) {
        super(nome, cpf, idade, email, genero);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return "=== DADOS DO PROFESSOR (VOLUNTÁRIO) ===\n" +
               super.toString() + "\n" +
               "Especialidade: " + especialidade;
    }
}
