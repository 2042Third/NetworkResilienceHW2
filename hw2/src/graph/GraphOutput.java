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
import java.nio.file.StandardOpenOption;
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
        File f = new File(defaultDir+fName).getParentFile();
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
        File f = new File(defaultDir+fName).getParentFile();
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

    /**
     * Append to fName file, two python lists from the given map.
     * */
    public void writePython(String fName,String varName, String varValue, Map<Integer, Integer> m) throws IOException {

        Path p = Paths.get(defaultDir+fName);
        if(!new File(defaultDir+fName).exists()){
            Files.write(p,strToBytes(""));
        }
        File f = new File(defaultDir+fName).getParentFile();
        if (f.mkdirs()){
            System.out.println("New directory created at "+ defaultDir);
        } else {
            System.out.println("No directory created.");
        }
        StringBuilder x = new StringBuilder();
        Integer[] keys = m.keySet().toArray(new Integer[0]);
        Integer[] values = m.values().toArray(new Integer[0]);

        buildPythonList(varName, x, keys, keys);
        buildPythonList(varValue, x, keys, values);


        Files.write(p,strToBytes(x.toString()), StandardOpenOption.APPEND);

    }
    /**
     * Append to fName file.
     * */
    public void appendTo(String fName,String a) throws IOException {

        Path p = Paths.get(defaultDir+fName);
        if(!new File(defaultDir+fName).exists()){
            Files.write(p,strToBytes(""));
        }
        File f = new File(defaultDir+fName).getParentFile();
        if (f.mkdirs()){
            System.out.println("New directory created at "+ defaultDir);
        } else {
            System.out.println("No directory created.");
        }


        Files.write(p,strToBytes(a), StandardOpenOption.APPEND);

    }
    /**
     * Builds a python list from the given information.
     * */
    public void buildPythonList(String varValue, StringBuilder x, Integer[] keys, Integer[] values) {
        x.append(varValue).append(" = [");
        for (int i=0;i< values.length;i++){
            x.append(String.valueOf(values[i]));
            if(i<keys.length-1){
                x.append(", ");
            }
        }
        x.append("]\n");
    }
}
