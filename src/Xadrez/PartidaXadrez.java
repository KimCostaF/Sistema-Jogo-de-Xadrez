package Xadrez;

import TabuleiroJogo.Peca;
import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import Xadrez.PeçasXadrez.Rei;
import Xadrez.PeçasXadrez.Torre;

import java.util.ArrayList;
import java.util.List;

public class PartidaXadrez {
    private int turno;
    private Cor jogadorAtual;
    private Tabuleiro tabuleiro;
    private List<Peca> pecasTabuleiro = new ArrayList<>();
    private List<Peca> pecasCapturadasXadrez = new ArrayList<>();

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8,8);
        turno = 1;
        jogadorAtual = Cor.Branco;
        preparacaoInicial();
    }

    public int getTurno() {
        return turno;
    }

    public Cor getJogadorAtual() {
        return jogadorAtual;
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

    public boolean[][] acoesPossiveis(PosicaoXadrez posicaoOrigem){
        Posicao posicao = posicaoOrigem.toPosicao();
        validarPosicaoOrigem(posicao);
        return tabuleiro.peca(posicao).acoesPossiveis();
    }

    public PecaXadrez moverPecaXadrez(PosicaoXadrez posicaoOrigem,PosicaoXadrez posicaoDestino){
        Posicao origem = posicaoOrigem.toPosicao();
        Posicao destino = posicaoDestino.toPosicao();
        validarPosicaoOrigem(origem);
        validarPosicaoDestino(origem,destino);
        Peca pecaCapturada = acaoMover(origem,destino);
        proximoTurno();
        return (PecaXadrez) pecaCapturada;
    }

    private Peca acaoMover(Posicao origem,Posicao destino){
        Peca p = tabuleiro.removerPeca(origem);
        Peca pecacapturada = tabuleiro.removerPeca(destino);
        tabuleiro.colocapeca(p,destino);

        if (pecacapturada!=null){
            pecasTabuleiro.remove(pecacapturada);
            pecasCapturadasXadrez.add(pecacapturada);
        }

        return pecacapturada;
    }

    private void validarPosicaoOrigem(Posicao posicao){
        if (!tabuleiro.possuiPeca(posicao)){
            throw new XadrezException("Não há peça na posição de origem");
        }
        if (jogadorAtual!= ((PecaXadrez)tabuleiro.peca(posicao)).getCor()){
            throw new XadrezException("A peça escolhida não é do jogador atual");
        }
        if (!tabuleiro.peca(posicao).existeAcaoPossivel()){
            throw new XadrezException("Não há movimentos possiveis para essa peça");
        }
    }

    private void validarPosicaoDestino(Posicao origem,Posicao destino){
        if (!tabuleiro.peca(origem).acaoPossivel(destino)){
            throw new XadrezException("A peça escolhida não pode mover para a posição de destino");
        }
    }

    private void colocarNovaPeca(char coluna,int linha,PecaXadrez pecaXadrez){
        tabuleiro.colocapeca(pecaXadrez,new PosicaoXadrez(coluna,linha).toPosicao());
        pecasTabuleiro.add(pecaXadrez);
    }

    private void proximoTurno(){
        turno++;
        jogadorAtual = (jogadorAtual == Cor.Branco) ? Cor.Preto : Cor.Branco;
    }

    private void preparacaoInicial(){
        colocarNovaPeca('c',1,new Torre(tabuleiro,Cor.Branco));
        colocarNovaPeca('c',2,new Torre(tabuleiro,Cor.Branco));
        colocarNovaPeca('d',2,new Torre(tabuleiro,Cor.Branco));
        colocarNovaPeca('e',2,new Torre(tabuleiro,Cor.Branco));
        colocarNovaPeca('e',1,new Torre(tabuleiro,Cor.Branco));
        colocarNovaPeca('d',1,new Rei(tabuleiro,Cor.Branco));

        colocarNovaPeca('c',7,new Torre(tabuleiro,Cor.Preto));
        colocarNovaPeca('c',8,new Torre(tabuleiro,Cor.Preto));
        colocarNovaPeca('d',7,new Torre(tabuleiro,Cor.Preto));
        colocarNovaPeca('e',7,new Torre(tabuleiro,Cor.Preto));
        colocarNovaPeca('e',8,new Torre(tabuleiro,Cor.Preto));
        colocarNovaPeca('d',8,new Rei(tabuleiro,Cor.Preto));

    }
}
