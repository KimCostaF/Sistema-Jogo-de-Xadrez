package Xadrez;

import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import Xadrez.PeçasXadrez.Rei;
import Xadrez.PeçasXadrez.Torre;

public class PartidaXadrez {
    private Tabuleiro tabuleiro;

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8,8);
        preparacaoInicial();
    }

    public PecaXadrez[][] getPecas(){
        PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i=0;i< tabuleiro.getLinhas();i++){
            for (int j=0;j< tabuleiro.getColunas();j++){
                mat[i][j] = (PecaXadrez) tabuleiro.peca(i,j);
            }
        }
        return mat;
    }

    private void preparacaoInicial(){
        tabuleiro.colocapeca(new Torre(tabuleiro,Cor.Branco), new Posicao(2,1));
        tabuleiro.colocapeca(new Rei(tabuleiro,Cor.Preto), new Posicao(0,4));
        tabuleiro.colocapeca(new Rei(tabuleiro,Cor.Branco), new Posicao(7,4));
    }
}
