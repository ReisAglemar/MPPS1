import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        char[][] tabuleiro = new char[3][3];
        boolean ganhador = false;
        int jogadas = 0;

        tabuleiro = preparaTabuleiro(tabuleiro);
        mostraTabuleiro(tabuleiro);

        while (!ganhador) {

            if (jogadas%2 == 0){
                tabuleiro = solicitaJogada(tabuleiro, 'X', sc);
            } else {
                tabuleiro = solicitaJogada(tabuleiro, 'O', sc);
            }

            mostraTabuleiro(tabuleiro);
            ganhador = verificaGanhador(tabuleiro);
            jogadas++;
        }
    }

    public static char[][] preparaTabuleiro(char[][] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                tabuleiro[i][j] = '-';
            }
        }
        return tabuleiro;
    }

    public static void mostraTabuleiro(char[][] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                System.out.print(tabuleiro[i][j] + " ");
                if (j == 2) {
                    System.out.println(" ");
                }
            }
        }
    }

    public static char[][] solicitaJogada(char[][] tabuleiro, char jogador, Scanner sc) {

        while (true) {
            String informacaoJogada = """
                    
                        Jogador %s é a Sua Vez de Jogar.
                        Escolha Sua Posição!
                    """.formatted(jogador);
            System.out.println(informacaoJogada);

            System.out.print("De 1 a 3, Escolha Sua Linha: ");
            String linhaTemporaria = sc.nextLine();

            System.out.print("De 1 a 3, Escolha Sua Coluna: ");
            String colunaTemporaria = sc.nextLine();


            try {

                int linha = Integer.parseInt(linhaTemporaria);
                int coluna = Integer.parseInt(colunaTemporaria);
                if (tabuleiro[linha - 1][coluna - 1] == '-') {
                    tabuleiro[linha - 1][coluna - 1] = jogador;
                    return tabuleiro;
                }
                System.out.println("Sua Escolha Foi Considerada Inválida, pois já há Jogada Nessa Linha e Coluna");
                mostraTabuleiro(tabuleiro);

            } catch (IndexOutOfBoundsException e) {
                System.out.println("\nAlgo não Saiu Conforme o Esperado, Parece Que Você TTentou Informar um valor Fora do Intervalo");
                System.out.println("Intervalo: 1 a 3");
                System.out.println("Você Informou na linha: " + linhaTemporaria);
                System.out.println("Você Informou na coluna: " + colunaTemporaria);
                mostraTabuleiro(tabuleiro);
            } catch (NumberFormatException e) {
                System.out.println("\nAlgo não Saiu Conforme o Esperado, Parece Que Você Tentou Informar um valor Inválido");
                System.out.println("Intervalo: 1 a 3");
                System.out.println("Você Informou na linha: " + linhaTemporaria);
                System.out.println("Você Informou na coluna: " + colunaTemporaria);
                mostraTabuleiro(tabuleiro);
            }
        }
    }

    public static boolean verificaGanhador(char[][] tabuleiro) {

        // testa as linhas
        for (int i = 0; i < tabuleiro.length; i++) {
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2] && tabuleiro[i][0] != '-') {

                System.out.printf("%s Ganha o Jogo na Linha %d\n", tabuleiro[i][0], i + 1);
                return true;
            }
        }

        // testa as colunas
        for (int j = 0; j < tabuleiro.length; j++) {
            if (tabuleiro[0][j] == tabuleiro[1][j] && tabuleiro[1][j] == tabuleiro[2][j] && tabuleiro[0][j] != '-') {

                System.out.printf("%s Ganha o Jogo na Coluna %d\n", tabuleiro[0][j], j + 1);
                return true;
            }
        }

        //testa as diagonais
        if (tabuleiro[1][1] != '-' && (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2] ||
                tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0])) {

            System.out.printf("%s Ganha o Jogo na Diagonal\n", tabuleiro[1][1]);
            return true;
        }
        return false;
    }
}
