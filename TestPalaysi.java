import java.util.Arrays;
import java.util.concurrent.*;

public class TestPalaysi {
    public static void main(String[] args) {
        double FonctionPassés = 0;
        FonctionPassés += TestDebCarré(); //CHECK
        FonctionPassés += TesDebCarré2(); //CHECK
        FonctionPassés += TestSuppValPoss();
        FonctionPassés += TestInitPossibles();//CHECK
        //FonctionPassés += TestChercheTrou();
        System.out.println("Nombre de Test Passés: " + FonctionPassés);
    }

    /*
     * ===================================================================
     * ======================== TEST debCarré ============================
     * ===================================================================
     */
    public static double TestDebCarré() {
        int[] T = SudokuBase.debCarre(3, 5, 7);
        int[] U = SudokuBase.debCarre(2, 1, 1);
        if (T[0] == 3 && T[1] == 6 && U[0] == 0 && U[1] == 0)
            return 1;
        else
            return 0;
    }

    public static double TesDebCarré2() {
        int[] T = SudokuBase.debCarre(4, 5, 6);
        if (T[0] == 4 && T[1] == 4)
            return 1;
        else
            return 0;
    }

    // ON PASSE TOUT
    /*
     * ===================================================================
     * ======================== TEST SupValPoss ==========================
     * ===================================================================
     */
    public static boolean ensemblePlein(boolean[] ensemble) {
        boolean[] ensPlein1 = {true, true, true, true, true, true, true, true, true, true};
        boolean[] ensPlein2 = {false, true, true, true, true, true, true, true, true, true};
        return (Arrays.equals(ensemble, ensPlein1) || Arrays.equals(ensemble, ensPlein2));
    }

    public static double TestSuppValPoss() {
        int[][] Grille = {
                {6, 2, 9, 7, 8, 1, 3, 4, 5},
                {4, 0, 3, 9, 6, 5, 8, 1, 2},
                {8, 1, 0, 2, 4, 3, 0, 9, 7},
                {9, 5, 8, 3, 1, 2, 4, 7, 6},
                {7, 3, 2, 4, 5, 6, 1, 8, 9},
                {1, 6, 4, 8, 7, 9, 2, 5, 3},
                {3, 8, 1, 5, 2, 7, 9, 6, 0},
                {5, 9, 6, 1, 3, 4, 7, 2, 8},
                {2, 4, 7, 6, 9, 8, 5, 3, 1}
        };
        boolean[][][] valPossibles = new boolean[9][9][10];
        int[][] nbValPossibles = new int[9][9];
        valPossibles[1][1] = new boolean[]{false, true, true, true, true, true, true, true, true, true};
        nbValPossibles[1][1] = 9;
        valPossibles[6][8] = new boolean[]{false, true, true, true, true, true, true, true, true, true};
        nbValPossibles[6][8] = 9;
        valPossibles[2][6] = new boolean[]{false, true, true, true, true, true, true, true, true, true};
        nbValPossibles[2][6] = 9;

        valPossibles[1][3] = new boolean[]{false, true, true, true, true, true, true, true, true, true};
        nbValPossibles[1][3] = 9;
        valPossibles[8][4] = new boolean[]{false, true, true, true, true, true, true, true, true, true};
        nbValPossibles[8][4] = 9;
        valPossibles[0][8] = new boolean[]{false, true, true, true, true, true, true, true, true, true};
        nbValPossibles[0][8] = 9;

        valPossibles[2][2] = new boolean[]{false, true, true, true, true, true, true, true, true, true};
        nbValPossibles[2][2] = 9;

        SudokuBase.suppValPoss(Grille, 1, 8, valPossibles, nbValPossibles);

        // les tableaux de booléens dans les trous sur la ligne, la colonne et le carré de (1,8) devraient être égaux à celui-là :
        boolean[] rst = {false, true, false, true, true, true, true, true, true, true};

        boolean cond1 = Arrays.equals(valPossibles[1][1], rst) && nbValPossibles[1][1] == 8;//CHECK
        boolean cond2 = Arrays.equals(valPossibles[6][8], rst) && nbValPossibles[6][8] == 8;//CHECK
        boolean cond3 = Arrays.equals(valPossibles[2][6], rst) && nbValPossibles[2][6] == 8;//CHECK

        // mais les autres ne doivent pas avoir été changés, y compris ceux sur la ligne, la colonne et le carré de (1,8) - et même si ce serait correct d'un point de vue logique
        boolean cond4 = ensemblePlein(valPossibles[1][3]) && nbValPossibles[1][3] == 9;
        System.out.println(nbValPossibles[1][3]);
        boolean cond5 = ensemblePlein(valPossibles[8][4]) && nbValPossibles[8][4] == 9;//CHECK
        boolean cond6 = ensemblePlein(valPossibles[0][8]) && nbValPossibles[0][8] == 9;
        // ni pour les autres trous de la grille
        boolean cond7 = ensemblePlein(valPossibles[2][2]) && nbValPossibles[2][2] == 9;//CHECK
        if (cond1 && cond2 && cond3 && cond4 && cond5 && cond6 && cond7)
            return 1;
        else
            return 0;
    }

