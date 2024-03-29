package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import Model.Edge;
import Model.Graph;
import Model.Vertex;
import View.AdjustmentPanel;
import View.Main;
import View.ToolPanel;
import View.mainDrawPanel;

public class RadioAction implements ActionListener {
	ToolPanel toolPanel;
	mainDrawPanel mainPanel;

	public RadioAction(ToolPanel toolPanel, mainDrawPanel mainPanel) {
		this.toolPanel = toolPanel;
		this.mainPanel = mainPanel;
	}

	public void reset() {
		mainPanel.graph.danhSachDinh.clear();
		mainPanel.graph.danhSachCanh.clear();
		mainPanel.graph.mtk.clear();
		mainPanel.graph.weightMaxtrix.clear();
		mainDrawPanel.selected1 = null;
		mainDrawPanel.selected2 = null;
		AdjustmentPanel.textArea.setText("");
		toolPanel.jtfStartPoint.setText("");
		AdjustmentPanel.resulTextArea.setText("");
		AdjustmentPanel.jtfDiemdau1.setText("");
		AdjustmentPanel.jtfDiemdau2.setText("");
		AdjustmentPanel.jtfWeight.setText("");
		mainPanel.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == toolPanel.jrbCoHuong) {
			Main.type = "CoHuong";
			reset();
		}
		if (e.getSource() == toolPanel.jrbVoHuong) {
			Main.type = "VoHuong";
			reset();
		}
		if (e.getSource() == toolPanel.jrbBFS) {
			Main.algorithmType = "BFS";
			toolPanel.jlbEndPoint.setVisible(false);
			toolPanel.jtfEndPoint.setVisible(false);
		}
		if (e.getSource() == toolPanel.jrbDFS) {
			Main.algorithmType = "DFS";
			toolPanel.jlbEndPoint.setVisible(false);
			toolPanel.jtfEndPoint.setVisible(false);

		}
		if (e.getSource() == toolPanel.jrbDijkstra) {
			Main.algorithmType = "Dijkstra";
			toolPanel.jlbEndPoint.setVisible(true);
			toolPanel.jtfEndPoint.setVisible(true);

		}
		if (e.getSource() == toolPanel.jbStart) {
			for (Edge edge : mainPanel.graph.danhSachCanh) {
				edge.setColor(Color.BLACK);
				mainPanel.repaint();
			}
			if (Main.algorithmType.equals("DFS")) {
				for (Vertex vertex : mainPanel.graph.danhSachDinh) {
					vertex.setOrder(0);
					vertex.setColor(Color.BLACK);
				}
				StringBuilder StringBuffer = new StringBuilder();
				AlgorithmAction algorithmAction = new AlgorithmAction(mainPanel.graph.getMtk(),
						mainPanel.graph.getWeightMaxtrix());
				int orderTemp = 1;
				try {
					int start = Integer.parseInt(toolPanel.jtfStartPoint.getText());
					for (int i = start - 1; i < mainPanel.graph.getMtk().size(); i++) {
						if (!algorithmAction.daXet(i))
							StringBuffer.append(algorithmAction.DFS(i));
					}
					for (int i = 0; i < algorithmAction.saveArrayList.size(); i++) {
						int index = algorithmAction.saveArrayList.get(i);
						for (Edge edge : mainPanel.graph.danhSachCanh) {
							if (edge.diemdau1.index == index && edge.diemdau2.index == algorithmAction.truoc.get(index)
									|| edge.diemdau2.index == index
											&& edge.diemdau1.index == algorithmAction.truoc.get(index))
								edge.setColor(Color.MAGENTA);
							mainPanel.repaint();
						}
						mainPanel.repaint();
					}
					for (int i = 0; i < algorithmAction.saveArrayList.size(); i++) {
						for (Vertex vertex : mainPanel.graph.danhSachDinh) {
							if (vertex.index == algorithmAction.saveArrayList.get(i)) {
								vertex.setOrder(orderTemp++);
								if (vertex.getOrder() == 1)
									vertex.setColor(Color.RED);
								else if (i == algorithmAction.saveArrayList.size() - 1)
									vertex.setColor(Color.BLUE);
								else
									vertex.setColor(Color.MAGENTA);
							}
							mainPanel.repaint();
						}
						mainPanel.repaint();
					}
					StringBuffer.delete(StringBuffer.lastIndexOf("->"), StringBuffer.length());
					AdjustmentPanel.resulTextArea.setText(StringBuffer.toString());
				} catch (Exception e2) {
				}
				mainPanel.repaint();
			}
			if (Main.algorithmType.equals("BFS")) {
				for (Vertex vertex : mainPanel.graph.danhSachDinh) {
					vertex.setOrder(0);
					vertex.setColor(Color.BLACK);
				}
				StringBuilder StringBuffer = new StringBuilder();
				AlgorithmAction algorithmAction = new AlgorithmAction(mainPanel.graph.getMtk(),
						mainPanel.graph.getWeightMaxtrix());
				int orderTemp = 1;
				try {
					int start = Integer.parseInt(toolPanel.jtfStartPoint.getText());
					for (int i = start - 1; i < mainPanel.graph.getMtk().size(); i++) {
						if (!algorithmAction.daXet(i))
							StringBuffer.append(algorithmAction.BFS(i));
					}
					for (int i = 0; i < algorithmAction.saveArrayList.size(); i++) {
						int index = algorithmAction.saveArrayList.get(i);
						for (Edge edge : mainPanel.graph.danhSachCanh) {
							if (edge.diemdau1.index == index && edge.diemdau2.index == algorithmAction.truoc.get(index)
									|| edge.diemdau2.index == index
											&& edge.diemdau1.index == algorithmAction.truoc.get(index))
								edge.setColor(Color.MAGENTA);
							mainPanel.repaint();
						}
						mainPanel.repaint();
					}
					for (int i = 0; i < algorithmAction.saveArrayList.size(); i++) {
						for (Vertex vertex : mainPanel.graph.danhSachDinh) {
							if (vertex.index == algorithmAction.saveArrayList.get(i)) {
								vertex.setOrder(orderTemp++);
								if (vertex.getOrder() == 1)
									vertex.setColor(Color.RED);
								else if (i == algorithmAction.saveArrayList.size() - 1)
									vertex.setColor(Color.BLUE);
								else
									vertex.setColor(Color.MAGENTA);
							}
							mainPanel.repaint();
						}
						mainPanel.repaint();
					}
					StringBuffer.delete(StringBuffer.lastIndexOf("->"), StringBuffer.length());
					AdjustmentPanel.resulTextArea.setText(StringBuffer.toString());
				} catch (Exception e2) {
				}
			}
			if (Main.algorithmType.equals("Dijkstra")) {
				AlgorithmAction algorithmAction = new AlgorithmAction(mainPanel.graph.getMtk(),
						mainPanel.graph.getWeightMaxtrix());
				for (Vertex vertex : mainPanel.graph.danhSachDinh) {
					vertex.setColor(Color.BLACK);
					vertex.setOrder(0);
				}
				try {
					int start = Integer.parseInt(toolPanel.jtfStartPoint.getText());
					int end = Integer.parseInt(toolPanel.jtfEndPoint.getText());
					AdjustmentPanel.resulTextArea.setText("Duong di ngan nhat tu " + start + " den " + end + " la: ");

					AdjustmentPanel.resulTextArea.append("\n" + algorithmAction.Dijkstra(start - 1, end - 1));
					if (algorithmAction.distance.get(end - 1) != Graph.MAX)
						AdjustmentPanel.resulTextArea.append("\n" + "Length: " + algorithmAction.distance.get(end - 1));
					int orderTemp = 1;
					for (int i = algorithmAction.saveArrayList.size() - 1; i > -1; i--) {
						for (Vertex vertex : mainPanel.graph.danhSachDinh) {
							if (vertex.index == algorithmAction.saveArrayList.get(i)) {
								vertex.setOrder(orderTemp++);
								if (vertex.getOrder() == 1)
									vertex.setColor(Color.RED);
								else if (i == 0)
									vertex.setColor(Color.BLUE);
								else
									vertex.setColor(Color.GREEN);
							}
							mainPanel.repaint();
						}
						mainPanel.repaint();
					}
					mainPanel.repaint();
					for (int i = algorithmAction.saveArrayList.size() - 1; i > 0; i--) {
						if (Main.type == "VoHuong") {
							for (Edge edge : mainPanel.graph.danhSachCanh) {
								if (edge.diemdau1.index == algorithmAction.saveArrayList.get(i)
										&& edge.diemdau2.index == algorithmAction.saveArrayList.get(i - 1)
										|| edge.diemdau2.index == algorithmAction.saveArrayList.get(i)
												&& edge.diemdau1.index == algorithmAction.saveArrayList.get(i - 1))
									edge.setColor(Color.GREEN);
								mainPanel.repaint();
							}
							mainPanel.repaint();
						} else if (Main.type == "CoHuong") {
							for (Edge edge : mainPanel.graph.danhSachCanh) {
								if (edge.diemdau1.index == algorithmAction.saveArrayList.get(i)
										&& edge.diemdau2.index == algorithmAction.saveArrayList.get(i - 1))
									edge.setColor(Color.GREEN);
								mainPanel.repaint();
							}
							mainPanel.repaint();
						}
					}
					mainPanel.repaint();

				} catch (Exception e2) {
				}
				toolPanel.jtfStartPoint.setText("");
				toolPanel.jtfEndPoint.setText("");
			}
		}
		if (e.getSource() == toolPanel.jbClear) {
			reset();
		}
		if (e.getSource() == toolPanel.jrbXoaDiem) {
			toolPanel.jlbDiemCuoi.setVisible(false);
			toolPanel.jtfDiemCuoi.setVisible(false);
			toolPanel.jtfDiemDau.setText("");
			toolPanel.jtfDiemCuoi.setText("");
			Main.remove = "XoaDiem";
		}
		if (e.getSource() == toolPanel.jrbXoaCanh) {
			toolPanel.jlbDiemCuoi.setVisible(true);
			toolPanel.jtfDiemCuoi.setVisible(true);
			toolPanel.jtfDiemDau.setText("");
			toolPanel.jtfDiemCuoi.setText("");
			Main.remove = "XoaCanh";
		}
		if (e.getSource() == toolPanel.jbRemove) {
			if (Main.remove == "XoaCanh") {
				try {
					int d1 = Integer.parseInt(toolPanel.jtfDiemDau.getText());
					int d2 = Integer.parseInt(toolPanel.jtfDiemCuoi.getText());
					if (Main.type == "VoHuong") {
						Iterator<Edge> itr = mainPanel.graph.danhSachCanh.iterator();
						while (itr.hasNext()) {
							Edge edge = itr.next();
							if (edge.diemdau1.index + 1 == d1 && edge.diemdau2.index + 1 == d2
									|| edge.diemdau2.index + 1 == d1 && edge.diemdau1.index + 1 == d2) {
								itr.remove();
								mainPanel.graph.mtk.get(d1 - 1).set(d2 - 1, 0);
								mainPanel.graph.mtk.get(d2 - 1).set(d1 - 1, 0);
								mainPanel.graph.weightMaxtrix.get(d1 - 1).set(d2 - 1, Graph.MAX);
								mainPanel.graph.weightMaxtrix.get(d2 - 1).set(d1 - 1, Graph.MAX);
								mainPanel.MatrixChange(mainPanel.graph);
								mainPanel.repaint();
							}
						}
						mainPanel.repaint();
					} else if (Main.type == "CoHuong") {
						Iterator<Edge> itr = mainPanel.graph.danhSachCanh.iterator();
						while (itr.hasNext()) {
							Edge edge = itr.next();
							if (edge.diemdau1.index + 1 == d1 && edge.diemdau2.index + 1 == d2) {
								itr.remove();
								mainPanel.graph.mtk.get(d1 - 1).set(d2 - 1, 0);
								mainPanel.graph.weightMaxtrix.get(d1 - 1).set(d2 - 1, Graph.MAX);
								mainPanel.MatrixChange(mainPanel.graph);
								mainPanel.repaint();
							}
						}
						mainPanel.repaint();
					}
					toolPanel.jtfDiemDau.setText("");
					toolPanel.jtfDiemCuoi.setText("");
				} catch (Exception e2) {
				}
			} else if (Main.remove == "XoaDiem") {
				try {
					int diem = Integer.parseInt(toolPanel.jtfDiemDau.getText());
					mainPanel.graph.mtk.remove(diem - 1);
					mainPanel.graph.weightMaxtrix.remove(diem - 1);
					for (int i = 0; i < mainPanel.graph.mtk.size(); i++) {
						mainPanel.graph.mtk.get(i).remove(diem - 1);
						mainPanel.graph.weightMaxtrix.get(i).remove(diem - 1);
					}
					mainPanel.MatrixChange(mainPanel.graph);
					mainPanel.repaint();
					Iterator<Edge> itr = mainPanel.graph.danhSachCanh.iterator();
					while (itr.hasNext()) {
						Edge edge = itr.next();
						if (edge.diemdau1.index + 1 == diem || edge.diemdau2.index + 1 == diem) {
							itr.remove();
							mainPanel.repaint();
						}
					}
					Iterator<Vertex> itr1 = mainPanel.graph.danhSachDinh.iterator();
					while (itr1.hasNext()) {
						Vertex vertex = itr1.next();
						if (vertex.index + 1 == diem) {
							itr1.remove();
							mainPanel.repaint();
						}

					}
					for (int i = diem - 1; i < mainPanel.graph.danhSachDinh.size(); i++) {
						Vertex vertex = mainPanel.graph.danhSachDinh.get(i);
						vertex.setIndex(vertex.index - 1);
						mainPanel.repaint();
					}
					mainPanel.repaint();
					toolPanel.jtfDiemDau.setText("");
				} catch (Exception e2) {
				}
			}
		}
	}
}
