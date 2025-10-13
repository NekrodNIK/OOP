package sys.pro;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/** AdjListGraph. */
public class AdjListGraph implements Graph {
    protected ArrayList<ArrayList<Integer>> adj;
    protected int vertexesCount;

    public AdjListGraph() {
        adj = new ArrayList<ArrayList<Integer>>();
    }

    @Override
    public int newVertex() {
        adj.addLast(new ArrayList<Integer>());
        vertexesCount += 1;
        return adj.size() - 1;
    }

    @Override
    public void removeVertex(int index) {
        for (int i = 0; i < adj.size(); i++) {
            if (!adj.get(i).isEmpty()) {
                adj.get(i).remove(Integer.valueOf(index));
            }
        }

        adj.get(index).clear();
        adj.get(index).add(-1);
        vertexesCount -= 1;
    }

    @Override
    public void addDirectedEdge(int from, int to) {
        while (adj.size() <= Math.max(from, to)) {
            newVertex();
        }

        if (adj.get(from).size() > 0 && adj.get(from).get(0) == -1) {
            adj.get(from).clear();
            vertexesCount += 1;
        }

        if (adj.get(to).size() > 0 && adj.get(to).get(0) == -1) {
            adj.get(to).clear();
            vertexesCount += 1;
        }

        adj.get(from).add(to);
    }

    @Override
    public void removeDirectedEdge(int from, int to) {
        adj.get(from).remove(Integer.valueOf(to));
    }

    @Override
    public Stream<Integer> getAllVertexes() {
        return IntStream.range(0, adj.size())
                .mapToObj((item) -> Integer.valueOf(item))
                .filter((index) -> adj.get(index).size() == 0 || adj.get(index).get(0) != -1);
    }

    @Override
    public Stream<Integer> getAdjacentVertexes(int index) {
        if (adj.get(index).size() > 0 && adj.get(index).get(0) == -1) {
            return Stream.empty();
        }

        return adj.get(index).stream();
    }

    @Override
    public long vertexesCount() {
        return vertexesCount;
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
        for (int i = 0; i < adj.size(); i++) {
            visited.add(false);
        }

        getAllVertexes().filter((v) -> !visited.get(v)).forEach((v) -> dfs(result, visited, v));
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
                        (from) ->
                                getAdjacentVertexes(from)
                                        .map((to) -> "%d %d\n".formatted(from, to)))
                .reduce("", (x, y) -> x + y);
    }
}
