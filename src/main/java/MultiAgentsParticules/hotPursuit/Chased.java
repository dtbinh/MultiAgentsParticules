package MultiAgentsParticules.hotPursuit;

import MultiAgentsParticules.Agent;

public class Chased extends Agent {

	private double codeDirection = 0;
	
	public Chased(int positionX, int positionY) {
		super(positionX, positionY);
		// TODO Auto-generated constructor stub
		
	}

	
	@Override
	public void doIt() {
		// TODO Auto-generated method stub
		
		/**
		 * il verifie la valeur de codeDirection, ensuite il fait un deplacement en changeant la valeur de direction
		 */
		
	}

	public double getCodeDirection() {
		return codeDirection;
	}

	public void setCodeDirection(double codeDirection) {
		this.codeDirection = codeDirection;
	}

	@Override
	public void doItToric() {
		// TODO Auto-generated method stub
		
	}

	
}
