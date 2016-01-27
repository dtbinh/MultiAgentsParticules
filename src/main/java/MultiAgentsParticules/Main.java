package MultiAgentsParticules;


public class Main {
	
	private static SMA sma;

	public static void main(String[] args) throws InterruptedException {
		
//		SMA sma = new SMA();
//		sma.init(40,50,50);
//		View v = new View(sma);
//		sma.addObserver(v);
//		v.launch();
		
		setSma(new SMA());
		getSma().init(2000,1000,1000);
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