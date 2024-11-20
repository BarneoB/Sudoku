import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.util.*;

public class SudokuBase {
    private static Scanner clavier = new Scanner(System.in);

    public static int randomMinMax(int min, int max) {
        // Resultat : un entier entre min et max choisi aleatoirement
        Random rand = new Random();
        int res = rand.nextInt(max - min + 1) + min;
        // System.out.println(res + " in [" + min + "," + max + "]");
        // assert min <= res && res <= max : "tirage aleatoire hors des bornes";
        return res;
    }

    /**
     * pré-requis : min <= max
     * résultat :   un entier saisi compris entre min et max, avec re-saisie éventuelle jusqu'à ce qu'il le soit
     */
    public static int saisirEntierMinMax(int min, int max) {
        int entier = clavier.nextInt();
        while (entier < min || entier > max) {
            System.out.println("Donnez un entier compris entre " + min + " et " + max + ": ");
            entier = clavier.nextInt();
        }
        return entier;
    }  // fin saisirEntierMinMax

    /**
     * Donnée : un chemin vers un fichier texte de 9 lignes d'entiers
     * séparés par un espace
     * Résultat : retourne une matrice d'entiers 9x9 remplie à partir du
     * fichier ou null en cas d'erreur
     */
    public static int[][] lireGrilleDeSudoku(String fichier) {
        int[][] grille = new int[9][9];
        try (BufferedReader lecteur = new BufferedReader(new FileReader(fichier))) {
            for (int i = 0; i < 9; i++) {
                String ligne = lecteur.readLine();
                String[] valeurs = ligne.split("\\s+");
                for (int j = 0; j < 9; j++)
                    grille[i][j] =
                            Integer.parseInt(valeurs[j]);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return grille;
    }
    //.........................................................................

    /**
     * MODIFICI
     * pré-requis : mat1 et mat2 ont les mêmes dimensions
     * action : copie toutes les valeurs de mat1 dans mat2 de sorte que mat1 et mat2 soient identiques
     */
    public static void copieMatrice(int[][] mat1, int[][] mat2) {
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat1[0].length; j++) {
                mat2[i][j] = mat1[i][j];
            }
        }
    }  // fin copieMatrice

    //.........................................................................

    /**
     * pré-requis :  n >= 0
     * résultat : un tableau de booléens représentant le sous-ensemble de l'ensemble des entiers
     * de 1 à n égal à lui-même
     */
    public static boolean[] ensPlein(int n) {
        boolean T[] = new boolean[n + 1];
        for (int i = 0; i < n + 1; i++) {
            T[i] = true;
        }
        ;
        return T;
    }  // fin ensPlein

    public static boolean[] ensVide(int n) {
        boolean T[] = new boolean[n];
        for (int i = 1; i < n; i++) {
            T[i] = false;
        }
        return T;
    }

    //.........................................................................


    public static int nombreValeurPosssible(boolean[] m) {
        int compt = 0;
        for (int i = 1; i < m.length; i++) {
            if (m[i]) {
                compt++;
            }
        }
        return compt;
    }

    /**
     * pré-requis : 1 <= val < ens.length
     * action :     supprime la valeur val de l'ensemble représenté par ens, s'il y est
     * résultat :   vrai ssi val était dans cet ensemble
     */
    public static boolean supprime(boolean[] ens, int val) {
        if (ens[val-1]) {
            ens[val-1] = false;
            return true;
        } else {
            return false;
        }
    }  // fin supprime

    //.........................................................................


    /**
     * pré-requis : l'ensemble représenté par ens n'est pas vide
     * résultat :   un élément de cet ensemble
     */
    public static int uneValeur(boolean[] ens) {
        //renvoyer l'indice du tableau du tableau de boolean qui correspond au valeur possible du trou
        //Parcours partiel ---> dès que l'on croise une valeur la boucle s'arrete
        int i = 0;
        int compt = 1;
        while (!ens[i] && i < ens.length-1) {
            compt++;
            i++;
        }

        return compt;
    }  // fin uneValeur


    //.........................................................................

