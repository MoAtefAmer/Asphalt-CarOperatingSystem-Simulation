package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class MainMenu extends JFrame implements Runnable {

	JButton btn1 = new JButton();
	JButton btn2 = new JButton();
	JButton btn3 = new JButton();
	JButton btnh = new JButton();

	public MainMenu() {
		setTitle("CHOOSE YOUR RIDE");
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("donkey.jpg"));
		getContentPane().add(label, BorderLayout.CENTER);
		this.setSize(490, 380);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnh = (JButton) e.getSource();
				try {
					CarGUI car = new CarGUI(64);
					memoryGui m = new memoryGui(64);
					car.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();

			}
		});

		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnh = (JButton) e.getSource();
				try {
					CarGUI car = new CarGUI(256);
					car.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();

			}
		});

		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnh = (JButton) e.getSource();
				try {
					CarGUI car = new CarGUI(512);
					car.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});

		btn1.setBorder(new LineBorder(Color.BLACK));
		btn2.setBorder(new LineBorder(Color.BLACK));
		btn3.setBorder(new LineBorder(Color.BLACK));
		btn1.setVisible(true);
		btn2.setVisible(true);
		btn3.setVisible(true);
		label.setLayout(new GridLayout(1, 3));
		label.add(btn1);
		label.add(btn2);
		label.add(btn3);
		btn1.setContentAreaFilled(false);
		btn2.setOpaque(false);
		btn2.setContentAreaFilled(false);
		btn3.setOpaque(false);
		btn3.setContentAreaFilled(false);
		this.getContentPane().add(label, BorderLayout.CENTER);
		label.setVisible(true);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		MainMenu x = new MainMenu();
	}

}
