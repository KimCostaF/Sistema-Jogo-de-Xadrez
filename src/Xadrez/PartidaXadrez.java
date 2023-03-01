package Xadrez;

import TabuleiroJogo.Peca;
import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import Xadrez.PeçasXadrez.Peao;
import Xadrez.PeçasXadrez.Rei;
import Xadrez.PeçasXadrez.Torre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartidaXadrez {
    private int turno;
    private Cor jogadorAtual;
    private boolean cheque;

    private boolean chequemate;
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

    public Cor  getJogadorAtual() {
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

    public boolean getCheque(){
        return cheque;
    }

    public boolean getChequemate(){
        return chequemate;
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
        if (Testecheque(jogadorAtual)){
            desfazerAcao(origem,destino,pecaCapturada);
            throw new XadrezException("O jogador não pode se colocar em chequemate");
        }
        cheque = (Testecheque(oponente(jogadorAtual)))?true:false;

        if (Testechequemate(oponente(jogadorAtual))){
            chequemate = true;
        }else {
            proximoTurno();
        }


        return (PecaXadrez) pecaCapturada;
    }

    private Peca acaoMover(Posicao origem,Posicao destino){
        PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(origem);
        p.aumentarContadorMovimento();
        Peca pecacapturada = tabuleiro.removerPeca(destino);
        tabuleiro.colocapeca(p,destino);

        if (pecacapturada!=null){
            pecasTabuleiro.remove(pecacapturada);
            pecasCapturadasXadrez.add(pecacapturada);
        }

        return pecacapturada;
    }

    private void desfazerAcao(Posicao origem,Posicao destino, Peca pecaCapturada){
        PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(destino);
        p.diminuirContadorMovimento();
        tabuleiro.colocapeca(p,origem);

        if (pecaCapturada !=null){
            tabuleiro.colocapeca(pecaCapturada,destino);
            pecasCapturadasXadrez.remove(pecaCapturada);
            pecasTabuleiro.add(pecaCapturada);
        }
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

    private Cor oponente(Cor cor){
        return (cor == Cor.Branco)? Cor.Preto: Cor.Branco;
    }

    private PecaXadrez rei(Cor cor){
        List<Peca> lista = pecasTabuleiro.stream().filter(x->((PecaXadrez)x).getCor()==cor).collect(Collectors.toList());
        for (Peca p :lista){
            if (p instanceof Rei){
                return (PecaXadrez) p;
            }
        }
        throw new IllegalStateException("Não há rei da cor "+cor+" no tabuleiro");
    }

    private boolean Testecheque(Cor cor){
        Posicao posicaorei = rei(cor).getPosicaoXadrez().toPosicao();
        List<Peca> pecasoponente = pecasTabuleiro.stream().filter(x->((PecaXadrez)x).getCor()==oponente(cor)).collect(Collectors.toList());
        for(Peca p: pecasoponente){
            boolean[][] mat = p.acoesPossiveis();
            if (mat[posicaorei.getLinha()][posicaorei.getColuna()]){
                return true;
            }

        }
        return false;
    }

    private boolean Testechequemate(Cor cor){
        if (!Testecheque(cor)){
            return false;
        }
        List<Peca> lista = pecasTabuleiro.stream().filter(x->((PecaXadrez)x).getCor()==cor).collect(Collectors.toList());
        for (Peca p : lista){
            boolean[][] mat = p.acoesPossiveis();
            for (int i=0;i<tabuleiro.getLinhas();i++){
                for (int j=0;j<tabuleiro.getColunas();j++){
                    if (mat[i][j]){
                        Posicao origem  = ((PecaXadrez)p).getPosicaoXadrez().toPosicao();
                        Posicao destino = new Posicao(i,j);
                        Peca pecacapturada = acaoMover(origem,destino);
                        boolean testeCheque = Testecheque(cor);
                        desfazerAcao(origem,destino,pecacapturada);
                        if (!testeCheque){
                            return false;
                        }
                    }
                }
            }


        }
        return true;
    }

    //Preparacao inicial das peças
    /*
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

     */

    //outro metodo de preparacao porem facilitando um cheque mate
    /*
    private void preparacaoInicial(){
        colocarNovaPeca('h',7,new Torre(tabuleiro,Cor.Branco));
        colocarNovaPeca('d',1,new Torre(tabuleiro,Cor.Branco));
        colocarNovaPeca('e',1,new Rei(tabuleiro,Cor.Branco));

        colocarNovaPeca('b',8,new Torre(tabuleiro,Cor.Preto));
        colocarNovaPeca('a',8,new Rei(tabuleiro,Cor.Preto));


    }
     */

    private void preparacaoInicial(){
        colocarNovaPeca('a',1,new Torre(tabuleiro,Cor.Branco));
        colocarNovaPeca('e',1,new Rei(tabuleiro,Cor.Branco));
        colocarNovaPeca('h',1,new Torre(tabuleiro,Cor.Branco));
        colocarNovaPeca('a',2,new Peao(tabuleiro,Cor.Branco));
        colocarNovaPeca('b',2,new Peao(tabuleiro,Cor.Branco));
        colocarNovaPeca('c',2,new Peao(tabuleiro,Cor.Branco));
        colocarNovaPeca('d',2,new Peao(tabuleiro,Cor.Branco));
        colocarNovaPeca('e',2,new Peao(tabuleiro,Cor.Branco));
        colocarNovaPeca('f',2,new Peao(tabuleiro,Cor.Branco));
        colocarNovaPeca('g',2,new Peao(tabuleiro,Cor.Branco));
        colocarNovaPeca('h',2,new Peao(tabuleiro,Cor.Branco));

        colocarNovaPeca('a',8,new Torre(tabuleiro,Cor.Preto));
        colocarNovaPeca('e',8,new Rei(tabuleiro,Cor.Preto));
        colocarNovaPeca('h',8,new Torre(tabuleiro,Cor.Preto));
        colocarNovaPeca('a',7,new Peao(tabuleiro,Cor.Preto));
        colocarNovaPeca('b',7,new Peao(tabuleiro,Cor.Preto));
        colocarNovaPeca('c',7,new Peao(tabuleiro,Cor.Preto));
        colocarNovaPeca('d',7,new Peao(tabuleiro,Cor.Preto));
        colocarNovaPeca('e',7,new Peao(tabuleiro,Cor.Preto));
        colocarNovaPeca('f',7,new Peao(tabuleiro,Cor.Preto));
        colocarNovaPeca('g',7,new Peao(tabuleiro,Cor.Preto));
        colocarNovaPeca('h',7,new Peao(tabuleiro,Cor.Preto));

    }
}
