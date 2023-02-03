package application;

import Xadrez.PecaXadrez;
import Xadrez.Cor;
import Xadrez.PosicaoXadrez;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static PosicaoXadrez lerPosicaoXadrez(Scanner scanner){
        try{
            String s = scanner.nextLine();
            char coluna = s.charAt(0);
            int linha = Integer.parseInt(s.substring(1));
            return new PosicaoXadrez(coluna,linha);
        }
        catch (RuntimeException e){
            throw new InputMismatchException("Erro ao ler a posição da peça de xadrez, valores válidos são de a1 até" +
                    "h8");
        }

    }

    public static void printTabuleiro(PecaXadrez[][] pecaXadrezs){
        for (int i=0;i< pecaXadrezs.length;i++){
            System.out.print((8 - i) + " ");
            for (int j=0;j< pecaXadrezs.length;j++){
                printPeca(pecaXadrezs[i][j],false);
            }
            System.out.println();
        }
        System.out.print("  a b c d e f g h");
    }
    private static void printPeca(PecaXadrez pecaXadrez, boolean background){
        if (background){
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (pecaXadrez ==null){
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (pecaXadrez.getCor() == Cor.Branco){
                System.out.print(ANSI_WHITE + pecaXadrez + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + pecaXadrez + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    public static void printTabuleiro(PecaXadrez[][] pecaXadrezs,boolean[][] acoesPossiveis){
        for (int i=0;i< pecaXadrezs.length;i++){
            System.out.print((8 - i) + " ");
            for (int j=0;j< pecaXadrezs.length;j++){
                printPeca(pecaXadrezs[i][j],acoesPossiveis[i][j]);
            }
            System.out.println();
        }
        System.out.print("  a b c d e f g h");
    }
}
