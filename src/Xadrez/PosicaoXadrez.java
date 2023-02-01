package Xadrez;

import TabuleiroJogo.Posicao;

public class PosicaoXadrez {
    private char coluna;
    private int linha;

    public PosicaoXadrez(char coluna, int linha) {
        if (coluna<'a' || coluna>'h'|| linha>8 ){
            throw new XadrezException("Erro ao instanciar PosicaoXadrez. Entradas validas são de a1 até h8");
        }
        this.coluna = coluna;
        this.linha = linha;
    }

    public void setColuna(char coluna) {
        this.coluna = coluna;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    protected Posicao toPosicao(){
        return new Posicao(8-linha,coluna-'a');
    }

    protected static PosicaoXadrez fromPosition(Posicao posicao){
        return new PosicaoXadrez((char)('a'-posicao.getColuna()),8- posicao.getLinha());
    }

    @Override
    public String toString() {
        return ""+ coluna +linha;
    }
}
