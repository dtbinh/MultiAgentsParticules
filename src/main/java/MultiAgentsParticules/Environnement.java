package MultiAgentsParticules;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import MultiAgentsParticules.bille.Bille;

public class Environnement {

	private boolean[][] espace;
	private int height;
	private int width;
	// all agents
	private List<Agent> agents;
	// all positions
	private List<String> positionsOfAgents = new LinkedList<String>();
	
	public Environnement(int width, int height){
		agents = new LinkedList<Agent>();
		this.height = height;
		this.width = width;
		this.espace = new boolean[width][height];
	}
	
	public Environnement(){
		agents = new LinkedList<Agent>();
	}
		
	public void init(int nbAgents){
		Agent tmp;
		Random r = new Random();
		int posX;
		int posY;
		this.espace = new boolean[width][height];
		for(int i = 0 ; i < nbAgents ; i++){
			posX = r.nextInt(width);
			posY = r.nextInt(height);
			while(this.espace[posX][posY]){
				posX = r.nextInt(width);
				posY = r.nextInt(height);
			}
			tmp = new Bille(posX,posY);
			this.espace[posX][posY] = true;
			tmp.setId(agents.size());
			agents.add(tmp);
		}
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setAgents(List<Agent> agents){
		 this.agents = agents;
	}
	
	public boolean[][] getEspace(){
		return this.espace;
	}
	
	public boolean isTaken(int width, int height){
		return this.espace[width][height];
	}
}
