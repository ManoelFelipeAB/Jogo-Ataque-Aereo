public class Navio extends Inimigo {
    public Navio(int x, int y) {
        super(x, y);
        this.setPontos(5);
    }

    @Override
    public boolean levarBomba() {
        if (getEstado() == '.') {
            setMsgBatalha("BOOMM! Navio atingido!");
            setEstado('N');
        } else {
            setMsgBatalha("CRASH! Caiu nos destroços de navio!");
        }
        return false;
    }
}
