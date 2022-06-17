package View;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static mainDrawPanel mainPanel;
	ToolPanel toolPanel;
	AdjustmentPanel adjustmentPanel;
	public static JPanel panel;
	public static String type = "VoHuong";
	public static String algorithmType = "BFS";
	public static String remove = "XoaCanh";

	public Main() {
		super("Project Giua ki Mon LTDT");
		mainPanel = new mainDrawPanel();
		toolPanel = new ToolPanel(mainPanel);
		adjustmentPanel = new AdjustmentPanel(mainPanel);
		panel = new JPanel();
		panel.setLayout(new BorderLayout(10, 10));
		panel.add(toolPanel, BorderLayout.NORTH);
		panel.add(mainPanel, BorderLayout.CENTER);
		panel.add(adjustmentPanel, BorderLayout.WEST);
		setContentPane(panel);
		setSize(1000, 700);
		setVisible(true);
		setLocationRelativeTo(null);
		setMinimumSize(getSize());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Main();
	}
}
