package MultiAgentsParticules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import MultiAgentsParticules.bille.Bille;
import MultiAgentsParticules.wator.Fish;
import MultiAgentsParticules.wator.Shark;

public class SMA extends Observable{

	private static Environnement environnement = new Environnement();
	private static List<Agent> agents = new LinkedList<Agent>();
	private static View view;
	
	public void initBille(int nbAgents, int width, int height){
		environnement.init(width, height);
		Agent tmp;
		Random r = new Random();
		int posX;
		int posY;
		for(int i = 0 ; i < nbAgents ; i++){
			posX = r.nextInt(width);
			posY = r.nextInt(height);
			while(environnement.getEspace()[posX][posY] != null){
				posX = r.nextInt(width);
				posY = r.nextInt(height);
			}
			tmp = new Bille(posX,posY);
			environnement.getEspace()[posX][posY] = tmp;
			tmp.setId(agents.size());
			agents.add(tmp);
		}
	}
	
	public void initFishShark(int nbFish, int nbShark, int width, int height){
		environnement.init(width, height);	
		Agent agent;
		Random r = new Random();
		int posX;
		int posY;
		for(int i = 0 ; i < nbFish ; i++){
			posX = r.nextInt(width);
			posY = r.nextInt(height);
			while(environnement.getEspace()[posX][posY] != null){
				posX = r.nextInt(width);
				posY = r.nextInt(height);
			}
			agent = new Fish(posX,posY ,2);
			environnement.getEspace()[posX][posY] = agent;
			agent.setId(agents.size());
			agents.add(agent);
		}
		for(int i = 0 ; i < nbShark ; i++){
			posX = r.nextInt(width);
			posY = r.nextInt(height);
			while(environnement.getEspace()[posX][posY] != null){
				posX = r.nextInt(width);
				posY = r.nextInt(height);
			}
			agent = new Shark(posX,posY,3,2);
			environnement.getEspace()[posX][posY] = agent;
			agent.setId(agents.size());
			agents.add(agent);
		}
	}


	public void run(int nbRounds) throws InterruptedException{
		Collections.shuffle(agents);
		List<Agent> list;
		for(int i = 0 ; i < nbRounds ; i++){
			//System.out.println("********** ROUND "+ i +" *********");
			list = new ArrayList<Agent>(agents);
			//Collections.copy(list , agents);
			for(Agent a : list){
				a.doIt(true);
			}
			this.setChanged();
			notifyObservers();
			Thread.sleep(40);
			//nbTypeOfAgent();
		}
	}
	
	public static void nbTypeOfAgent(){
		int cptFish = 0;
		int cptShark = 0;
		for(Agent a : agents){
			if(a.getType() == TypeOfAgentEnum.FISH)
				cptFish++;
			else if(a.getType() == TypeOfAgentEnum.SHARK)
				cptShark++;
		}
		System.out.println("Fish : " + cptFish);
		System.out.println("Shark : " + cptShark);
	}
	
	public static List<Agent> getAgents(){
		return agents;
	}
	
	public static Environnement getEnvironnement(){
		return environnement;
	}
	
}
