package main;

public class SeatbeltWarning extends Process implements Runnable {
	String disk = "Seat belt warning";

	public SeatbeltWarning() {
		super();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Long start = System.nanoTime();
		this.getPCB().setPriority(10);
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

		int size = Memory.memory.length - 1;
		String speed = Memory.memory[size];
		int value = Integer.parseInt(speed.split(",")[1]);

		String belt = Memory.memory[size - 1];
		int bell = Integer.parseInt(belt.split(",")[1]);
		boolean trigger = false;
		this.responseTime = (long) ((System.nanoTime() - start) * (Math.pow(10, -6)));
		System.out.println("RESPONSE TIME OF " + this.disk + " :   " + this.responseTime + " MilliSeconds");
		while (value >= 10 && bell == 0) {
			while (this != Asphalt.running) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				if (!trigger) {
					Thread.sleep(10000);
					trigger = true;
				}
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			belt = Memory.memory[size - 1];
			bell = Integer.parseInt(belt.split(",")[1]);
			if (bell == 1)
				break;

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

			speed = Memory.memory[size];
			value = Integer.parseInt(speed.split(",")[1]);

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
