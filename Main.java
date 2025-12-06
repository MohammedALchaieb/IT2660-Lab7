import java.util.List;

class Main {
  public static void main(String[] args) {

    String[] vertices = {
        "Liberal Arts",               // 0
        "Student Services",           // 1
        "Health Careers & Sciences",  // 2
        "Health Technologies Center", // 3
        "Recreation Center",          // 4
        "Technology Learning Center", // 5
        "Business & Technology",      // 6
        "Theatre"                     // 7
    };

    int[][] edges = {
        {0, 1}, {1, 0}, // Liberal Arts <-> Student Services
        {0, 7}, {7, 0}, // Liberal Arts <-> Theatre

        {1, 2}, {2, 1}, // Student Services <-> Health Careers & Sciences
        {1, 5}, {5, 1}, // Student Services <-> Technology Learning Center

        {2, 3}, {3, 2}, // Health Careers & Sciences <-> Health Technologies Center
        {2, 4}, {4, 2}, // Health Careers & Sciences <-> Recreation Center

        {4, 5}, {5, 4}, // Recreation Center <-> Technology Learning Center

        {5, 6}, {6, 5}, // Technology Learning Center <-> Business & Technology

        {6, 7}, {7, 6}  // Business & Technology <-> Theatre
    };

    
    UnweightedGraph<String> graph = new UnweightedGraph<>(vertices, edges);


    int startVertex = 6;
    UnweightedGraph<String>.SearchTree dfs =
        graph.dfs(startVertex);


    System.out.println("DFS search order starting from Business & Technology:");
    List<Integer> order = dfs.getSearchOrder();
    for (int v : order) {
      System.out.println("Visited vertex " + v + " : " + graph.getVertex(v));
    }

    System.out.println();


    System.out.println("Parent-child relationships:");
    for (int i = 0; i < graph.getSize(); i++) {
      int parent = dfs.getParent(i);
      if (parent == -1) {
        System.out.println(graph.getVertex(i) + " is the root of the DFS tree");
      } else {
        System.out.println("Parent of " + graph.getVertex(i) +
                           " is " + graph.getVertex(parent));
      }
    }

    System.out.println();


    dfs.printPath(3); // Health Technologies Center
    System.out.println();

    dfs.printPath(1); // Student Services
    System.out.println();

    dfs.printPath(4); // Recreation Center
    System.out.println();

    System.out.println();


    dfs.printTree();
  }
}
