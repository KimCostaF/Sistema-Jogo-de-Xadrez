package Xadrez;

import TabuleiroJogo.Peca;
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

    public PecaXadrez moverPecaXadrez(PosicaoXadrez posicaoOrigem,PosicaoXadrez posicaoDestino){
        Posicao origem = posicaoOrigem.toPosicao();
        Posicao destino = posicaoDestino.toPosicao();
        validarPosicaoOrigem(origem);
        Peca pecaCapturada = acaoMover(origem,destino);
        return (PecaXadrez) pecaCapturada;
    }

    private Peca acaoMover(Posicao origem,Posicao destino){
        Peca p = tabuleiro.removerPeca(origem);
        Peca pecacapturada = tabuleiro.removerPeca(destino);
        tabuleiro.colocapeca(p,destino);
        return pecacapturada;
    }

    private void validarPosicaoOrigem(Posicao posicao){
        if (!tabuleiro.possuiPeca(posicao)){
            throw new XadrezException("Não há peça na posição de origem");
        }
    }

    private void colocarNovaPeca(char coluna,int linha,PecaXadrez pecaXadrez){
        tabuleiro.colocapeca(pecaXadrez,new PosicaoXadrez(coluna,linha).toPosicao());
    }

    private void preparacaoInicial(){
        colocarNovaPeca('c',1,new Torre(tabuleiro,Cor.Branco));
        colocarNovaPeca('c',2,new Torre(tabuleiro,Cor.Branco));
        colocarNovaPeca('d',2,new Torre(tabuleiro,Cor.Branco));
        colocarNovaPeca('e',2,new Torre(tabuleiro,Cor.Branco));
        colocarNovaPeca('e',1,new Torre(tabuleiro,Cor.Branco));
        colocarNovaPeca('d',1,new Torre(tabuleiro,Cor.Branco));

        colocarNovaPeca('c',7,new Torre(tabuleiro,Cor.Preto));
        colocarNovaPeca('c',8,new Torre(tabuleiro,Cor.Preto));
        colocarNovaPeca('d',7,new Torre(tabuleiro,Cor.Preto));
        colocarNovaPeca('e',7,new Torre(tabuleiro,Cor.Preto));
        colocarNovaPeca('e',8,new Torre(tabuleiro,Cor.Preto));
        colocarNovaPeca('d',8,new Rei(tabuleiro,Cor.Preto));

    }
}
