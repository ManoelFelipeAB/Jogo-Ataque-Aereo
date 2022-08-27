import java.io.Serializable;

public class Inimigo implements Serializable {
    private char estado;
    private int pontos;
    private String msgBatalha;
    private Coordenadas coord;

    public Inimigo(int x, int y) {
        this.estado = '.';
        this.pontos = 0;
        this.msgBatalha = "";
        this.coord = new Coordenadas(x, y);
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getMsgBatalha() {
        return msgBatalha;
    }

    public void setMsgBatalha(String msgBatalha) {
        this.msgBatalha = msgBatalha;
    }

    public Coordenadas getCoord() {
        return coord;
    }

    public void setCoord(Coordenadas coord) {
        this.coord = coord;
    }

    public boolean levarBomba() {
        this.msgBatalha = "SPLASH! Caiu na agua!"; // msg padrão
        return false; // não finaliza jogo
    }
}
