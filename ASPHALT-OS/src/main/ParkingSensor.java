package main;

public class ParkingSensor extends Process implements Runnable {
	String disk = "running ParkingSensor";

	public ParkingSensor() {

	}

	@Override
	public void run() {
		Long start = System.nanoTime();
		this.getPCB().setPriority(5);
		this.getPCB().setStackPointer(Memory.add(disk));
		Asphalt.dispatcher.add(this);

		this.getPCB().setStatus(status.RUNNING);
		try {
			Thread.sleep(300);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int size = Memory.memory.length - 3;
		String reverese = Memory.memory[size];
		int value = Integer.parseInt(reverese.split(",")[1]);

		this.responseTime = (long) ((System.nanoTime() - start) * (Math.pow(10, -6)));
		System.out.println("RESPONSE TIME OF " + this.disk + " :   " + this.responseTime + " MilliSeconds");
		while (value == 1) {
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
			sensor.warning();
			Asphalt.mutex.semSignal(this);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			reverese = Memory.memory[size];
			value = Integer.parseInt(reverese.split(",")[1]);

		}
		Memory.remove(this.getPCB().getStackPointer());
		this.getPCB().setStatus(status.TERMINATED);
		this.turnaroundTime = (long) ((System.nanoTime() - start) * (Math.pow(10, -6)));
		System.out.println("TURNAROUND TIME OF " + this.disk + " :   " + this.turnaroundTime + " MilliSeconds");
		Asphalt.dispatcher.run();
	}

	public String toString() {
		return disk;
	}

}