    /*
     * ===================================================================
     * ======================== TEST initPossible ========================
     * ===================================================================
     */
    public static double TestInitPossibles() {
        int[][] grille = {{6, 0, 0, 0, 0, 1, 0, 4, 0},
                {0, 0, 0, 9, 6, 5, 0, 1, 2},
                {8, 1, 0, 0, 4, 0, 0, 0, 0},
                {0, 5, 0, 3, 0, 2, 0, 7, 0},
                {7, 0, 0, 0, 0, 0, 1, 8, 9},
                {0, 0, 0, 0, 7, 0, 0, 0, 3},
                {3, 0, 0, 0, 2, 0, 9, 0, 4},
                {0, 9, 0, 0, 0, 0, 7, 2, 0},
                {2, 4, 0, 6, 9, 0, 0, 0, 0}};
        boolean[][][] valPossibles = new boolean[9][9][10];
        int[][] nbValPoss = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                nbValPoss[i][j] = 9;
            }
        }

        SudokuBase.initPossibles(grille, valPossibles, nbValPoss);

        // j'ai choisi de vérifier seulement pour 3 cases vides de la grille...

        boolean ensZéroUn[] = {false, false, true, true, false, false, false, true, false, false};
        valPossibles[0][1][0] = false;
        boolean okZéroUn = (Arrays.equals(valPossibles[0][1], ensZéroUn) && nbValPoss[0][1] == 3);

        boolean ensTroisHuit[] = {false, false, false, false, false, false, true, false, false, false};
        valPossibles[3][8][0] = false;
        boolean okTroisHuit = (Arrays.equals(valPossibles[3][8], ensTroisHuit) && nbValPoss[3][8] == 1);

        boolean ensQuatreDeux[] = {false, false, true, true, true, false, true, false, false, false};
        valPossibles[4][2][0] = false;
        boolean okQuatreDeux = (Arrays.equals(valPossibles[4][2], ensQuatreDeux) && nbValPoss[4][2] == 4);
        if (okZéroUn && okTroisHuit && okQuatreDeux)
            return 0;
        else
            return 1;
    }

    /*
     * ===================================================================
     * ======================== TEST ChercheTrou =========================
     * ===================================================================
     */
    public static double TestChercheTrou() {
        int[][] grilleTrouée = {
                {6, 2, 9, 7, 8, 1, 3, 0, 5},
                {4, 7, 3, 9, 6, 5, 8, 1, 2},
                {8, 1, 5, 2, 4, 3, 6, 9, 7},
                {9, 5, 8, 3, 1, 2, 4, 7, 6},
                {7, 3, 0, 4, 5, 6, 1, 8, 9},
                {1, 6, 4, 8, 7, 9, 2, 5, 3},
                {3, 8, 1, 5, 2, 7, 9, 6, 4},
                {5, 9, 6, 1, 3, 4, 0, 2, 8},
                {2, 4, 7, 6, 9, 8, 5, 3, 1}};
        int[][] nbValeursPossibles = new int[9][9];
        nbValeursPossibles[1][5] = 1;
        nbValeursPossibles[0][7] = 2;
        nbValeursPossibles[4][2] = 1;
        nbValeursPossibles[5][1] = 1;
        nbValeursPossibles[7][6] = 3;
        int[] trou = SudokuBase.chercheTrou(grilleTrouée, nbValeursPossibles);
        System.out.println(Arrays.toString(trou));
        nbValeursPossibles[4][2] = 3;
        int[] trou2 = SudokuBase.chercheTrou(grilleTrouée, nbValeursPossibles);
        System.out.println(Arrays.toString(trou2));
        if (trou[0] == 4 && trou[1] == 2 && trou2[0] == 0 && trou2[1] == 7)
            return 0;
        else
            return 1;
    }

}
