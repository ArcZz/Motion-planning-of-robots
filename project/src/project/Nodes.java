/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author zhangzhi
 */
public class Nodes {

    public int x;
    public int y;
    public int F;
    public int G;
    public int H;
    public boolean pass = true;
    public Nodes parent;

    public void calcF() {
        this.F = this.G + this.H;
    }

    public Nodes(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
}
