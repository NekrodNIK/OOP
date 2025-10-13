package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * GraphTest
 */
public class GraphTest {
  private static Stream<Class<? extends Graph>> getImplementations() {
    return Stream.of(AdjListGraph.class, AdjMatrixGraph.class);
  }

  private static Stream<Arguments> getImplementationsPairs() {
    ArrayList<Class<? extends Graph>> arr = new ArrayList<Class<? extends Graph>>();
    arr.add(AdjListGraph.class);
    arr.add(AdjMatrixGraph.class);

    ArrayList<Arguments> result = new ArrayList<Arguments>();

    for (Class<? extends Graph> cls1 : arr) {
      for (Class<? extends Graph> cls2 : arr) {
          result.add(Arguments.of(cls1, cls2));
      }
    }
    return result.stream();
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

    // assertEquals(1, graph.getAdjacentVertexes(0).findFirst().get());
    // assertEquals(3, graph.getAdjacentVertexes(2).findFirst().get());
    // assertEquals(10, graph.getAdjacentVertexes(6).findFirst().get());
    // assertEquals(11, graph.getAdjacentVertexes(10).findFirst().get());
    // assertEquals(8, graph.getAdjacentVertexes(7).findFirst().get());
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

  @ParameterizedTest
  @MethodSource("getImplementations")
  void testToString(Class<? extends Graph> cls) {
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

    graph.addDirectedEdge(0, 1);
    graph.addDirectedEdge(3, 4);
    graph.addDirectedEdge(5, 1);
    graph.addDirectedEdge(10, 2);
    graph.addDirectedEdge(2, 1);
    graph.addDirectedEdge(2, 3);
    graph.addDirectedEdge(5, 10);

    assertTrue(graph.toString().contains("0 1") &&
        graph.toString().contains("3 4") &&
        graph.toString().contains("5 1") &&
        graph.toString().contains("10 2") &&
        graph.toString().contains("2 1") &&
        graph.toString().contains("2 3") &&
        graph.toString().contains("5 10"));
  }
}
