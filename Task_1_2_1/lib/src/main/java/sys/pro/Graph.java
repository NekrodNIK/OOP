package sys.pro;

import java.util.Scanner;
import java.util.stream.Stream;

/** Graph interface. */
public interface Graph {
    /*
     * Gets vertex count.
     * @param index vertex index
     * @returns count
     */
    public long vertexesCount();

    /*
     * Creates new vertex and returns its vertex.
     * @param index vertex index
     * @returns index
     */
    public int newVertex();

    /*
     * Removes vertex.
     * @param index vertex index
     */
    public void removeVertex(int index);

    /*
     * Adds directed edge.
     * @param from 'from' vertex
     * @param to 'to' vertex
     */
    public void addDirectedEdge(int from, int to);

    /*
     * Removes directed edge.
     * @param from 'from' vertex
     * @param to 'to' vertex
     */
    public void removeDirectedEdge(int from, int to);

    /*
     * Gets all vertexes.
     * @returns result stream
     */
    public Stream<Integer> getAllVertexes();

    /*
     * Gets adjacent vertexes.
     * @returns result stream
     */
    public Stream<Integer> getAdjacentVertexes(int index);

    /*
     * Topological sort.
     * @returns result stream
     */
    public Stream<Integer> topSort();

    /*
     * Reads graph from scanner.
     * @param Scanner scanner
     */
    public default void readFromScanner(Scanner scanner) {
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().strip().split(" ");
            Integer from = Integer.valueOf(line[0]);
            Integer to = Integer.valueOf(line[1]);

            addDirectedEdge(from, to);
        }

        scanner.close();
    }
}
