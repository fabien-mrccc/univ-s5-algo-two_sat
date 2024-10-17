import java.io.BufferedReader;
import java.io.FileReader;

public class Parser {

    /**
     * Builds the implication graph related to the file given.
     * @param filename the path of the file to read
     */
    public static ImplicationGraph buildImplicationGraph(String filename) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            bufferedReader.readLine();
            String[] cardinalLine = bufferedReader.readLine().split(" ");
            int graphSize = 2 * Integer.parseInt(cardinalLine[2]);
            ImplicationGraph implicationGraph = new ImplicationGraph(graphSize);
            fillGraph(bufferedReader, implicationGraph);
            return implicationGraph;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Fills the graph with the edges appearing on the file.
     * @param bufferedReader the bufferedReader to read the file.
     * @param implicationGraph the graph we want to fill.
     * @throws Exception bufferedReader exception
     */
    private static void fillGraph(BufferedReader bufferedReader,ImplicationGraph implicationGraph) throws Exception {
        String line = bufferedReader.readLine();
        while (line != null) {
            String[] clauseLine = line.split(" ");
            implicationGraph.addEdgesFromClause(clauseLine);
            line = bufferedReader.readLine();
        }
    }
}
