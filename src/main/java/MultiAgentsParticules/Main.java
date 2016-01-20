package MultiAgentsParticules;

import java.awt.Graphics;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		SMA sma = new SMA();
		sma.init(50,60,60);
		//sma.run(100);
		View v = new View(sma);
		sma.addObserver(v);
		v.launch();
	}
}
