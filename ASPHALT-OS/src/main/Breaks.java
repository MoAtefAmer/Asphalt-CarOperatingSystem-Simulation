package main;

public class Breaks extends Process implements Runnable {
	String disk = "Slowing down";

	@Override
	public void run() {
		Long start = System.nanoTime();
		// TODO Auto-generated method stub
		this.getPCB().setPriority(1);
		this.getPCB().setStackPointer(Memory.add(disk));
		Scheduler.add(this);
		while (this != Asphalt.running) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		int size = Asphalt.memorys.memory.length - 1;
		int speed = Integer.parseInt(Asphalt.memorys.memory[size].split(",")[1]);
		String mem = "";
		this.responseTime = (long) ((System.nanoTime() - start) * (Math.pow(10, -6)));
		System.out.println("RESPONSE TIME OF " + this.disk + " :   " + this.responseTime + " MilliSeconds");
		for (int i = 0; i < 5; i++) {
			while (this != Asphalt.running) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			speed -= 5;
			if (speed <= 0) {
				mem = "Speed,0";
				Asphalt.memorys.memory[size] = mem;
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mem = "speed," + speed;
			Asphalt.memorys.memory[size] = mem;
			size = Asphalt.memorys.memory.length - 1;
			speed = Integer.parseInt(Asphalt.memorys.memory[size].split(",")[1]);

		}
		Asphalt.memorys.remove(this.getPCB().getStackPointer());
		this.getPCB().setStatus(status.TERMINATED);
		this.turnaroundTime = (long) ((System.nanoTime() - start) * (Math.pow(10, -6)));
		System.out.println("TURNAROUND TIME OF " + this.disk + " :   " + this.turnaroundTime + " MilliSeconds");
		Asphalt.dispatcher.run();

	}

	public String toString() {
		return disk;
	}
}