    /**
     * 1 2 3 4 5 6 7 8 9
     * -------------------
     * 1 |6 2 9|7 8 1|3 4 5|
     * 2 |4 7 3|9 6 5|8 1 2|
     * 3 |8 1 5|2 4 3|6 9 7|
     * -------------------
     * 4 |9 5 8|3 1 2|4 7 6|
     * 5 |7 3 2|4 5 6|1 8 9|
     * 6| 1 6 4|8 7 9|2 5 3|
     * -------------------
     * 7 3 8 1|5 2 7|9 6 4
     * 8 |5 9 6|1 3 4|7 2 8|
     * 9 |2 4 7|6 9 8|5 3 1|
     * -------------------
     * <p>
     * <p>
     * 1 2 3 4 5 6 7 8 9
     * -------------------
     * 1 |6 0 0|0 0 1|0 4 0|
     * 2 |0 0 0|9 6 5|0 1 2|
     * 3 |8 1 0|0 4 0|0 0 0|
     * -------------------
     * 4 |0 5 0|3 0 2|0 7 0|
     * 5 |7 0 0|0 0 0|1 8 9|
     * 6||0 0 0|0 7 0|0 0 3|
     * -------------------
     * 7 |3 0 0|0 2 0|9 0 4|
     * 8 |0 9 0|0 0 0|7 2 0|
     * 9 |2 4 0|6 9 0|0 0 0|
     * -------------------
     * <p>
     * <p>
     * pré-requis : 0<=k<=3 et g est une grille k^2xk^2 dont les valeurs sont comprises
     * entre 0 et k^2 et qui est partitionnée en k sous-carrés kxk
     * action : affiche la  grille g avec ses sous-carrés et avec les numéros des lignes
     * et des colonnes de 1 à k^2.
     * Par exemple, pour k = 3, on obtient le dessin d'une grille de Sudoku
     */
    public static void afficheGrille(int k, int[][] g) {
        int kCarré = k * k;
        System.out.print("   ");
        for (int i = 0; i < kCarré; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < kCarré; i++) {
            if ((i) % 3 == 0) {
                System.out.println("  -------------------");
            }
            System.out.print(i + 1 + " |");
            for (int j = 0; j < kCarré; j++) {
                System.out.print(g[i][j]);
                if ((j + 1) % 3 != 0) {
                    System.out.print(" ");
                }
                if ((j + 1) % 3 == 0) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
        System.out.print("  -------------------");
    } // fin afficheGrille
    //.........................................................................

    /**
     * pré-requis : k > 0, 0 <= i< k^2 et 0 <= j < k^2
     * résultat : (i,j) étant les coordonnées d'une case d'une grille k^2xk^2 partitionnée
     * en k sous-carrés kxk, retourne les coordonnées de la case du haut à gauche
     * du sous-carré de la grille contenant cette case.
     * Par exemple, si k=3, i=5 et j=7, la fonction retourne (3,6).
     */
    public static int[] debCarre(int k, int i, int j) {
        //Renvoie l'indice de la première case du sous carré des coordonné passée en paramètre
        //ATTENTION : les coordonnées en paramètre sont les coordonnée Sudoku mais les coordonnée retourner
        //sont les coordonnées machine

        //Coorsonnées Sudoku = (5,7) -> machine (4,6) --> -1
        //Coordonnées machine = (3,6) -> sudoku (4,7) --> +1
        int n = 0;
        int coordonnéeLigne = -1;
        int coordonnéeColonne = -1;
        int[] coordonnéeFinal = new int[2];

        while ((n <= k * k) && (coordonnéeLigne == -1)) {
            if ((i >= n * k) && (((n + 1) * k - 1) > i - 1)) {
                coordonnéeLigne = n * k;
            }
            n++;
        }
        n = 0;
        while ((n <= k * k) && (coordonnéeColonne == -1)) {
            if ((j >= n * k) && (((n + 1) * k - 1) > j - 1)) {
                coordonnéeColonne = n * k;
            }
            n++;
        }
        coordonnéeFinal[0] = coordonnéeLigne;
        coordonnéeFinal[1] = coordonnéeColonne;

        return coordonnéeFinal;
    }// fin debCarre


    //.........................................................................

    // Initialisation
    //.........................................................................

    /**
     * MODIFICI
     * pré-requis : gComplete est une matrice 9X9
     * action   :   remplit gComplete pour que la grille de Sudoku correspondante soit complète
     * stratégie :  les valeurs sont données directement dans le code et on peut utiliser copieMatrice pour mettre à jour gComplete
     */
    public static void initGrilleComplete(int[][] gComplete) {
        int[][] grilleComplete =
                {
                        {6, 2, 9, 7, 8, 1, 3, 4, 5},
                        {4, 7, 3, 9, 6, 5, 8, 1, 2},
                        {8, 1, 5, 2, 4, 3, 6, 9, 7},
                        {9, 5, 8, 3, 1, 2, 4, 7, 6},
                        {7, 3, 2, 4, 5, 6, 1, 8, 9},
                        {1, 6, 4, 8, 7, 9, 2, 5, 3},
                        {3, 8, 1, 5, 2, 7, 9, 6, 4},
                        {5, 9, 6, 1, 3, 4, 7, 2, 8},
                        {2, 4, 7, 6, 9, 8, 5, 3, 1}
                };
        copieMatrice(grilleComplete, gComplete);
    } // fin initGrilleComplete

    //.........................................................................

    /**
     * MODIFICI
     * pré-requis : gSecret est une grille de Sudoku complète de mêmes dimensions que gIncomplete et 0 <= nbTrous <= 81
     * action :     modifie gIncomplete pour qu'elle corresponde à une version incomplète de la grille de Sudoku gSecret
     * (gIncomplete peut être complétée en gSecret),
     * avec nbTrous trous à des positions aléatoires
     */
    public static void initGrilleIncomplete(int nbTrous, int[][] gSecret, int[][] gIncomplete) {
        //___________________________________________________________________________
        //copie de gsecret dans gIcomplete
        copieMatrice(gSecret, gIncomplete);
        //boucle for de k à nbbtrous
        for (int k = 0; k < nbTrous; k++) {
            int i = randomMinMax(0, 8);
            int j = randomMinMax(0, 8);
            while (gIncomplete[i][j] == 0) {
                i = randomMinMax(0, 8);
                j = randomMinMax(0, 8);
            }
            gIncomplete[i][j] = 0;
        }

    } // fin initGrilleIncomplete

    //.........................................................................


    /**
     * pré-requis : 0 <= nbTrous <= 81
     * résultat :   une grille  9x9 saisie dont les valeurs sont comprises ente 0 et 9
     * avec exactement  nbTrous valeurs nulles
     * et avec re-saisie jusqu'à ce que ces conditions soient vérifiées.
     * On suppose dans la version de base que la grille saisie est bien une grille de Sudoku incomplète.
     * stratégie : utilise la fonction saisirEntierMinMax
     */
    public static int[][] saisirGrilleIncomplete(int nbTrous, int[][] g) {
        boolean grilleSudoku = false;
        while (!grilleSudoku) {
            int nbTrousSaisie = 0;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.println("Donnez un entier compris entre 0 et 9: ");
                    g[i][j] = saisirEntierMinMax(0, 9);
                    if (g[i][j] == 0) {
                        nbTrousSaisie++;
                    }
                }
            }
            if (nbTrousSaisie == nbTrous) {
                grilleSudoku = true;
            }
        }
        return g;
    }  // fin saisirGrilleIncomplete

