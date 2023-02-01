package Xadrez;

import TabuleiroJogo.Peca;
import TabuleiroJogo.Tabuleiro;

public class PecaXadrez extends Peca{
    private Cor cor;

    public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }
}
