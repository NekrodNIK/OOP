package sys.pro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/** AdjMatrixGraph. */
public class AdjMatrixGraph implements Graph {
    protected List<Optional<List<Integer>>> adj;
    protected int vertexesCount;

    public AdjMatrixGraph() {
        adj = new ArrayList<Optional<List<Integer>>>();
    }

    @Override
    public int newVertex() {
        for (int i = 0; i < adj.size(); i++) {
            if (adj.get(i).isPresent()) {
                adj.get(i).get().add(0);
            }
        }

        ArrayList<Integer> arr = new ArrayList<Integer>();

        for (int i = 0; i <= adj.size(); i++) {
            arr.add(0);
        }

        adj.add(Optional.of(arr));

        vertexesCount++;
        return adj.size() - 1;
    }

    @Override
    public void removeVertex(int index) {
        if (adj.get(index).isEmpty()) {
            return;
        }

        for (int i = 0; i < adj.size(); i++) {
            if (adj.get(i).isPresent()) {
                adj.get(i).get().set(index, 0);
            }
        }

        vertexesCount--;
    }

    @Override
    public void addDirectedEdge(int from, int to) {
        while (adj.size() <= Math.max(from, to)) {
            if (adj.size() - 1 == from && adj.size() - 1 == to) {
                newVertex();
            } else {
                for (int i = 0; i < adj.size(); i++) {
                    if (adj.get(i).isPresent()) {
                        adj.get(i).get().add(0);
                    }
                }
                adj.addLast(Optional.empty());
            }
        }

        if (adj.get(from).isEmpty()) {
            adj.set(from, Optional.of(new ArrayList<Integer>()));
            for (int i = 0; i < adj.size(); i++) {
                adj.get(from).get().add(0);
            }

            vertexesCount++;
        }

        if (adj.get(to).isEmpty()) {
            adj.set(to, Optional.of(new ArrayList<Integer>()));
            for (int i = 0; i < adj.size(); i++) {
                adj.get(to).get().add(0);
            }

            vertexesCount++;
        }

        adj.get(from).get().set(to, 1);
    }

    @Override
    public void removeDirectedEdge(int from, int to) {
        if (adj.get(from).isEmpty() || adj.get(to).isEmpty()) {
            return;
        }

        adj.get(from).get().set(to, 0);
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

        return getAllVertexes().filter((i) -> adj.get(index).get().get(i) == 1);
    }

    @Override
    public long vertexesCount() {
        return vertexesCount;
    }

    @Override
    public long edgesCount() {
        return -1;
    }

    @Override
    public long vertexWithMaxIndex() {
        return adj.size() - 1;
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
