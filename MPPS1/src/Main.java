import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        char[][] tabuleiro = new char[3][3];

        do {
            int jogadas = 0;
            boolean ganhador = false;

            manualDeInstrucoa();
            preparaTabuleiro(tabuleiro);
            mostraTabuleiro(tabuleiro);

            while (!ganhador) {

                if (jogadas % 2 == 0) {
                    solicitaJogada(tabuleiro, 'X', sc);
                } else {
                    solicitaJogada(tabuleiro, 'O', sc);
                }

                mostraTabuleiro(tabuleiro);
                ganhador = verificaGanhador(tabuleiro);
                jogadas++;
            }

        } while (jogarNovamente(sc));

        despedida();
    }

    public static void manualDeInstrucoa() {
        String instrucoa = """
                
                        Bem-vindo ao Jogo da Velha!
                
                    Preparado para desafiar sua mente e a paciência do seu adversário?
                    Aqui estão as instruções para garantir que ninguém brigue no final!
                
                        Como jogar:
                    Na sua vez, você só precisa informar as **coordenadas** da posição onde quer marcar.
                    É fácil como contar até 3 (literalmente)!
                
                        Exemplo de Jogo:
                    Digamos que o tabuleiro está assim:
                
                      x  -  -
                      O  -  -
                      -  -  -
                
                    Agora é a vez do jogador 'X'. Ele deve informar **dois números**, um para a linha e outro para a coluna.
                
                    Por exemplo: se 'X' escolher **linha 2** e **coluna 2**, o tabuleiro vai ficar assim:
                
                      x  -  -
                      O  X  -
                      -  -  -
                
                    Viu? Simples! É só imaginar que está jogando batalha naval... mas sem navios.
                
                        Coordenadas do Tabuleiro:
                
                      11 | 12 | 13
                     -----+----+-----
                      21 | 22 | 23
                     -----+----+-----
                      31 | 32 | 33
                
                    Ou seja:
                    - O primeiro número é a linha (de cima para baixo).
                    - O segundo número é a coluna (da esquerda para a direita).
                
                    Boa sorte e que vença o melhor... ou o mais sortudo! :)
                    E lembre-se: ninguém gosta de um jogador que só tenta atrapalhar o outro! ;)
                """;
        System.out.println(instrucoa);
    }

    public static void preparaTabuleiro(char[][] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                tabuleiro[i][j] = '-';
            }
        }
//        return tabuleiro;
    }

    public static void mostraTabuleiro(char[][] tabuleiro) {
        System.out.println(" ");
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                System.out.print("  " + tabuleiro[i][j]);
            }
            System.out.println(" ");
        }
    }

    public static void solicitaJogada(char[][] tabuleiro, char jogador, Scanner sc) {
        while (true) {
            String informacaoJogada = """
                    
                        Jogador '%s' é a Sua Vez de Jogar.
                        Escolha Suas Coordenadas!
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
                    return;
                }
                System.out.println("\nSua Escolha Foi Considerada Inválida, pois já há Jogada Nessa Linha e Coluna");
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

                System.out.printf("\nO Jogador '%s' Ganha o Jogo na Linha %d\n", tabuleiro[i][0], i + 1);
                return true;
            }
        }

        // testa as colunas
        for (int j = 0; j < tabuleiro.length; j++) {
            if (tabuleiro[0][j] == tabuleiro[1][j] && tabuleiro[1][j] == tabuleiro[2][j] && tabuleiro[0][j] != '-') {

                System.out.printf("\nO Jogador '%s' Ganha o Jogo na Coluna %d\n", tabuleiro[0][j], j + 1);
                return true;
            }
        }

        //testa as diagonais
        if (tabuleiro[1][1] != '-' && (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2] ||
                tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0])) {

            System.out.printf("\nO Jogador '%s' Ganha o Jogo na Diagonal\n", tabuleiro[1][1]);
            return true;
        }

        //condição de empate
        boolean empatou = true;
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                if (tabuleiro[i][j] == '-') {
                    empatou = false;
                    break;
                }
            }
        }

        if (empatou) {
            System.out.println("\nO jogo terminou empatado!");
            despedida();
            System.exit(0);
        }
        return false;
    }

    public static boolean jogarNovamente(Scanner sc){

        String jogar = """
                    
                    Caso queira Jogar Novamente Digite 1
                
                    Caso Queira Sair Pressione Enter
                    
                """;
        System.out.println(jogar);

        String escolha = sc.nextLine();

        if (escolha.equals("1")) {
            return true;
        }
        return false;
    }

    public static void despedida() {
        String despedida = """
                
                    Obrigado por jogar o Incrível Jogo da Velha!
                
                    Esperamos que você tenha se divertido (e que ninguém tenha virado a mesa no final):O.
                
                    Se curtiu e quer ajudar a manter o jogo vivo (ou só quer ser generoso), aceitamos doações!
                    Chave Pix: aglemar.reis@gmail.com
                
                    Não prometemos poderes especiais, mas garantimos boas vibrações para sua próxima jogatina!
                
                    Bye, take care, and may the Tic-Tac-Toe force be with you!
                """;
        System.out.println(despedida);
    }
}
