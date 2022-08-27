import java.io.Serializable;
import java.util.Scanner;

public class Piloto implements Serializable {
    private String nome;
    private int disparos;
    private int qtdNavios;
    private int qtdSubmarinos;
    private int pontos;

    public Piloto(String nome) {
        this.nome = nome;
        this.disparos = 0;
        this.qtdNavios = 0;
        this.qtdSubmarinos = 0;
        this.pontos = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDisparos() {
        return disparos;
    }

    public void setDisparos(int disparos) {
        this.disparos = disparos;
    }

    public int getQtdNavios() {
        return qtdNavios;
    }

    public void setQtdNavios(int qtdNavios) {
        this.qtdNavios = qtdNavios;
    }

    public int getQtdSubmarinos() {
        return qtdSubmarinos;
    }

    public void setQtdSubmarinos(int qtdSubmarinos) {
        this.qtdSubmarinos = qtdSubmarinos;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

//    public boolean dispara(int x, int y, Scanner leitor){
//
//
//    }
}
