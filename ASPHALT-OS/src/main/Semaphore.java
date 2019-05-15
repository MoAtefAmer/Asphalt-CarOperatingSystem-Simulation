package main;

import java.util.LinkedList;
import java.util.Queue;

public class Semaphore {
	private boolean value;
	private int ownerID;

	public Semaphore(boolean value) {
		this.value = true;

	}

	void semWait(Process p) {
		if (value) {
			ownerID = p.getPCB().getID();
			value = false;
		} else {
			p.suspend();
			p.getPCB().setStatus(status.BLOCKED);
			Asphalt.blockedQueue.add(p);
			Asphalt.running = null;
			Asphalt.dispatcher.run();
		}
	}

	void semSignal(Process p) {
		if (Asphalt.blockedQueue.isEmpty())
			value = true;
		else {
			Process now = (Process) Asphalt.blockedQueue.remove();
			now.getPCB().setStatus(status.READY);
			Asphalt.readyQueue.add(now);
		}

	}
}
