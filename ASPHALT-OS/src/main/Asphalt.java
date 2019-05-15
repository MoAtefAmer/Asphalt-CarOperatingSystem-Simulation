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
import java.util.PriorityQueue;

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

public class Asphalt extends JFrame implements Runnable, ActionListener {

	static Scheduler dispatcher = new Scheduler();
	static Memory memorys = new Memory(20);
	static Semaphore mutex = new Semaphore(true);
	static PriorityQueue<Process> readyQueue;
	static PriorityQueue<Process> blockedQueue;
	static Process running;
	static Asphalt car;
	static memoryGui mem;
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
	static double utilization = 0.0;
	Long currentFrame;
	Clip clip;
	String filePath = "trial.wav";
	AudioInputStream audioInputStream;
	private static int N;

	public Asphalt(int size) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		final Image backgroundImage = ImageIO.read(getClass().getResource("vromvrom.jpg"));
		setContentPane(new JPanel(new BorderLayout()) {
			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(backgroundImage, 0, 0, null);
			}
		});
		this.N = size;
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
			Thread t4 = new Thread(breaks);
			t4.start();

		}
		if (e.getSource().equals(gas)) {

			Gas gas = new Gas();
			Thread t4 = new Thread(gas);
			t4.start();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SpeedWarning speed = new SpeedWarning();
			Thread te = new Thread(speed);
			te.start();
		}
		if (e.getSource().equals(seatbelt)) {
			WearBelt sensor = new WearBelt();
			Thread t4 = new Thread(sensor);
			t4.start();

			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SeatbeltWarning belt = new SeatbeltWarning();
			Thread ts = new Thread(belt);
			ts.start();
		}
		if (e.getSource().equals(reverse)) {
			Reverse r = new Reverse();
			Thread t = new Thread(r);
			t.run();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ParkingSensor sensor = new ParkingSensor();
			Thread t4 = new Thread(sensor);
			t4.start();

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

	public static void main(String[] args)
			throws InterruptedException, IOException, UnsupportedAudioFileException, LineUnavailableException {
		readyQueue = new PriorityQueue<>();
		blockedQueue = new PriorityQueue<>();
		SpeedWarning speed = new SpeedWarning();
		SeatbeltWarning belt = new SeatbeltWarning();
		Gas gas = new Gas();
		Breaks breaks = new Breaks();
		Breaks breaks2 = new Breaks();
		ParkingSensor park = new ParkingSensor();
		WearBelt wear = new WearBelt();
		WearBelt wear1 = new WearBelt();
		ParkingSensor sens = new ParkingSensor();
		mem = new memoryGui(60);
		car = new Asphalt(64);
		Thread t = new Thread(mem);
		Thread t2 = new Thread(car);

		t.start();
		t2.run();

		int total = 0;
		int run = 0;
		while (true) {
			total++;
			if (running != null)
				run++;
			Thread.sleep(100);
			if (total == 40) {
				utilization = (((double) (run)) / total) * 100;
				total = 0;
				run = 0;
			}
		}
	}

}
