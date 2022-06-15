package Model;

import java.awt.Color;
import java.awt.geom.Line2D;

public class Edge {
	public Vertex diemdau1;
	public Vertex diemdau2;
	public Line2D line2d;
	public int weight;
	Color color;

	public Edge(Vertex diemdau1, Vertex diemdau2, Line2D line2d, int weight) {
		super();
		this.diemdau1 = diemdau1;
		this.diemdau2 = diemdau2;
		this.line2d = line2d;
		this.weight = weight;
	}

	public Vertex getDiemdau1() {
		return diemdau1;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setDiemdau1(Vertex diemdau1) {
		this.diemdau1 = diemdau1;
	}

	public Vertex getDiemdau2() {
		return diemdau2;
	}

	public void setDiemdau2(Vertex diemdau2) {
		this.diemdau2 = diemdau2;
	}

	public void setLine2D(Line2D line2d) {
		this.line2d = line2d;
	}

	public Line2D getLine2D() {
		return line2d;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "(" + this.diemdau1.index + ", " + this.diemdau2.index + ")";
	}
}
