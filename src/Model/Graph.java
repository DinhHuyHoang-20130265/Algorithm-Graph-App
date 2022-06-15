package Model;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import View.Main;

public class Graph {
	public ArrayList<Vertex> danhSachDinh;
	public ArrayList<Edge> danhSachCanh;
	public ArrayList<ArrayList<Integer>> mtk;
	public ArrayList<ArrayList<Integer>> weightMaxtrix;
	public final static int MAX = (int) 10e6;

	public Graph(ArrayList<Vertex> danhSachDinh, ArrayList<Edge> danhSachCanh, ArrayList<ArrayList<Integer>> mtk,
			ArrayList<ArrayList<Integer>> weightMaxtrix) {
		super();
		this.danhSachDinh = new ArrayList<Vertex>();
		this.danhSachCanh = new ArrayList<Edge>();
		this.mtk = new ArrayList<ArrayList<Integer>>();
		this.weightMaxtrix = new ArrayList<ArrayList<Integer>>();
	}

	public ArrayList<Vertex> getDanhSachDinh() {
		return danhSachDinh;
	}

	public void setDanhSachDinh(ArrayList<Vertex> danhSachDinh) {
		this.danhSachDinh = danhSachDinh;
	}

	public ArrayList<Edge> getDanhSachCanh() {
		return danhSachCanh;
	}

	public void setDanhSachCanh(ArrayList<Edge> danhSachCanh) {
		this.danhSachCanh = danhSachCanh;
	}

	public ArrayList<ArrayList<Integer>> getMtk() {
		return mtk;
	}

	public ArrayList<ArrayList<Integer>> getWeightMaxtrix() {
		return weightMaxtrix;
	}

	public void setMtk(ArrayList<ArrayList<Integer>> mtk) {
		this.mtk = mtk;
	}

	public void themDinhWeightMax() {
		if (weightMaxtrix.size() == 0) {
			weightMaxtrix.add(new ArrayList<Integer>());
			weightMaxtrix.get(0).add(MAX);
			return;
		}
		for (int i = 0; i < weightMaxtrix.size(); i++)
			weightMaxtrix.get(i).add(MAX);
		ArrayList<Integer> dongmoi = new ArrayList<Integer>();
		for (int i = 0; i < weightMaxtrix.size() + 1; i++)
			dongmoi.add(MAX);
		weightMaxtrix.add(dongmoi);
	}

	public void themDinh(Ellipse2D el) {
		if (mtk.size() == 0) {
			mtk.add(new ArrayList<Integer>());
			mtk.get(0).add(0);
			danhSachDinh.add(new Vertex(mtk.size() - 1, mtk.size() - 1, new ArrayList<Vertex>(), el));
			return;
		}
		for (int i = 0; i < mtk.size(); i++)
			mtk.get(i).add(0);
		ArrayList<Integer> dongmoi = new ArrayList<Integer>();
		for (int i = 0; i < mtk.size() + 1; i++)
			dongmoi.add(0);
		mtk.add(dongmoi);
		Vertex v1 = new Vertex(mtk.size() - 1, mtk.size() - 1, new ArrayList<Vertex>(), el);
		danhSachDinh.add(v1);
	}

	public void adjustWeightMaxtrix(int diemdau1, int diemdau2, int value) {
		weightMaxtrix.get(diemdau1).set(diemdau2, value);
	}

	public void suaDinh(Ellipse2D el, int index) {
		danhSachDinh.get(index).setEl(el);
	}

	public void suaCanh(Line2D line2d, int index) {
		danhSachCanh.get(index).setLine2D(line2d);
	}

	public void themCanh(Vertex diemdau1, Vertex diemdau2, Line2D line2d) {
		if (Main.type.equals("VoHuong")) {
			mtk.get(diemdau1.index).set(diemdau2.index, 1);
			mtk.get(diemdau2.index).set(diemdau1.index, 1);
			danhSachCanh.add(new Edge(diemdau1, diemdau2, line2d, MAX));
			diemdau1.danhSachKe.add(diemdau2);
			diemdau2.danhSachKe.add(diemdau1);
		} else if (Main.type.equals("CoHuong")) {
			mtk.get(diemdau1.index).set(diemdau2.index, 1);
			danhSachCanh.add(new Edge(diemdau1, diemdau2, line2d, MAX));
			diemdau1.danhSachKe.add(diemdau2);
		}
	}
}
