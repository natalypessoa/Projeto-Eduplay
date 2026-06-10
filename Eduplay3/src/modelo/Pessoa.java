package modelo;

public abstract class Pessoa {
    private static int contador = 0;
    private int id;
    private String email;
    private String nome;
    private String cpf;
    private int idade;
    private String genero;
   
    // Construtor padrão
    public Pessoa(String nome, String cpf, int idade, String email, String genero) {
        this.nome = nome;
        this.id = ++contador;
        this.email = email;
        this.cpf = cpf;
        this.idade = idade;
        this.genero = genero;
    }

    public int getid() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    @Override
    public String toString() {
        return "ID: " + id + "\n" +
               "Nome: " + nome + "\n" +
               "CPF: " + cpf + "\n" +
               "Idade: " + idade + "\n" +
               "Email: " + email + "\n" +
               "Gênero: " + genero;
    }
}

    
