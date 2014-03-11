/**
 * Created with IntelliJ IDEA.
 * User: Rahul Nair
 */
public class Edge {
    int source;
    int sink;
    int rev;
    int capacity;
    int flowValue;

    public Edge(int source, int sink, int rev, int capacity) {
        this.source = source;
        this.sink = sink;
        this.rev = rev;
        this.capacity = capacity;
    }
}
