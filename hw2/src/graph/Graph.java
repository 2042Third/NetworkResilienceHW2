/**
 * by Yi Yang
 * for Network Resilience homework 2
 * */
package graph;

import graph.nodes.Node;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Graph {
    protected double p = 0.0;
    protected double N = 0.0;
    protected Map<String,Node> g = new HashMap<>();
    public Map<Integer, Integer> dd = new HashMap<>();

    /**
     * Initialize the graph class.
     * @param NIn N; number of nodes.
     * @param pIn p; edge probability.
     * */
    public Graph(double NIn, double pIn){
        p = pIn;
        N = NIn;
    }

    /**
     * Run the graph generation x times, and return the accumulated
     * */
    public static void runAndGetDD(int N, double p,int times, String outFile) throws IOException {
        final BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
        Thread pgs = progressPrint(queue);
        pgs.start();
        Map<Integer,Integer> dd = getDD(N,p);
        queue.add((int)(1/times)*100);
        if (times < 1){
            return;
        }
        for (int i=0 ;i< times-1;i++){
            dd = getDD(N,p,dd);
            queue.add((int)(i/times)*100);
        }
        queue.add(100);
        new GraphOutput().writeMap(outFile,dd);
    }

    /**
     *  Run the simulation adn return the degree distribution.
     * @param N ; number of nodes
     * @param p ; edge probability
     * @return degree distribution
     * */
    public static Map<Integer,Integer> getDD(int N, double p){
        Graph g = new RandomGraph(N,p);
        g.run();
        return g.getDD();
    }

    /**
     *  Run the simulation adn return the degree distribution.
     * @param N ; number of nodes
     * @param p ; edge probability
     * @param dd ; existing degree distribution
     * @return degree distribution
     * */
    public static Map<Integer,Integer> getDD(int N, double p, Map<Integer, Integer> dd){
        Graph g = new RandomGraph(N,p);
        g.setDegreeDistro(dd);
        g.run();
        return g.getDD();
    }

    /**
     * Returns a built thread object that can print the progress of the
     * */
    public static Thread progressPrint(BlockingQueue<Integer> queue){
        return new Thread(()-> {
            int i = 0;
            while(i < 100) {
                System.out.print("[");
                try {
                    i = queue.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int j=0;
                while(j++<i){
                    System.out.print("#");
                }
                while(j++<100){
                    System.out.print(" ");
                }
                System.out.print("] : "+ i+"%");
                System.out.print("\r");
            }
        });
    }

    /**
     * Runs the graph generation.
     * */
    public boolean run () {
        return false;
    }
    /**
     * Runs the graph generation with k average degree.
     * */
    public boolean run (Integer k) {
        return false;
    }

    /**
     * Return the graph as a string with format of csv.
     * */
    public String getCSV(){

        return "NO GRAPH SPECIFIED. -Network Resilience by Yi Yang";
    }

    /**
     * Set the degree distribution statistics.
     * @param dist; degree distribution.
     * */
    public void setDegreeDistro(Map<Integer, Integer> dist){
        dd = dist;
    }

    /**
     * Get the degree distribution of the graph.
     * */
    public Map<Integer,Integer> getDD() {
        for (String s: g.keySet()){
            Integer degree = g.get(s).getDegree();
            if(!dd.containsKey(degree)){
                dd.put(degree,1);
            }
            else {
                dd.replace(degree,dd.get(degree)+1);
            }
        }
        return dd;
    }

    /**
     * Generates unconnected N nodes.
     * */
    protected void generateNodes(){
        for (int i=0 ; i<N ; i++){
            String nodeid = String.valueOf(i);
            g.put(nodeid,new Node(nodeid));
        }
    }
}
