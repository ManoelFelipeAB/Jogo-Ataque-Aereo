public class Submarino extends Inimigo {
    public Submarino(int x, int y) {
        super(x, y);
        this.setPontos(5);
    }

    @Override
    public boolean levarBomba() {
        if (getEstado() == '.') {
            setMsgBatalha("BOOMM! Submarino atingido!");
            setEstado('S');
        } else {
            setMsgBatalha("CRASH! Caiu nos destroços de submarino!");
        }
        return false;
    }
}
