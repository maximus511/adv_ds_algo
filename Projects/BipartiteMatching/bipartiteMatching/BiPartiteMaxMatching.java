

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;



public class BiPartiteMaxMatching {

	/**
	 * @param args
	 */
	public static int noOfVertices=0;
	public static int noOfEdges=0;
	public static Graph biGraph=null;
	public static int leftVertices=0, rightVertices=0;
	public static int[] Pair;
	public static int[] Dist;
	public final int NIL=0;
	public final int INF=Integer.MAX_VALUE;
	public static void main(String[] args) {
		
		int result=new BiPartiteMaxMatching().findMaxMatxhing("matching-100k.txt");
		
		System.out.println("Total matching : "+result);

	}
	public int findMaxMatxhing(String inFile)
	{
		int maxMatching=0;
		constructGraph(inFile);
		//biGraph.printGraph();
		maxMatching=doHopCraft_Karp();
		return maxMatching;
	}
	
	public void constructGraph(String inFile)
	{

		File infile=new File(inFile);
		int u,v,w;
		try {
			Scanner scanner=new Scanner(infile);
			leftVertices=scanner.nextInt();
			rightVertices=scanner.nextInt();
			//leftVertices+=1;
			//rightVertices+=1;
			noOfEdges=scanner.nextInt();
			biGraph=new Graph(leftVertices+rightVertices);
			for(int i=0;i<noOfEdges;i++)
			{
			u=scanner.nextInt();
			v=scanner.nextInt();
			w=scanner.nextInt();
			biGraph.addEdge(u,leftVertices+v,w);// To distinct the vertex ID's 'leftVertice+v'
			biGraph.addEdge(leftVertices+v, u, w);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public boolean doBFS()
	{

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int v = 1; v <= leftVertices; ++v) 
            if (Pair[v] == NIL) 
            { 
                Dist[v] = 0; 
                queue.add(v); 
            }
            else 
                Dist[v] = INF;
 
        Dist[NIL] = INF;
 
        while (!queue.isEmpty()) 
        {
            int v = queue.poll();
            if (Dist[v] < Dist[NIL]) 
                for (Edge e: biGraph.getOutEdges(v)) 
                    if (Dist[Pair[e.v]] == INF) 
                    {
                        Dist[Pair[e.v]] = Dist[v] + 1;
                        queue.add(Pair[e.v]);
                    }           
        }
        return Dist[NIL] != INF;
    
	}
	
	public boolean doDFS(int v)
	{

        if (v != NIL) 
        {
            for (Edge e : biGraph.getOutEdges(v)) 
                if (Dist[Pair[e.v]] == Dist[v] + 1)
                    if (doDFS(Pair[e.v])) 
                    {
                        Pair[e.v] = v;
                        Pair[v] = e.v;
                        return true;
                    }               
 
            Dist[v] = INF;
            return false;
        }
        return true;
    
	}
	
	public int doHopCraft_Karp()
	{

        Pair = new int[leftVertices+rightVertices+1];
        Dist = new int[leftVertices+rightVertices+1];
        int matching = 0;
        while (doBFS())
            for (int v = 1; v <= leftVertices; ++v)
                if (Pair[v] == NIL)
                    if (doDFS(v))
                        matching = matching + 1;
        return matching;
    
	}
	
}
