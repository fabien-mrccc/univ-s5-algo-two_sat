# Problème 2-SAT

## Informations utiles
- Membres du projet : FANANI Amina, MARCUCCINI Fabien 
- Chargé de TP : ROLLAND Marius
- Matière : "Algorithmique 2" en troisième année de licence informatique à l'université d'Aix-Marseille
- Version du SDK : openjdk-22 | Amazon Corretto 22.0.2 - aarch64


## PARTIE 1 : README

### But du projet
- L'énoncé du projet est fourni dans le fichier **/pdf/tp1.pdf**. <br>
- L'objectif général est d'automatiser la vérification de satisfaisabilité de formules propositionnelles avec des clauses de longueur 2 *(voir définition des termes techniques dans l'énoncé)*.

### Ligne de commande pour utiliser le projet
- La ligne de commande qui permet d'exécuter le code du projet dans le terminal est la suivante :<br>
**java -jar out/artifacts/2SAT/2SAT.jar <chemin_fichier_formule>**<br>
- Exemple d'utilisation : *java -jar out/artifacts/2SAT/2SAT.jar formulas/formula.txt*<br>

### Description du rôle des classes

- **ComponentsIdentifier :** Identifie les composantes fortement connexes d'un graphe dans des structures distinctes à l'aide d'une recherche en profondeur. <br><br>

- **Edge :** Représente un arc dans un graphe orienté.<br><br>

- **Graph :** Représente un graphe orienté via une liste d'incidence, permettant l'ajout d'arêtes et la création d'un graphe miroir.<br><br>

- **GraphSearch :** Effectue une recherche en profondeur itérative sur un graphe orienté, enregistrant les prédécesseurs et les temps d'entrée/sortie des nœuds.<br><br>

- **ImplicationGraph :** Étend la classe Graph pour représenter les implications d'un problème 2-SAT, ajoutant des arêtes basées sur des clauses logiques et modélisant chaque littéral par un sommet.<br><br>

- **Kosaraju :** Identifie les composantes fortement connexes d'un graphe des implications en utilisant l'algorithme de Kosaraju.<br><br>

- **Main :** Exécute l'algorithme 2-SAT en construisant le graphe des implications à partir d'une formule issue d'un fichier formaté, puis en identifiant les composantes fortement connexes et en vérifiant la satisfaisabilité de la formule.<br><br>

- **Parser :** Construit le graphe des implications à partir d'une formule 2-SAT formatée dans un fichier spécifié.<br><br>

- **TwoSat :** Vérifie la consistance d'un système 2-SAT en s'assurant qu'aucune composante ne contient un littéral et son opposé.


## PARTIE 2 : RAPPORT

### 1. Structures de données choisies
Pour résoudre le problème 2-SAT, nous avons utilisé des ArrayList pour les sommets et les composantes fortement connexes, tout en conservant des LinkedList dans la classe Graph pour les listes d'incidence, et des HashSet dans TwoSat pour les littéraux :<br>

- **ArrayList :** Accès O(1) pour les sommets et les composantes, et ajout O(1), adapté aux parcours des composantes fortement connexes.<br><br>

- **LinkedList :** Permet une insertion rapide O(1) lors de la construction du graphe, tout en étant efficace pour les parcours séquentiels.<br><br>
*Nous avons utilisé une structure ArrayList<LinkedList<Edge>> dans la classe Graph, où chaque sommet est associé à une LinkedList de ses arcs sortants. Cela combine l'efficacité d'accès d'une ArrayList avec la souplesse d'ajout d'une LinkedList.*<br><br>

- **HashSet :** Évite les doublons pour stocker les littéraux dans la classe TwoSat.<br>

### 2. Preuve du fonctionnement de la solution
L'algorithme implémenté fonctionne en plusieurs étapes, avec une logique bien fondée sur la théorie des graphes et la structure du problème 2-SAT :<br>
1. **Construction du graphe des implications :** Transformation de chaque clause l1∨l2 en deux implications ¬l1⇒l2 et ¬l2⇒l1, modélisant les dépendances logiques de la formule à traiter.<br><br>
2. **Application de l'algorithme de Kosaraju :** Identification des arcs faisant partis de composantes fortement connexes dans le graphe des implications via une première exploration en profondeur du graphe, suivi d'un deuxième parcours sur la transposée respectant l'ordre des temps de sortie du premier parcours.<br><br>
3. **Regroupement des arcs des composantes fortement connexes :** Parcours en profondeur de la liste des arcs identifiés par Kosaraju afin de reconstruire distinctement les sous-graphes  représentant les composantes fortements connexes.<br><br>
4. **Vérification de la satisfaisabilité :** Analyse de la présence de composantes fortement connexes avec un littéral et son opposé indiquant ainsi une insatisfaisabilité de la formule *(un littéral ne peut impliquer son opposé)*, sinon la formule est satisfaisable.<br>

### 3. Problèmes rencontrés

- #### Lecture des clauses des formules 2-SAT dans la classe Parser.
  - **Raison :** Utilisation incorrecte de la méthode bufferedReader.readLine(), entraînant le saut d'une ligne sur deux lors de la lecture du fichier.<br>
  - **Conséquences :** Erreur dans la construction des clauses et du graphe des implications, entraînant des résultats incohérents dans la suite de l'algorithme 2-SAT.<br>
  - **Solution :** Identification de l'erreur après plusieurs heures de recherche et correction du flux de lecture.<br>
  - **Résultat :** La construction du graphe des implications fonctionne correctement ainsi que les algorithmes qui les utilisent.<br>

- #### Identification distincte des composantes fortement connexes issues de la classe Kosaraju.
  - **Raison :** La classe Kosaraju retourne une liste simple d'arcs ne permettant pas d'identifier quels arcs font partis d'une même composante fortement connexe.
  - **Conséquence :** Difficulté à réutiliser l'algorithme de parcours de graphe car on ne peut reconstruire des objets Graph avec uniquement une liste d'arcs quelconques.
  - **Solution :** Écriture d'une nouvelle classe ComponentsIdentifier qui prend en compte les spécifités du parcours nécessaire à l'identification des différentes composantes fortement connexes.
  - **Résultat :** Utilisation d'une structure ArrayList<ArrayList<Integer'>> permettant des regroupements distincts entre chaque littéral appartenant à la même composante fortement connexe.

### 4. Étude de complexité
* **Algorithme :** Résolution du problème 2-SAT avec l'algorithme de Kosaraju.<br>
* **Complexité :** Linéaire, O(n+m), où n est le nombre de sommets et m le nombre d'arcs.<br>
* **Étapes :** Construction du graphe des implications en O(m), premier DFS en O(n+m), construction du graphe miroir en O(n+m) et le deuxième DFS réalisé sur le graphe miroir, également en O(n+m).<br>
* **Résultat :** La complexité globale de l'algorithme est linéaire en fonction du nombre de clauses.<br>

