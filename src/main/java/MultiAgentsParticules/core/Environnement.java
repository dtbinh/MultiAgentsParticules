package MultiAgentsParticules.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import MultiAgentsParticules.core.enums.TypeOfAgentEnum;
import MultiAgentsParticules.hotPursuit.model.Empty;
import MultiAgentsParticules.hotPursuit.model.Hunted;

public class Environnement {

	private Agent[][] espace;
	private int height;
	private int width;
	// all agents
	public int[][] matriceDijkstra;

	public Environnement(int width, int height) {
		this.height = height;
		this.width = width;
		this.espace = new Agent[width][height];
		this.matriceDijkstra = new int[width][height];
	}

	public Environnement() {
	}

	public void init(int width, int height) {
		this.espace = new Agent[width][height];
		this.setHeight(height);
		this.setWidth(width);
	}

	public void initDijsktra(int width, int height) {
		this.espace = new Agent[width][height];
		this.matriceDijkstra = new int[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				espace[i][j] = new Empty(i, j);
			}
		}
		this.setHeight(height);
		this.setWidth(width);
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

	public Agent[][] getEspace() {
		return this.espace;
	}

	public int[][] getMatriceDijkstra(){
		return matriceDijkstra;
	}
	
	public void initMatrice(Agent chased) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				matriceDijkstra[i][j] = -1;
			}
		}
		matriceDijkstra[chased.getPositionX()][chased.getPositionY()] = 0;
	}

	public void updateMatrice(int x, int y) {
		int neighborvalue = matriceDijkstra[x][y] + 1;
		Iterator<Agent> it = getNeighbors(x,y).iterator();
		while (it.hasNext()) {
			Agent neighbor = it.next();
			if(espace[neighbor.getPositionX()][neighbor.getPositionY()].getType() != TypeOfAgentEnum.WALL && (matriceDijkstra[neighbor.getPositionX()][neighbor.getPositionY()] == -1 || matriceDijkstra[neighbor.getPositionX()][neighbor.getPositionY()] > neighborvalue)){
				matriceDijkstra[neighbor.getPositionX()][neighbor.getPositionY()] = neighborvalue;
				updateMatrice(neighbor.getPositionX(), neighbor.getPositionY());
			}
		}			
	}
	
	
	public List<Agent> getNeighbors(int x, int y){
		ArrayList<Agent> agents = new ArrayList<Agent>();
	    int neighborX, neighborY;

	    // NORD
	    neighborY = ((y + 1) >= espace.length) ? 0 : (y + 1);
	    agents.add(espace[x][neighborY]);

	    // SUD
	    neighborY = ((y - 1) < 0) ? (espace.length -1) : (y - 1);
	    agents.add(espace[x][neighborY]);

	    // NORD_EST
	    neighborY = ((y + 1) >= espace.length) ? 0 : (y + 1);
	    neighborX = ((x + 1) >= espace.length) ? 0 : (x + 1);
	    agents.add(espace[neighborX][neighborY]);

	    // NORD_OUEST
	    neighborY = ((y + 1) >= espace.length) ? 0 : (y + 1);
	    neighborX = ((x - 1) < 0) ? (espace.length -1) : (x - 1);
	    agents.add(espace[neighborX][neighborY]);

	    // SUD_EST
	    neighborY = ((y - 1) < 0) ? (espace.length -1) : (y - 1);
	    neighborX = ((x + 1) >= espace.length) ? 0 : (x + 1);
	    agents.add(espace[neighborX][neighborY]);

	    // OUEST:
	    neighborX = ((x - 1) < 0) ? (espace.length -1) : (x - 1);
	    agents.add(espace[neighborX][y]);

	    // SUD_OUEST
	    neighborY = ((y - 1) < 0) ? (espace.length -1) : (y - 1);
	    neighborX = ((x - 1) < 0) ? (espace.length -1) : (x - 1);
	    agents.add(espace[neighborX][neighborY]);

	    // EST
	    neighborX = ((x + 1) >= espace.length) ? 0 : (x + 1);
	    agents.add(espace[neighborX][y]);

	    return agents;	
	}

}
