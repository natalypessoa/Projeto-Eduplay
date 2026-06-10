package aula;
import modelo.*;

public class AulaPratica extends Aula {
    private String atividade;

    public AulaPratica(String tema, Professor professor, String curso, String atividade) {
        super(tema, professor, curso);
        this.atividade = atividade;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    @Override
    public String getTipo() {
        return "Prática";
    }

    @Override
    public String toString() {
        return "=== AULA PRÁTICA ===\n" +
               super.toString() + "\n" +
               "Atividade Prática: " + atividade;
    }
}