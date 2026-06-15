package aplicacao;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.ExcecaoXadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PartidaXadrez partida = new PartidaXadrez();

        List<PecaXadrez> capturadas = new ArrayList<>();

        while (!partida.getXequeMate()) {

            try {
                Interface.limparTela();
                Interface.imprimirPartida(partida, capturadas);

                System.out.println();
                System.out.print("Origem: ");
                PosicaoXadrez origem = Interface.lerPosicaoXadrez(sc);

                boolean[][] movimentosPossiveis = partida.movimentosPossiveis(origem);

                Interface.limparTela();
                Interface.imprimirTabuleiro(partida.getPecas(), movimentosPossiveis);

                System.out.println();
                System.out.print("Destino: ");
                PosicaoXadrez destino = Interface.lerPosicaoXadrez(sc);

                PecaXadrez capturada = partida.executarJogadaXadrez(origem, destino);

                if (capturada != null) {
                    capturadas.add(capturada);
                }

                if (partida.getPromovida() != null) {

                    System.out.print("Escolha a peça para promoção (B/C/T/Q): ");
                    String tipo = sc.nextLine().toUpperCase();

                    partida.substituirPecaPromovida(tipo);
                }

            } catch (ExcecaoXadrez e) {
                System.out.println(e.getMessage());
                sc.nextLine();

            } catch (InputMismatchException e) {
                System.out.println("Erro de entrada.");
                sc.nextLine();
            }
        }
        Interface.limparTela();
        Interface.imprimirPartida(partida, capturadas);
        //oi
    }
}