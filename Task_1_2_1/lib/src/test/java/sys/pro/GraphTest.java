package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * GraphTest
 */
public class GraphTest {
  private static Stream<Class<? extends Graph>> getImplementations() {
    return Stream.of(AdjGraph.class);
  }

  @ParameterizedTest
  @MethodSource("getImplementations")
  void testAddDirectedEdge(Class<? extends Graph> cls) {
    Graph graph;

    try {
      graph = cls.getDeclaredConstructor().newInstance();
    } catch (InstantiationException
        | IllegalAccessException
        | InvocationTargetException
        | NoSuchMethodException e) {
      fail();
      return;
    }

    Integer from = graph.newVertex();
    Integer to = graph.newVertex();
    graph.addDirectedEdge(from, to);

    assertEquals(to, graph.getAdjacentVertexes(from).findFirst().get());
  }

  @ParameterizedTest
  @MethodSource("getImplementations")
  void testRemoveDirectedEdge(Class<? extends Graph> cls) {
    Graph graph;

    try {
      graph = cls.getDeclaredConstructor().newInstance();
    } catch (InstantiationException
        | IllegalAccessException
        | InvocationTargetException
        | NoSuchMethodException e) {
      fail();
      return;
    }

    Integer from = graph.newVertex();
    Integer to = graph.newVertex();
    graph.addDirectedEdge(from, to);
    assertEquals(to, graph.getAdjacentVertexes(from).findFirst().get());

    graph.removeDirectedEdge(from, to);
    assertTrue(graph.getAdjacentVertexes(from).findFirst().isEmpty());
  }

  @ParameterizedTest
  @MethodSource("getImplementations")
  void testRemoveVertex(Class<? extends Graph> cls) {
    Graph graph;

    try {
      graph = cls.getDeclaredConstructor().newInstance();
    } catch (InstantiationException
        | IllegalAccessException
        | InvocationTargetException
        | NoSuchMethodException e) {
      fail();
      return;
    }

    Integer from = graph.newVertex();
    Integer to = graph.newVertex();
    graph.addDirectedEdge(from, to);
    assertEquals(to, graph.getAdjacentVertexes(from).findFirst().get());

    graph.removeVertex(from);
    assertTrue(graph.getAdjacentVertexes(from).findFirst().isEmpty());
  }

  @ParameterizedTest
  @MethodSource("getImplementations")
  void testTopSort(Class<? extends Graph> cls) {
    Graph graph;

    try {
      graph = cls.getDeclaredConstructor().newInstance();
    } catch (InstantiationException
        | IllegalAccessException
        | InvocationTargetException
        | NoSuchMethodException e) {
      fail();
      return;
    }

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

  @ParameterizedTest
  @MethodSource("getImplementations")
  void testReadFile(Class<? extends Graph> cls) {
    Graph graph;

    try {
      graph = cls.getDeclaredConstructor().newInstance();
    } catch (InstantiationException
        | IllegalAccessException
        | InvocationTargetException
        | NoSuchMethodException e) {
      fail();
      return;
    }

    Scanner scanner = new Scanner(
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
}
