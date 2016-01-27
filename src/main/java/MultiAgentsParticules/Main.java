package MultiAgentsParticules;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Main {
	
	private static SMA sma;

	public static void main(String[] args) throws InterruptedException {
		
//		SMA sma = new SMA();
//		sma.init(40,50,50);
//		View v = new View(sma);
//		sma.addObserver(v);
//		v.launch();
		
		setSma(new SMA());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		getSma().init(20000,width,height);
		ViewJFX v = new ViewJFX();
		getSma().addObserver(v);
		v.show();
	}

	public static SMA getSma() {
		return sma;
	}

	public static void setSma(SMA sma) {
		Main.sma = sma;
	}
}