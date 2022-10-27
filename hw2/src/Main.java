/**
 * by Yi Yang
 * for Network Resilience homework 2
 * */
import graph.Graph;
import graph.GraphOutput;
import graph.RandomGraph;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        runQa();
    }

    /**
     * Runs the question a and b of hw2 network resilience.
     * */
    public static void runQa(){
        String fileName = "question_a.csv";
        Graph g = new RandomGraph(50,0.1);
        g.run();
        GraphOutput gout = new GraphOutput();
        gout.setGraph(g);
        try{
            gout.writeGraph(fileName);
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("File writing failure.");
        }
        System.out.println("Written File");
    }

    /**
     * Runs and writes question c of hw2 network resilience.
     * */
    public static void runQc(){
        try{
            runAndGetDD(1000,0.01,100,"100times");
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Question c failed.");
        }
    }

    /**
     * Run the graph generation x times, and return the accumulated
     * */
    public static void runAndGetDD(int N, double p,int times, String outFile) throws IOException {
        final BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
        Thread pgs = progressPrint(queue);
        Graph g = new RandomGraph(N,p);
        pgs.start();
        g.run();
        Map<Integer,Integer> dd = g.getDD();
        queue.offer((int)(1/times)*100);
        if (times < 1){
            return;
        }
        for (int i=0 ;i< times-1;i++){
            g = new RandomGraph(1000,0.01);
            g.setDegreeDistro(dd);
            g.run();
            dd = g.getDD();
            queue.offer((int)(i/times)*100);
        }
        queue.offer(100);
        new GraphOutput().writeMap(outFile,dd);
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
}