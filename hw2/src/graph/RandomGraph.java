/**
 * by Yi Yang
 * for Network Resilience homework 2
 * */
package graph;

import graph.nodes.Node;

public class RandomGraph extends Graph {
    /**
     * Initialize the graph class.
     *
     * @param NIn N; number of nodes.
     * @param pIn p; edge probability.
     */
    public RandomGraph(double NIn, double pIn) {
        super(NIn, pIn);
    }

    @Override
    public boolean run () {
        this.generateNodes();
        randEdges();
        return false;
    }

    @Override
    public String getCSV () {
        StringBuilder out = new StringBuilder();
        String[] l = g.keySet().toArray(new String[0]);
        for (String s : l) {
            out.append(g.get(s).getCSVString());
        }
        return out.toString();
    }

    /**
     * For all N(N-1)/2 possible edges, link if a random
     * number generated is greater than p.
     * */
    private void randEdges () {
        for (int i=0; i<N ;i++){
            for (int f=i+1;f<N;f++){
                if(randEdge()){
                    String a = String.valueOf(i);
                    String b = String.valueOf(f);
                    g.get(a).link(b);
                    g.get(b).link(a);
                }
            }
        }
    }
    /**
     * Generate a random number between 0 and 1.
     * If the number exceeds p return true, else false.
     * @return true, if random number greater than p.
     * */
    private boolean randEdge() {
        double rand = Math.random();
        return rand < p;
    }
}
