package sys.pro;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.collect.Streams;

/**
 * AdjGraph
 */
public class AdjGraph implements Graph {
  protected ArrayList<ArrayList<Integer>> adj;

  public AdjGraph() {
    adj = new ArrayList<ArrayList<Integer>>();
  }

  @Override
  public int newVertex() {
    adj.addLast(new ArrayList<Integer>());
    return adj.size() - 1;
  }

  @Override
  public void removeVertex(int index) {
    for (Integer i : adj.get(index)) {
      adj.get(i).remove(Integer.valueOf(index));
    }

    adj.get(index).clear();
  }

  @Override
  public void addDirectedEdge(int from, int to) {
    while (size() <= from) {
      newVertex();
    }

    adj.get(from).add(to);
  }

  @Override
  public void removeDirectedEdge(int from, int to) {
    adj.get(from).remove(Integer.valueOf(to));
  }

  @Override
  public Stream<Integer> getAllVertexes() {
    return IntStream.range(0, size()).mapToObj((item) -> Integer.valueOf(item));
  }

  @Override
  public Stream<Integer> getAdjacentVertexes(int index) {
    return adj.get(index).stream();
  }

  @Override
  public int size() {
    return adj.size();
  }

  private void dfs(List<Integer> result, List<Boolean> visited, Integer v) {
    visited.set(v, true);
    for (int u : adj.get(v)) {
      if (!visited.get(u)) {
        dfs(result, visited, u);
      }
    }

    result.add(v);
  }

  @Override
  public Stream<Integer> topSort() {
    ArrayList<Integer> result = new ArrayList<Integer>();

    ArrayList<Boolean> visited = new ArrayList<Boolean>();
    for (int i = 0; i < size(); i++) {
      visited.add(false);
    }

    for (int v = 0; v < size(); v++) {
      if (!visited.get(v)) {
        dfs(result, visited, v);
      }
    }

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
