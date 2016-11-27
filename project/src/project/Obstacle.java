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
public class Obstacle extends Nodes {

    public int x;
    public int y;

    public boolean pass = false;

    public int speed;

    public Obstacle(int x, int y) {
        super(x, y);
    }

}
