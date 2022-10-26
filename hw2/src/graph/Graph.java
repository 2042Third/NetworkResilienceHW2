/**
 * by Yi Yang
 * for Network Resilience homework 2
 * */
package graph;

import graph.nodes.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
     * Runs the graph generation.
     * */
    public boolean run () {
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
