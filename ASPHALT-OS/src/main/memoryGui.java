package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class memoryGui extends JFrame implements Runnable, ActionListener {
	private JPanel panel_1;
	private JButton btn1;
	private JPanel panel_2;
	private JButton flush;
	private JButton cpu;
	private Icon icon = new ImageIcon("donkey-icon.png");
	private Icon icon2 = new ImageIcon("reload-2-icon.png");
	private Icon icon3 = new ImageIcon("refresh2.png");
	private Icon icon4 = new ImageIcon("flush.png");
	private Icon icon5 = new ImageIcon("memorybg.png");
	private Icon icon6 = new ImageIcon("memorybg2.png");
	private BufferedImage background;
	String[] memory = Asphalt.memorys.memory;
	private JButton btnh = new JButton();
	private JButton btnNewButton_1;
	private int memorySize;
	private JPanel panel;
	private JList list;
	private String[] dummy;
	private JScrollPane scroll = new JScrollPane(list);
	private JLabel lblNewLabel;
	private JButton btne;
	private JTextArea textfield;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			memory[19] = Asphalt.memorys.memory[19];
			// list = new BackgroundImageList(memory);

			list.revalidate();
			list.repaint();
			textfield.revalidate();
			textfield.repaint();
			textfield.setText("Running now: " + Asphalt.running + "\n" + "Ready Queue: " + Asphalt.readyQueue.toString()
					+ "\n" + "Blocked Queue: " + Asphalt.blockedQueue.toString());
			this.revalidate();
			this.repaint();
			this.setVisible(true);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		}

	}

	public memoryGui(int size) {
		getContentPane().setBackground(new Color(102, 102, 0));
		this.setSize(500, 700);
		this.setTitle("System Memory");
		this.setResizable(true);
		memorySize = size;
		getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		dummy = new String[memorySize];

		list = new BackgroundImageList(memory);

		list.setFont(new Font("Arial", Font.HANGING_BASELINE, 13));
		// list.setOpaque(false);
		// list.setBackground(new Color(0, 0, 0, 0));
		// // list.setBackground(g);
		// list.setForeground(Color.WHITE);

		// list.setBackground(Color.blue);
		// list.setForeground(Color.orange);
		// getContentPane().add(panel_2, BorderLayout.WEST);

		Color x = list.getSelectionBackground();
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(51, 102, 0));
		getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(3, 1, 0, 0));

		textfield = new JTextArea();
		textfield.setEditable(false);
		textfield.setColumns(10);
		// textfield.setSize(100, 100);
		textfield.setText(Asphalt.running + "");

		panel_2.add(textfield);

		btnNewButton_1 = new JButton(icon3);

		btnNewButton_1.addActionListener(this);

		panel_2.add(btnNewButton_1);
		cpu = new JButton(icon);
		cpu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btne = (JButton) arg0.getSource();

				DynamicLineAndTimeSeriesChart x = new DynamicLineAndTimeSeriesChart("CPU Util");
				DynamicLineAndTimeSeriesChartM y = new DynamicLineAndTimeSeriesChartM("MEMORY Util");
				y.revalidate();
				y.repaint();
				y.setSize(700, 700);
				y.setVisible(true);
				x.revalidate();
				x.repaint();
				x.setSize(700, 700);
				x.setVisible(true);
				list.revalidate();
				list.repaint();
			}
		});

		panel_2.add(cpu);

		panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		panel.add(list);

		panel.add(new JScrollPane(list));

		int i = 0;
	}

	public void actionPerformed(ActionEvent arg0) {
		btnh = (JButton) arg0.getSource();
		memory[0] = "ooooooooooooh";
		memory[10] = "nice";
		memory[0] = "suckysucky";
		list.revalidate();
		list.repaint();
	}

}