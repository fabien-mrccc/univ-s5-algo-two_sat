### Membres du projet :
- FANANI Amina
- MARCUCCINI Fabien

### Chargé de TP :
- ROLLAND Marius

# PARTIE 1 : RAPPORT :
## 1. Structures de donnée choisies :
Dans la résolution du problème 2-SAT, nous avons utilisé des ArrayList pour la gestion des sommets et des composantes fortement connexes, tout en conservant les LinkedList uniquement dans la classe Graph pour représenter les listes d’incidence des arcs sortants. Nous avons également utilisé les hashSet pour la gestion des littéraux dans la classe TwoSat:<br>

**ArrayList pour les composantes et les sommets :**<br>
* L'ArrayList offre un accès en temps constant O(1) aux éléments via leur indice, ce qui est particulièrement avantageux dans les parcours où nous avons besoin de consulter rapidement les éléments ou les stocker séquentiellement comme dans l’ordre des sommets sortants (ArrayList<Graph<Integer>.Edge> predecessors de la classe Search).<br>
* Le coût d’ajout en fin de liste est amorti en O(1), ce qui correspond bien aux besoins d'ajout lors du parcours des SCC(Strongly Connected Component).<br>

**LinkedList pour les listes d'incidence :**<br>
La LinkedList a été choisie ici car elle permet une insertion rapide O(1) lors de la construction du graphe. Lors du parcours des voisins, la LinkedList reste performante dans un contexte de parcours séquentiel. Nous avons utilisé une structure ArrayList<LinkedList<Edge>> dans la classe Graph, où chaque sommet est associé à une LinkedList de ses arcs sortants. Cela combine l'efficacité d'accès d'une ArrayList avec la souplesse d'ajout d'une LinkedList.<br>

**HashSet pour les littéraux :**<br>
La structure de donnée HashSet a été choisie pour stocker les littéraux dans la classe TwoSat car elle permet de ne pas avoir de doublons plus facilement.<br>

## 2. Preuves du fonctionnement du code :
L'algorithme implémenté fonctionne en plusieurs étapes, avec une logique bien fondée sur la théorie des graphes et la structure du problème 2-SAT :<br>
1. **Graphe des implications** : Le problème 2-SAT est d'abord transformé en un graphe des implications. Chaque clause de la forme l1​∨l2​ est traduite en deux implications : ¬l1⇒l2 et ¬l2⇒l1​. Cela permet de modéliser les dépendances logiques entre les variables et leurs négations sous forme de graphe orienté.<br>
2. **Algorithme de Kosaraju** : Nous utilisons l'algorithme de Kosaraju pour identifier les composantes fortement connexes (SCC) dans le graphe des implications. Kosaraju garantit que chaque SCC regroupe des littéraux qui se déduisent mutuellement. Si une SCC contient à la fois un littéral x et son opposé ¬x, alors la formule est insatisfaisable, car cela introduit une contradiction (un littéral qui implique son opposé).<br>
3. **Parcours en profondeur (DFS)** : Le DFS est utilisé pour parcourir le graphe, d'abord pour calculer l'ordre de sortie des sommets et ensuite pour explorer le graphe transposé. Cet ordre est crucial pour identifier les SCC dans le graphe d'implications transposé.<br>
Ces étapes permettent de déterminer efficacement si une formule 2-SAT est satisfaisable ou non. Si aucune composante fortement connexe ne contient un littéral et son opposé, alors la formule est satisfaisable.<br>

## 3. Problèmes rencontrés :
L'un des problèmes majeurs que nous avons rencontrés durant ce projet a été un bug dans la classe Parser, qui a causé de nombreuses heures de recherche. Le problème était lié à l'utilisation incorrecte de la méthode bufferedReader.readLine() dans la classe chargée de lire les formules 2-SAT depuis un fichier, ce qui avait pour effet de sauter une ligne sur deux dans le fichier d'entrée. Cela a conduit à des erreurs dans la construction des clauses et, par conséquent, dans la génération du graphe des implications. Ce bug était difficile à identifier car les erreurs résultantes se manifestent plus tard dans l'algorithme, notamment dans la construction incorrecte des arcs du graphe des implications, rendant les résultats incohérents. Nous avons passé plusieurs heures à chercher la source du problème avant de finalement isoler cette erreur. Une fois le bug identifié, nous avons corrigé le flux de lecture en nous assurant de ne pas sauter une ligne sur deux. Cela a résolu le problème et permis à l'algorithme de fonctionner correctement.<br>

## 4. Étude de complexité :
L'algorithme que nous avons implémenté pour résoudre le problème 2-SAT a une complexité en temps linéaire, soit O(n+m), où n est le nombre de sommets dans le graphe et m le nombre d’arc. En effet, dans l’algorithme de Kosaraju, la construction du graphe des implications prend un temps proportionnel au nombre de clauses O(m). Le premier DFS sur le graphe d'implications prend O(n+m), car chaque sommet et chaque arc est exploré une fois, la construction du graphe miroir se fait également en O(n+m).Enfin, le deuxième DFS effectué sur le graphe miroir prend O(n+m) comme le premier DFS. La complexité globale de l'algorithme est donc linéaire en nombre de clauses.<br>


# PARTIE 2 : README :
## Executer le code avec une ligne de commande :
La ligne de commande qui permet d'executer le code du projet dans le terminal est la suivante :<br>
**java -jar out/artifacts/2SAT/2SAT.jar chemin_fichier_à_lire**<br>
Exemple de chemin_fichier_à_lire : formulas/formula.txt<br>

## Explication du fonctionnement du code :
Nous vous présentons les classes utilisées dans notre projet ainsi que leur rôle, afin de démontrer que notre code fonctionne comme prévu :<br>
**Classe Parser** : a pour rôle de lire un fichier contenant une formule en 2-SAT et de construire le graphe des implications associé à cette formule. Le fichier d'entrée contient les clauses de la formule sous forme de littéraux, et la classe Parser les traduit en un graphe orienté, où chaque sommet représente un littéral ou sa négation, et les arcs représentent les implications entre ces littéraux.<br>

**Classe Edge** : représente un arc dans un graphe orienté.<br>

**Classe Graph** : est une classe générique qui représente un graphe orienté avec un ensemble de nœuds (sommets) et d'arêtes (arcs), où chaque arête relie deux sommets avec une étiquette optionnelle.<br>

**Classe ImplicationGraph** : étend la classe générique Graph<Integer>, qui représente un graphe orienté. Elle est spécifiquement conçue pour modéliser le graphe des implications utilisé dans les problèmes 2-SAT. Ce graphe est construit à partir des clauses de la formule logique, où chaque littéral est représenté par un sommet, et les implications entre les littéraux sont représentées par des arcs dirigés.<br>

**Classe Search** : est conçue pour effectuer une recherche en profondeur (Depth-First Search, DFS) sur un graphe orienté, représenté par la classe Graph. Elle permet d'explorer les sommets du graphe de manière itérative et récursive tout en enregistrant diverses informations sur chaque sommet visité, comme le temps d'entrée, le temps de sortie et les prédécesseurs.<br>

**Classe Kosaraju** : implémente l'algorithme de Kosaraju pour détecter les composantes fortement connexes (SCC) dans un graphe orienté, en particulier dans le contexte d'un graphe d'implications utilisé pour résoudre le problème 2-SAT.<br>

**Classe TwoSat** : est conçue pour vérifier la consistance des composantes fortement connexes dans le contexte du problème 2-SAT. Elle détermine si une formule 2-SAT est satisfaisante en s'assurant qu'aucune composante ne contient à la fois un littéral et son opposé.<br>



