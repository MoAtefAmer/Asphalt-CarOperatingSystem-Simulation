package main;

import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;

public abstract class Process extends Thread implements Comparable<Process> {
	private PCB PCB;
	Long turnaroundTime;
	Long responseTime;
	String disk;

	public Process() {
		PCB = new PCB(9247, 15, new ArrayList<>());
	}

	public abstract void run();

	public int compareTo(Process p) {
		if (getPCB().getPriorty() > p.getPCB().getPriorty()) {
			return 1;
		} else if (getPCB().getPriorty() < p.getPCB().getPriorty()) {
			return -1;
		} else {
			return 0;
		}
	}

	public PCB getPCB() {
		return PCB;
	}

	public void setPCB(PCB pCB) {
		PCB = pCB;
	}

}
