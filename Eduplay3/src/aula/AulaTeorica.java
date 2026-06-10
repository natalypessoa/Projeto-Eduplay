package aula;
import modelo.*;

public class AulaTeorica extends Aula {
    private String conteudoTeorico;

    public AulaTeorica(String tema, Professor professor, String curso, String conteudoTeorico) {
        super(tema, professor, curso);
        this.conteudoTeorico = conteudoTeorico;
    }

    public String getConteudoTeorico() {
        return conteudoTeorico;
    }

    public void setConteudoTeorico(String conteudoTeorico) {
        this.conteudoTeorico = conteudoTeorico;
    }

    @Override
    public String getTipo() {
        return "Teórica";
    }

    @Override
    public String toString() {
        return "=== AULA TEÓRICA ===\n" +
               super.toString() + "\n" +
               "Conteúdo Teórico: " + conteudoTeorico;
    }
}
