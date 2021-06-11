package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Graph graph = new Graph();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");

        graph.addEdge("A","B");
        graph.addEdge("B","C");
        graph.addEdge("A","C");
        graph.addEdge("F","E");
        graph.addEdge("B","D");

        ArrayList<String> dfsTraversal=graph.dfs();
        System.out.println(dfsTraversal);

        ArrayList<String> bfsTraversal=graph.bfs();
        System.out.println(bfsTraversal);

        System.out.println(graph.isConnected());

        ArrayList<ArrayList<String>> lists=graph.printComponents();
        System.out.println(lists);

        System.out.println(graph.isCyclic());


        graph.removeEdge("A","C");
        System.out.println(graph.isCyclic());
        graph.removeNode("C");

        graph.printGraph();



    }
}
