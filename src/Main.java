import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {

        buildImplicationGraph(args);

        /*Parser parser = new Parser();
        Graph<String> graph = parser.parse(filename);

        Kosaraju k = new Kosaraju(graph);
        int[] composantes = k.sccs();

        if (TwoSat.checkConsistency(composantes)) {
            System.out.println("Formula " + filename + ": satisfiable");
            exit(0);
        } else {
            System.out.println("Formula " + filename + ": unsatisfiable");
            exit(-1);
        }*/
        exit(0);
    }

    /**
     * Builds the graph related to the file given.
     * @param args
     */
    private static void buildImplicationGraph(String[] args) {
        String filename = "formulas/formula.txt";
        if (0 < args.length) {
            filename = args[0];
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            bufferedReader.readLine();
            String[] cardinalLine = bufferedReader.readLine().split(" ");
            int size = 2* Integer.parseInt(cardinalLine[2]);
            ImplicationGraph implicationGraph = initializeGraph(size);
            fillGraph(bufferedReader, implicationGraph);
            System.out.println(implicationGraph);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Initialize the graph with a length equal to size.
     * @param size the necessary length to stock all the edges
     * @return the graph
     */
    private static ImplicationGraph initializeGraph(int size){
        return new ImplicationGraph(size);
    }

    /**
     * Fills the graph with the edges appearing on the file.
     * @param bufferedReader the bufferedReader to read the file.
     * @param implicationGraph the graph we want to fill.
     * @throws Exception
     */
    private static void fillGraph(BufferedReader bufferedReader,ImplicationGraph implicationGraph) throws Exception {
        while ((bufferedReader.readLine()) != null) {
            String[] clauseLine = bufferedReader.readLine().split(" ");
            changeClauseIntoEdges(implicationGraph, clauseLine);
        }
    }

    private static void changeClauseIntoEdges(ImplicationGraph implicationGraphs, String[] clauseLine) throws Exception {
        implicationGraphs.addEdge(getVertexIndex(- Integer.parseInt(clauseLine[0]), implicationGraphs.getCardinal()),
                getVertexIndex(Integer.parseInt(clauseLine[1]), implicationGraphs.getCardinal()),clauseLine[2]);
        implicationGraphs.addEdge(getVertexIndex(- Integer.parseInt(clauseLine[1]), implicationGraphs.getCardinal()),
                getVertexIndex(Integer.parseInt(clauseLine[0]), implicationGraphs.getCardinal()),clauseLine[2]);
    }

    /**
     * Return the index of the vertex we gave in parameters.
     * @param vertex the vertex we want the index of.
     * @param size the size of the graphe
     * @return the index of the vertex
     */
    private static int getVertexIndex(int vertex, int size){
        if(vertex < 0){
            return vertex + size/2;
        }
        return vertex + size/2 -1;
    }
}
