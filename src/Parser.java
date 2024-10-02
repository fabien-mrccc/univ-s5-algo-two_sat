import java.io.BufferedReader;
import java.io.FileReader;

public class Parser {

    /**
     * Builds the implication graph related to the file given.
     * @param filename the file to read
     */
    public ImplicationGraph buildImplicationGraph(String filename) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            bufferedReader.readLine();
            String[] cardinalLine = bufferedReader.readLine().split(" ");
            int size = 2 * Integer.parseInt(cardinalLine[2]);
            ImplicationGraph implicationGraph = new ImplicationGraph(size);
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
    private void fillGraph(BufferedReader bufferedReader,ImplicationGraph implicationGraph) throws Exception {
        while ((bufferedReader.readLine()) != null) {
            String[] clauseLine = bufferedReader.readLine().split(" ");
            implicationGraph.addEdgesFromClause(clauseLine);
        }
    }
}
