package application;

import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;
import Xadrez.PosicaoXadrez;
import Xadrez.XadrezException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PartidaXadrez partidaXadrez = new PartidaXadrez();
        List<PecaXadrez> Capturada = new ArrayList<>();
        while (true){
             try{
                UI.clearScreen();
                UI.imprimirPartida(partidaXadrez,Capturada  );
                System.out.println();
                System.out.printf("Origem: ");
                PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);


                boolean[][] movimentosPossiveis = partidaXadrez.acoesPossiveis(origem);
                UI.clearScreen();
                UI.printTabuleiro(partidaXadrez.getPecas(), movimentosPossiveis);

                System.out.println();
                System.out.print("Destino: ");
                PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);

                PecaXadrez pecacapturada = partidaXadrez.moverPecaXadrez(origem,destino);

                if (pecacapturada != null){
                    Capturada.add(pecacapturada);
                }
            }


        catch (XadrezException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        catch (InputMismatchException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }

        }




    }
}