package sys.pro;

import java.util.Scanner;
import java.util.stream.Stream;

public interface Graph {
  public int size();

  public int newVertex();

  public void removeVertex(int index);

  public void addDirectedEdge(int from, int to);

  public void removeDirectedEdge(int from, int to);

  public Stream<Integer> getAllVertexes();
  public Stream<Integer> getAdjacentVertexes(int index);

  public Stream<Integer> topSort();

  public default void readFromScanner(Scanner scanner) {
    while (scanner.hasNext()) {
      String[] line = scanner.nextLine().strip().split(" ");
      Integer from = Integer.valueOf(line[0]);
      Integer to = Integer.valueOf(line[1]);

      addDirectedEdge(from, to);
    }

    scanner.close();
  }
}
