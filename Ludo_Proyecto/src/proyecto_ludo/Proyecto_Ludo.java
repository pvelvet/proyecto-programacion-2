package proyecto_ludo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

// Rodny Antepaz V-29.655.218
// Pedro Marcano V-29.864.683
// Jacobo Vicent V-28.570.595

public class Proyecto_Ludo {
    
    //Colores
    public static final String ANSI_PURPLE ="\u001B[35m";  
    public static final String ANSI_CYAN ="\u001B[36m";
    public static final String ANSI_RESET= "\u001B[0m";
    
    private static LudoTablero ludoTablero;
    private static Jugador jugadorActual;
    private static final List<Jugador> listaDeJugadores = new ArrayList<Jugador>();
    private static String[] listaDeColores = {"Verde", "Amarillo", "Rojo", "Azul"};
    static Scanner Entrada = new Scanner(System.in);
    	
    public static void main(String[] args) {
         String Opcion;

        do {
            System.out.println("");
            System.out.println("");
            System.out.println(ANSI_PURPLE+"\t*********************************************");
            System.out.println(ANSI_PURPLE+"\t*      --- Menú de Proyecto Ludo ---        *");
            System.out.println("\t*********************************************"+ANSI_RESET);
            System.out.println("");
            System.out.println("[1] -->> Continuar.");
            System.out.println("[2] -->> Leer instrucciones.");
            System.out.println("[3] -->> Salir.");
            System.out.println("");
            System.out.print("Ingrese una opción: ");
            Opcion = Entrada.nextLine();
            switch (Opcion) {
                case "1":
                    Continuar();
                    break;
                case "2":
                    Instrucciones();
                    break;
                    
                default:
                    System.out.println("");
                    System.out.println(ANSI_CYAN+"\t\t*****************************");
                    System.out.println(ANSI_CYAN+"\t\t*** -- OPCIÓN INVÁLIDA -- ***");
                    System.out.println("\t\t*****************************"+ANSI_RESET);
            }
        } while (!Opcion.equals("3"));

}
        /*
    		 * 
		 *  inicializa el juego preguntando por el número de jugadores
		 *  
         */
        public static void Instrucciones() {
            System.out.println("");
            System.out.println("");
            System.out.println(ANSI_PURPLE+"\t*********************************************");
            System.out.println(ANSI_PURPLE+"\t*            --- INSTRUCCIONES ---          *");
            System.out.println("\t*********************************************"+ANSI_RESET);
            System.out.println("");
            System.out.println("");
            System.out.println("1- Se juega con 1 o 2 dados de 6 caras y el objetivo es trasladar las 4 fichas desde la partida hasta la llegada.");
            System.out.println("2- El turno se da por la izquierda y las fichas se mueven de izquierda a derecha.");
            System.out.println("3- A cada jugador, en su turno, le corresponde lanzar el dado y mover sus fichas si le corresponde.");
            System.out.println("4- El 6 del dado sirve como salida.");
            System.out.println("Se utiliza para sacar fichas de la partida y se obtiene un turno extra.");
            System.out.println("5- Las fichas se mueven según el número de espacios indicados por el dado.");
            System.out.println("6- Un jugador puede capturar las fichas de un contrincante, si en su turno ocupa la casilla de este último.");
            System.out.println("7- Cuando una ficha se encuentra en corona o en cualquier casillero de color está segura, es decir ningún contrincante puede capturarla.");
            System.out.println("Sin embargo, debe llegar a la casa o meta con la cuenta exacta, de lo contrario tiene que moverse dentro de la línea de color según");
            System.out.println("lo indicado por el dado.");
            System.out.println("8- Puedes mover la ficha en la línea, sin importar que tengas otras fichas disponibles para avanzar.");
            
        }
        public static void Continuar() {
        System.out.println("");
            System.out.println("");
            System.out.println(ANSI_PURPLE+"\t*********************************************");
            System.out.println(ANSI_PURPLE+"\t*          --- Empieza el juego ---         *");
            System.out.println("\t*********************************************"+ANSI_RESET);
            System.out.println("");
            System.out.println("");
        System.out.println("¡Bienvenido!");
        System.out.print("Por favor, escribe el número de jugadores de (2) a (4):");
        boolean inputCorrect = false;
        Scanner scanner = null;

        while (!inputCorrect) {
            
            scanner = new Scanner(System.in);
            int número = 0;
            try {
                número = scanner.nextInt();
            } catch (Exception e) {
                //ignore
            }

            if (número > 1 && número < 5) {

                inputCorrect = true;

                for (int i = 0; i < número; i++) {
                    listaDeJugadores.add(new Jugador(listaDeColores[i]));
                }

            } else {

                System.out.println("Algo salió mal. Escribe el número de jugadores de (2) a (4).");

            }

        }

        for (int i = 0; i < listaDeJugadores.size(); i++) {
            System.out.println(listaDeJugadores.get(i) + " se ha unido al juego.");
        }

        
        System.out.println("Los jugadores tienen que tirar los dados para"
                + " determinar quién va primero. Para tirar un dado,"
                + " escribir \"r\".");
        boolean initComplete = false;
        int jugadorCounter = 0;

        while (!initComplete) {

            Jugador jugadorActual = listaDeJugadores.get(jugadorCounter);
            System.out.println(jugadorActual + " en su turno.");

            scanner = new Scanner(System.in);
            String input = "a";

            try {
                input = scanner.next();
            } catch (Exception e) {
                // ignorar
            }

            if (input.equals("r")) {

                jugadorActual.tirarElDado();
                System.out.println(jugadorActual + " ha lanzado " + jugadorActual.getNúmeroRodado());

            } else {

                System.out.println("Algo salió mal. Escribir \"r\".");
                continue;

            }

            if (++jugadorCounter == listaDeJugadores.size()) {
                initComplete = true;
            }

        }

        for (int i = 0; i < listaDeJugadores.size(); i++) {
            System.out.println(listaDeJugadores.get(i) + " ha lanzado " + listaDeJugadores.get(i).getNúmeroRodado());
        }

        List<Jugador> rodadoMasAlto = determinarElRodilloMasAlto(listaDeJugadores);

        /*
		 * 
		 * si hay varios grandes apostadores, haz un bucle
		 * para terminar con solo 1 rodillo más alto
		 * 
         */
        boolean soloUnoMásAlto = rodadoMasAlto.size() == 1;

        while (!soloUnoMásAlto) {

            String nombreDeJugadores = "";

            for (int i = 0; i < rodadoMasAlto.size(); i++) {
                nombreDeJugadores += rodadoMasAlto.get(i) + ", ";
            }

            System.out.println("Hay varios jugadores con puntaje alto. "
                    + nombreDeJugadores + "hay que rodar de nuevo. Recuerda "
                    + "tira un dado, escribir \"r\"");

            boolean rollsComplete = false;
            int rollerCounter = 0;

            while (!rollsComplete) {

                Jugador jugadorActual = rodadoMasAlto.get(rollerCounter);
                System.out.println(jugadorActual + " en su turno.");

                scanner = new Scanner(System.in);
                String input = "a";

                try {
                    input = scanner.next();
                } catch (Exception e) {
                    // ignore
                }

                if (input.equals("r")) {

                    jugadorActual.tirarElDado();
                    System.out.println(jugadorActual + " ha lanzdo " + jugadorActual.getNúmeroRodado());

                } else {

                    System.out.println("Algo salió mal. Escribir \"r\".");
                    continue;

                }

                if (++rollerCounter == rodadoMasAlto.size()) {
                    rollsComplete = true;
                }

            }

            for (int i = 0; i < rodadoMasAlto.size(); i++) {
                System.out.println(rodadoMasAlto.get(i) + " ha lanzado " + rodadoMasAlto.get(i).getNúmeroRodado());
            }

            rodadoMasAlto = determinarElRodilloMasAlto(rodadoMasAlto);

            soloUnoMásAlto = rodadoMasAlto.size() == 1;

        }

        jugadorActual = rodadoMasAlto.get(0);

        System.out.println(jugadorActual + " comienza el juego."
                + ""
                + "");

        ludoTablero = new LudoTablero();
        for (int i = 0; i < listaDeJugadores.size(); i++) {
            ludoTablero.inicializarPiezas(listaDeJugadores.get(i));
        }

        correrJuego();

    }

