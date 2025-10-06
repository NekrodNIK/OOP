package sys.pro;

public interface Graph {
  public int addVertex();
  public int removeVertex(int index);
  public void addEdge(int from, int to);
  public void removeEdge(int from, int to);
}
