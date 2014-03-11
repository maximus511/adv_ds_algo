
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 */
public class MinCostFlowProgram {

    public static void main(String[] args) {

        File infile = new File("d:\\maxflow.txt");
        try {
            Scanner fileScanner = new Scanner(infile);
            int vertexCount = fileScanner.nextInt();
            int edgeCount = fileScanner.nextInt();
            int source = fileScanner.nextInt();
            int sink = fileScanner.nextInt();
            int flowUnits = fileScanner.nextInt();
            List<MinCostEdge>[] graph = createGraph(vertexCount);

            while (fileScanner.hasNext()) {
                int currSource = fileScanner.nextInt();
                int currSink = fileScanner.nextInt();
                addEdge(graph, currSource, currSink, fileScanner.nextInt(), fileScanner.nextInt());
            }
            int minFlowCost = minCostFlow(graph, source, sink, flowUnits);
            System.out.println("Min Cost output: "+minFlowCost);

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static List<MinCostEdge>[] createGraph(int n) {
        List<MinCostEdge>[] graph = new List[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<MinCostEdge>();
        return graph;
    }

    public static void addEdge(List<MinCostEdge>[] graph, int s, int t, int cap, int cost) {
        graph[s].add(new MinCostEdge(t, cap, cost, graph[t].size()));
        graph[t].add(new MinCostEdge(s, 0, -cost, graph[s].size() - 1));
    }

    public static int minCostFlow(List<MinCostEdge>[] graph, int source, int sink, int maxFlowUnit) {
        int graphLength = graph.length;
        int[] priorityArray = new int[graphLength];
        int[] currentFlowArray = new int[graphLength];
        int[] previousEdgeArray = new int[graphLength];
        int[] previousVertexArray = new int[graphLength];
        int[] tempArray = new int[graphLength];

        int flow = 0;
        int flowCost = 0;
        while (flow < maxFlowUnit) {
            Queue<Long> priorityQueue = new PriorityQueue<Long>();
            priorityQueue.add((long) source);
            Arrays.fill(priorityArray, 0, graphLength, Integer.MAX_VALUE);
            priorityArray[source] = 0;
            currentFlowArray[source] = Integer.MAX_VALUE;
            while (!priorityQueue.isEmpty()) {
                long cur = priorityQueue.remove();
                int d = (int) (cur >>> 32);
                int u = (int) cur;
                if (d != priorityArray[u]) {
                    continue;
                }
                for (int i = 0; i < graph[u].size(); i++) {
                    MinCostEdge minCostEdge = graph[u].get(i);
                    int v = minCostEdge.sink;
                    if (minCostEdge.capacity <= minCostEdge.source) {
                        continue;
                    }
                    int nprio = priorityArray[u] + minCostEdge.cost + tempArray[u] - tempArray[v];
                    if (priorityArray[v] > nprio) {
                        priorityArray[v] = nprio;
                        priorityQueue.add(((long) nprio << 32) + v);
                        previousVertexArray[v] = u;
                        previousEdgeArray[v] = i;
                        currentFlowArray[v] = Math.min(currentFlowArray[u], minCostEdge.capacity - minCostEdge.source);
                    }
                }
            }
            if (priorityArray[sink] == Integer.MAX_VALUE) {
                break;
            }
            for (int i = 0; i < graphLength; i++) {
                tempArray[i] += priorityArray[i];
            }
            int currFlowValue = Math.min(currentFlowArray[sink], maxFlowUnit - flow);
            flow += currFlowValue;
            for (int vertex = sink; vertex != source; vertex = previousVertexArray[vertex]) {
                MinCostEdge e = graph[previousVertexArray[vertex]].get(previousEdgeArray[vertex]);
                e.source += currFlowValue;
                graph[vertex].get(e.rev).source -= currFlowValue;
                flowCost += currFlowValue * e.cost;
            }
        }
        return flowCost;
    }
}
