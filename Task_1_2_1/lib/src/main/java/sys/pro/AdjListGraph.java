package sys.pro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/** AdjListGraph. */
public class AdjListGraph implements Graph {
  protected List<Optional<List<Integer>>> adj;
  protected int vertexesCount;

  public AdjListGraph() {
    adj = new ArrayList<Optional<List<Integer>>>();
  }

  @Override
  public int newVertex() {
    adj.addLast(Optional.of(new ArrayList<Integer>()));
    vertexesCount += 1;
    return adj.size() - 1;
  }

  @Override
  public void removeVertex(int index) {
    for (int i = 0; i < adj.size(); i++) {
      if (!adj.get(i).isEmpty()) {
        adj.get(i).get().remove(Integer.valueOf(index));
      }
    }

    adj.set(index, Optional.empty());
    vertexesCount -= 1;
  }

  @Override
  public void addDirectedEdge(int from, int to) {
    while (adj.size() <= Math.max(from, to)) {
      if (adj.size() - 1 == from && adj.size() - 1 == to) {
        newVertex();
      } else {
        adj.addLast(Optional.empty());
      }
    }

    if (adj.get(from).isEmpty()) {
      adj.set(from, Optional.of(new ArrayList<Integer>()));
      vertexesCount += 1;
    }

    if (adj.get(to).isEmpty()) {
      adj.set(from, Optional.of(new ArrayList<Integer>()));
      vertexesCount += 1;
    }

    adj.get(from).get().add(to);
  }

  @Override
  public void removeDirectedEdge(int from, int to) {
    if (adj.get(from).isEmpty()) {
      return;
    }

    adj.get(from).get().remove(Integer.valueOf(to));
  }

  @Override
  public Stream<Integer> getAllVertexes() {
    return IntStream.range(0, adj.size())
        .filter((index) -> adj.get(index).isPresent())
        .mapToObj((item) -> Integer.valueOf(item));
  }

  @Override
  public Stream<Integer> getAdjacentVertexes(int index) {
    if (adj.get(index).isEmpty()) {
      return Stream.empty();
    }

    return adj.get(index).get().stream();
  }

  @Override
  public long vertexesCount() {
    return vertexesCount;
  }

  private void dfs(List<Integer> result, List<Boolean> visited, Integer v) {
    visited.set(v, true);
    for (int u : adj.get(v).orElse(new ArrayList<Integer>())) {
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
      if (!this.getAdjacentVertexes(v)
          .toList()
          .equals(graph.getAdjacentVertexes(v).toList())) {
        return false;
      }
    }

    return true;
  }

  @Override
  public String toString() {
    return getAllVertexes()
        .flatMap(
            (from) -> getAdjacentVertexes(from)
                .map((to) -> "%d %d\n".formatted(from, to)))
        .reduce("", (x, y) -> x + y);
  }
}
