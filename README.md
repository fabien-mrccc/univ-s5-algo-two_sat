# 2-SAT Problem

## Useful Information
- Project Members: FANANI Amina, MARCUCCINI Fabien 
- Lab Supervisor: ROLLAND Marius
- Subject: "Algorithms 2" in the third year of Computer Science at Aix-Marseille University
- SDK Version: openjdk-22 | Amazon Corretto 22.0.2 - aarch64

## PART 1: README

### Project Objective
- The project statement is provided in the file **/pdf/tp1.pdf** *(french version)*. <br>
- The general goal is to automate the satisfiability checking of propositional formulas with clauses of length 2 *(see technical term definitions in the statement)*.

### Command Line to Use the Project
- The command line to execute the project code in the terminal is as follows:<br>
**java -jar out/artifacts/2SAT/2SAT.jar <file_path_formula>**<br>
- Example of use: *java -jar out/artifacts/2SAT/2SAT.jar formulas/formula.txt*<br>

### Description of Class Roles

- **ComponentsIdentifier:** Identifies strongly connected components of a graph in separate structures using depth-first search.<br>

- **Edge:** Represents an edge in a directed graph.<br>

- **Graph:** Represents a directed graph via an incidence list, allowing the addition of edges and the creation of a mirror graph.<br>

- **GraphSearch:** Performs iterative depth-first search on a directed graph, recording predecessors and entry/exit times of nodes.<br>

- **ImplicationGraph:** Extends the Graph class to represent the implications of a 2-SAT problem, adding edges based on logical clauses and modeling each literal as a vertex.<br>

- **Kosaraju:** Identifies strongly connected components of an implication graph using Kosaraju's algorithm.<br>

- **Main:** Executes the 2-SAT algorithm by constructing the implication graph from a formula in a formatted file, then identifying strongly connected components and checking the satisfiability of the formula.<br>

- **Parser:** Constructs the implication graph from a formatted 2-SAT formula specified in a file.<br>

- **TwoSat:** Checks the consistency of a 2-SAT system by ensuring that no component contains both a literal and its negation.

## PART 2: REPORT

### 1. Chosen Data Structures
To solve the 2-SAT problem, we used ArrayLists for vertices and strongly connected components, while maintaining LinkedLists in the Graph class for incidence lists, and HashSets in TwoSat for literals:<br>

- **ArrayList:** O(1) access for vertices and components, and O(1) addition, suitable for traversing strongly connected components.<br>

- **LinkedList:** Allows quick O(1) insertion when constructing the graph, while being efficient for sequential traversals.<br><br>
*We used an ArrayList<LinkedList<Edge>> structure in the Graph class, where each vertex is associated with a LinkedList of its outgoing edges. This combines the access efficiency of an ArrayList with the flexibility of adding a LinkedList.*<br>

- **HashSet:** Avoids duplicates when storing literals in the TwoSat class.<br>

### 2. Proof of Functionality of the Solution
The implemented algorithm works in several steps, with sound logic based on graph theory and the structure of the 2-SAT problem:<br>
1. **Construction of the Implication Graph:** Transformation of each clause l1∨l2 into two implications ¬l1⇒l2 and ¬l2⇒l1, modeling the logical dependencies of the formula to be processed.<br><br>
2. **Application of Kosaraju's Algorithm:** Identification of edges belonging to strongly connected components in the implication graph via an initial depth-first search of the graph, followed by a second traversal on the transposed graph respecting the exit times from the first traversal.<br><br>
3. **Grouping Edges of Strongly Connected Components:** Depth-first traversal of the list of edges identified by Kosaraju to distinctly reconstruct the subgraphs representing the strongly connected components.<br><br>
4. **Satisfiability Check:** Analysis of the presence of strongly connected components containing both a literal and its negation, indicating the formula's unsatisfiability *(a literal cannot imply its negation)*; otherwise, the formula is satisfiable.<br>

### 3. Problems Encountered

- #### Reading Clauses from 2-SAT Formulas in the Parser Class.
  - **Reason:** Incorrect use of the method bufferedReader.readLine(), causing the skipping of every other line when reading the file.<br>
  - **Consequences:** Error in the construction of clauses and the implication graph, leading to inconsistent results in the 2-SAT algorithm.<br>
  - **Solution:** Identification of the error after several hours of investigation and correction of the reading flow.<br>
  - **Result:** The construction of the implication graph now works correctly, as do the algorithms that use it.<br><br>

- #### Distinct Identification of Strongly Connected Components from the Kosaraju Class.
  - **Reason:** The Kosaraju class returns a simple list of edges, making it impossible to identify which edges belong to the same strongly connected component.
  - **Consequence:** Difficulty in reusing the graph traversal algorithm, as we cannot reconstruct Graph objects with just a list of arbitrary edges.
  - **Solution:** Writing a new class, ComponentsIdentifier, that considers the specific traversal needed to identify different strongly connected components.
  - **Result:** Use of an ArrayList<ArrayList<Integer>> structure allowing for distinct grouping between each literal belonging to the same strongly connected component.

### 4. Complexity Study
* **Algorithm:** Solving the 2-SAT problem with Kosaraju's algorithm.<br>
* **Complexity:** Linear, O(n+m), where n is the number of vertices and m is the number of edges.<br>
* **Steps:** Construction of the implication graph in O(m), first DFS in O(n+m), construction of the mirror graph in O(n+m), and the second DFS performed on the mirror graph, also in O(n+m).<br>
* **Result:** The overall complexity of the algorithm is linear with respect to the number of clauses.<br>
