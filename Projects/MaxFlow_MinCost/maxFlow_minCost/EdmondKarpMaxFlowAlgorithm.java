import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EdmondKarpMaxFlowAlgorithm {
    public static void main(String[] args) {
        File infile = new File("d:\\maxflow.txt");
        try {
            Scanner fileScanner = new Scanner(infile);
            int vertexCount = fileScanner.nextInt();
            int edgeCount = fileScanner.nextInt();
            int source = fileScanner.nextInt();
            int sink = fileScanner.nextInt();
            fileScanner.nextInt();
            List<Edge>[] graph = createGraph(vertexCount);

            while (fileScanner.hasNext()) {
                int currSource = fileScanner.nextInt();
                int currSink = fileScanner.nextInt();
                graph[currSource].add(new Edge(currSource, currSink, graph[currSink].size(), fileScanner.nextInt()));
                graph[currSink].add(new Edge(currSink, currSource, graph[currSource].size() - 1, 0));
                fileScanner.nextInt();
            }
            Long start = System.currentTimeMillis();
            int maxFlow = maxFlow(graph, source, sink);
            System.out.println("Total time taken : " + (System.currentTimeMillis() - start) + "ms");
            System.out.println("Max Flow: " + maxFlow);
            for (List<Edge> aGraph : graph) {
                for (Edge e : aGraph) {
                    if (e.flowValue > 0) {
                        System.out.println(e.source + " " + e.sink + " " + e.flowValue + "/" + e.capacity);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static List<Edge>[] createGraph(int verticesCount) {
        List<Edge>[] graph = new List[verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            graph[i] = new ArrayList<Edge>();
        }
        return graph;
    }

    public static int maxFlow(List<Edge>[] graph, int source, int sink) {
        int flow = 0;
        int[] queue = new int[graph.length + 1];
        while (true) {
            int queueTail = 0;
            queue[queueTail++] = source;
            Edge[] predecessor = new Edge[graph.length];
            for (int queueHead = 0; queueHead < queueTail && predecessor[sink] == null; queueHead++) {
                int cur = queue[queueHead];
                for (Edge e : graph[cur]) {
                    if (predecessor[e.sink] == null && e.capacity > e.flowValue) {
                        predecessor[e.sink] = e;
                        queue[queueTail++] = e.sink;
                    }
                }
            }
            if (predecessor[sink] == null) {
                break;
            }
            int df = currentFlowValue(graph, source, sink, predecessor);
            flow += df;
        }
        return flow;
    }

    private static int currentFlowValue(List<Edge>[] graph, int source, int sink, Edge[] predecessor) {
        int currFlow = Integer.MAX_VALUE;
        for (int u = sink; u != source; u = predecessor[u].source) {
            currFlow = Math.min(currFlow, predecessor[u].capacity - predecessor[u].flowValue);
        }
        for (int u = sink; u != source; u = predecessor[u].source) {
            predecessor[u].flowValue += currFlow;
            graph[predecessor[u].sink].get(predecessor[u].rev).flowValue -= currFlow;
        }
        return currFlow;
    }
}