    //.........................................................................


    /**
     * pré-requis : gOrdi est une grille de Sudoku incomplète,
     * valPossibles est une matrice 9x9 de tableaux de 10 booléens
     * et nbValPoss est une matrice 9x9 d'entiers
     * action : met dans valPossibles l'ensemble des entiers de 1 à 9 pour chaque trou de gOrdi
     * et leur nombre dans nbValPoss
     */
    public static void initPleines(int[][] gOrdi, boolean[][][] valPossibles, int[][] nbValPoss) {
        for (int i = 0; i < gOrdi.length; i++) {
            for (int j = 0; j < gOrdi[i].length; j++) {
                if (gOrdi[i][j] == 0) {
                    valPossibles[i][j] = ensPlein(9);
                    nbValPoss[i][j] = 9;
                }
            }

        }

    }

    // fin initPleines

    //.........................................................................


    /**
     * pré-requis : gOrdi est une grille de Sudoku incomplète,
     * 0<=i<9, 0<=j<9,g[i][j]>0,
     * valPossibles est une matrice 9x9 de tableaux de 10 booléens
     * et nbValPoss est une matrice 9x9 d'entiers
     * action : supprime dans les matrices valPossibles et nbValPoss la valeur gOrdi[i][j] pour chaque case de la ligne,
     * de la colonne et du carré contenant la case (i,j) correspondant à un trou de gOrdi.
     */
    public static void suppValPoss(int[][] gOrdi, int i, int j, boolean[][][] valPossibles, int[][] nbValPoss) {
        for (int k = 0; k < 9; k++) {
            if (k != j && gOrdi[i][k] == 0) {
                supprime(valPossibles[i][k], gOrdi[i][j]);
                nbValPoss[i][k]--;
            }
            if (k != i && gOrdi[k][j] == 0) {
                supprime(valPossibles[k][j], gOrdi[i][j]);
                nbValPoss[k][j]--;
            }
        }
        for (int ilignes = 0; ilignes < 3; ilignes++) {
            for (int jcolonnes = 0; jcolonnes < 3; jcolonnes++) {
                int[] debCarre = debCarre(3, i, j);
                int ligne = debCarre[0] + ilignes;
                int colonne = debCarre[1] + jcolonnes;
                if (ligne != i && colonne != j && gOrdi[ligne][colonne] == 0) {
                    supprime(valPossibles[ligne][colonne], gOrdi[i][j]);
                    nbValPoss[ligne][colonne]--;
                }
            }
        }
    }// fin suppValPoss

