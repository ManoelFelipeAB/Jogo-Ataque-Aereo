public class Posto extends Inimigo{
    public Posto(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean levarBomba() {
        if (getEstado() == '.') {
            setMsgBatalha("**BOOMM! POSTO atingido! Você foi ELIMINADO!!!**");
            setEstado('P');
            return true;
        } else {
            setMsgBatalha( "CRASH! Caiu nos destroços de posto!");
        }
        return false;
    }
}
