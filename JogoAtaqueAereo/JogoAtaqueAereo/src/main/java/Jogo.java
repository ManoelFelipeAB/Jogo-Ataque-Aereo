import java.io.*;
import java.util.Scanner;

public class Jogo implements Serializable {
    private String mensagem;
    private Mar mar;
    private Piloto piloto;

    public Jogo() {
        iniciaJogo("Início de Jogo! Boa sorte!");
    }

    public void iniciaJogo(String str) {
// Cria o mar com tamanho 10 x 10
// e com a quantidade de navios, submarinos e postos indicada
        this.mar = new Mar(7, 4, 5, 10);
        this.mensagem = str;

    }

    public void iniciaPiloto() {
// Inicializa atributos do Piloto
// e com a quantidade de navios, submarinos e postos indicada
        this.piloto.setDisparos(0);
        this.piloto.setQtdNavios(0);
        this.piloto.setQtdSubmarinos(0);
        this.piloto.setPontos(0);
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void criarPiloto(Scanner leitor) {
// Lê do teclado o nome do piloto e instancia piloto
// e com a quantidade de navios, submarinos e postos indicada
        System.out.println("Qual o nome do piloto?");
        String nome = leitor.nextLine();
        this.piloto = new Piloto(nome);
    }

    public static void clearScreen() {
// Limpa a tela da console
        if (System.getProperty("os.name").contains("Windows"))
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        else
            try {
                Runtime.getRuntime().exec("clear");
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private boolean verificaDisparo(int x, int y) {
        // Limpa a tela da console
        boolean fimDeJogo = false;
        boolean atigiuInimigo = false;
        Inimigo inimigo = new Inimigo(x, y);
        inimigo.setEstado('%');
        for (Inimigo i : this.mar.getInimigos())
            if (i.getCoord().getX() == x && i.getCoord().getY() == y) {
                inimigo = i;
                if (inimigo instanceof Posto) {
                    Posto post = (Posto) inimigo;
                    fimDeJogo = post.levarBomba();
                }
                if (inimigo instanceof Navio) {
                    Navio nav = (Navio) inimigo;

                    if (nav.getEstado() == '.') { // não foi atingido
                        this.piloto.setQtdNavios(this.piloto.getQtdNavios() + 1);
                        this.piloto.setPontos(this.piloto.getPontos() + nav.getPontos());
                    }
                    fimDeJogo = nav.levarBomba();
                }
                if (inimigo instanceof Submarino) {
                    Submarino sub = (Submarino) inimigo;
                    if (sub.getEstado() == '.') { // não foi atingido
                        this.piloto.setQtdSubmarinos(this.piloto.getQtdSubmarinos() + 1);
                        this.piloto.setPontos(this.piloto.getPontos() + sub.getPontos());
                    }
                    fimDeJogo = sub.levarBomba();
                }
                atigiuInimigo = true;
                break;
            }
// Se não disparou em Navio, Submarino ou Posto, marca a
// a posição no mar, para não atirar novamente no mesmo local.
        if (!atigiuInimigo) {
            this.mar.getInimigos().add(inimigo); // inimigo genérico
            fimDeJogo = inimigo.levarBomba();
        }
        this.mensagem = "(" + x + ", " + y + ") " + inimigo.getMsgBatalha();
        this.piloto.setDisparos(this.piloto.getDisparos() + 1);
        return fimDeJogo;
    }

    public void mostraJogo() {
// Limpa a tela e atualiza o desenho do mar e mensagens
        clearScreen();
        System.out.println("--------------------------------------------");
        System.out.println("Piloto: " + this.piloto.getNome() + "\n");
        System.out.println("Radar:");
        this.mar.desenharMar();
        System.out.println("Navios Atingidos: " + this.piloto.getQtdNavios());
        System.out.println("Submarinos Atingidos: " + this.piloto.getQtdSubmarinos());
        System.out.println("Disparos : " + this.piloto.getDisparos());
        System.out.println("Pontos : " + this.piloto.getPontos());
        System.out.println("Mensagem: " + this.mensagem);
    }

    public void jogar(Scanner leitor, String partida) {
// Executa o Jogo, novo ou continuação
        int x;
        int y;
        boolean fimDeJogo = false;
        boolean continuar = true;
        String resp;
        if (partida.equals("nova")) this.criarPiloto(leitor);

        while (continuar) {
            while (!fimDeJogo) {
                this.mostraJogo();
                System.out.println("Coordenada X para disparo: ");
                x = leitor.nextInt();
                System.out.println("Coordenada Y para disparo: ");
                y = leitor.nextInt();
                fimDeJogo = this.verificaDisparo(x, y);
            }

            this.mostraJogo();
            System.out.println("Deseja salvar partida (s/n)? ");
            resp = leitor.next();
            if (resp.equals("S") || resp.equals("s")) {
                salvaPartida(this); // Salva o Jogo em arquivo
            }
            System.out.println("Deseja jogar NOVO jogo(s/n)? ");
            resp = leitor.next();
            if (resp.equals("S") || resp.equals("s")) {
                iniciaJogo("NOVO jogo! Boa sorte!");
                iniciaPiloto();
                continuar = true;
                fimDeJogo = false;
            } else {
                continuar = false;
            }
        }
    }

    private void salvaPartida(Jogo jogoAtual) {
        String filepath = "./arquivoJogo";

        try {
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(jogoAtual);
            objectOut.close();
            System.out.println("O jogo foi salvo com sucesso.");

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Não foi possível salvar o jogo.");
        }
    }

    private static Jogo recuperaJogo() {
        String filepath = "./arquivoJogo";
        Jogo jogo;

        try {
            FileInputStream  fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            jogo = (Jogo) objectIn.readObject();
            objectIn.close();
            System.out.println("Jogo carregado com sucesso");
            return jogo;

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Não foi possível carregar o jogo.");
            return null;
        }

    }

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        int opt;
        Jogo meuJogo;
        Jogo.clearScreen();
        System.out.println(" Menu Inicial ");
        System.out.println("1) Comecar um jogo");
        System.out.println("2) Carregar um jogo");
        System.out.print("\nOpcao desejada: ");
        opt = leitor.nextInt();
        leitor.nextLine();
        if (opt == 1) {
            meuJogo = new Jogo();
            meuJogo.jogar(leitor, "nova");
        } else {
            meuJogo = Jogo.recuperaJogo();
            if (meuJogo != null)
                meuJogo.jogar(leitor, "mesma");
        }
        leitor.close();
        Jogo.clearScreen();
        System.out.println("Fim de jogo!");
    }
}
