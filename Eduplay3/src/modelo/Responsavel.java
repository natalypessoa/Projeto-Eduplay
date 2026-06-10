package modelo;

public class Responsavel extends Pessoa {
    private String telefone;

    public Responsavel(String nome, String cpf, int idade, String email, String genero, String telefone) {
        super(nome, cpf, idade, email, genero);
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "=== DADOS DO RESPONSÁVEL ===\n" +
               super.toString() + "\n" +
               "Telefone: " + telefone;
    }
}
