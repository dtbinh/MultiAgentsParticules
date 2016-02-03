package MultiAgentsParticules;

import MultiAgentsParticules.enums.TypeOfAgentEnum;

public class Empty extends Agent {

	public Empty(int positionX, int positionY) {
		super(positionX, positionY);
		setType(TypeOfAgentEnum.EMPTY);
	}

	public void doIt(boolean torique) {
		
	}

}
