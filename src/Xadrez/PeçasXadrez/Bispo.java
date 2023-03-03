package Xadrez.PeçasXadrez;

import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PecaXadrez;

public class Bispo extends PecaXadrez {

    public Bispo(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] acoesPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0,0);

        //Movimentação diagonal esquerda superior
        p.setValores(posicao.getLinha()-1,posicao.getColuna()-1);
        while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().possuiPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setValores(p.getLinha()-1,p.getColuna()-1);
        }
        if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // Movimentação diagonal direita superior
        p.setValores(posicao.getLinha()-1,posicao.getColuna()+1);
        while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().possuiPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setValores(p.getLinha()-1,p.getColuna()+1);
        }
        if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //Movimentação diagonal direita inferior
        p.setValores(posicao.getLinha()+1,posicao.getColuna()+1);
        while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().possuiPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setValores(p.getLinha()+1,p.getColuna()+1);
        }
        if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //Movimentação diagonal esquerda inferior
        p.setValores(posicao.getLinha()+1,posicao.getColuna()-1);
        while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().possuiPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setValores(p.getLinha()+1,p.getColuna()-1);
        }
        if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }






        return mat;
}

}
