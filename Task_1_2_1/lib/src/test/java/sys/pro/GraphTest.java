package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/** GraphTest. */
public abstract class GraphTest {
    abstract Graph createGraph();

    private static Stream<Arguments> getImplementationsPairs() {
        ArrayList<Class<? extends Graph>> arr = new ArrayList<Class<? extends Graph>>();
        arr.add(AdjListGraph.class);
        arr.add(AdjMatrixGraph.class);
        arr.add(IncidenceMatrixGraph.class);

        ArrayList<Arguments> result = new ArrayList<Arguments>();

        for (Class<? extends Graph> cls1 : arr) {
            for (Class<? extends Graph> cls2 : arr) {
                result.add(Arguments.of(cls1, cls2));
            }
        }
        return result.stream();
    }

    @Test
    void testAddDirectedEdge() {
        Graph graph = createGraph();
        
        graph.addDirectedEdge(3, 10);
        assertEquals(10, graph.getAdjacentVertexes(3).findFirst().get());

        graph.removeVertex(3);
        graph.removeVertex(10);
        
        graph.addDirectedEdge(1, 4);
        assertEquals(4, graph.getAdjacentVertexes(1).findFirst().get());
    }

    @Test
    void testRemoveDirectedEdge() {
        Graph graph = createGraph();
        Integer from = graph.newVertex();
        Integer to = graph.newVertex();
        graph.addDirectedEdge(from, to);
        assertEquals(to, graph.getAdjacentVertexes(from).findFirst().get());

        graph.removeDirectedEdge(from, to);
        assertTrue(graph.getAdjacentVertexes(from).findFirst().isEmpty());
    }

    @Test
    void testRemoveVertex() {
        Graph graph = createGraph();
        Integer from = graph.newVertex();
        Integer to = graph.newVertex();
        graph.addDirectedEdge(from, to);
        assertEquals(to, graph.getAdjacentVertexes(from).findFirst().get());

        graph.removeVertex(from);
        assertTrue(graph.getAdjacentVertexes(to).findFirst().isEmpty());
    }

    @Test
    void testTopSortSimple() {
        Graph graph = createGraph();
        ArrayList<Integer> expected = new ArrayList<Integer>();

        expected.add(graph.newVertex());
        expected.add(graph.newVertex());
        graph.addDirectedEdge(expected.get(expected.size() - 2), expected.getLast());

        expected.add(graph.newVertex());
        graph.addDirectedEdge(expected.get(expected.size() - 2), expected.getLast());

        expected.add(graph.newVertex());
        graph.addDirectedEdge(expected.get(expected.size() - 2), expected.getLast());

        expected.add(graph.newVertex());
        graph.addDirectedEdge(expected.get(expected.size() - 2), expected.getLast());

        expected.add(graph.newVertex());
        graph.addDirectedEdge(expected.get(expected.size() - 2), expected.getLast());

        assertEquals(expected, graph.topSort().toList());
    }

    @Test
    void testTopSort() {
        Graph graph = createGraph();

        int v0 = graph.newVertex();
        int v1 = graph.newVertex();
        int v2 = graph.newVertex();
        int v3 = graph.newVertex();
        int v4 = graph.newVertex();
        int v5 = graph.newVertex();
        int v6 = graph.newVertex();
        int v7 = graph.newVertex();

        graph.addDirectedEdge(v0, v1);
        graph.addDirectedEdge(v1, v2);
        graph.addDirectedEdge(v2, v3);
        graph.addDirectedEdge(v0, v4);
        graph.addDirectedEdge(v4, v5);
        graph.addDirectedEdge(v5, v3);
        graph.addDirectedEdge(v1, v6);
        graph.addDirectedEdge(v4, v6);
        graph.addDirectedEdge(v6, v7);
        graph.addDirectedEdge(v7, v3);

        int v8 = graph.newVertex();

        List<Integer> result = graph.topSort().toList();

        assertEquals(9, graph.vertexesCount());

        assertTrue(result.contains(v8));
        assertTrue(result.indexOf(v0) < result.indexOf(v1));
        assertTrue(result.indexOf(v1) < result.indexOf(v2));
        assertTrue(result.indexOf(v2) < result.indexOf(v3));
        assertTrue(result.indexOf(v0) < result.indexOf(v4));
        assertTrue(result.indexOf(v4) < result.indexOf(v5));
        assertTrue(result.indexOf(v5) < result.indexOf(v3));
        assertTrue(result.indexOf(v1) < result.indexOf(v6));
        assertTrue(result.indexOf(v4) < result.indexOf(v6));
        assertTrue(result.indexOf(v6) < result.indexOf(v7));
        assertTrue(result.indexOf(v7) < result.indexOf(v3));
        assertEquals(9, result.size());
        assertEquals(9, result.stream().distinct().count());
    }

