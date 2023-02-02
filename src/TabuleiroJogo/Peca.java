package TabuleiroJogo;

public abstract class Peca {
    protected Posicao posicao;
    private Tabuleiro tabuleiro;

    public Peca(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        posicao = null;
    }

    protected Tabuleiro getTabuleiro() {
         return tabuleiro;
    }

    public abstract boolean[][] acoesPossiveis();

    public boolean acaoPossivel(Posicao posicao){
        return acoesPossiveis()[posicao.getLinha()][posicao.getColuna()];
    }

    public boolean existeAcaoPossivel(){
        boolean[][] mat = acoesPossiveis();
        for (int i=0;i<mat.length;i++){
            for(int j=0;j< mat.length;j++){
                if (mat[i][j]){
                    return true;
                }
            }
        }
        return false;
    }


}
