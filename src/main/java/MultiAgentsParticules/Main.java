package MultiAgentsParticules;

import java.awt.Graphics;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		SMA sma = new SMA();
		sma.init(200,100,100);
		View v = new View(sma);
		sma.addObserver(v);
		v.launch();
	}
}