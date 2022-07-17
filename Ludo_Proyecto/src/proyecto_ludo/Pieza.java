
package proyecto_ludo;


public class Pieza {

    private String color;
    private int piezaNúmero;
    private int xCoord;
    private int yCoord;
    private boolean sacado;
    private boolean terminado;

    Pieza(int piezaNumber, String color) {

        this.piezaNúmero = piezaNumber + 1;
        this.color = color;

    }

    String getColor() {
        return color;
    }

    int getPiezaNúmero() {
        return piezaNúmero;
    }

    int getX() {
        return xCoord;
    }

    int getY() {
        return yCoord;
    }

    void setX(int x) {
        xCoord = x;
    }

    void setY(int y) {
        yCoord = y;
    }

    void setTakenOut(boolean mode) {
        sacado = mode;
    }

    boolean seHaSacado() {
        return sacado;
    }

    void setTerminado(boolean mode) {
        terminado = mode;
    }

    boolean isTerminado() {
        return terminado;
    }

}
