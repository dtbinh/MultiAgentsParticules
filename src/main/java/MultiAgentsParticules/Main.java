package MultiAgentsParticules;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		SMA sma = new SMA();
//		sma.initBille(40,30,30);
		sma.initFishShark(1000, 1, 100, 100);

		View v = new View(sma);
		sma.addObserver(v);
		v.launch();
	}
}