    /*
	 * 
	 * Controla la secuencia del juego
	 * 
     */
    private static void correrJuego() {

        Scanner scanner = null;
        boolean gameCompleted = false;

        game:
        while (!gameCompleted) {

            ludoTablero.imprimirTablero();

            System.out.println(jugadorActual + " en su turno. Lo único que puedes hacer es rodar -\"r\".");

            boolean rollComplete = false;

            while (!rollComplete) {

                scanner = new Scanner(System.in);
                String input = "a";

                try {
                    input = scanner.next();
                } catch (Exception e) {
                    // ignorar
                }

                if (input.equals("r")) {

                    jugadorActual.tirarElDado();
                    rollComplete = true;

                } else {

                    System.out.println("Algo salió mal. Escribir \"r\".");
                    continue;

                }

            }

            boolean movimientosPosibles = ludoTablero.movimientosPosibles(jugadorActual, jugadorActual.getNúmeroRodado());

            if (!movimientosPosibles) {

                System.out.println("No hay movimientos posibles. Hacia adelante...");
                establecerSiguienteJugador();
                continue;

            }

            System.out.println(jugadorActual + " ha lanzado " + jugadorActual.getNúmeroRodado()
                    + ". Commandos:\n"
                    + "\"t (número de la pieza)\"sin los paréntesis para sacar una pieza del círculo de su casilla, ejemplo: t 1. ;\n"
                    + //Escriba t 1 para mover la pieza 1 fuera del círculo de inicio, t 2 para la pieza dos y t 3 y así sucesivamente (t(barra espaciadora)(número de pieza))
                    "\"m (número de la pieza)\"sin los paréntesis para mover una pieza, ejemplo: m 1."); //Escriba m 1 para mover la pieza 1, m 2 para mover la pieza 2, m 3 y así sucesivamente (m(barra espaciadora)(número de pieza))

            boolean turnComplete = false;

            while (!turnComplete) {

                scanner = new Scanner(System.in);

                String command = null;
                boolean comandoExitoso = false;

                try {
                    command = scanner.next();
                } catch (Exception e) {
                    System.out.println("Comando inválido. Intentar otra vez.");
                    continue;
                }

                if (command.equals("t")) {

                    // si no sacó 6, no puede sacar una pieza
                    if (!jugadorActual.haRodadoSeis()) {
                        System.out.println("Movimiento inválido. Solo se pueden sacar piezas "
                                + "cuando ha salido un 6.");
                        continue;
                    }

                    int piezaNúmero = 0;

                    try {
                        piezaNúmero = scanner.nextInt() - 1;
                    } catch (Exception e) {
                        System.out.println("Número de pieza proporcionado no válido. Intentar otra vez.");
                        continue;
                    }

                    if (piezaNúmero < 0 || piezaNúmero > 3) {

                        System.out.println("¡Número de pieza incorrecto!");
                        continue;

                    }

                    Pieza pieza = jugadorActual.getPieza(piezaNúmero);

                    comandoExitoso = ludoTablero.tomarPiezaFuera(pieza);

                } else if (command.equals("m")) {

                    int piezaNúmero = 0;

                    try {
                        piezaNúmero = scanner.nextInt() - 1;
                    } catch (Exception e) {
                        System.out.println("Número de pieza proporcionado no válido. Intentar otra vez.");
                        continue;
                    }

                    if (piezaNúmero < 0 || piezaNúmero > 3) {

                        System.out.println("¡Número de pieza incorrecto!");
                        continue;

                    }

                    Pieza pieza = jugadorActual.getPieza(piezaNúmero);
                    int squareAmount = jugadorActual.getNúmeroRodado();

                   comandoExitoso = ludoTablero.moverPieza(pieza, squareAmount);

                } else {

                    System.out.println("Comando inválido. Intentar otra vez.");
                    continue;

                }

                if (comandoExitoso) {

                    if (jugadorActual.haGanado()) {

                        System.out.println( jugadorActual + " ¡HA GANADO EL JUEGO!");
                        System.out.println("FELICIDADES.");
                        break game;

                    }

                    if (jugadorActual.haRodadoSeis()) {
                        System.out.println(jugadorActual + " ha sacado un seis, lo que significa que "
                                + "obtienen otro turno. Tirar los dados.");
                        continue game;
                    }

                    establecerSiguienteJugador();
                    turnComplete = true;

                } else {
                    System.out.println("No se puede completar el movimiento. Prueba algo más.");
                }

            }

        }

        scanner.close();

    }

