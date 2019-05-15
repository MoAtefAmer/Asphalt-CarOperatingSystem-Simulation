package main;

public class Memory {
	static String[] memory;
	static int pointer;
	static boolean[] deleted;
	static double utilization;
	static int num = 4;

	public Memory(int size) {
		memory = new String[size];
		for (int i = 0; i < size; i++) {
			memory[i] = "empty";
		}

		pointer = size - 1;
		memory[pointer] = "Speed,5";
		memory[pointer - 1] = "Belt,0";
		memory[pointer - 2] = "Reverese,0";
		memory[pointer - 3] = "CruiseControl,0";
		deleted = new boolean[size];
		deleted[pointer] = true;
		deleted[pointer - 3] = true;
		deleted[pointer - 1] = true;
		deleted[pointer - 2] = true;
		utilization = ((num * 100) / memory.length);

	}

	public static int add(String s) {
		int i = 0;
		for (i = memory.length - 1; i >= 0; i--) {
			if (!deleted[i]) {
				memory[i] = s;
				deleted[i] = true;
				break;
			}

		}
		num++;
		utilization = ((num * 100) / memory.length);
		return i;

	}

	public static void remove(int index) {
		deleted[index] = false;
		num--;
		utilization = ((num * 100) / memory.length);
	}

	public String toString() {
		String s = "";
		for (int i = memory.length - 1; i >= 0; i--) {
			s += i + " : " + memory[i] + "  " + deleted[i] + '\n';
		}

		s += "Utilization of memory: " + ((num * 100) / memory.length) + "% \n";
		return s;
	}

	public static void add5() {
		int size = Asphalt.memorys.memory.length - 1;
		int speed = Integer.parseInt(Asphalt.memorys.memory[size].split(",")[1]);
		String mem = "";
		speed += 5;
		mem = "Speed," + speed;
		Asphalt.memorys.memory[size] = mem;
	}

}
