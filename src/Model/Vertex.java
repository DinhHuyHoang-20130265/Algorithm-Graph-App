package Model;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Vertex {
	Integer ten;
	public Integer index;
	ArrayList<Vertex> danhSachKe;
	public Ellipse2D el;
	int order;
	Color color;

	public Vertex(Integer ten, Integer index, ArrayList<Vertex> danhSachKe, Ellipse2D el) {
		super();
		this.ten = ten;
		this.index = index;
		this.danhSachKe = danhSachKe;
		this.el = el;
	}

	@Override
	public String toString() {
		return "" + this.index;
	}

	public int getOrder() {
		return order;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public Integer getTen() {
		return ten;
	}

	public void setTen(Integer ten) {
		this.ten = ten;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public ArrayList<Vertex> getDanhSachKe() {
		return danhSachKe;
	}

	public void setDanhSachKe(ArrayList<Vertex> danhSachKe) {
		this.danhSachKe = danhSachKe;
	}

	public Ellipse2D getEl() {
		return el;
	}

	public void setEl(Ellipse2D el) {
		this.el = el;
	}
}
