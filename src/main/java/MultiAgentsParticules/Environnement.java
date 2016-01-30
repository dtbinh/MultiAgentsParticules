package MultiAgentsParticules;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import MultiAgentsParticules.bille.Bille;
import MultiAgentsParticules.wator.Fish;
import MultiAgentsParticules.wator.Shark;

public class Environnement {

	private Agent[][] espace;
	private int height;
	private int width;
	// all agents
	
	public Environnement(int width, int height){
		this.height = height;
		this.width = width;
		this.espace = new Agent[width][height];
	}
	
		
	public Environnement() {
	}

	public void init(int width, int height) {
		this.espace = new Agent[width][height];	
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
	
	public Agent[][] getEspace(){
		return this.espace;
	}
	
}
