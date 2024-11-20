<h1 align="Center">
SAE 1.01 Sudoku a deux joueurs
</h1>

***
## Table of Contents
<ul>
<li><a href="#Information">Information</a></li>
<li><a href= >Collaboration</a></li>
<li><a href= >Contenu</a></li>
<li><a href= >Stratégie Utilisés</a></li>
<li><a href="#Mode_Emploi">Mode d'Emploi</a></li>
</ul>

<h2 align="Center" id="Information">
Information
</h2>

***

<div align="Justify">
Le Sudoku est un jeu à un seul joueur. Le joueur doit compléter une grille de Sudoku incomplète, trouvée par exemple dans un magazine, en une grille de Sudoku complète.
Une grille de Sudoku complète est une grille carrée 9x9 partitionnée en 9 carrés 3x3 appelés
les carrés de la grille, telle que chaque ligne, chaque colonne et chaque carré de la grille
contient exactement les entiers de 1 à 9. La table 1 donne un exemple de grille de Sudoku
complète.

On considère ici une variante du jeu de Sudoku qui se joue à 2 joueurs. Chacun des 2 joueurs
choisit en secret une grille de Sudoku complète, en enlève certaines valeurs et donne la grille
de Sudoku incomplète obtenue à l’autre joueur, qui doit ensuite compléter sa grille, c’est-à-dire celle donnée par l’autre joueur. Pour équilibrer les chances des 2 joueurs, le nombre de
trous est le même pour les 2 grilles. Chaque joueur joue un coup à tour de rôle. Un coup consiste, soit à remplir l’un des
trous de sa grille, soit à utiliser un joker, c’est-à-dire demander à l’autre joueur de lui révéler la valeur d’un trou, qu’il peut ainsi combler.

Ici le deuxième joueur sera un ordinateur.
</div>

<div align="Center">
<img src="Document/logoSudoku.png" alt="SudokuLogo" width="500"/></img>
</div>

<h2 align="Center">
Collaboration
</h2>

***
<h3 align="Center">
Tâches ou fonction effectuée par bryan
</h3>

> * copieMatrice 
> * ensPlein
> * supprime
> * afficheGrille
> * initGrilleComplete
> * saisirGrilleIncomplete
> * suppValPoss
> * initPartie
> * chercheTrou
> * tourOrdinateur
> * partie et main

<h3 align="Center">
Tâches ou fonction effectuée par Alexis
</h3>

> * saisirEntierMinMax
> * ensVide
> * nombreValeurPossible
> * uneValeur
> * debCarre
> * initGrilleIncomplete
> * initPleines
> * initPossibles
> * tourHumain

<h2 align="Center">
Contenu
</h2>

***
* <h3>Dossier ressouce pour le README.md</h3>
* <h3>Le fichier de base nommée SudokuBase</h3>
* <h3>Le fichier de Test fournis par nos professeurs</h3>
* <h3>Le fichier Ut contenant des fonctions utiles</h3>

<h2 align="Center">
Stratégie Utilisés
</h2>

***
blblblbblbl

<h2 align="Center" id="Mode_Emploi">
Mode D'Emploi
</h2>

<div align="Justify">Pour commencer, 
vous aurez une grille de Sudoku partiellement remplie. 
Votre tâche est de remplir les cases vides.

Regardez la grille et essayez de trouver une case où il n'y a qu'une seule possibilité.
Par exemple, si une ligne, une colonne et un carré de 3x3 contiennent tous les chiffres de 1 à 9 sauf le 2,
alors la case vide doit être un 2.

Continuez à remplir la grille de cette manière.
Si vous trouvez une case où il y a plus d'une possibilité, faites une supposition et continuez.
Si vous arrivez à un point où votre supposition conduit à une contradiction, revenez en arrière et essayez une autre supposition.

Une fois que vous avez rempli toute la grille, vérifiez votre travail.
Chaque ligne, chaque colonne et chaque carré de 3x3 doit contenir tous les chiffres de 1 à 9 sans répétition.

Si votre grille est correctement remplie, félicitations! Vous avez terminé le puzzle Sudoku. Si ce n'est pas le cas, ne vous découragez pas. Continuez à pratiquer et vous vous améliorerez.

Note: Dans notre variante du jeu, vous jouez contre un ordinateur.
Vous pouvez choisir de remplir une case ou d'utiliser un joker pour demander à l'ordinateur de révéler la valeur d'une case.
</div>