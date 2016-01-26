package MultiAgentsParticules;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class SMA extends Observable{

	private static Environnement environnement = new Environnement();
	private static List<Agent> agents = new LinkedList<Agent>();
	private static View view;
	
	public void init(int nbAgents, int width, int height){
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
			//System.out.println("********** ROUND "+ i +" *********");
			for(Agent a : agents){
				a.doItToric();
			}
			this.setChanged();
			notifyObservers();
			Thread.sleep(100);
		}
	}
	
	public List<Agent> getAgents(){
		return agents;
	}
}
