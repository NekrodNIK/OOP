package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class IncidenceMatrixGraphTest extends GraphTest {
    @Override
    Graph createGraph() {
        return new IncidenceMatrixGraph();
    }

    @Test
    void testEdgesCount() {
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph();

        graph.addDirectedEdge(0, 1);
        graph.addDirectedEdge(1, 5);
        graph.addDirectedEdge(5, 11);
        graph.addDirectedEdge(1, 2);
        graph.addDirectedEdge(2, 10);
        graph.addDirectedEdge(10, 50);
        graph.addDirectedEdge(50, 0);
        assertEquals(7, graph.edgesCount());

        graph.removeVertex(10);
        assertEquals(5, graph.edgesCount());

        graph.removeVertex(2);
        assertEquals(4, graph.edgesCount());

        graph.removeVertex(0);
        assertEquals(2, graph.edgesCount());

        graph.removeVertex(5);
        assertEquals(0, graph.edgesCount());
    }
}
