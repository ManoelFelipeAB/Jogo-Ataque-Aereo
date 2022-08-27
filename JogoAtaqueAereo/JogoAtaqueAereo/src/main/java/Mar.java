import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Mar implements Serializable {
    private int navios;
    private int submarinos;
    private int postos;
    private int tamanho;
    private ArrayList<Inimigo> inimigos;

    public Mar(int navios, int submarinos, int postos, int tamanho) {
        this.navios = navios;
        this.submarinos = submarinos;
        this.postos = postos;
        this.tamanho = tamanho;
        this.inimigos = new ArrayList<>();
        iniciarMar();
    }

    public ArrayList<Inimigo> getInimigos() {
        return inimigos;
    }

    public void setInimigos(ArrayList<Inimigo> inimigos) {
        this.inimigos = inimigos;
    }

    public Coordenadas acharLocalizacao() {
        int x = 0, y = 0;
        boolean ocupado = true;
        Random rand = new Random();

        while (ocupado) {
            x = rand.nextInt(this.tamanho);
            y = rand.nextInt(this.tamanho);
            ocupado = false;
            for (Inimigo i : this.inimigos)
                if ((i.getCoord().getX() == x) && (i.getCoord().getY() == y)) {
                    ocupado = true;
                    break;
                }
        }
        return new Coordenadas(x, y);
    }

    public void desenharMar() {
        char[][] mar = new char[tamanho][tamanho]; // array de chars
        System.out.println("+---+---+---+---+---+---+---+---+---+---+---+");
        System.out.println("+---+ 0 + 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 +");
        for (int i = 0; i < this.tamanho; i++) {
            System.out.println("+---+---+---+---+---+---+---+---+---+---+---+");
            for (int j = 0; j < this.tamanho; j++) {
                if (j == 0) {
                    System.out.print("| " + i + " | ");
                }
                mar[i][j] = '.';
                for (Inimigo e : this.inimigos) {
                    if ((e.getCoord().getX() == j) && (e.getCoord().getY() == i)) {
                        mar[i][j] = e.getEstado();
                    }
                }
                System.out.print(mar[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println("+---+---+---+---+---+---+---+---+---+---+---+");
    }

    public void iniciarMar() {
        // Posiciona inimigos no mar
        for (int i = 0; i < this.navios; i++) {
            Coordenadas coord = acharLocalizacao(); //recebe posição livre no mar
            inimigos.add(new Navio(coord.getX(), coord.getY()));
        }
        for (int i = 0; i < this.submarinos; i++) {
            Coordenadas coord = acharLocalizacao();
            inimigos.add(new Submarino(coord.getX(), coord.getY()));
        }
        for (int i = 0; i < this.postos; i++) {
            Coordenadas coord = acharLocalizacao();
            inimigos.add(new Posto(coord.getX(), coord.getY()));
        }

    }
}
