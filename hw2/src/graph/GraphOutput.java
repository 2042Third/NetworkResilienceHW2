/**
 * by Yi Yang
 * for Network Resilience homework 2
 * */
package graph;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class GraphOutput {
    public Graph graph;

    protected static String defaultDir = "../graphOutputs/";

    /**
     * Sets the graph to be outputted.
     * @param g; graph object
     * */
    public void setGraph(Graph g){
        graph = g;
    }
    /**
     * Writes the input file into the default location.
     * */
    public void writeGraph(String fName) throws IOException {
        Path p = Paths.get(defaultDir+fName);
        File f = new File(defaultDir);
        if (f.mkdirs()){
            System.out.println("New directory created at "+ defaultDir);
        } else {
            System.out.println("No directory created.");
        }
        Files.write(p,strToBytes(graph.getCSV()));
    }
    /**
     * Writes the input file into the default location.
     * @param fName ; file name
     * @param m ; a map that contains integers for conversion to a csv
     * */
    public void writeMap(String fName, Map<Integer,Integer> m) throws IOException {
        Path p = Paths.get(defaultDir+fName);
        File f = new File(defaultDir);
        if (f.mkdirs()){
            System.out.println("New directory created at "+ defaultDir);
        } else {
            System.out.println("No directory created.");
        }
        StringBuilder x = new StringBuilder();
        for (Integer i: m.keySet()){
            x.append(i)
                    .append(", ")
                    .append(m.get(i))
                    .append("\n");
        }
        Files.write(p,strToBytes(x.toString()));
    }
    /**
     * Converts a String object to bytes.
     * @param a ; String object
     * @return byte array
     * */
    private byte[] strToBytes (String a) {
        return a.getBytes();
    }

}
