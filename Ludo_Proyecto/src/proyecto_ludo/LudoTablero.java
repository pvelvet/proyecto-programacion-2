
package proyecto_ludo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LudoTablero {

    // matrices de diseño de Arrays
    String[][] square = new String[15][15];
    String[][] border = new String[16][15];

    // registro de piezas en un bloque
    //los primeros dos enteros son coordinados, el tercero es la pieza
    // max 4 piezas posibles en un bloque
    Pieza[][][] piezaRecord = new Pieza[15][15][4];

    // coordenadas x e y de las posiciones de inicio (inicio)
    int[][] casillaVerde = {{2, 2}, {2, 3}, {3, 2}, {3, 3}};
    int[][] casillaAmarilla = {{2, 11}, {2, 12}, {3, 11}, {3, 12}};
    int[][] casillaRoja = {{11, 2}, {11, 3}, {12, 2}, {12, 3}};
    int[][] casillaAzul = {{11, 11}, {11, 12}, {12, 11}, {12, 12}};

    Map<String, int[][]> mapaDePosición;

    // coordenadas x e y de la ruta de cada color
    int[][] rutaVerde = {{6, 1}, {6, 2}, {6, 3}, {6, 4}, {6, 5},
    {5, 6}, {4, 6}, {3, 6}, {2, 6}, {1, 6}, {0, 6},
    {0, 7}, {0, 8}, {2, 8}, {3, 8}, {4, 8}, {5, 8},
    {6, 9}, {6, 10}, {6, 11}, {6, 12}, {6, 13}, {6, 14},
    {7, 14}, {8, 14}, {8, 12}, {8, 11}, {8, 10}, {8, 9},
    {9, 8}, {10, 8}, {11, 8}, {12, 8}, {13, 8}, {14, 8},
    {14, 7}, {14, 6}, {12, 6}, {11, 6}, {10, 6}, {9, 6},
    {8, 5}, {8, 4}, {8, 3}, {8, 2}, {8, 1}, {8, 0},
    {7, 0}, {7, 1}, {7, 2}, {7, 3}, {7, 4}, {7, 5}, {7, 6}};

    int[][] rutaAmarilla = {{1, 8}, {2, 8}, {3, 8}, {4, 8}, {5, 8},
    {6, 9}, {6, 10}, {6, 11}, {6, 12}, {6, 13}, {6, 14},
    {7, 14}, {8, 14}, {8, 12}, {8, 11}, {8, 10}, {8, 9},
    {9, 8}, {10, 8}, {11, 8}, {12, 8}, {13, 8}, {14, 8},
    {14, 7}, {14, 6}, {12, 6}, {11, 6}, {10, 6}, {9, 6},
    {8, 5}, {8, 4}, {8, 3}, {8, 2}, {8, 1}, {8, 0},
    {7, 0}, {6, 0}, {6, 2}, {6, 3}, {6, 4}, {6, 5},
    {5, 6}, {4, 6}, {3, 6}, {2, 6}, {1, 6}, {0, 6},
    {0, 7}, {1, 7}, {2, 7}, {3, 7}, {4, 7}, {5, 7}, {6, 7}};

    int[][] rutaRoja = {{13, 6}, {12, 6}, {11, 6}, {10, 6}, {9, 6},
    {8, 5}, {8, 4}, {8, 3}, {8, 2}, {8, 1}, {8, 0},
    {7, 0}, {6, 0}, {6, 2}, {6, 3}, {6, 4}, {6, 5},
    {5, 6}, {4, 6}, {3, 6}, {2, 6}, {1, 6}, {0, 6},
    {0, 7}, {0, 8}, {2, 8}, {3, 8}, {4, 8}, {5, 8},
    {6, 9}, {6, 10}, {6, 11}, {6, 12}, {6, 13}, {6, 14},
    {7, 14}, {8, 14}, {8, 12}, {8, 11}, {8, 10}, {8, 9},
    {9, 8}, {10, 8}, {11, 8}, {12, 8}, {13, 8}, {14, 8},
    {14, 7}, {13, 7}, {12, 7}, {11, 7}, {10, 7}, {9, 7}, {8, 7}};

    int[][] rutaAzul = {{8, 13}, {8, 12}, {8, 11}, {8, 10}, {8, 9},
    {9, 8}, {10, 8}, {11, 8}, {12, 8}, {13, 8}, {14, 8},
    {14, 7}, {14, 6}, {12, 6}, {11, 6}, {10, 6}, {9, 6},
    {8, 5}, {8, 4}, {8, 3}, {8, 2}, {8, 1}, {8, 0},
    {7, 0}, {6, 0}, {6, 2}, {6, 3}, {6, 4}, {6, 5},
    {5, 6}, {4, 6}, {3, 6}, {2, 6}, {1, 6}, {0, 6},
    {0, 7}, {0, 8}, {2, 8}, {3, 8}, {4, 8}, {5, 8},
    {6, 9}, {6, 10}, {6, 11}, {6, 12}, {6, 13}, {6, 14},
    {7, 14}, {7, 13}, {7, 12}, {7, 11}, {7, 10}, {7, 9}, {7, 8}};

    Map<String, int[][]> mapaRuta;

    LudoTablero() {

        /*
		 * 
		 * Inicializar mapa de posición
		 * 
         */
        mapaDePosición = new HashMap<String, int[][]>();
        mapaDePosición.put("Verde", casillaVerde);
        mapaDePosición.put("Amarillo", casillaAmarilla);
        mapaDePosición.put("Rojo", casillaRoja);
        mapaDePosición.put("Azul", casillaAzul);

        /*
		 * 
		 *Inicializar mapa de ruta
		 * 
         */
        mapaRuta = new HashMap<String, int[][]>();
        mapaRuta.put("Verde", rutaVerde);
        mapaRuta.put("Amarillo", rutaAmarilla);
        mapaRuta.put("Rojo", rutaRoja);
        mapaRuta.put("Azul", rutaAzul);

        /*
		 * 
		 * Inicializar registro de pieza
		 * 
         */
        for (int i = 0; i < piezaRecord.length; i++) {

            piezaRecord[i] = new Pieza[15][4];

            for (int j = 0; j < piezaRecord[i].length; j++) {

                piezaRecord[i][j] = new Pieza[4];

                for (int k = 0; k < piezaRecord[i][j].length; k++) {
                    piezaRecord[i][j][k] = null;
                }

            }

        }

        System.out.println(piezaRecord[3][3]);
        System.out.println(piezaRecord[3][3][0]);

        /*
		 * 
		 * Inicializar el diseño del tablero
		 * 
         */
        square[0][0] = "|Verde";
        square[0][1] = "      ";
        square[0][2] = "      ";
        square[0][3] = "      ";
        square[0][4] = "      ";
        square[0][5] = "     |";
        square[0][6] = "|    |";
        square[0][7] = "|    |";
        square[0][8] = "|    |";
        square[0][9] = "|     ";
        square[0][10] = "      ";
        square[0][11] = "      ";
        square[0][12] = "      ";
        square[0][13] = "     ";
        square[0][14] = "Amarillo|";
        square[1][0] = "|    |";
        square[1][1] = "|     ";
        square[1][2] = "      ";
        square[1][3] = "      ";
        square[1][4] = "     |";
        square[1][5] = "|    |";
        square[1][6] = "|    |";
        square[1][7] = "|    |";
        square[1][8] = "|    |";
        square[1][9] = "|    |";
        square[1][10] = "|     ";
        square[1][11] = "      ";
        square[1][12] = "      ";
        square[1][13] = "     |";
        square[1][14] = "|    |";
        square[2][0] = "|    |";
        square[2][1] = "|    |";
        square[2][2] = "|    |";
        square[2][3] = "|    |";
        square[2][4] = "|    |";
        square[2][5] = "|    |";
        square[2][6] = "|    |";
        square[2][7] = "|    |";
        square[2][8] = "|    |";
        square[2][9] = "|    |";
        square[2][10] = "|    |";
        square[2][11] = "|    |";
        square[2][12] = "|    |";
        square[2][13] = "|    |";
        square[2][14] = "|    |";
        square[3][0] = "|    |";
        square[3][1] = "|    |";
        square[3][2] = "|    |";
        square[3][3] = "|    |";
        square[3][4] = "|    |";
        square[3][5] = "|    |";
        square[3][6] = "|    |";
        square[3][7] = "|    |";
        square[3][8] = "|    |";
        square[3][9] = "|    |";
        square[3][10] = "|    |";
        square[3][11] = "|    |";
        square[3][12] = "|    |";
        square[3][13] = "|    |";
        square[3][14] = "|    |";
        square[4][0] = "|    |";
        square[4][1] = "|     ";
        square[4][2] = "      ";
        square[4][3] = "      ";
        square[4][4] = "     |";
        square[4][5] = "|    |";
        square[4][6] = "|    |";
        square[4][7] = "|    |";
        square[4][8] = "|    |";
        square[4][9] = "|    |";
        square[4][10] = "|     ";
        square[4][11] = "      ";
        square[4][12] = "      ";
        square[4][13] = "     |";
        square[4][14] = "|    |";
        square[5][0] = "|     ";
        square[5][1] = "      ";
        square[5][2] = "      ";
        square[5][3] = "      ";
        square[5][4] = "      ";
        square[5][5] = "     |";
        square[5][6] = "|    |";
        square[5][7] = "|    |";
        square[5][8] = "|    |";
        square[5][9] = "|     ";
        square[5][10] = "      ";
        square[5][11] = "      ";
        square[5][12] = "      ";
        square[5][13] = "      ";
        square[5][14] = "     |";
        square[6][0] = "|    |";
        square[6][1] = "|    |";
        square[6][2] = "|    |";
        square[6][3] = "|    |";
        square[6][4] = "|    |";
        square[6][5] = "|    |";
        square[6][6] = "|     ";
        square[6][7] = "      ";
        square[6][8] = "     |";
        square[6][9] = "|    |";
        square[6][10] = "|    |";
        square[6][11] = "|    |";
        square[6][12] = "|    |";
        square[6][13] = "|    |";
        square[6][14] = "|    |";
        square[7][0] = "|    |";
        square[7][1] = "|    |";
        square[7][2] = "|    |";
        square[7][3] = "|    |";
        square[7][4] = "|    |";
        square[7][5] = "|    |";
        square[7][6] = "|     ";
        square[7][7] = "      ";
        square[7][8] = "     |";
        square[7][9] = "|    |";
        square[7][10] = "|    |";
        square[7][11] = "|    |";
        square[7][12] = "|    |";
        square[7][13] = "|    |";
        square[7][14] = "|    |";
        square[8][0] = "|    |";
        square[8][1] = "|    |";
        square[8][2] = "|    |";
        square[8][3] = "|    |";
        square[8][4] = "|    |";
        square[8][5] = "|    |";
        square[8][6] = "|     ";
        square[8][7] = "      ";
        square[8][8] = "     |";
        square[8][9] = "|    |";
        square[8][10] = "|    |";
        square[8][11] = "|    |";
        square[8][12] = "|    |";
        square[8][13] = "|    |";
        square[8][14] = "|    |";
        square[9][0] = "|     ";
        square[9][1] = "      ";
        square[9][2] = "      ";
        square[9][3] = "      ";
        square[9][4] = "      ";
        square[9][5] = "     |";
        square[9][6] = "|    |";
        square[9][7] = "|    |";
        square[9][8] = "|    |";
        square[9][9] = "|     ";
        square[9][10] = "      ";
        square[9][11] = "      ";
        square[9][12] = "      ";
        square[9][13] = "      ";
        square[9][14] = "     |";
        square[10][0] = "|    |";
        square[10][1] = "|     ";
        square[10][2] = "      ";
        square[10][3] = "      ";
        square[10][4] = "     |";
        square[10][5] = "|    |";
        square[10][6] = "|    |";
        square[10][7] = "|    |";
        square[10][8] = "|    |";
        square[10][9] = "|    |";
        square[10][10] = "|     ";
        square[10][11] = "      ";
        square[10][12] = "      ";
        square[10][13] = "     |";
        square[10][14] = "|    |";
        square[11][0] = "|    |";
        square[11][1] = "|    |";
        square[11][2] = "|    |";
        square[11][3] = "|    |";
        square[11][4] = "|    |";
        square[11][5] = "|    |";
        square[11][6] = "|    |";
        square[11][7] = "|    |";
        square[11][8] = "|    |";
        square[11][9] = "|    |";
        square[11][10] = "|    |";
        square[11][11] = "|    |";
        square[11][12] = "|    |";
        square[11][13] = "|    |";
        square[11][14] = "|    |";
        square[12][0] = "|    |";
        square[12][1] = "|    |";
        square[12][2] = "|    |";
        square[12][3] = "|    |";
        square[12][4] = "|    |";
        square[12][5] = "|    |";
        square[12][6] = "|    |";
        square[12][7] = "|    |";
        square[12][8] = "|    |";
        square[12][9] = "|    |";
        square[12][10] = "|    |";
        square[12][11] = "|    |";
        square[12][12] = "|    |";
        square[12][13] = "|    |";
        square[12][14] = "|    |";
        square[13][0] = "|    |";
        square[13][1] = "|     ";
        square[13][2] = "      ";
        square[13][3] = "      ";
        square[13][4] = "     |";
        square[13][5] = "|    |";
        square[13][6] = "|    |";
        square[13][7] = "|    |";
        square[13][8] = "|    |";
        square[13][9] = "|    |";
        square[13][10] = "|     ";
        square[13][11] = "      ";
        square[13][12] = "      ";
        square[13][13] = "     |";
        square[13][14] = "|    |";
        square[14][0] = "|Rojo  ";
        square[14][1] = "      ";
        square[14][2] = "      ";
        square[14][3] = "      ";
        square[14][4] = "      ";
        square[14][5] = "     |";
        square[14][6] = "|    |";
        square[14][7] = "|    |";
        square[14][8] = "|    |";
        square[14][9] = "|     ";
        square[14][10] = "      ";
        square[14][11] = "      ";
        square[14][12] = "      ";
        square[14][13] = "      ";
        square[14][14] = " Azul|";

        border[0][0] = "------";
        border[0][1] = "------";
        border[0][2] = "------";
        border[0][3] = "------";
        border[0][4] = "------";
        border[0][5] = "------";
        border[0][6] = "------";
        border[0][7] = "------";
        border[0][8] = "------";
        border[0][9] = "------";
        border[0][10] = "------";
        border[0][11] = "------";
        border[0][12] = "------";
        border[0][13] = "------";
        border[0][14] = "------";
        border[1][0] = "      ";
        border[1][1] = "------";
        border[1][2] = "------";
        border[1][3] = "------";
        border[1][4] = "------";
        border[1][5] = "      ";
        border[1][6] = "------";
        border[1][7] = "------";
        border[1][8] = "------";
        border[1][9] = "      ";
        border[1][10] = "------";
        border[1][11] = "------";
        border[1][12] = "------";
        border[1][13] = "------";
        border[1][14] = "      ";
        border[2][0] = "      ";
        border[2][1] = "      ";
        border[2][2] = "------";
        border[2][3] = "------";
        border[2][4] = "      ";
        border[2][5] = "      ";
        border[2][6] = "------";
        border[2][7] = "------";
        border[2][8] = "------";
        border[2][9] = "      ";
        border[2][10] = "      ";
        border[2][11] = "------";
        border[2][12] = "------";
        border[2][13] = "      ";
        border[2][14] = "      ";
        border[3][0] = "      ";
        border[3][1] = "      ";
        border[3][2] = "------";
        border[3][3] = "------";
        border[3][4] = "      ";
        border[3][5] = "      ";
        border[3][6] = "------";
        border[3][7] = "------";
        border[3][8] = "------";
        border[3][9] = "      ";
        border[3][10] = "      ";
        border[3][11] = "------";
        border[3][12] = "------";
        border[3][13] = "      ";
        border[3][14] = "      ";
        border[4][0] = "      ";
        border[4][1] = "      ";
        border[4][2] = "------";
        border[4][3] = "------";
        border[4][4] = "      ";
        border[4][5] = "      ";
        border[4][6] = "------";
        border[4][7] = "------";
        border[4][8] = "------";
        border[4][9] = "      ";
        border[4][10] = "      ";
        border[4][11] = "------";
        border[4][12] = "------";
        border[4][13] = "      ";
        border[4][14] = "      ";
        border[5][0] = "      ";
        border[5][1] = "------";
        border[5][2] = "------";
        border[5][3] = "------";
        border[5][4] = "------";
        border[5][5] = "      ";
        border[5][6] = "------";
        border[5][7] = "------";
        border[5][8] = "------";
        border[5][9] = "      ";
        border[5][10] = "------";
        border[5][11] = "------";
        border[5][12] = "------";
        border[5][13] = "------";
        border[5][14] = "      ";
        border[6][0] = "------";
        border[6][1] = "------";
        border[6][2] = "------";
        border[6][3] = "------";
        border[6][4] = "------";
        border[6][5] = "------";
        border[6][6] = "------";
        border[6][7] = "------";
        border[6][8] = "------";
        border[6][9] = "------";
        border[6][10] = "------";
        border[6][11] = "------";
        border[6][12] = "------";
        border[6][13] = "------";
        border[6][14] = "------";
        border[7][0] = "------";
        border[7][1] = "------";
        border[7][2] = "------";
        border[7][3] = "------";
        border[7][4] = "------";
        border[7][5] = "------";
        border[7][6] = "      ";
        border[7][7] = "      ";
        border[7][8] = "      ";
        border[7][9] = "------";
        border[7][10] = "------";
        border[7][11] = "------";
        border[7][12] = "------";
        border[7][13] = "------";
        border[7][14] = "------";
        border[8][0] = "------";
        border[8][1] = "------";
        border[8][2] = "------";
        border[8][3] = "------";
        border[8][4] = "------";
        border[8][5] = "------";
        border[8][6] = "      ";
        border[8][7] = "      ";
        border[8][8] = "      ";
        border[8][9] = "------";
        border[8][10] = "------";
        border[8][11] = "------";
        border[8][12] = "------";
        border[8][13] = "------";
        border[8][14] = "------";
        border[9][0] = "------";
        border[9][1] = "------";
        border[9][2] = "------";
        border[9][3] = "------";
        border[9][4] = "------";
        border[9][5] = "------";
        border[9][6] = "------";
        border[9][7] = "------";
        border[9][8] = "------";
        border[9][9] = "------";
        border[9][10] = "------";
        border[9][11] = "------";
        border[9][12] = "------";
        border[9][13] = "------";
        border[9][14] = "------";
        border[10][0] = "      ";
        border[10][1] = "------";
        border[10][2] = "------";
        border[10][3] = "------";
        border[10][4] = "------";
        border[10][5] = "      ";
        border[10][6] = "------";
        border[10][7] = "------";
        border[10][8] = "------";
        border[10][9] = "      ";
        border[10][10] = "------";
        border[10][11] = "------";
        border[10][12] = "------";
        border[10][13] = "------";
        border[10][14] = "      ";
        border[11][0] = "      ";
        border[11][1] = "      ";
        border[11][2] = "------";
        border[11][3] = "------";
        border[11][4] = "      ";
        border[11][5] = "      ";
        border[11][6] = "------";
        border[11][7] = "------";
        border[11][8] = "------";
        border[11][9] = "      ";
        border[11][10] = "      ";
        border[11][11] = "------";
        border[11][12] = "------";
        border[11][13] = "      ";
        border[11][14] = "      ";
        border[12][0] = "      ";
        border[12][1] = "      ";
        border[12][2] = "------";
        border[12][3] = "------";
        border[12][4] = "      ";
        border[12][5] = "      ";
        border[12][6] = "------";
        border[12][7] = "------";
        border[12][8] = "------";
        border[12][9] = "      ";
        border[12][10] = "      ";
        border[12][11] = "------";
        border[12][12] = "------";
        border[12][13] = "      ";
        border[12][14] = "      ";
        border[13][0] = "      ";
        border[13][1] = "      ";
        border[13][2] = "------";
        border[13][3] = "------";
        border[13][4] = "      ";
        border[13][5] = "      ";
        border[13][6] = "------";
        border[13][7] = "------";
        border[13][8] = "------";
        border[13][9] = "      ";
        border[13][10] = "      ";
        border[13][11] = "------";
        border[13][12] = "------";
        border[13][13] = "      ";
        border[13][14] = "      ";
        border[14][0] = "      ";
        border[14][1] = "------";
        border[14][2] = "------";
        border[14][3] = "------";
        border[14][4] = "------";
        border[14][5] = "      ";
        border[14][6] = "------";
        border[14][7] = "------";
        border[14][8] = "------";
        border[14][9] = "      ";
        border[14][10] = "------";
        border[14][11] = "------";
        border[14][12] = "------";
        border[14][13] = "------";
        border[14][14] = "      ";
        border[15][0] = "------";
        border[15][1] = "------";
        border[15][2] = "------";
        border[15][3] = "------";
        border[15][4] = "------";
        border[15][5] = "------";
        border[15][6] = "------";
        border[15][7] = "------";
        border[15][8] = "------";
        border[15][9] = "------";
        border[15][10] = "------";
        border[15][11] = "------";
        border[15][12] = "------";
        border[15][13] = "------";
        border[15][14] = "------";

    }

    void imprimirTablero() {

        for (int i = 0; i < 16; i++) {

            for (int j = 0; j < 15; j++) {
                System.out.print(border[i][j]);
            }

            System.out.println();

            if (i == 15) {
                break;
            }

            for (int j = 0; j < 15; j++) {
                System.out.print(bloqueDeProcesamiento(i, j));
            }

            System.out.println();

        }

    }

    /*
	 * 
	 * Método para renderizar el bloque con
         * su contenido actual
	 * 
     */
    private String bloqueDeProcesamiento(int yCoord, int xCoord) {

        String bloquePorDefecto= square[yCoord][xCoord];
        Pieza[] piezaArray = piezaRecord[yCoord][xCoord];
        String contents = "";

        for (int i = 0; i < piezaArray.length; i++) {

            Pieza pieza = piezaArray[i];
            if (pieza == null) {
                break;
            }

            if (contents.length() == 0) {

                String color = pieza.getColor();
                contents += color.substring(0, 1) + pieza.getPiezaNúmero();

            } // si ya contiene caracteres,
            // la letra del color ya esta en
            else {
                contents += pieza.getPiezaNúmero();
            }

        }

        String renderedBlock = bloquePorDefecto.substring(0, 1) + contents + bloquePorDefecto.substring(contents.length() + 1);

        return renderedBlock;

    }

    /*
	 * 
	 * Llamado al comienzo de un juego
	 * 
     */
    void inicializarPiezas(Jugador jugador) {

        String color = jugador.getColor();

        int[][] coords = mapaDePosición.get(color);

        for (int i = 0; i < 4; i++) {

            int yCoord = coords[i][0];
            int xCoord = coords[i][1];

            seleccionarPiezas(jugador.getPieza(i), yCoord, xCoord);

        }

    }

    /*
	 * 
	 * Saca una pieza del círculo de origen.
         * Devuelve verdadero si tiene éxito
	 * 
     */
    boolean tomarPiezaFuera(Pieza pieza) {

        if (pieza.seHaSacado()) {
            return false;
        }

        boolean movidoConÉxito = moverPieza(pieza, 6);

        if (movidoConÉxito) {

            pieza.setTakenOut(true);
            return true;

        }

        return false;

    }

    /*
	 * 
	 * Mueve una pieza si es posible.
         * Devuelve verdadero si tiene éxito
	 * 
     */
    boolean moverPieza(Pieza pieza, int howManySquares) {

        if (!pieza.seHaSacado() && howManySquares != 6) {
            return false;
        }

        String color = pieza.getColor();
        int[][] path = mapaRuta.get(color);
        int currentY = pieza.getY();
        int currentX = pieza.getX();
        int bloqueActual = 0;

        for (int i = 0; i < path.length; i++) {

            // si la pieza todavía está en casa, currentBlock
            // permanecerá 0
            if (path[i][0] == currentY && path[i][1] == currentX) {

                bloqueActual = i;
                break;

            }

        }

        int endPosition = bloqueActual + howManySquares;
        if (endPosition >= path.length) {
            return false; // no puede terminar sin número exacto
        }
        for (int i = bloqueActual+ 1; i <= endPosition; i++) {
            if (bloqueado(pieza, path[i][0], path[i][1])) {
                return false; //// Bloqueado por el enemigo
            }
        }

        if (contieneUnaPiezaEnemiga(pieza, path[endPosition][0], path[endPosition][1])) {
            consumirEnemigo(path[endPosition][0], path[endPosition][1]);
        }

        if (!pieza.seHaSacado()) {

            seleccionarPiezas(pieza, path[endPosition - 1][0], path[endPosition - 1][1]);
            pieza.setTakenOut(true);

        } else {
            seleccionarPiezas(pieza, path[endPosition][0], path[endPosition][1]);
        }

        if (endPosition + 1 == path.length) {
            pieza.setTerminado(true);
        }

        return true;

    }

    /*
	 * 
	 * Comprueba si hay posibles movimientos.
	 * 
     */
    boolean movimientosPosibles(Jugador jugador, int númeroRodado) {

        if (númeroRodado == 6) {

            for (int i = 0; i < 4; i++) {

                Pieza pieza = jugador.getPieza(i);
                if (puedeSacarLaPieza(pieza)) {
                    return true;
                }

            }

        }

        for (int i = 0; i < 4; i++) {

            Pieza pieza = jugador.getPieza(i);
            if (puedeMoverLaPieza(pieza, númeroRodado)) {
                return true;
            }

        }

        return false;

    }

    private boolean puedeSacarLaPieza(Pieza pieza) {

        if (pieza.seHaSacado()) {
            return false;
        }

        boolean puedeMover = puedeMoverLaPieza(pieza, 6);

        if (puedeMover) {
            return true;
        }

        return false;

    }

    private boolean puedeMoverLaPieza(Pieza pieza, int howManySquares) {

        if (!pieza.seHaSacado() && howManySquares != 6) {
            return false;
        }

        String color = pieza.getColor();
        int[][] path = mapaRuta.get(color);
        int currentY = pieza.getY();
        int currentX = pieza.getX();
        int bloqueActual = 0;

        for (int i = 0; i < path.length; i++) {

            // si la pieza todavía está en casa, currentBlock
            // permanecerá 0
            if (path[i][0] == currentY && path[i][1] == currentX) {

                bloqueActual = i;
                break;

            }

        }

        int posiciónFinal = bloqueActual + howManySquares;
        if (posiciónFinal >= path.length) {
            return false; // no puede terminar sin número exacto
        }
        for (int i = bloqueActual + 1; i <= posiciónFinal; i++) {
            if (bloqueado(pieza, path[i][0], path[i][1])) {
                return false; //bloqueado por el enemigo
            }
        }
        return true;

    }

    /*
	 * 
	 * Consume una pieza enemiga
         * enviándolo de vuelta a su casilla
	 * 
     */
    private void consumirEnemigo(int yCoord, int xCoord) {

        // si llego hasta aqui solo hay una pieza
        // y pertenece a un enemigo
        Pieza pieza = piezaRecord[yCoord][xCoord][0];
        String color = pieza.getColor();
        int[][] casillaCoords = mapaDePosición.get(color);

        for (int i = 0; i < 4; i++) {

            int homeY = casillaCoords[i][0];
            int homeX = casillaCoords[i][1];

            if (piezaRecord[homeY][homeX][0] == null) {

                pieza.setTakenOut(false);
                seleccionarPiezas(pieza, homeY, homeX);
                break;

            }

        }

    }

    /*
	 * 
	 * Métodos para saber si un bloque
         * contiene una pieza enemiga (solo una)
         * o está bloqueado (2 o más piezas)
	 * 
     */
    boolean contieneUnaPiezaEnemiga(Pieza pieza, int yCoord, int xCoord) {

        List<Pieza> piezaList = new ArrayList<Pieza>();

        for (int i = 0; i < 4; i++) {

            Pieza actualPieza = piezaRecord[yCoord][xCoord][i];

            // si contiene una pieza amiga
            if (actualPieza != null && actualPieza.getColor().equals(pieza.getColor())) {
                return false;
            } else if (actualPieza != null) {
                piezaList.add(actualPieza);
            } else {
                break;
            }

        }

        if (piezaList.size() == 1) {
            return true;
        } else {
            return false;
        }

    }

    boolean bloqueado(Pieza pieza, int yCoord, int xCoord) {

        List<Pieza> piezaList = new ArrayList<Pieza>();

        for (int i = 0; i < 4; i++) {

            Pieza actualPieza = piezaRecord[yCoord][xCoord][i];

            // si contiene una pieza amiga
            if (actualPieza != null && actualPieza.getColor().equals(pieza.getColor())) {
                return false;
            } else if (actualPieza != null) {
                piezaList.add(actualPieza);
            } else {
                break;
            }

        }

        if (piezaList.size() > 1) {
            return true;
        } else {
            return false;
        }

    }

    /*
	 * 
	 * Establece las coordenadas de una pieza,
         * actualizando el pieceRecord en consecuencia
	 * 
     */
    void seleccionarPiezas(Pieza pieza, int yCoord, int xCoord) {

        boolean notInitialized = pieza.getX() == 0 && pieza.getY() == 0;

        if (notInitialized) {

            piezaRecord[yCoord][xCoord][0] = pieza;
            pieza.setY(yCoord);
            pieza.setX(xCoord);

        } else {

            //ordenar el bloque en el que se encontraba actualmente la pieza
            int currentY = pieza.getY();
            int currentX = pieza.getX();

            for (int i = 0; i < piezaRecord[currentY][currentX].length; i++) {

                if (piezaRecord[currentY][currentX][i] == pieza) {

                    piezaRecord[currentY][currentX][i] = null;

                    reorganizarBloque(currentY, currentX);
                    break;

                }

            }

            // ordenar el bloque de la nueva pieza
            piezaRecord[yCoord][xCoord][3] = pieza;
            pieza.setY(yCoord);
            pieza.setX(xCoord);
            reorganizarBloque(yCoord, xCoord);

        }

    }

    /*
	 * 
	 * Dispone una coordenada para mantener las piezas en orden,
         * como en 1 2 3 4, no 2 3 1 4
	 * 
     */
    private void reorganizarBloque(int yCoord, int xCoord) {

        List<Pieza> piezaList = new ArrayList<Pieza>();

        for (int i = 0; i < 4; i++) {

            Pieza pieza = piezaRecord[yCoord][xCoord][i];

            if (pieza != null) {

                piezaList.add(pieza);
                piezaRecord[yCoord][xCoord][i] = null;

            }

        }

        if (piezaList.size() != 0) {

            int numeroDePiezas = piezaList.size();

            for (int i = 0; i < numeroDePiezas; i++) {

                int lowestPiezaNumber = 5;
                int lowestPiezaIndex = 5;

                for (int j = i; j < numeroDePiezas; j++) {

                    int nextPiezaNumber = piezaList.get(j).getPiezaNúmero();
                    boolean isLower = nextPiezaNumber < lowestPiezaNumber;

                    if (isLower) {

                        lowestPiezaNumber = nextPiezaNumber;
                        lowestPiezaIndex = j;

                    }
                    lowestPiezaNumber = isLower ? nextPiezaNumber : lowestPiezaNumber;

                }

                piezaRecord[yCoord][xCoord][i] = piezaList.get(lowestPiezaIndex);

            }

        }

    }

}