    //.........................................................................

    /**
     * pré-requis : gOrdi est une grille de Sudoju incomplète,
     * valPossibles est une matrice 9x9 de tableaux de 10 booléens
     * et nbValPoss est une matrice 9x9 d'entiers
     * action :      met dans valPossibles l'ensemble des valeurs possibles de chaque trou de gOrdi
     * et leur nombre dans nbValPoss
     */
    public static void initPossibles(int[][] gOrdi, boolean[][][] valPossibles, int[][] nbValPoss) {
        //__
        for (int i = 0; i < gOrdi.length; i++) {
            for (int j = 1; j < gOrdi[0].length; j++) {
                if (gOrdi[i][j] != 0) {
                    valPossibles[i][j] = ensVide(10);
                    nbValPoss[i][j] = 0;
                } else {
                    valPossibles[i][j] = ensPlein(9);
                    //parcourir pour le sous-carré (en appelant debCarre)
                    for (int ilignes = 0; ilignes < 3; ilignes++) {
                        for (int jcolonnes = 0; jcolonnes < 3; jcolonnes++) {
                            int[] debCarre = debCarre(3, i, j);
                            int ligne = debCarre[0] + ilignes;
                            int colonne = debCarre[1] + jcolonnes;
                            if (gOrdi[ligne][colonne] != 0) {
                                valPossibles[i][j][gOrdi[ligne][colonne]] = false;
                            }
                        }
                    }
                    //parcourir pour la ligne
                    for (int k = 0; k < gOrdi.length; k++) {
                        if (gOrdi[i][k] != 0) {
                            valPossibles[i][j][gOrdi[i][k]] = false;
                        }
                    }
                    //parcourir pour la colonne
                    for (int k = 0; k < gOrdi.length; k++) {
                        if (gOrdi[k][j] != 0) {
                            valPossibles[i][j][gOrdi[k][j]] = false;
                        }
                    }
                }
                nbValPoss[i][j] = nombreValeurPosssible(valPossibles[i][j]);
            }
        }
    }  // fin initPossibles
    //.........................................................................


    /**
     * pré-requis : gSecret, gHumain et gOrdi sont des grilles 9x9
     * action :     - demande au joueur humain de saisir le nombre nbTrous compris entre 0 et 81,
     * - met dans gSecret une grille de Sudoku complète,
     * - met dans gHumain une grille de Sudoku incomplète, pouvant être complétée en gSecret
     * et ayant exactement nbTrous trous de positions aléatoires,
     * - met dans gOrdi une grille de Sudoku incomplète saisie par le joueur humain
     * ayant  nbTrous trous,
     * - met dans valPossibles l'ensemble des valeurs possibles de chaque trou de gOrdi
     * et leur nombre dans nbValPoss.
     * retour : la valeur de nbTrous
     */
    public static int initPartie(int[][] gSecret, int[][] gHumain, int[][] gOrdi, boolean[][][] valPossibles, int[][] nbValPoss) {
        System.out.println("Donnez un nombre de zéros compris entre 0 et 81: ");
        int nbTrous = saisirEntierMinMax(0, 81);
        if (nbTrous != 0) {
            int[][] g = new int[9][9];
            gOrdi = saisirGrilleIncomplete(nbTrous, g);
            initGrilleComplete(gSecret);
            initGrilleIncomplete(nbTrous, gSecret, gHumain);
            initPossibles(gOrdi, valPossibles, nbValPoss);
        }
        afficheGrille(3, gOrdi);
        System.out.println(nbTrous);
        return nbTrous;
    }

