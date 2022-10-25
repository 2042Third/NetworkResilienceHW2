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

public class GraphOutput {
    public Graph graph;

    protected static String defaultDir = "../graphOutputs/";

    /**
     * Sets the graph to be outputted.
     * */
    public void setGraph(Graph g){
        graph = g;
    }

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

    private byte[] strToBytes (String a) {
        return a.getBytes();
    }

}
