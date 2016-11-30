/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.ArrayList;

/**
 *
 * @author zhangzhi
 */
public class Path {
    public ArrayList<Nodes> path;
    public int step;

    Path(){}
    Path(ArrayList<Nodes> p, int s){
    this.path = p;
    this.step = s;
  
    }
}
