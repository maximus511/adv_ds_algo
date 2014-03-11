/**
 * Created with IntelliJ IDEA.
 */
public class MinCostEdge {
    int source;
    int sink;
    int capacity;
    int cost;
    int rev;

    MinCostEdge(int sink, int capacity, int cost, int rev) {
        this.sink = sink;
        this.capacity = capacity;
        this.cost = cost;
        this.rev = rev;
    }
}