    //...........................................................
    // Tour du joueur humain
    //...........................................................

    /**
     * pré-requis : gHumain est une grille de Sudoku incomplète pouvant se compléter en
     * la  grille de Sudoku complète gSecret
     * <p>
     * résultat :   le nombre de points de pénalité pris par le joueur humain pendant le tour de jeu
     * <p>
     * action :     effectue un tour du joueur humain
     */
    public static int tourHumain(int[][] gSecret, int[][] gHumain) {
        int penalité = 0;
        boolean TourFini =false;
        while(!TourFini) {
            System.out.println("Choisissez un numéro de ligne : ");
            int i = saisirEntierMinMax(0, gSecret.length);
            System.out.println("Choisissez un numéro de colonne : ");
            int j = saisirEntierMinMax(0, gSecret.length);
            if (gHumain[i-1][j-1] == 0) {
                System.out.println("Choisissez un numéro à placer : ");
                int x = saisirEntierMinMax(0, gSecret.length);
                if (x == 0) {
                    penalité++;
                    gHumain[i-1][j-1] = gSecret[i-1][j-1];
                    TourFini=true;
                }
                if (x != gSecret[i-1][j-1] || gHumain[i-1][j-1] != 0) {
                    penalité++;
                    System.out.println("Faux numéro, veuillez rejouer :");
                } else {
                    gHumain[i-1][j-1] = x;
                    TourFini=true;
                }
            }else {
                System.out.println("Case déjà remplie, veuillez rejouer :");
            }
        }
        return penalité;
    }  // fin  tourHumain

    //.........................................................................

    // Tour de l'ordinateur
    //.........................................................................

    /**
     * pré-requis : gOrdi et nbValPoss sont des matrices 9x9
     * résultat :   le premier trou (i,j) de gOrdi (c'est-à-dire tel que gOrdi[i][j]==0)
     * évident (c'est-à-dire tel que nbValPoss[i][j]==1) dans l'ordre des lignes,
     * s'il y en a, sinon le premier trou de gOrdi dans l'ordre des lignes
     */
    public static int[] chercheTrou(int[][] gOrdi, int[][] nbValPoss) {
        int[] coordonnee = new int[2];
        boolean TrouTrouvé = false;
        int i = 0;
        int j = 0;
        while (i < gOrdi.length - 1 && j < gOrdi.length - 1 && !TrouTrouvé) {
            if (gOrdi[i][j] == 0) {
                coordonnee[0] = i;
                coordonnee[1] = j;
                if (nbValPoss[i][j] == 1) {
                    TrouTrouvé = true;
                }
            }
            i++;
            if (i == gOrdi.length - 1) {
                i = 0;
                j++;
            }
        }
        i = 0;
        j = 0;
        while (i < gOrdi.length - 1 && j < gOrdi.length - 1 && !TrouTrouvé) {
            if (gOrdi[i][j] == 0) {
                coordonnee[0] = i;
                coordonnee[1] = j;
                TrouTrouvé = true;
            }
            i++;
            if (i == gOrdi.length - 1) {
                i = 0;
                j++;
            }
        }
        return coordonnee;
    }  // fin chercheTrou

    //.........................................................................

