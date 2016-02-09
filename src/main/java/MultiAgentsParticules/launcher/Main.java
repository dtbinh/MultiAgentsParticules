package MultiAgentsParticules.launcher;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import MultiAgentsParticules.bille.view.ViewJFX;
import MultiAgentsParticules.core.SMA;
import MultiAgentsParticules.hotPursuit.model.GameOverExcception;
import MultiAgentsParticules.hotPursuit.view.ViewPursuit;
import MultiAgentsParticules.wator.view.View;

public class Main {

	private static SMA sma;

	public static void main(String[] args) throws InterruptedException, ParseException, GameOverExcception, IOException {
		String project = "hunt";
		int width = 600;
		int height = 600;
		int speed = 100; // 100 ms between every foreach where is executed doIt method
		int sizeAgent = 10;
		boolean torique = false;

		Options options = new Options();
		options.addOption("h", "height", true, "height of the grid");
		options.addOption("w", "width", true, "width of the grid");
		options.addOption("speed", "speed", true, "speed of the execution");
		options.addOption("sa", "sizeAgent", true, "size of agent");
		options.addOption("t", "toroidal", false, "boolean toroidal or not");
		options.addOption("p", "project", true, "project to execute");

		// Particles
		options.addOption("nb", "nbMarbles", true, "number of marbles");

		// Wator
		options.addOption("nbF", "nbFish", true, "number of fish");
		options.addOption("nbS", "nbShark", true, "number of shark");
		options.addOption("repF", "nbCycleRepFish", true, "number of cycle before reproduction for fish");
		options.addOption("repS", "nbCycleRepShark", true, "number of cycle before reproduction for shark");
		options.addOption("death", "nbCycleDeathShark", true, "number of cycle before death of shark");
		options.addOption("speakFish", "speakFish", true, "number of round to speak for fish(s)");
		options.addOption("speakShark", "speakShark", true, "number of round to speak for the shark(s)");
		
		// Hunt
		options.addOption("nbH", "nbHunters", true, "number of hunters");
		options.addOption("nbW", "nbWalls", true, "number of walls");
		options.addOption("speakHunter", "speakHunter", true, "number of round to speak for hunter(s)");
		options.addOption("speakHunted", "speakHunted", true, "number of round to speak for the hunted");
		
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse(options, args);

		if (cmd.getOptionValue("h") != null) {
			height = Integer.parseInt(cmd.getOptionValue("h"));
		}

		if (cmd.getOptionValue("w") != null) {
			width = Integer.parseInt(cmd.getOptionValue("w"));
		}

		if (cmd.getOptionValue("speed") != null) {
			speed = Integer.parseInt(cmd.getOptionValue("speed"));
		}

		if (cmd.getOptionValue("sa") != null) {
			sizeAgent = Integer.parseInt(cmd.getOptionValue("sa"));
		}

		if (cmd.hasOption("t")) {
			torique = true;
		}

		if (cmd.getOptionValue("p") != null) {
			String tmp = cmd.getOptionValue("p");
			if (tmp.equals("wator")) {
				/*
				 * SHARK VS FISH - nombre de fish - nombre de shark - durée de
				 * reproduction fish - durée de reproduction shark - durée de
				 * vie shark
				 */
				int nbFish = 1000;
				int nbShark = 10;
				int nbCycleRepFish = 2;
				int nbCycleRepShark = 4;
				int nbCycleDeathShark = 5;
				int roundForSpeakFish = 1,roundForSpeakShark = 1;

				if (cmd.getOptionValue("nbF") != null) {
					nbFish = Integer.parseInt(cmd.getOptionValue("nbF"));
				}
				if (cmd.getOptionValue("nbS") != null) {
					nbShark = Integer.parseInt(cmd.getOptionValue("nbS"));
				}
				if (cmd.getOptionValue("repF") != null) {
					nbCycleRepFish = Integer.parseInt(cmd.getOptionValue("repF"));
				}
				if (cmd.getOptionValue("repS") != null) {
					nbCycleRepShark = Integer.parseInt(cmd.getOptionValue("repS"));
				}
				if (cmd.getOptionValue("death") != null) {
					nbCycleDeathShark = Integer.parseInt(cmd.getOptionValue("death"));
				}
				if (cmd.getOptionValue("speakFish") != null) {
					roundForSpeakFish = Integer.parseInt(cmd.getOptionValue("speakFish"));
				}
				if (cmd.getOptionValue("speakShark") != null) {
					roundForSpeakShark = Integer.parseInt(cmd.getOptionValue("speakShark"));
				}
				setSma(new SMA());
				sma.initFishShark(nbFish, nbShark, nbCycleRepFish, nbCycleRepShark, nbCycleDeathShark, roundForSpeakFish, roundForSpeakShark, width / sizeAgent, height / sizeAgent);
				View v = new View(torique, speed, sizeAgent);
				sma.addObserver(v);
				v.launch();
				v.out.close();
				/*
				 * ViewJFX vfx = new ViewJFX();
				 * vfx.init(torique,speed,sizeAgent); getSma().addObserver(vfx);
				 * vfx.show();
				 */
			} else if (tmp.equals("particles")) {
				/*
				 * BILLE - hauteur, largeur - nombre de bille - vitesse - taille
				 * des agents - torique
				 */
				int nbParticles = 5000;
				if (cmd.getOptionValue("nb") != null) {
					nbParticles = Integer.parseInt(cmd.getOptionValue("nb"));
				}
				setSma(new SMA());
				getSma().initBille(nbParticles, width , height);
				ViewJFX vfx = new ViewJFX();
				vfx.init(torique, speed, sizeAgent);
				getSma().addObserver(vfx);
				vfx.show();
			} else {
				/*
				 * HAUNTER VS CHASED - hauteur, largeur - taille des agents -
				 * vitesse - nombre de chasseur - nombre de murs - torique
				 * Rapport de vitesse entre les agents
				 */
				int nbHunters = 1;
				int nbWalls = 0;
				torique = true;
				int roundForSpeakHunter = 1,roundForSpeakHunted = 1;
				if (cmd.getOptionValue("nbH") != null) {
					nbHunters = Integer.parseInt(cmd.getOptionValue("nbH"));
				}
				if (cmd.getOptionValue("nbW") != null) {
					nbWalls = Integer.parseInt(cmd.getOptionValue("nbW"));
				}
				if (cmd.getOptionValue("speakHunted") != null) {
					roundForSpeakHunted = Integer.parseInt(cmd.getOptionValue("speakHunted"));
				}
				if (cmd.getOptionValue("speakHunter") != null) {
					roundForSpeakHunter = Integer.parseInt(cmd.getOptionValue("speakHunter"));
				}
				setSma(new SMA());
				sma.initPursuit(nbHunters, nbWalls, roundForSpeakHunter ,roundForSpeakHunted ,width / sizeAgent, height / sizeAgent);
				ViewPursuit v = new ViewPursuit(torique, speed, sizeAgent);
				sma.addObserver(v);
				v.launch();
			}
		} else {
			int nbHunters = 1;
			int nbWalls = 0;
			torique = true;
			int roundForSpeakHunter = 1 ,roundForSpeakHunted = 1;

			if (cmd.getOptionValue("nbH") != null) {
				nbHunters = Integer.parseInt(cmd.getOptionValue("nbH"));
			}
			if (cmd.getOptionValue("nbW") != null) {
				nbWalls = Integer.parseInt(cmd.getOptionValue("nbW"));
			}
			if (cmd.getOptionValue("speakHunted") != null) {
				roundForSpeakHunted = Integer.parseInt(cmd.getOptionValue("speakHunted"));
			}
			if (cmd.getOptionValue("speakHunter") != null) {
				roundForSpeakHunter = Integer.parseInt(cmd.getOptionValue("speakHunter"));
			}
			setSma(new SMA());
			sma.initPursuit(nbHunters, nbWalls, roundForSpeakHunter ,roundForSpeakHunted , width / sizeAgent, height / sizeAgent);
			ViewPursuit v = new ViewPursuit(torique, speed, sizeAgent);
			sma.addObserver(v);
			v.launch();
		}
	}

	public static SMA getSma() {
		return sma;
	}

	public static void setSma(SMA sma) {
		Main.sma = sma;
	}
}