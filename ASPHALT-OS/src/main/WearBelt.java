package main;


public class WearBelt extends Process implements Runnable {
	String disk = "wearing or taking off SeatBelt";

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Long start = System.nanoTime();
		this.getPCB().setPriority(4);
		this.getPCB().setStackPointer(Memory.add(disk));
		Asphalt.dispatcher.add(this);

		int size = Memory.memory.length - 1;
		this.responseTime = (long) ((System.nanoTime() - start) * (Math.pow(10, -6)));
		System.out.println("RESPONSE TIME OF " + this.disk + " :   " + this.responseTime + " MilliSeconds");
		// System.err.println(this.getPCB().getStackPointer());
		// System.err.println(Asphalt.memorys.toString());
		while (this != Asphalt.running) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Asphalt.memorys.memory[size - 1] = Asphalt.memorys.memory[size - 1].equals("Belt,1") ? "Belt,0" : "Belt,1";
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
