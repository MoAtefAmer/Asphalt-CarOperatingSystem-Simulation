package main;

public class Gas extends Process implements Runnable {

	String disk = "speeding up";

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Long start = System.nanoTime();
		this.getPCB().setPriority(2);
		this.getPCB().setStackPointer(Memory.add(disk));
		Scheduler.add(this);
		this.getPCB().setStatus(status.READY);
		

		while (this != Asphalt.running) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.getPCB().setStatus(status.RUNNING);
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
			Asphalt.memorys.add5();
			size = Asphalt.memorys.memory.length - 1;
			speed = Integer.parseInt(Asphalt.memorys.memory[size].split(",")[1]);
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		this.getPCB().setStatus(status.TERMINATED);
		Asphalt.memorys.remove(this.getPCB().getStackPointer());
		// new SpeedWarning().start();
		this.turnaroundTime = (long) ((System.nanoTime() - start) * (Math.pow(10, -6)));
		System.out.println("TURNAROUND TIME OF " + this.disk + " :   " + this.turnaroundTime + " MilliSeconds");
		Asphalt.dispatcher.run();

	}

	public String toString() {
		return disk;
	}

}
