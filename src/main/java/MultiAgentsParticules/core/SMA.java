package MultiAgentsParticules.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import MultiAgentsParticules.bille.model.Bille;
import MultiAgentsParticules.core.enums.TypeOfAgentEnum;
import MultiAgentsParticules.hotPursuit.model.GameOverExcception;
import MultiAgentsParticules.hotPursuit.model.Hunted;
import MultiAgentsParticules.hotPursuit.model.Hunter;
import MultiAgentsParticules.hotPursuit.model.Wall;
import MultiAgentsParticules.wator.model.Fish;
import MultiAgentsParticules.wator.model.Shark;
import MultiAgentsParticules.wator.view.View;

public class SMA extends Observable {

	private static Environnement environnement = new Environnement();
	private static List<Agent> agents = new LinkedList<Agent>();
	private static View view;
	private static int mCurrentTurn = 0;
	private static int cptFish;
	private static int cptShark;
	
	public void initBille(int nbAgents,int width, int height) {
		environnement.init(width, height);
		Agent tmp;
		Random r = new Random();
		int posX;
		int posY;
		for (int i = 0; i < nbAgents; i++) {
			posX = r.nextInt(width);
			posY = r.nextInt(height);
			while (environnement.getEspace()[posX][posY] != null) {
				posX = r.nextInt(width);
				posY = r.nextInt(height);
			}
			tmp = new Bille(posX, posY, 1);
			environnement.getEspace()[posX][posY] = tmp;
			tmp.setId(agents.size());
			agents.add(tmp);
		}
	}

	public void initFishShark(int nbFish, int nbShark, int nbCycleRepFish, int nbCycleRepShark, int nbCycleDeathShark,int roundForSpeakFish, int roundForSpeakShark, int width, int height) {
		environnement.init(width, height);
		this.setNbFish(nbFish);
		this.setNbShark(nbShark);
		Agent agent;
		Random r = new Random();
		int posX;
		int posY;
		for (int i = 0; i < nbFish; i++) {
			posX = r.nextInt(width);
			posY = r.nextInt(height);
			while (environnement.getEspace()[posX][posY] != null) {
				posX = r.nextInt(width);
				posY = r.nextInt(height);
			}
			agent = new Fish(posX, posY, nbCycleRepFish ,roundForSpeakFish);
			environnement.getEspace()[posX][posY] = agent;
			agent.setId(agents.size());
			agents.add(agent);
		}
		for (int i = 0; i < nbShark; i++) {
			posX = r.nextInt(width);
			posY = r.nextInt(height);
			while (environnement.getEspace()[posX][posY] != null) {
				posX = r.nextInt(width);
				posY = r.nextInt(height);
			}
			agent = new Shark(posX, posY, nbCycleRepShark, nbCycleDeathShark,roundForSpeakShark);
			environnement.getEspace()[posX][posY] = agent;
			agent.setId(agents.size());
			agents.add(agent);
		}
	}

	public void initPursuit(int nbHunters, int nbWalls, int roundForSpeakHunter, int roundForSpeakHunted, int width, int height) {
		environnement.initDijsktra(width, height);
		Agent tmp;
		Random r = new Random();
		int posX;
		int posY;
		for (int i = 0; i < nbHunters; i++) {
			posX = r.nextInt(width);
			posY = r.nextInt(height);
			while (environnement.getEspace()[posX][posY].getType() != TypeOfAgentEnum.EMPTY) {
				posX = r.nextInt(width);
				posY = r.nextInt(height);
			}
			tmp = new Hunter(posX, posY, roundForSpeakHunter);
			environnement.getEspace()[posX][posY] = tmp;
			tmp.setId(agents.size());
			agents.add(tmp);
		}
		posX = r.nextInt(width);
		posY = r.nextInt(height);
		while (environnement.getEspace()[posX][posY].getType() != TypeOfAgentEnum.EMPTY) {
			posX = r.nextInt(width);
			posY = r.nextInt(height);
		}
		tmp = new Hunted(posX, posY, roundForSpeakHunted);
		environnement.getEspace()[posX][posY] = tmp;
		tmp.setId(agents.size());
		agents.add(tmp);

		/*
		 * WALL
		 */
		for (int i = 0; i < nbWalls; i++) {
			posX = r.nextInt(width);
			posY = r.nextInt(height);
			while (environnement.getEspace()[posX][posY].getType() != TypeOfAgentEnum.EMPTY) {
				posX = r.nextInt(width);
				posY = r.nextInt(height);
			}
			tmp = new Wall(posX, posY);
			environnement.getEspace()[posX][posY] = tmp;
			tmp.setId(agents.size());
			agents.add(tmp);
		}

		environnement.initMatrice(tmp);
	}

	public void run(boolean torique, int speed) throws InterruptedException, GameOverExcception {
		Collections.shuffle(agents);
		List<Agent> list;
		while (true) {
			// System.out.println("********** ROUND "+ i +" *********");
			list = new ArrayList<Agent>(agents);
			// Collections.copy(list , agents);
			for (Agent a : list) {
				a.doIt(torique);
			}
			mCurrentTurn++;
			this.setChanged();
			notifyObservers();
			Thread.sleep(speed);
			// nbTypeOfAgent();
		}
	}

	public void runJFX(boolean torique, int speed) throws InterruptedException, GameOverExcception {
		Collections.shuffle(agents);
		List<Agent> list;
		// System.out.println("********** ROUND "+ i +" *********");
		list = new ArrayList<Agent>(agents);
		// Collections.copy(list , agents);
		for (Agent a : list) {
			a.doIt(torique);
		}
		mCurrentTurn++;
		this.setChanged();
		notifyObservers();
		// nbTypeOfAgent();
	}

	public static void nbTypeOfAgent() {
		cptFish = 0;
		cptShark = 0;
		for (Agent a : agents) {
			if (a.getType() == TypeOfAgentEnum.FISH)
				cptFish++;
			else if (a.getType() == TypeOfAgentEnum.SHARK)
				cptShark++;
		}
		System.out.println("Fish : " + cptFish);
		System.out.println("Shark : " + cptShark);
	}

	public static List<Agent> getAgents() {
		return agents;
	}

	public static Environnement getEnvironnement() {
		return environnement;
	}

	public int getCurrentTurn() {
		return mCurrentTurn;
	}

	public static int getNbFish() {
		return cptFish;
	}

	public static void setNbFish(int nbFish) {
		SMA.cptFish = nbFish;
	}

	public static int getNbShark() {
		return cptShark;
	}

	public static void setNbShark(int nbShark) {
		SMA.cptShark = nbShark;
	}

}
