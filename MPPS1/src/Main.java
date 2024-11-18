public class Main {


    public static void main(String[] args) {

        char[][] tabuleiro = new char[3][3];

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                tabuleiro[i][j] = '-';
            }
        }

        mostraTabuleiro(tabuleiro);
        verificaGanhador(tabuleiro);


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

    public static void verificaGanhador(char[][] tabuleiro) {

        // testa as linhas
        for (int i = 0; i < tabuleiro.length; i++) {
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2]) {

                System.out.printf("%s Ganha o Jogo na Linha %d\n", tabuleiro[0][0], i + 1);
                return;
            }
        }

        // testa as colunas
        for (int j = 0; j < tabuleiro.length; j++) {
            if (tabuleiro[0][j] == tabuleiro[1][j] && tabuleiro[1][j] == tabuleiro[2][j]) {

                System.out.printf("%s Ganha o Jogo na Coluna %d\n", tabuleiro[0][0], j + 1);
                return;
            }
        }

        //testa as diagonais
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2] ||
                tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0]) {

            System.out.printf("%s Ganha o Jogo na Diagonal\n", tabuleiro[0][0]);
        }
    }
}
