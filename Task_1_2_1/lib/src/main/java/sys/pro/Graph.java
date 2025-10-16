package sys.pro;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/** Graph interface. */
public interface Graph {
  /*
   * Gets vertex count.
   * 
   * @returns count
   */
  public long vertexesCount();

  /*
   * Gets edges count.
   * 
   * @returns count
   */
  public long edgesCount();

  /*
   * Gets vertex with max index.
   * 
   * @returns index
   */
  public long vertexWithMaxIndex();

  /*
   * Creates new vertex and returns its vertex.
   * 
   * @returns index
   */
  public int newVertex();

  /*
   * Removes vertex.
   * 
   * @param index vertex index
   */
  public void removeVertex(int index);

  /*
   * Adds directed edge.
   * 
   * @param from 'from' vertex
   * 
   * @param to 'to' vertex
   */
  public void addDirectedEdge(int from, int to);

  /*
   * Removes directed edge.
   * 
   * @param from 'from' vertex
   * 
   * @param to 'to' vertex
   */
  public void removeDirectedEdge(int from, int to);

  /*
   * Gets all vertexes.
   * 
   * @returns result stream
   */
  public Stream<Integer> getAllVertexes();

  /*
   * Gets adjacent vertexes.
   * 
   * @param index vertex index
   * 
   * @returns result stream
   */
  public Stream<Integer> getAdjacentVertexes(int index);

  /*
   * Topological sort.
   * 
   * @returns result stream
   */
  public default Stream<Integer> topSort() {
    ArrayList<Integer> result = new ArrayList<Integer>();

    ArrayList<Boolean> visited = new ArrayList<Boolean>();
    for (int i = 0; i <= vertexWithMaxIndex(); i++) {
      visited.add(false);
    }

    getAllVertexes()
        .filter((v) -> !visited.get(v))
        .forEach((v) -> dfs(this, result, visited, v));

    return result.reversed().stream();
  }

  static void dfs(Graph graph, List<Integer> result, List<Boolean> visited, Integer v) {
    visited.set(v, true);
    graph.getAdjacentVertexes(v)
        .filter((u) -> !visited.get(u))
        .forEach((u) -> dfs(graph, result, visited, u));

    result.add(v);
  }

  /*
   * Reads graph from scanner.
   * 
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
