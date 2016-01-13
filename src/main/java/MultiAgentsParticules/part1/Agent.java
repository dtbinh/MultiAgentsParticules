package MultiAgentsParticules.part1;

import java.util.LinkedList;
import java.util.List;

public class Agent {

	private int id;
	private int positionX;
	private int positionY;
	private int stepX;
	private int stepY;
	private Environnement environnement;
	
	public Agent(int positionX, int positionY){
		this.setPositionX(positionX);
		this.setPositionY(positionY);
	}
	
	public void doIt(){
		List<String> listOfPossibilities = new LinkedList<String>();
		/*if(this.positionX > 0 && !environnement.isTaken(positionX-1, positionY)){
			//listOfPossibilities.add();
		}if(this.positionY == 0){
			
		}*/
		
		System.out.println(this);
	}


	public int getPositionX() {
		return positionX;
	}


	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}


	public int getPositionY() {
		return positionY;
	}


	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}


	public int getStepX() {
		return stepX;
	}


	public void setStepX(int stepX) {
		this.stepX = stepX;
	}


	public int getStepY() {
		return stepY;
	}


	public void setStepY(int stepY) {
		this.stepY = stepY;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString(){
		return "I am " + this.id + " in [" + this.positionX +","+ this.positionY + "]";
	}

	public Environnement getEnvironnement() {
		return environnement;
	}

	public void setEnvironnement(Environnement environnement) {
		this.environnement = environnement;
	}
}
