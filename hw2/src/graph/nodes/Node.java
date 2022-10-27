/**
 * by Yi Yang
 * for Network Resilience homework 2
 * */

package graph.nodes;

import java.util.*;
import java.util.Set;

public class Node {
    public String id ;
    private Set<String> links = new HashSet<String> ();
    /**
     * Initializes a node in a Network.
     * @param nodeID; required to input a node ID.
     * */
    public Node (String nodeID) {
        id = nodeID;
    }

    /**
     * Links this node with another node.
     * This is a directed operation(only adds to this node).
     * @param incomingNode; the node tobe linked with.
     * */
    public void link(Node incomingNode) {
        links.add(incomingNode.id);
    }
    /**
     * Links this node with another node.
     * This is a directed operation(only adds to this node).
     * @param incomingNodeid; the node id tobe linked with.
     * */
    public void link(String incomingNodeid) {
        links.add(incomingNodeid);
    }
    /**
     * Checks if this is linked with the node in question.
     * @param incomingNode; input node to be checked.
     * */
    public boolean isLinkedWith(Node incomingNode){
        return links.contains(incomingNode.id);
    }
    /**
     * Checks if this is linked with the node in question.
     * @param incomingNodeId; input node id.
     * */
    public boolean isLinkedWith(String incomingNodeId){
        return links.contains(incomingNodeId);
    }

    /**
     * Get the csv representation of this node's links.
     * @return a string representation of the node's links
     * */
    public String getCSVString(){
        StringBuilder out = new StringBuilder();
        String[] l = links.toArray(new String[0]);
        for (String s : l) {
            out.append(id).append(",")
                    .append(s).append("\n");
        }
        return out.toString();
    }

    /**
     * Return the degree of the node.
     * */
    public int getDegree(){
        return links.size();
    }
}
