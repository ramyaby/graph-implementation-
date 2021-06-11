package com.company;

import java.util.*;

public class Graph {
    private class Node
    {
        String label;

        public Node(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }
    HashMap<String,Node> nodes=new HashMap<>();
    HashMap<Node, List<Node>> adjascencyList=new HashMap<>();

    public void addNode(String label)
    {
        Node node=new Node(label);
        nodes.put(label,node);
        adjascencyList.putIfAbsent(node,new ArrayList<>());
    }

    public void addEdge(String from,String to)
    {
        Node fromNode=nodes.get(from);
        Node toNode=nodes.get(to);
        if(fromNode==null||toNode==null)
            return;
        adjascencyList.get(fromNode).add(toNode);
        adjascencyList.get(toNode).add(fromNode);
    }

    public void removeEdge(String from,String to)
    {
        Node fromNode=nodes.get(from);
        Node toNode=nodes.get(to);
        if(fromNode==null||toNode==null)
            return;
        adjascencyList.get(fromNode).remove(toNode);
        adjascencyList.get(toNode).remove(fromNode);
    }

    public void removeNode(String label)
    {
        Node node=nodes.get(label);
        if(node==null)
            return;
        for(Node x:adjascencyList.keySet())
        {
            if(adjascencyList.get(x).contains(node))
                adjascencyList.get(x).remove(node);
        }
        adjascencyList.remove(node);
        nodes.remove(node);

    }

    public ArrayList<String> dfs()
    {
        ArrayList<String> list=new ArrayList<>();

        Stack<Node> stack=new Stack<>();
        Set<Node> visited=new HashSet<>();

        for(Node node:adjascencyList.keySet())
        {
            if(visited.contains(node))
                continue;
            stack.push(node);

            while (!stack.isEmpty())
            {
                Node cur=stack.pop();
                if(visited.contains(cur))
                    continue;
                visited.add(cur);
                list.add(cur.label);
                for(Node x:adjascencyList.get(cur))
                    if(!visited.contains(x))
                     stack.push(x);
            }
        }

        return list;
    }

    public ArrayList<String> bfs()
    {
        ArrayList<String> list=new ArrayList<>();

        Queue<Node> queue=new ArrayDeque<>();
        Set<Node> visited=new HashSet<>();

        for(Node node:adjascencyList.keySet())
        {
            if(visited.contains(node))
                continue;
            queue.add(node);

            while (!queue.isEmpty())
            {
                Node cur=queue.poll();
                if(visited.contains(cur))
                    continue;
                visited.add(cur);
                list.add(cur.label);
                for(Node x:adjascencyList.get(cur))
                    if(!visited.contains(x))
                        queue.add(x);
            }
        }

        return list;
    }

    public boolean isConnected()
    {
        int components=0;

        Stack<Node> stack=new Stack<>();
        Set<Node> visited=new HashSet<>();

        for(Node node:adjascencyList.keySet())
        {
            if(visited.contains(node))
                continue;
            components++;
            stack.push(node);

            while (!stack.isEmpty())
            {
                Node cur=stack.pop();
                if(visited.contains(cur))
                    continue;
                visited.add(cur);

                for(Node x:adjascencyList.get(cur))
                    if(!visited.contains(x))
                        stack.push(x);
            }
        }
        return components==1;
    }

    public ArrayList<ArrayList<String>> printComponents()
    {
        ArrayList<ArrayList<String>> list=new ArrayList<>();

        Stack<Node> stack=new Stack<>();
        Set<Node> visited=new HashSet<>();

        for(Node node:adjascencyList.keySet())
        {
            if(visited.contains(node))
                continue;

            stack.push(node);
            ArrayList<String> subList=new ArrayList<>();

            while (!stack.isEmpty())
            {
                Node cur=stack.pop();
                if(visited.contains(cur))
                    continue;
                visited.add(cur);
                subList.add(cur.label);

                for(Node x:adjascencyList.get(cur))
                    if(!visited.contains(x))
                        stack.push(x);
            }
            list.add(subList);
        }
        return list;
    }

    public boolean isCyclic()
    {
        Queue<Node> queue=new ArrayDeque<>();
        Set<Node> visited=new HashSet<>();

        for(Node node:adjascencyList.keySet())
        {
            if(visited.contains(node))
                continue;
            queue.add(node);

            while (!queue.isEmpty())
            {
                Node cur=queue.poll();
                if(visited.contains(cur))
                    return true;
                visited.add(cur);
                for(Node x:adjascencyList.get(cur))
                    if(!visited.contains(x))
                        queue.add(x);
            }
        }
        return false;
    }
    public void printGraph()
    {
        for(Node x:adjascencyList.keySet())
            System.out.println(x.label+"="+adjascencyList.get(x));
    }
}