    /**
     * pré-requis : gOrdi est une grille de Sudoju incomplète,
     * valPossibles est une matrice 9x9 de tableaux de 10 booléens
     * et nbValPoss est une matrice 9x9 d'entiers
     * action :     effectue un tour de l'ordinateur
     */
    public static int tourOrdinateur(int[][] gOrdi, boolean[][][] valPossibles, int[][] nbValPoss) {
        int penalité = 0;
        boolean jokerPris = false;
        boolean valeurPlacé = false;
        int[] coordonnee = chercheTrou(gOrdi, nbValPoss);
        int i = coordonnee[0];
        int j = coordonnee[1];
        if (nbValPoss[i][j] != 1) {
            penalité++;
            jokerPris = true;
        }
        int valeur = uneValeur(valPossibles[i][j]);

        while((jokerPris && nbValPoss[i][j]==2 && !valeurPlacé)){
            System.out.println("L'ordinateur a choisi la valeur : " + valeur);
            System.out.println("c'est la bonne valeur ? (Oui = 1 et Non = 0)");
            int verif = clavier.nextInt();
            switch (verif) {
                case 1:
                    System.out.println("Bonne valeur");
                    valeurPlacé = true;
                case 0:
                    System.out.println("Mauvaise Valeur, Quelle était la valeur ?\n");
                    valeur = clavier.nextInt();
                    valeurPlacé = true;
                default:
                    System.out.println("Valeur Incorect");
            }
        }
        gOrdi[i][j] = valeur;
        suppValPoss(gOrdi, i, j, valPossibles, nbValPoss);
        return penalité;
    }  // fin tourOrdinateur

    //.........................................................................
    // Partie
    //.........................................................................

    /**
     * pré-requis : aucun
     * <p>
     * action : crée et initialise les matrices utilisées dans une partie, et effectue une partie de Sudoku entre le joueur humain et l'ordinateur.
     * <p>
     * résultat :   0 s'il y a match nul, 1 si c'est le joueur humain qui gagne et 2 sinon
     */
    public static int partie() {
        int[][] gSecret = new int[9][9];
        int[][] gHumain = new int[9][9];
        int[][] gOrdi = new int[9][9];
        boolean[][][] valPossibles = new boolean[9][9][10];
        int[][] nbValPoss = new int[9][9];
        int nbTrous = initPartie(gSecret, gHumain, gOrdi, valPossibles, nbValPoss);
        int scoreHumain = 0;
        int scoreOrdi = 0;
        while (nbTrous > 0) {
            afficheGrille(3, gHumain);
            scoreHumain += tourHumain(gSecret, gHumain);
            scoreOrdi += tourOrdinateur(gOrdi, valPossibles, nbValPoss);
            nbTrous--;
        }
        return switch (scoreHumain - scoreOrdi) {
            case 0 -> 0;

            case 1 -> 1;

            case -1 -> 2;

            default -> -1;
        };
    }  // fin partie

    //.........................................................................


    /**
     * pré-requis : aucun
     * action :     effectue une partie de Sudoku entre le joueur humain et l'ordinateur
     * et affiche qui a gagné
     */
    public static void main(String[] args) {
        int nbTrous = 3;
        int[][] grilleComplète={
            {6,2,9,7,8,1,3,4,5},
            {4,7,3,9,6,5,8,1,2},
            {8,1,5,2,4,3,6,9,7},
            {9,5,8,3,1,2,4,7,6},
            {7,3,2,4,5,6,1,8,9},
            {1,6,4,8,7,9,2,5,3},
            {3,8,1,5,2,7,9,6,4},
            {5,9,6,1,3,4,7,2,8},
            {2,4,7,6,9,8,5,3,1}};
        int[][] grilleIncomplète={
            {6,2,9,7,8,1,3,0,5},
            {4,7,3,9,6,5,8,1,2},
            {8,0,5,2,4,3,6,9,7},
            {9,5,8,3,1,2,4,7,6},
            {7,3,2,4,5,6,1,8,9},
            {1,6,0,0,7,9,2,5,3},
            {3,8,1,5,2,7,9,6,4},
            {5,9,6,1,3,4,7,2,8},
            {2,4,7,6,9,8,5,3,1}};
        while (nbTrous != 0){
            tourHumain(grilleComplète,grilleIncomplète);
            nbTrous--;
        }
        int res = partie();
        switch (res) {
            case 0 -> System.out.println("Match nul");

            case 1 -> System.out.println("Vous avez gagné");

            case -1 -> System.out.println("Vous avez perdu");

            default -> System.out.println("Erreur Jeu");
        }
        ;
    }  // fin main

} // fin SudokuBase