
package proyecto_ludo;

import java.util.Random;


public class Dado {

    // nextInt() da un int de 0 a 5, sumando 1
    // para hacerlo en el rango de 1-6
    int roll() {
        return new Random().nextInt(6) + 1;
    }

}
