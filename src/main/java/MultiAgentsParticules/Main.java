package MultiAgentsParticules;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Main {
	
	private static SMA sma;

	public static void main(String[] args) throws InterruptedException {
		
		//SMA sma = new SMA();
//		sma.initBille(40,30,30);
		//sma.initFishShark(1000, 1, 100, 100);

		//View v = new View(sma);
		//sma.addObserver(v);
		//v.launch();
//		SMA sma = new SMA();
//		sma.init(40,50,50);
//		View v = new View(sma);
//		sma.addObserver(v);
//		v.launch();
		
		setSma(new SMA());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		//getSma().initBille(7000,width,height);
		getSma().initFishShark(1000,7,width,height);
		ViewJFX vfx = new ViewJFX();
		getSma().addObserver(vfx);
		vfx.show();
	}

	public static SMA getSma() {
		return sma;
	}

	public static void setSma(SMA sma) {
		Main.sma = sma;
	}
}