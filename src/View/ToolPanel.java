package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Controller.RadioAction;

public class ToolPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JRadioButton jrbBFS, jrbDFS, jrbDijkstra, jrbVoHuong, jrbCoHuong, jrbXoaDiem, jrbXoaCanh;
	public JButton jbStart, jbClear;
	JButton jbAddWeight;
	public JButton jbRemove;
	JLabel jlbStartPoint;
	public JLabel jlbEndPoint;
	JLabel jlbDiemDau;
	public JLabel jlbDiemCuoi;
	public JTextField jtfStartPoint, jtfEndPoint, jtfDiemDau, jtfDiemCuoi;
	JPanel graphTypePanel, algorithmTypePanel, startRunPanel, removePanel;
	mainDrawPanel mainPanel;

	public ToolPanel(mainDrawPanel mainPanel) {
		this.mainPanel = mainPanel;
		RadioAction radioAction = new RadioAction(this, mainPanel);
		setBorder(BorderFactory.createTitledBorder("Action"));
		setLayout(new GridLayout(1, 3));
		graphTypePanel = new JPanel();
		graphTypePanel.setLayout(new GridLayout(2, 1));
		graphTypePanel.setBorder(BorderFactory.createTitledBorder("Graph Type"));
		graphTypePanel.add(jrbVoHuong = new JRadioButton("Vo Huong"));
		graphTypePanel.add(jrbCoHuong = new JRadioButton("Co Huong"));
		ButtonGroup jrbGroup = new ButtonGroup();
		jrbGroup.add(jrbCoHuong);
		jrbGroup.add(jrbVoHuong);
		jrbVoHuong.setSelected(true);
		add(graphTypePanel);

		algorithmTypePanel = new JPanel();
		algorithmTypePanel.setLayout(new GridLayout(3, 1));
		algorithmTypePanel.setBorder(BorderFactory.createTitledBorder("Algorithm Type"));
		algorithmTypePanel.add(jrbBFS = new JRadioButton("BFS"));
		algorithmTypePanel.add(jrbDFS = new JRadioButton("DFS"));
		algorithmTypePanel.add(jrbDijkstra = new JRadioButton("Dijkstra"));
		ButtonGroup jrbGroup1 = new ButtonGroup();
		jrbGroup1.add(jrbBFS);
		jrbGroup1.add(jrbDFS);
		jrbGroup1.add(jrbDijkstra);
		jrbBFS.setSelected(true);
		jrbCoHuong.addActionListener(radioAction);
		jrbVoHuong.addActionListener(radioAction);
		jrbBFS.addActionListener(radioAction);
		jrbDFS.addActionListener(radioAction);
		jrbDijkstra.addActionListener(radioAction);
		add(algorithmTypePanel);

		startRunPanel = new JPanel();
		startRunPanel.setLayout(new GridLayout(3, 2));
		startRunPanel.setBorder(BorderFactory.createTitledBorder("Run Algorithm"));
		startRunPanel.add(jlbStartPoint = new JLabel("Start point: "));
		startRunPanel.add(jtfStartPoint = new JTextField(10));
		startRunPanel.add(jlbEndPoint = new JLabel("End point: "));
		startRunPanel.add(jtfEndPoint = new JTextField(10));
		startRunPanel.add(jbStart = new JButton("Start"));
		startRunPanel.add(jbClear = new JButton("Clear"));
		jlbEndPoint.setVisible(false);
		jtfEndPoint.setVisible(false);
		jbClear.addActionListener(radioAction);
		jbStart.addActionListener(radioAction);
		add(startRunPanel);

		removePanel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		removePanel.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		removePanel.add(jrbXoaDiem = new JRadioButton("Xoa Diem"), gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		removePanel.add(jrbXoaCanh = new JRadioButton("Xoa Canh"), gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		removePanel.add(jlbDiemDau = new JLabel("Diem dau: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		removePanel.add(jtfDiemDau = new JTextField(3), gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		removePanel.add(jlbDiemCuoi = new JLabel("Diem cuoi: "), gbc);
		gbc.gridx = 3;
		gbc.gridy = 1;
		removePanel.add(jtfDiemCuoi = new JTextField(3), gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		removePanel.add(jbRemove = new JButton("Remove"), gbc);
		ButtonGroup group2 = new ButtonGroup();
		group2.add(jrbXoaCanh);
		group2.add(jrbXoaDiem);
		jrbXoaCanh.setSelected(true);
		jrbXoaCanh.addActionListener(radioAction);
		jrbXoaDiem.addActionListener(radioAction);
		jbRemove.addActionListener(radioAction);

		add(removePanel);
	}
}
