package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import Model.Edge;
import Model.Graph;
import Model.Vertex;

public class mainDrawPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Graph graph = new Graph(new ArrayList<Vertex>(), new ArrayList<Edge>(), new ArrayList<ArrayList<Integer>>(),
			new ArrayList<ArrayList<Integer>>());
	public static Vertex selected1, selected2;
	static Color defaultColor = Color.BLACK;

	public void MatrixChange(Graph graph) {
		AdjustmentPanel.textArea.setText("");
		for (int d = 0; d < graph.getMtk().size(); d++) {
			for (int j = 0; j < graph.getMtk().size(); j++) {
				AdjustmentPanel.textArea.append(String.valueOf(graph.getMtk().get(d).get(j)));
			}
			AdjustmentPanel.textArea.append("\n");
		}
	}

	protected double angleBetween(Ellipse2D from, Ellipse2D to) {
		double x = from.getCenterX();
		double y = from.getCenterY();

		double deltaX = to.getCenterX() - x;
		double deltaY = to.getCenterY() - y;

		double rotation = -Math.atan2(deltaX, deltaY);
		rotation = Math.toRadians(Math.toDegrees(rotation) + 180);

		return rotation;
	}

	protected Point2D getPointOnCircle(Ellipse2D center, double radians, double radius) {

		double x = center.getCenterX();
		double y = center.getCenterY();

		radians = radians - Math.toRadians(90.0); // 0 becomes the top
		// Calculate the outter point of the line
		double xPosy = Math.round((float) (x + Math.cos(radians) * radius));
		double yPosy = Math.round((float) (y + Math.sin(radians) * radius));

		return new Point2D.Double(xPosy, yPosy);

	}

	public Line2D createLine(Ellipse2D e1, Ellipse2D e2) {
		double from = angleBetween(e1, e2);
		double to = angleBetween(e2, e1);

		Point2D pointFrom = getPointOnCircle(e1, from, 15);
		Point2D pointTo = getPointOnCircle(e2, to, 15);

		Line2D line2d = new Line2D.Double(pointFrom, pointTo);
		return line2d;
	}

	public mainDrawPanel() {
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLoweredBevelBorder());

		MouseAdapter mouse = new MouseAdapter() {
			private int x, dx;
			private int y, dy;

			@Override
			public void mousePressed(MouseEvent e) {
				requestFocusInWindow();
				for (int i = graph.danhSachDinh.size() - 1; i > -1; i--) {
					if (graph.danhSachDinh.get(i).el.contains(e.getX(), e.getY())) {
						x = e.getX();
						y = e.getY();
						return;
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				x = 0;
				y = 0;
				dx = 0;
				dy = 0;
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				for (int i = graph.danhSachDinh.size() - 1; i > -1; i--) {
					dx = e.getX() - x;
					dy = e.getY() - y;
					Vertex ellipse2d = graph.danhSachDinh.get(i);
					if (ellipse2d.el.contains(x, y)) {
						Ellipse2D eltemp = new Ellipse2D.Double(ellipse2d.el.getX() + dx, ellipse2d.el.getY() + dy, 30,
								30);
						graph.suaDinh(eltemp, i);
						for (int j = 0; j < graph.danhSachCanh.size(); j++) {
							Edge edge = graph.danhSachCanh.get(j);
							if (edge.diemdau1.el == ellipse2d.el) {
								Line2D line2d = createLine(ellipse2d.el, edge.diemdau2.el);
								graph.suaCanh(line2d, j);
							} else if (edge.diemdau2.el == ellipse2d.el) {
								Line2D line2d = createLine(edge.diemdau1.el, ellipse2d.el);
								graph.suaCanh(line2d, j);
							}
						}
						repaint();
						x += dx;
						y += dy;
					}
				}
				repaint();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				requestFocusInWindow();
				try {
					for (int i = 0; i < graph.danhSachDinh.size(); i++) // xét tất cả các đỉnh trong đồ thị
					{
						if (graph.danhSachDinh.get(i).el.contains(e.getX(), e.getY())) {
							if (selected1 == null) // chưa chọn diemdau1
							{
								selected1 = graph.danhSachDinh.get(i);
								return;
							} else {
								if (selected1 != null && graph.danhSachDinh.get(i) != selected1) {
									selected2 = graph.danhSachDinh.get(i);
									Line2D line2d = createLine(selected1.el, selected2.el);
									graph.themCanh(selected1, selected2, line2d);
									selected1 = null;
									selected2 = null;
								}
								MatrixChange(graph);
								return;
							}
						}
						repaint();
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
				Ellipse2D el = new Ellipse2D.Double(e.getX(), e.getY(), 30, 30);
				graph.themDinh(el);
				graph.themDinhWeightMax();
				MatrixChange(graph);
				repaint();
			}
		};
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (Vertex vertex : graph.danhSachDinh) {
			g2.setColor(vertex.getColor());
			g2.draw(vertex.el);
			if (vertex.getOrder() != 0)
				g2.drawString(vertex.getOrder() + "", (int) (vertex.el.getX()), (int) (vertex.el.getY()));
			g2.drawString("" + (graph.getDanhSachDinh().indexOf(vertex) + 1), (int) (vertex.el.getCenterX() - 5),
					(int) (vertex.el.getCenterY() + 5));
			g2.setColor(defaultColor);
		}
		for (Edge edge : graph.danhSachCanh) {
			g2.setColor(edge.getColor());
			if (Main.type.equals("CoHuong")) {
				int dx = (int) (edge.line2d.getX2() - edge.line2d.getX1());
				int dy = (int) (edge.line2d.getY2() - edge.line2d.getY1());
				double D = Math.sqrt(dx * dx + dy * dy);
				double xm = D - 10, xn = xm, ym = 5, yn = -5, x;
				double sin = dy / D, cos = dx / D;
				x = xm * cos - ym * sin + edge.line2d.getX1();
				ym = xm * sin + ym * cos + edge.line2d.getY1();
				xm = x;

				x = xn * cos - yn * sin + edge.line2d.getX1();
				yn = xn * sin + yn * cos + edge.line2d.getY1();
				xn = x;
				int[] xpoints = { (int) edge.line2d.getX2(), (int) xm, (int) xn };
				int[] ypoints = { (int) edge.line2d.getY2(), (int) ym, (int) yn };
				g2.fillPolygon(xpoints, ypoints, 3);
				g2.draw(edge.line2d);
				if (edge.weight < Graph.MAX) {
					g2.drawString("" + edge.weight,
							(int) (edge.diemdau1.el.getCenterX() + edge.diemdau2.el.getCenterX()) / 2,
							(int) (edge.diemdau1.el.getCenterY() + edge.diemdau2.el.getCenterY()) / 2);
				} else
					g2.drawString("" + "null",
							(int) (edge.diemdau1.el.getCenterX() + edge.diemdau2.el.getCenterX()) / 2,
							(int) (edge.diemdau1.el.getCenterY() + edge.diemdau2.el.getCenterY()) / 2);
			} else if (Main.type.equals("VoHuong")) {
				g2.draw(edge.line2d);
				if (edge.weight < Graph.MAX) {
					g2.drawString("" + edge.weight,
							(int) (edge.diemdau1.el.getCenterX() + edge.diemdau2.el.getCenterX()) / 2,
							(int) (edge.diemdau1.el.getCenterY() + edge.diemdau2.el.getCenterY()) / 2);
				} else
					g2.drawString("" + "null",
							(int) (edge.diemdau1.el.getCenterX() + edge.diemdau2.el.getCenterX()) / 2,
							(int) (edge.diemdau1.el.getCenterY() + edge.diemdau2.el.getCenterY()) / 2);
			}
			g2.setColor(defaultColor);
		}
	}
}