    @Test
    void testReadFile() {
        Graph graph = createGraph();

        Scanner scanner =
                new Scanner(
                        """
                        0 1
                        2 3
                        6 10
                        7 8
                        10 11
                        """);

        graph.readFromScanner(scanner);

        assertEquals(1, graph.getAdjacentVertexes(0).findFirst().get());
        assertEquals(3, graph.getAdjacentVertexes(2).findFirst().get());
        assertEquals(10, graph.getAdjacentVertexes(6).findFirst().get());
        assertEquals(11, graph.getAdjacentVertexes(10).findFirst().get());
        assertEquals(8, graph.getAdjacentVertexes(7).findFirst().get());
    }

    @ParameterizedTest
    @MethodSource("getImplementationsPairs")
    void testEquals(Class<? extends Graph> cls1, Class<? extends Graph> cls2) {
        Graph graph1;
        Graph graph2;

        try {
            graph1 = cls1.getDeclaredConstructor().newInstance();
            graph2 = cls1.getDeclaredConstructor().newInstance();
        } catch (InstantiationException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            fail();
            return;
        }

        graph1.addDirectedEdge(0, 1);
        graph2.addDirectedEdge(0, 1);

        graph1.addDirectedEdge(3, 4);
        graph2.addDirectedEdge(3, 4);

        graph1.addDirectedEdge(5, 1);
        graph2.addDirectedEdge(5, 1);

        graph1.addDirectedEdge(10, 2);
        graph2.addDirectedEdge(10, 2);

        graph1.addDirectedEdge(2, 1);
        graph2.addDirectedEdge(2, 1);

        graph1.addDirectedEdge(2, 3);
        graph2.addDirectedEdge(2, 3);

        graph1.addDirectedEdge(5, 10);
        graph2.addDirectedEdge(5, 10);

        assertEquals(graph1, graph2);
    }

    @Test
    void testToString() {
        Graph graph = createGraph();

        graph.addDirectedEdge(0, 1);
        graph.addDirectedEdge(3, 4);
        graph.addDirectedEdge(5, 1);
        graph.addDirectedEdge(10, 2);
        graph.addDirectedEdge(2, 1);
        graph.addDirectedEdge(2, 3);
        graph.addDirectedEdge(5, 10);

        assertTrue(
                graph.toString().contains("0 1")
                        && graph.toString().contains("3 4")
                        && graph.toString().contains("5 1")
                        && graph.toString().contains("10 2")
                        && graph.toString().contains("2 1")
                        && graph.toString().contains("2 3")
                        && graph.toString().contains("5 10"));
    }

    @Test
    void testAddVertexFromDeletedVertex() {
        Graph graph = createGraph();

        int v0 = graph.newVertex();
        int v1 = graph.newVertex();

        graph.removeVertex(v0);
        graph.addDirectedEdge(v0, v1);

        assertEquals(v1, graph.getAdjacentVertexes(v0).findFirst().get());
    }

    @Test
    void testAddVertexToDeletedVertex() {
        Graph graph = createGraph();

        int v0 = graph.newVertex();
        int v1 = graph.newVertex();

        graph.removeVertex(v1);
        graph.addDirectedEdge(v0, v1);

        assertEquals(v1, graph.getAdjacentVertexes(v0).findFirst().get());
    }
}
