package MultiAgentsParticules.part1;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		SMA sma = new SMA();
		sma.init(10,20,20);
		sma.run(2);
	}
}
