package MultiAgentsParticules.part1;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class SMA extends Observable{

	private static Environnement environnement = new Environnement();
	private static List<Agent> agents = new LinkedList<Agent>();
	private static View view;
	
	public void init(int nbAgents, int height, int width){
		environnement.setHeight(height);
		environnement.setWidth(width);
		environnement.setAgents(agents);
		environnement.init(nbAgents);
	}
	
	public static Environnement getEnvironnement(){
		return environnement;
	}
	
	public void run(int nbRounds) throws InterruptedException{
		Collections.shuffle(agents);
		for(int i = 0 ; i < nbRounds ; i++){
			System.out.println("********** ROUND "+ nbRounds +" *********");
			for(Agent a : agents){
				a.doIt();
			}
		}
		this.setChanged();
		Thread.sleep(1000);
	}
}
