/**
 * by Yi Yang
 * for Network Resilience homework 2
 * */
import graph.Graph;
import graph.GraphOutput;
import graph.RandomGraph;

import java.io.IOException;

public class Main {
    public static void main(String[] args)  {
//          question a and b
        String fileName = "question_a.csv";
        Graph g = new RandomGraph(50,0.1);
//        String fileName = "question_c.csv";
//        Graph g = new RandomGraph(1000,0.01);
        g.run();
        GraphOutput gout = new GraphOutput();
        gout.setGraph(g);
        try{
            gout.writeGraph(fileName);
        }catch (IOException e){
            System.out.println("File writing failure.");
            System.out.println(e.getMessage());
        }
        System.out.println("Written File");
    }
}