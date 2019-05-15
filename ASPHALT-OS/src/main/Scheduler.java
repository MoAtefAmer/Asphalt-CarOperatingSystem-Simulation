package main;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Scheduler extends Process implements Runnable {

	static long totalTime;
	static long startTime;
	static long finalTime;
	static double utilization;
	String disk = "DISPATCHER";

	public Scheduler() {
		super();
	}

	@SuppressWarnings("deprecation")
	public static void add(Process p) {
		p.getPCB().setStatus(status.READY);
		p.stop();
		Asphalt.readyQueue.add(p);
		Asphalt.dispatcher.run();
	}

	@SuppressWarnings("deprecation")

	public static void remove(Process p) {
		Asphalt.blockedQueue.remove(p);
	}

	@SuppressWarnings("deprecation")
	public void run() {
		if (Asphalt.running != null)
			Asphalt.running.stop();
		this.getPCB().setPriority(0);
		this.getPCB().setStackPointer(Memory.add(disk));
		if (!Asphalt.readyQueue.isEmpty() && Asphalt.running != null) {
			Process process = Asphalt.readyQueue.peek();
			if (Asphalt.running != null && Asphalt.running.getPCB().getStatus().equals(status.TERMINATED)) {
				Asphalt.running = Asphalt.readyQueue.remove();
				Memory.remove(this.getPCB().getStackPointer());
				Asphalt.running.getPCB().setStatus(status.RUNNING);
				Asphalt.running.resume();
				this.getPCB().setStatus(status.TERMINATED);
				return;
			}
			if (Asphalt.readyQueue.peek().getPCB().getPriorty() >= Asphalt.running.getPCB().getPriorty()) {
				Asphalt.running.getPCB().setStatus(status.RUNNING);
				Asphalt.running.resume();
			} else {
				Asphalt.running.getPCB().setStatus(status.READY);
				Asphalt.running.suspend();

				Asphalt.readyQueue.add(Asphalt.running);
				Asphalt.running = Asphalt.readyQueue.remove();
				Asphalt.running.getPCB().setStatus(status.RUNNING);
				Asphalt.running.resume();

			}

			// running.resume();
		} else {
			if (!Asphalt.readyQueue.isEmpty() && Asphalt.running == null) {
				startTime = System.currentTimeMillis();
				Asphalt.running = Asphalt.readyQueue.remove();
				Asphalt.running.getPCB().setStatus(status.RUNNING);
				Asphalt.running.resume();

			} else {

				Asphalt.running = null;
				finalTime = System.currentTimeMillis();
				utilization = (((double) (finalTime - startTime)) / ((double) (finalTime - totalTime))) * 100;
				totalTime = System.currentTimeMillis();
			}

		}
		this.getPCB().setStatus(status.TERMINATED);
		Memory.remove(this.getPCB().getStackPointer());
	}

	public String toString() {
		return disk;
	}
}
