package Xadrez.PeçasXadrez;

import TabuleiroJogo.Peca;
import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PecaXadrez;

public class Rei extends PecaXadrez {
    public Rei(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "R";
    }

    private boolean podeMover(Posicao posicao){
        PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
        return p==null || p.getCor() != getCor();
    }

    @Override
    public boolean[][] acoesPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0,0);

        //Movimento vertical cima
        p.setValores(posicao.getLinha()-1,posicao.getColuna());
        if (getTabuleiro().posicaoExiste(p)&&podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //Movimento vertical para baixo
        p.setValores(posicao.getLinha()+1,posicao.getColuna());
        if (getTabuleiro().posicaoExiste(p)&&podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //Movimento horizontal para esquerda
        p.setValores(posicao.getLinha(),posicao.getColuna()-1);
        if (getTabuleiro().posicaoExiste(p)&&podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //Movimento horizontal para direita
        p.setValores(posicao.getLinha(),posicao.getColuna()+1);
        if (getTabuleiro().posicaoExiste(p)&&podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //Movimento diagonal superior esquerdo
        p.setValores(posicao.getLinha()-1,posicao.getColuna()-1);
        if (getTabuleiro().posicaoExiste(p)&&podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //Movimento diagonal superior direito
        p.setValores(posicao.getLinha()-1,posicao.getColuna()+1);
        if (getTabuleiro().posicaoExiste(p)&&podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //Movimento diagonal inferior esquerdo
        p.setValores(posicao.getLinha()+1,posicao.getColuna()-1);
        if (getTabuleiro().posicaoExiste(p)&&podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //Movimento diagonal inferior direito
        p.setValores(posicao.getLinha()+1,posicao.getColuna()+1);
        if (getTabuleiro().posicaoExiste(p)&&podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }




        return mat;
    }


}
