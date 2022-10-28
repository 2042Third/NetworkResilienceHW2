/**
 * by Yi Yang
 * for Network Resilience homework 2
 * */
import graph.Graph;
import graph.GraphOutput;
import graph.RandomGraph;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
//        runQa(); // question a
//        runQc(); // question c
//        runQd(); // question f
        runQf();
    }

    /**
     * Runs the question a and b of hw2 network resilience.
     * */
    public static void runQa(){
        String fileName = "qa/question_a.csv";
        Graph g = new RandomGraph(50,0.1);
        g.run();
        write(fileName, g);
        Map<Integer, Integer> dd = g.getDD();
        g.printStats(dd);
        System.out.println("xxxxxxxxxxxxxxxx above are stats for question a xxxxxxxxxxxxxxx");
    }

    /**
     * Runs and writes question c of hw2 network resilience.
     * */
    public static void runQc(){

        double N = 1000;
        double p = 0.01;
        double rmp=1.0/2;
        Graph g = new RandomGraph(N,p);
        GraphOutput gout = new GraphOutput();
        StringBuilder x = new StringBuilder();
        x.append("allGraphs = [");
        for (int i=0; i<100;i++) {
            g = new RandomGraph(N,p);
            g.run();
            g.printStats(g.getDD());
            x.append("["+"g"+i+", "+"gv"+i+"]");
            if(i<99){
                x.append(", ");
            }
            try {
                gout.writePython("qc/question_c.py", "g"+i, "gv"+i, g.getDD());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Cannot write python file output");
            }
        }
        x.append("]\n");

        try {
            gout.appendTo("qc/question_c.py",x.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot write python file end output");
        }
        System.out.println("xxxxxxxxxxxxxxxx above are stats for question c xxxxxxxxxxxxxxx");
    }

    /**
     * Runs question d, e
     * */
    public static void runQd(){
        String fileName = "qd/question_d.csv";
        Integer k = 10;
        Graph g = new RandomGraph(500,0.1);
        g.run(k);
        g.printStats(g.getDD());
        write(fileName, g);
        System.out.println("xxxxxxxxxxxxxxxx above are stats for question d xxxxxxxxxxxxxxx");
    }
    /**
     * Runs question f, g
     * */
    public static void runQf(){
        String fileName = "qf/question_f.csv";
        double N = 1000;
        double p = 0.01;
        double rmp=1.0/2;
        Graph g = new RandomGraph(N,p);
        g.run();
        g.printStats(g.getDD());
        g.randRmNodes(500,rmp);
        g.printStats(g.getDD());
        write(fileName, g);

        GraphOutput gout = new GraphOutput();
        StringBuilder x = new StringBuilder();
        x.append("allGraphs = [");
//        for (int i=0; i<100;i++) {
            for (int i=0; i<1;i++) {
            g = new RandomGraph(N,p);
            g.run();
//            g.printStats(g.getDD());
            g.randRmNodes(500,rmp);
            List<Set<String>> cc = g.getConnectedComponents();
//            g.printStats(g.getDD());
            x.append("["+"g"+i+", "+"gv"+i+"]");
            if(i<99){
                x.append(", ");
            }
            try {
                gout.writePython("qf/question_f.py", "g"+i, "gv"+i, g.getDD());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Cannot write python file output");
            }
        }
        x.append("]\n");

        try {
            gout.appendTo("qf/question_f.py",x.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot write python file end output");
        }
        System.out.println("xxxxxxxxxxxxxxxx above are stats for question f xxxxxxxxxxxxxxx");
    }

    public static void write(String fileName, Graph g) {
        GraphOutput gout = new GraphOutput();
        String ddFile = ddFileName(fileName);
        try{
            gout.writeMap(ddFile, g.getDD());
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
        System.out.println(ddFileName(fileName));
    }


    public static String ddFileName(String a){
        int idx = a.indexOf(".");
        String snd;
        if(idx == -1){
            snd = "";
        }else{
            snd = a.substring(idx,a.length());
        }
        String fst = a.substring(0,idx);
        return fst+"_degreeDistribution"+snd;
    }

}