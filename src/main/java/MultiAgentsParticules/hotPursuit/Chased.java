package MultiAgentsParticules.hotPursuit;

import MultiAgentsParticules.Agent;

public class Chased extends Agent {

	private double codeDirection = 0;
	
	public Chased(int positionX, int positionY) {
		super(positionX, positionY);		
	}

	
	/**
	 * il verifie la valeur de codeDirection,
	 * ensuite il fait un deplacement en changeant 
	 * la valeur de direction
	 */	
	public void doIt(boolean torique) {
		
		
		
	}

	public double getCodeDirection() {
		return codeDirection;
	}

	public void setCodeDirection(double codeDirection) {
		this.codeDirection = codeDirection;
	}

	
}
