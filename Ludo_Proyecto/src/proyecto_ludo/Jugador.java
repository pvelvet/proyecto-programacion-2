
package proyecto_ludo;


public class Jugador {

    private Pieza[] pieza;
     private int númeroRodado;
    private String color;
    private boolean haRodadoSeis;
    
    Jugador(String color) {

        this.color = color;

        pieza = new Pieza[4];

        for (int i = 0; i < 4; i++) {
            pieza[i] = new Pieza(i, color);
        }

    }

    void tirarElDado() {

        // nextInt()da un int de 0 a 5, sumando 1
        // para hacerlo en el rango de 1-6
        númeroRodado = new Dado().roll();

        if (númeroRodado == 6) {
            haRodadoSeis = true;
        } else {
            haRodadoSeis = false;
        }

    }

    Pieza getPieza(int piezaNumero) {
        return pieza[piezaNumero];
    }

    String getColor() {
        return color;
    }

    int getNúmeroRodado() {
        return númeroRodado;
    }

    boolean haRodadoSeis() {
        return haRodadoSeis;
    }

  
    
    @Override
    public String toString() {
        return "Jugador " + color;
    }

    boolean haGanado() {

        // si alguna pieza no está completa,
        // devolverá falso
        for (int i = 0; i < 4; i++) {
            if (!pieza[i].isTerminado()) {
                return false;
            }
        }

        // por lo demás cierto - ¡hurra!
        return true;

    }

}

