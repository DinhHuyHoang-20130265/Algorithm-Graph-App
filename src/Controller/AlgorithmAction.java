package Controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import Model.Graph;

public class AlgorithmAction {
	ArrayList<ArrayList<Integer>> mtk;
	static ArrayList<ArrayList<Integer>> ke;
	static ArrayList<Boolean> chuaXet;
	ArrayList<ArrayList<Integer>> weightMaxtrix;
	ArrayList<Integer> distance;
	ArrayList<Integer> truoc;
	ArrayList<Integer> saveArrayList;

	public AlgorithmAction(ArrayList<ArrayList<Integer>> mtk, ArrayList<ArrayList<Integer>> weightMaxtrix) {
		this.mtk = mtk;
		this.weightMaxtrix = weightMaxtrix;
		ke = new ArrayList<ArrayList<Integer>>();
		chuaXet = new ArrayList<Boolean>();
		distance = new ArrayList<Integer>();
		truoc = new ArrayList<Integer>();
		saveArrayList = new ArrayList<Integer>();
		for (int i = 0; i < mtk.size(); i++) {
			ke.add(new ArrayList<Integer>());
		}
		// thêm nội dung cho danh sách kề
		for (int i = 0; i < mtk.size(); i++) {
			for (int j = 0; j < mtk.size(); j++) {
				if (ke(i, j) == true)
					ke.get(i).add(j);
			}
		}
		// tất cả các đỉnh đều có chuaXet=true;
		for (int i = 0; i < mtk.size(); i++) {
			chuaXet.add(true);
		}
		// khởi tạo distance từ đỉnh start đến đỉnh thứ i
		for (int i = 0; i < mtk.size(); i++) {
			distance.add(Graph.MAX);
		}
		// khởi tạo mảng lưu vết trước
		for (int i = 0; i < mtk.size(); i++) {
			truoc.add(-1);
		}
	}

	public boolean daXet(int point) {
		return !chuaXet.get(point);
	}

	public boolean ke(int x, int y) {
		return this.mtk.get(x).get(y) == 1;

	}

	public void setMtk(ArrayList<ArrayList<Integer>> mtk) {
		this.mtk = mtk;

	}

	public ArrayList<ArrayList<Integer>> getWeightMaxtrix() {
		return this.weightMaxtrix;
	}

	public ArrayList<ArrayList<Integer>> getMtk() {
		return this.mtk;
	}

	public String DFS(int start) {
		StringBuilder stringBuilder = new StringBuilder();
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(start);
		chuaXet.set(start, false);
		while (!stack.isEmpty()) {
			int temp = stack.peek();
			stringBuilder.append(temp + 1 + "->");
			saveArrayList.add(temp);
			stack.pop();
			for (int u : ke.get(temp)) {
				if (chuaXet.get(u)) {
					chuaXet.set(u, false);
					truoc.set(u, temp);
					stack.push(u);
				}
			}
		}
		return stringBuilder.toString();

	}

	public String BFS(int start) {
		StringBuilder stringBuilder = new StringBuilder();
		Queue<Integer> queue = new LinkedList<Integer>();
		chuaXet.set(start, false);
		queue.add(start);
		while (!queue.isEmpty()) {
			int current = queue.poll();
			stringBuilder.append(current + 1 + "->");
			saveArrayList.add(current);
			for (int i : ke.get(current)) {
				if (chuaXet.get(i)) {
					chuaXet.set(i, false);
					truoc.set(i, current);
					queue.add(i);
				}
			}
		}
		return stringBuilder.toString();
	}

	public String Dijkstra(int start, int end) {
		StringBuilder stringBuilder = new StringBuilder();
		int u = 0, min = 0;
		for (int i = 0; i < mtk.size(); i++) {
			distance.set(i, weightMaxtrix.get(start).get(i));
			truoc.set(i, start);
		}
		truoc.set(start, -1);
		distance.set(start, 0);
		chuaXet.set(start, false);
		while (chuaXet.get(end)) {
			min = Graph.MAX;
			for (int i = 0; i < mtk.size(); i++) {
				if (chuaXet.get(i) && min > distance.get(i)) {
					u = i;
					min = distance.get(i);
				}
			}

			ArrayList<Integer> tempArrayList = new ArrayList<>(distance);
			chuaXet.set(u, false);
			if (chuaXet.get(end)) {
				for (int i = 0; i < mtk.size(); i++) {
					if (chuaXet.get(i) && (distance.get(u) + weightMaxtrix.get(u).get(i)) < distance.get(i)) {
						distance.set(i, distance.get(u) + weightMaxtrix.get(u).get(i));
						truoc.set(i, u);
					}
				}
			}
			if (tempArrayList.equals(distance))
				break;
		}
		if (distance.get(end) == 10e6 || truoc.get(end) == -1) {
			stringBuilder.append("Khong co duong di");
			return stringBuilder.toString();
		} else {
			stringBuilder.append((end + 1) + " >- ");
			saveArrayList.add(end);
			int i = truoc.get(end);
			while (i != start) {
				saveArrayList.add(i);
				stringBuilder.append((i + 1) + " >- ");
				i = truoc.get(i);
			}
			saveArrayList.add(start);
			stringBuilder.append((start + 1));
			return stringBuilder.reverse().toString();
		}

	}
}
