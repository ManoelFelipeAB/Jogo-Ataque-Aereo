import java.io.Serializable;

public class Coordenadas implements Serializable {
    private int x,y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Coordenadas(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
