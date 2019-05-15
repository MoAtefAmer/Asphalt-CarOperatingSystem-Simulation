package main;

public class SpeedWarning extends Process implements Runnable {
	String disk = "Run speed warning";

	public SpeedWarning() {
		super();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Long start = System.nanoTime();
		this.getPCB().setPriority(5);
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
		int size = Asphalt.memorys.memory.length - 1;
		String speed = Memory.memory[size];
		int value = Integer.parseInt(speed.split(",")[1]);
		this.responseTime = (long) ((System.nanoTime() - start) * (Math.pow(10, -6)));
		System.out.println("RESPONSE TIME OF " + this.disk + " :   " + this.responseTime + " MilliSeconds");
		while (value >= 140) {
			while (this != Asphalt.running) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Asphalt.mutex.semWait(this);
			Sensor sensor = new Sensor();
			sensor.run();
			Asphalt.mutex.semSignal(this);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			speed = Asphalt.memorys.memory[size];
			value = Integer.parseInt(speed.split(",")[1]);

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
