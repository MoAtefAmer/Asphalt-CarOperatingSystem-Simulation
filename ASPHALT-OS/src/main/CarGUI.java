package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CarGUI extends JFrame implements Runnable, ActionListener {

	private final int BUFFER_SIZE = 128000;
	private File soundFile;
	private AudioInputStream audioStream;
	private AudioFormat audioFormat;
	private SourceDataLine sourceLine;
	private final JButton radio = new JButton();
	private final JButton reverse = new JButton();
	private final JButton brake = new JButton();
	private final JButton gas = new JButton();
	private final JButton seatbelt = new JButton();
	private final JButton memory = new JButton();
	private boolean flagplay = false;
	Long currentFrame;
	Clip clip;
	String filePath = "trial.wav";
	AudioInputStream audioInputStream;
	private static int N;

	public CarGUI(int size) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		final Image backgroundImage = ImageIO.read(getClass().getResource("vromvrom.jpg"));
		setContentPane(new JPanel(new BorderLayout()) {
			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(backgroundImage, 0, 0, null);
			}
		});
		CarGUI.N = size;
		this.getContentPane().setLayout(null);
		this.setSize(550, 550);
		JPanel radioPanel = new JPanel();
		JPanel reversePanel = new JPanel();
		JPanel brakePanel = new JPanel();
		JPanel gasPanel = new JPanel();
		JPanel seatbeltPanel = new JPanel();
		JPanel memoryPanel = new JPanel();
		radioPanel.setSize(150, 100);
		radioPanel.setOpaque(false);
		radioPanel.setVisible(true);
		radioPanel.setLocation(115, 350);
		radioPanel.setLayout(new GridLayout(1, 1));
		reversePanel.setSize(150, 100);
		reversePanel.setOpaque(false);
		reversePanel.setVisible(true);
		reversePanel.setLocation(400, 175);
		reversePanel.setLayout(new GridLayout(1, 1));
		brakePanel.setSize(150, 100);
		brakePanel.setOpaque(false);
		brakePanel.setVisible(true);
		brakePanel.setLocation(265, 300);
		brakePanel.setLayout(new GridLayout(1, 1));
		gasPanel.setSize(150, 100);
		gasPanel.setOpaque(false);
		gasPanel.setVisible(true);
		gasPanel.setLocation(415, 300);
		gasPanel.setLayout(new GridLayout(1, 1));
		seatbeltPanel.setSize(100, 150);
		seatbeltPanel.setOpaque(false);
		seatbeltPanel.setVisible(true);
		seatbeltPanel.setLocation(25, 200);
		seatbeltPanel.setLayout(new GridLayout(1, 1));
		memoryPanel.setSize(150, 50);
		memoryPanel.setOpaque(false);
		memoryPanel.setVisible(true);
		memoryPanel.setLocation(200, 50);
		memoryPanel.setLayout(new GridLayout(1, 1));
		radio.setBorderPainted(false);
		radio.setOpaque(false);
		radio.setContentAreaFilled(false);
		radio.addActionListener(this);
		radio.setPreferredSize(new Dimension(100, 100));
		radioPanel.add(radio);
		reverse.setBorderPainted(false);
		reverse.setOpaque(false);
		reverse.setContentAreaFilled(false);
		reverse.addActionListener(this);
		reverse.setPreferredSize(new Dimension(100, 100));
		reversePanel.add(reverse);
		brake.setBorderPainted(false);
		brake.setOpaque(false);
		brake.setContentAreaFilled(false);
		brake.addActionListener(this);
		brake.setPreferredSize(new Dimension(100, 100));
		brakePanel.add(brake);
		gas.setBorderPainted(false);
		gas.setOpaque(false);
		gas.setContentAreaFilled(false);
		gas.addActionListener(this);
		gas.setPreferredSize(new Dimension(100, 100));
		gasPanel.add(gas);
		seatbelt.setBorderPainted(false);
		seatbelt.setOpaque(false);
		seatbelt.setContentAreaFilled(false);
		seatbelt.addActionListener(this);
		seatbelt.setPreferredSize(new Dimension(100, 100));
		seatbeltPanel.add(seatbelt);
		memory.setBorderPainted(false);
		memory.setOpaque(false);
		memory.setContentAreaFilled(false);
		memory.addActionListener(this);
		memory.setPreferredSize(new Dimension(100, 100));
		memoryPanel.add(memory);
		this.add(radioPanel);
		this.add(reversePanel);
		this.add(brakePanel);
		this.add(gasPanel);
		this.add(seatbeltPanel);
		this.add(memoryPanel);
		this.setVisible(true);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		// memoryGui n = new memoryGui(N);
		// n.run();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(radio) && !(flagplay)) {
			try {
				playSound2();
				flagplay = true;
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			if (e.getSource().equals(radio) && flagplay) {
				clip.stop();
				clip.close();
				flagplay = false;
			}
		}
		if (e.getSource().equals(brake)) {

			Breaks breaks = new Breaks();
			breaks.run();

		}
		if (e.getSource().equals(gas)) {

			Gas gas = new Gas();
			gas.run();
		}
		if (e.getSource().equals(seatbelt)) {
			// WearBelt wear = new WearBelt();
			// wear.run();
		}
		if (e.getSource().equals(reverse)) {
			ParkingSensor park = new ParkingSensor();
			park.run();
		}

	}

	public void playSound2() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
		clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		if (!flagplay) {
			clip.start();
		}
	}

	public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		CarGUI car = new CarGUI(64);
		memoryGui mem = new memoryGui(58);
	}

}