    /*
	 * 
	 * El tipo de retorno es una lista en caso de que haya varios
	 * Apostadores altos.
	 * 
     */
    private static List<Jugador> determinarElRodilloMasAlto(List<Jugador> jugadoresQueRodaron) {

        List<Jugador> apostadoresAltos = new ArrayList<Jugador>();
        Jugador rodadoMasAlto = jugadoresQueRodaron.get(0);
        boolean completado = false;
        int jugadorCounter = 1;

        while (!completado) {

            Jugador sigJugador = jugadoresQueRodaron.get(jugadorCounter++);

            int actualMásalto = rodadoMasAlto.getNúmeroRodado();
            int siguienteJugadoresRodar = sigJugador.getNúmeroRodado();

            if (actualMásalto> siguienteJugadoresRodar) {

                // no hacer nada, highRoller ya apunta
                // Al jugador más alto del balanceo
            } else if (actualMásalto< siguienteJugadoresRodar) {

                // asignar al siguiente jugador como el tirador más alto
                rodadoMasAlto = sigJugador;

                // borrar la lista de múltiples rodillos
                // porque nextPlayer ha rodado
                // Más que los dos jugadores anteriores
               apostadoresAltos.clear();

            } else if (actualMásalto == siguienteJugadoresRodar) {

                // ya podría contener si hay 3
                // Apostadores altos
                if (!apostadoresAltos.contains(rodadoMasAlto)) {
                    apostadoresAltos.add(rodadoMasAlto);
                }
                if (!apostadoresAltos.contains(sigJugador)) {
                    apostadoresAltos.add(sigJugador);
                }

            }

            if (jugadorCounter == jugadoresQueRodaron.size()) {
                completado = true;
            }

        }

        if (apostadoresAltos.size() == 0) {
            apostadoresAltos.add(rodadoMasAlto);
        }

        return apostadoresAltos;

    }

    /*
	 * 
	 * Establece el siguiente jugador como actual
	 * en forma de cola circular
	 * 
     */
    private static void establecerSiguienteJugador() {

        int nextIndex = listaDeJugadores.indexOf(jugadorActual) + 1;

        if (nextIndex == listaDeJugadores.size()) {
            nextIndex = 0;
        }

        jugadorActual = listaDeJugadores.get(nextIndex);

    }

}






