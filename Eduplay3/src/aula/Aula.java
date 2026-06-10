package aula;
import modelo.*;

public abstract class Aula {
    private static int contador = 0;
    private int id;
    private String tema;
    private Professor professor;
    private String curso;

    public Aula(String tema, Professor professor, String curso) {
        this.id = ++contador;
        this.tema = tema;
        this.professor = professor;
        this.curso = curso;
    }

    public int getId() {
        return id;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        return "ID da Aula: " + id + "\n" +
               "Tema: " + tema + "\n" +
               "Tipo: " + getTipo() + "\n" +
               "Curso: " + curso + "\n" +
               "Professor Voluntário: " + (professor != null ? professor.getNome() : "Não atribuído");
    }
}

