package MultiAgentsParticules.part1;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		SMA sma = new SMA();
		sma.init(1000,200,200);
		//sma.run(100);
		View v = new View(sma);
		sma.addObserver(v);
	}
}
