
package sys.pro;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * AdjMatrixGraph
 */
public class AdjMatrixGraph implements Graph {
  protected ArrayList<ArrayList<Integer>> adj;
  protected int vertexesCount;

  public AdjMatrixGraph() {
    adj = new ArrayList<ArrayList<Integer>>();
  }

  @Override
  public int newVertex() {
    for (int i = 0; i < adj.size(); i++) {
      adj.get(i).add(0);
    }

    ArrayList<Integer> arr = new ArrayList<Integer>();

    for (int i = 0; i <= adj.size(); i++) {
      arr.add(0);
    }

    adj.add(arr);
    
    vertexesCount += 1;
    return adj.size() - 1;
  }

  @Override
  public void removeVertex(int index) {
    for (int i = 0; i < adj.size(); i++) {
      adj.get(i).set(index, 0);
    }

    for (int i = 0; i < adj.size(); i++) {
      adj.get(index).set(i, -1);
    }

    vertexesCount -= 1;
  }

  @Override
  public void addDirectedEdge(int from, int to) {
    while (adj.size() <= Math.max(from, to)) {
      newVertex();
    }

    if (adj.size() > 0 && adj.get(from).get(0) == -1) {
      for (int i = 0; i < adj.size(); i++) {
        adj.get(from).set(i, 0);
      }
    }

    if (adj.size() > 0 && adj.get(to).get(0) == -1) {
      for (int i = 0; i < adj.size(); i++) {
        adj.get(to).set(i, 0);
      }
    }

    adj.get(from).set(to, 1);
  }

  @Override
  public void removeDirectedEdge(int from, int to) {
    adj.get(from).set(to, 0);
  }

  @Override
  public Stream<Integer> getAllVertexes() {
    return IntStream.range(0, adj.size())
        .filter((index) -> adj.get(index).get(0) != -1)
        .mapToObj((item) -> Integer.valueOf(item));
  }

  @Override
  public Stream<Integer> getAdjacentVertexes(int index) {
    return IntStream.range(0, adj.size())
        .filter((i) -> adj.get(index).get(i) == 1)
        .mapToObj((item) -> Integer.valueOf(item));
  }

  @Override
  public long vertexesCount() {
    return vertexesCount;
  }

  private void dfs(List<Integer> result, List<Boolean> visited, Integer v) {
    visited.set(v, true);
    getAdjacentVertexes(v)
        .filter((u) -> !visited.get(u))
        .forEach((u) -> dfs(result, visited, u));

    result.add(v);
  }

  @Override
  public Stream<Integer> topSort() {
    ArrayList<Integer> result = new ArrayList<Integer>();

    ArrayList<Boolean> visited = new ArrayList<Boolean>();
    for (int i = 0; i < adj.size(); i++) {
      visited.add(false);
    }

    getAllVertexes()
        .filter((v) -> !visited.get(v))
        .forEach((v) -> dfs(result, visited, v));

    return result.reversed().stream();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Graph)) {
      return false;
    }

    Graph graph = (Graph) obj;
    List<Integer> vertexes = this.getAllVertexes().toList();

    if (!vertexes.equals(graph.getAllVertexes().toList())) {
      return false;
    }

    for (Integer v : vertexes) {
      if (!this.getAdjacentVertexes(v).toList()
          .equals(graph.getAdjacentVertexes(v).toList())) {
        return false;
      }
    }

    return true;
  }

  @Override
  public String toString() {
    return getAllVertexes()
        .flatMap((from) -> getAdjacentVertexes(from)
            .map((to) -> "%d %d\n"
                .formatted(from, to)))
        .reduce("", (x, y) -> x + y);
  }
}
