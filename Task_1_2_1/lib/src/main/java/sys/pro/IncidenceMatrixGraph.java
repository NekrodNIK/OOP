package sys.pro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/** IncidenceMatrixGraph. */
public class IncidenceMatrixGraph implements Graph {
    protected List<Optional<List<Integer>>> inc;
    protected int vertexesCount;
    protected int edgesCount;

    public IncidenceMatrixGraph() {
        inc = new ArrayList<Optional<List<Integer>>>();
    }

    @Override
    public int newVertex() {
        ArrayList<Integer> arr = new ArrayList<Integer>();

        for (int i = 0; i < edgesCount; i++) {
            arr.add(0);
        }

        inc.add(Optional.of(arr));
        vertexesCount += 1;
        return inc.size() - 1;
    }

    @Override
    public void removeVertex(int index) {
        if (inc.get(index).isEmpty()) {
            return;
        }

        List<Integer> edgesToRemove = new ArrayList<>();
        for (int i = 0; i < edgesCount; i++) {
            if (inc.get(index).get().get(i) != 0) {
                edgesToRemove.add(i);
            }
        }

        for (int i : edgesToRemove.reversed()) {
            for (int j = 0; j < inc.size(); j++) {
                if (inc.get(j).isPresent()) {
                    inc.get(j).get().remove(i);
                }
            }
        }

        inc.set(index, Optional.empty());
        vertexesCount -= 1;
        edgesCount -= edgesToRemove.size();
    }

    @Override
    public void addDirectedEdge(int from, int to) {
        while (inc.size() <= Math.max(from, to)) {
            inc.addLast(Optional.empty());
        }

        if (inc.get(from).isEmpty()) {
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (int i = 0; i < edgesCount; i++) {
                arr.add(0);
            }
            inc.set(from, Optional.of(arr));
            vertexesCount += 1;
        }

        if (inc.get(to).isEmpty()) {
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (int i = 0; i < edgesCount; i++) {
                arr.add(0);
            }
            inc.set(to, Optional.of(arr));
            vertexesCount += 1;
        }

        for (int i = 0; i < inc.size(); i++) {
            if (inc.get(i).isPresent()) {
                inc.get(i).get().add(0);
            }
        }

        inc.get(from).get().set(edgesCount, 1);
        inc.get(to).get().set(edgesCount, -1);

        edgesCount += 1;
    }

    @Override
    public void removeDirectedEdge(int from, int to) {
        if (inc.get(from).isEmpty() || inc.get(to).isEmpty()) {
            return;
        }

        IntStream.range(0, edgesCount)
                .forEach(
                        (i) ->
                                inc.get(from)
                                        .ifPresent(
                                                (u) ->
                                                        inc.get(to)
                                                                .ifPresent(
                                                                        (v) -> {
                                                                            if (u.get(i) == 1
                                                                                    && v.get(i)
                                                                                            == -1) {
                                                                                inc.set(
                                                                                        from,
                                                                                        Optional
                                                                                                .empty());
                                                                                inc.set(
                                                                                        to,
                                                                                        Optional
                                                                                                .empty());
                                                                            }
                                                                        })));
        edgesCount -= 1;
    }

    @Override
    public Stream<Integer> getAllVertexes() {
        return IntStream.range(0, inc.size())
                .filter((index) -> inc.get(index).isPresent())
                .mapToObj((item) -> Integer.valueOf(item));
    }

    @Override
    public Stream<Integer> getAdjacentVertexes(int index) {
        if (inc.get(index).isEmpty()) {
            return Stream.empty();
        }

        List<Integer> adjacent = new ArrayList<>();

        for (int edge = 0; edge < edgesCount; edge++) {
            if (inc.get(index).isPresent() && inc.get(index).get().get(edge) == 1) {
                for (int vertex = 0; vertex < inc.size(); vertex++) {
                    if (inc.get(vertex).isPresent()
                            && vertex != index
                            && inc.get(vertex).get().get(edge) == -1) {
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

    public long edgesCount() {
        return edgesCount;
    }

    @Override
    public long vertexWithMaxIndex() {
        return inc.size() - 1;
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
