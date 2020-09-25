/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Point;

/**
 *
 * @author Hoang Thao
 */
public class MyLine {
    public Point p1;
    public Point p2;

    public MyLine(Point p1, Point p2) {
	super();
	this.p1 = p1;
	this.p2 = p2;
    }

    public String toString() {
	String string = "(" + p1.x + "," + p1.y + ") and (" + p2.x + "," + p2.y
			+ ")";
	return string;
    }
}