package View;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Model.Edge;

public class AdjustmentPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel jlbWeight, jlbDiemdau1, jlbDiemdau2;
	public static JTextField jtfDiemdau1;
	public static JTextField jtfDiemdau2;
	public static JTextField jtfWeight;
	JButton addButton;
	mainDrawPanel mainPanel;
	public static JTextArea textArea;
	public static JTextArea resulTextArea;

	public AdjustmentPanel(mainDrawPanel mainPanel) {
		this.mainPanel = mainPanel;
		setLayout(new GridLayout(3, 1));
		JPanel contentPanel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		contentPanel.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		contentPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		setBorder(BorderFactory.createTitledBorder("Adjustment"));
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		contentPanel.add(jlbDiemdau1 = new JLabel("Diem dau 1: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		contentPanel.add(jtfDiemdau1 = new JTextField(5), gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		contentPanel.add(jlbDiemdau2 = new JLabel("Diem dau 2: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		contentPanel.add(jtfDiemdau2 = new JTextField(5), gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 2;
		contentPanel.add(jlbWeight = new JLabel("Trong so: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		contentPanel.add(jtfWeight = new JTextField(5), gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		contentPanel.add(addButton = new JButton("Set"), gbc);
		jtfDiemdau1.setText("");
		jtfDiemdau2.setText("");
		jtfWeight.setText("");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int d1 = Integer.parseInt(jtfDiemdau1.getText());
					int d2 = Integer.parseInt(jtfDiemdau2.getText());
					int w = Integer.parseInt(jtfWeight.getText());
					if (Main.type == "CoHuong") {
						for (Edge edge : mainPanel.graph.danhSachCanh) {
							if (mainPanel.graph.danhSachDinh.indexOf(edge.diemdau1) + 1 == d1
									&& mainPanel.graph.danhSachDinh.indexOf(edge.diemdau2) + 1 == d2) {
								edge.setWeight(w);
								mainPanel.graph.adjustWeightMaxtrix(d1 - 1, d2 - 1, w);
							}
							mainPanel.repaint();
						}
						mainPanel.repaint();
					} else if (Main.type == "VoHuong") {
						for (Edge edge : mainPanel.graph.danhSachCanh) {
							if (mainPanel.graph.danhSachDinh.indexOf(edge.diemdau1) + 1 == d1
									&& mainPanel.graph.danhSachDinh.indexOf(edge.diemdau2) + 1 == d2
									|| mainPanel.graph.danhSachDinh.indexOf(edge.diemdau2) + 1 == d1
											&& mainPanel.graph.danhSachDinh.indexOf(edge.diemdau1) + 1 == d2) {
								edge.setWeight(w);
								mainPanel.graph.adjustWeightMaxtrix(d1 - 1, d2 - 1, w);
								mainPanel.graph.adjustWeightMaxtrix(d2 - 1, d1 - 1, w);
							}
							mainPanel.repaint();
						}
						mainPanel.repaint();
					}

				} catch (Exception e1) {
				}
			}

		});
		add(contentPanel, BorderLayout.NORTH);

		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(BorderFactory.createTitledBorder("Matrix"));
		textArea = new JTextArea(7, 10);
		JScrollPane pane = new JScrollPane(textArea);
		statusPanel.add(pane);
		textArea.setEditable(false);
		add(statusPanel, BorderLayout.CENTER);

		JPanel resultPanel = new JPanel();
		resultPanel.setBorder(BorderFactory.createTitledBorder("Algorithm Result"));
		resulTextArea = new JTextArea(7, 10);
		JScrollPane pane1 = new JScrollPane(resulTextArea);
		resultPanel.add(pane1);
		resulTextArea.setEditable(false);
		add(resultPanel, BorderLayout.WEST);

	}
}
