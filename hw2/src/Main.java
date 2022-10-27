/**
 * by Yi Yang
 * for Network Resilience homework 2
 * */
import graph.Graph;
import graph.GraphOutput;
import graph.RandomGraph;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        runQd();
    }

    /**
     * Runs the question a and b of hw2 network resilience.
     * */
    public static void runQa(){
        String fileName = "question_a.csv";
        Graph g = new RandomGraph(50,0.1);
        g.run();
        write(fileName, g);
    }

    /**
     * Runs and writes question c of hw2 network resilience.
     * */
    public static void runQc(){
        try{
            Graph.runAndGetDD(1000,0.01,100,"100times.csv");
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Question c failed.");
        }
    }

    /**
     * Runs question d, e
     * */
    public static void runQd(){
        String fileName = "question_d.csv";
        Integer k = 10;
        Graph g = new RandomGraph(500,0.1);
        g.run(k);
        g.printStats(g.getDD());
        write(fileName, g);
    }

    public static void write(String fileName, Graph g) {
        GraphOutput gout = new GraphOutput();
        try{
            gout.writeMap("dd"+fileName, g.getDD());
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("question a File writing failure.");
        }
        gout.setGraph(g);
        try{
            gout.writeGraph(fileName);
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("File writing failure.");
        }
        System.out.println("Written File");
    }


}