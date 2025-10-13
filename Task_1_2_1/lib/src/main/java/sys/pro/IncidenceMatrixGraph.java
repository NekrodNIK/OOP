package sys.pro;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IncidenceMatrixGraph implements Graph {
    protected ArrayList<ArrayList<Integer>> inc;
    protected int vertexesCount;
    protected int edgesCount;

    public IncidenceMatrixGraph() {
        inc = new ArrayList<ArrayList<Integer>>();
    }

    @Override
    public int newVertex() {
        ArrayList<Integer> arr = new ArrayList<Integer>();

        for (int i = 0; i < edgesCount; i++) {
            arr.add(0);
        }

        inc.add(arr);
        vertexesCount += 1;
        return inc.size() - 1;
    }

    @Override
    public void removeVertex(int index) {
        for (int i = 0; i < edgesCount; i++) {
            inc.get(index).set(i, -2);
            edgesCount -= 2;
        }
        vertexesCount -= 1;
    }

    @Override
    public void addDirectedEdge(int from, int to) {
        while (inc.size() <= Math.max(from, to)) {
            newVertex();
        }

        for (int i = 0; i < inc.size(); i++) {
            if (inc.get(i).size() < edgesCount + 1) {
                inc.get(i).add(0);
            }
        }

        inc.get(from).set(edgesCount, 1);
        inc.get(to).set(edgesCount, -1);

        edgesCount += 1;
    }

    @Override
    public void removeDirectedEdge(int from, int to) {
        for (int i = 0; i < edgesCount; i++) {
            if (inc.get(from).get(i) == 1 && inc.get(to).get(i) == -1) {
                inc.get(from).set(i, -2);
                inc.get(to).set(i, -2);
            }
        }
        edgesCount -= 1;
    }

    @Override
    public Stream<Integer> getAllVertexes() {
        return IntStream.range(0, inc.size())
                .filter((index) -> inc.get(index).get(0) != -2)
                .mapToObj((item) -> Integer.valueOf(item));
    }

    @Override
    public Stream<Integer> getAdjacentVertexes(int index) {
        List<Integer> adjacent = new ArrayList<>();

        for (int edge = 0; edge < edgesCount; edge++) {
            if (inc.get(index).get(edge) == 1) {
                for (int vertex = 0; vertex < inc.size(); vertex++) {
                    if (vertex != index && inc.get(vertex).get(edge) == -1) {
                        adjacent.add(vertex);
                        break;
                    }
                }
            }
        }

        return adjacent.stream();
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
        for (int i = 0; i < inc.size(); i++) {
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
