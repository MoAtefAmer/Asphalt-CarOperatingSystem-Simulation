package main;

public class Reverse extends Process implements Runnable {
	String disk = "Reverse";

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Long start = System.nanoTime();
		this.getPCB().setPriority(3);
		this.getPCB().setStackPointer(Memory.add(disk));
		Asphalt.dispatcher.add(this);

		while (this != Asphalt.running) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.responseTime = (long) ((System.nanoTime() - start) * (Math.pow(10, -6)));
		System.out.println("RESPONSE TIME OF " + this.disk + " :   " + this.responseTime + " MilliSeconds");
		int size = Memory.memory.length - 1;
		if (Asphalt.memorys.memory[size].equals("Speed,0")) {
			Asphalt.memorys.memory[size - 2] = Asphalt.memorys.memory[size - 2].equals("Reverse,1") ? "Reverse,0"
					: "Reverse,1";
		}
		// System.err.println(this.getPCB().getStackPointer());
		// System.err.println(Asphalt.memorys.toString());

		this.getPCB().setStatus(status.TERMINATED);

		Memory.remove(this.getPCB().getStackPointer());
		this.turnaroundTime = (long) ((System.nanoTime() - start) * (Math.pow(10, -6)));
		System.out.println("TURNAROUND TIME OF " + this.disk + " :   " + this.turnaroundTime + " MilliSeconds");
		Asphalt.dispatcher.run();

	}

	public String toString() {
		return disk;
	}
}
