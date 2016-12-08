/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zhangzhi
 */
public class AStar {

    public ArrayList<Nodes> openList = new ArrayList<Nodes>();
    public ArrayList<Nodes> closeList = new ArrayList<Nodes>();
    

    public Nodes findPath(int row, Nodes startNode, Nodes endNode, int ox, int oy, int obx, int oby) {

        openList.add(startNode);

        while (openList.size() > 0) {

            Nodes currentNode = findMinFNodeInOpneList();
            openList.remove(currentNode);
            closeList.add(currentNode);

            ArrayList<Nodes> neighborNodes = findNeighborNodes(row, currentNode, ox, oy,obx,oby);
            neighborNodes.forEach((node) -> {
                if (exists(openList, node)) {
                    inOpenList(currentNode, node);
                } else {
                    notinOpenList(currentNode, endNode, node);
                }
            });
            if (find(openList, endNode) != null) {
                return find(openList, endNode);
            }
        }

        return find(openList, endNode);
    }

    public Nodes findMinFNodeInOpneList() {
        Nodes tempNode = openList.get(0);
        for (Nodes node : openList) {
            if (node.F < tempNode.F) {
                tempNode = node;
            }
        }
        return tempNode;
    }

    public ArrayList<Nodes> findNeighborNodes(int row, Nodes currentNode,int ox, int oy,int obx, int oby) {
        ArrayList<Nodes> arrayList = new ArrayList<Nodes>();

        int topXleft = currentNode.x - 1;
        int topYleft = currentNode.y - 1;
        if (canReach(row, topXleft, topYleft,ox,oy,obx,oby) && !exists(closeList, topXleft, topYleft)) {
            arrayList.add(new Nodes(topXleft, topYleft));
        }

        int topXright = currentNode.x + 1;
        int topYright = currentNode.y - 1;
        if (canReach(row, topXright, topYright,ox,oy,obx,oby) && !exists(closeList, topXright, topYright)) {
            arrayList.add(new Nodes(topXright, topYright));
        }

        int topX = currentNode.x;
        int topY = currentNode.y - 1;
        if (canReach(row, topX, topY,ox,oy,obx,oby) && !exists(closeList, topX, topY)) {
            arrayList.add(new Nodes(topX, topY));
        }
        int bottomXleft = currentNode.x - 1;
        int bottomYleft = currentNode.y + 1;
        if (canReach(row, bottomXleft, bottomYleft,ox,oy,obx,oby) && !exists(closeList, bottomXleft, bottomYleft)) {
            arrayList.add(new Nodes(bottomXleft, bottomYleft));
        }

        int bottomXright = currentNode.x + 1;
        int bottomYright = currentNode.y + 1;
        if (canReach(row, bottomXright, bottomYright,ox,oy,obx,oby) && !exists(closeList, bottomXright, bottomYright)) {
            arrayList.add(new Nodes(bottomXright, bottomYright));
        }
        int bottomX = currentNode.x;
        int bottomY = currentNode.y + 1;
        if ( canReach(row, bottomX, bottomY,ox,oy,obx,oby) && !exists(closeList, bottomX, bottomY)) {
            arrayList.add(new Nodes(bottomX, bottomY));
        }
        int leftX = currentNode.x - 1;
        int leftY = currentNode.y;
        if (canReach(row, leftX, leftY,ox,oy,obx,oby) && !exists(closeList, leftX, leftY)) {
            arrayList.add(new Nodes(leftX, leftY));
        }
        int rightX = currentNode.x + 1;
        int rightY = currentNode.y;
        if (canReach(row, rightX, rightY,ox,oy,obx,oby) && !exists(closeList, rightX, rightY)) {
            arrayList.add(new Nodes(rightX, rightY));
        }
        return arrayList;
    }

    public Nodes find(List<Nodes> nodes, Nodes node) {
        for (Nodes n : nodes) {
            if ((n.x == node.x) && (n.y == node.y)) {
                return n;
            }
        }
        return null;
    }

    public boolean canReach(int row, int x, int y,int ox, int oy, int obx, int oby) {

        if (x >= 0 && x < row && y >= 0 && y < row) {
            if(x == ox && y == oy){
                return false;
            }
            if(x == obx && y == oby){
                return false;
            }      
            return true;
        } else {
            return false;
        }
    }
  

    public boolean exists(List<Nodes> nodes, Nodes node) {
        if (nodes.stream().anyMatch((n) -> ((n.x == node.x) && (n.y == node.y)))) {
            return true;
        }
        return false;
    }

    public boolean exists(List<Nodes> nodes, int x, int y) {
        if (nodes.stream().anyMatch((n) -> ((n.x == x) && (n.y == y)))) {
            return true;
        }
        return false;
    }

    public int calcG(Nodes start, Nodes node) {
        int G = 0;
        int parentG = node.parent != null ? node.parent.G : 0;
        return G + parentG;
    }

    public int calcH(Nodes end, Nodes node) {
        int dx = Math.abs(node.x - end.x);
        int dy = Math.abs(node.y - end.y);
        int step;
        if (dx > dy) {
            step = dx;
        } else {
            step = dy;
        }
        return step;
    }

    public void inOpenList(Nodes tempStart, Nodes node) {
        int G = calcG(tempStart, node);
        if (G < node.G) {
            node.parent = tempStart;
            node.G = G;
            node.calcF();
        }
    }

    private void notinOpenList(Nodes tempStart, Nodes end, Nodes node) {
        node.parent = tempStart;
        node.G = calcG(tempStart, node);
        node.H = calcH(end, node);
        node.calcF();
        openList.add(node);
    }

}
