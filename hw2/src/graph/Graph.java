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
    protected Map<String,Node> g = new HashMap<String,Node>();


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
     * Generates unconnected N nodes.
     * */
    protected void generateNodes(){
        for (int i=0 ; i<N ; i++){
            String nodeid = String.valueOf(i);
            g.put(nodeid,new Node(nodeid));
        }
    }
}
