/*Name           - Chanuka Nimsara Mathagadeera
IIT Student Id - 2017388
UOW Id         - w1698507*/

import java.lang.*;
import java.util.LinkedList;

class MaxFlow {
    static final int V = Tester.getNodes();    //Number of nodes in graph

    //if there is a path from source(s) to sink(t) in graph then the return is true
    boolean bfs(int rGraph[][], int s, int t, int parent[]) {
        // Create a visited array and mark all nodes as not visited
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;
        // Create a queue, enqueue source vertex and mark source vertex as visited

        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        // Standard BFS Loop
        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (visited[v] == false && rGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        // If we reached sink in BFS starting from source, then return true, else false
        return (visited[t] == true);
    }

    // Returns tne maximum flow from s to t in the given graph
    int fordFulkerson(int graph[][], int s, int t) {
        int u, v;

         //fil the residual graph with the given capacities in the original graph to create a residual graph
        int rGraph[][] = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        // This array is filled by BFS and to store path
        int parent[] = new int[V];

        int max_flow = 0;  // There is no flow initially

        // Augment the flow while there is path from source to sink
        while (bfs(rGraph, s, t, parent)) {

            // find the maximum flow through the path found.

            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }

            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

            // Add path flow to overall flow
            max_flow += path_flow;
        }
        // Return the overall flow
        return max_flow;
    }
}
