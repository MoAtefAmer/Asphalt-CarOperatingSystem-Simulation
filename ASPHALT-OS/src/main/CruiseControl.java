package main;

public class CruiseControl extends Process implements Runnable {
	String disk = "CruiseControl is running";

	public CruiseControl() {

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.getPCB().setPriority(5);
		this.getPCB().setStackPointer(Memory.add(disk));
		Asphalt.dispatcher.add(this);
		Asphalt.dispatcher.run();
		int size = Memory.memory.length - 4;
		String speed = Memory.memory[size];
		int value = Integer.parseInt(speed.split(",")[1]);

		Memory.remove(this.getPCB().getStackPointer());
		Asphalt.dispatcher.run();
	}

	// Cruise Control should be a variable cause we only set the speed and
	// other processess should check that variable
	public String toString() {
		return disk;
	}
}
