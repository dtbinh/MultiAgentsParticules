package MultiAgentsParticules.hotPursuit.model;

import MultiAgentsParticules.core.Agent;
import MultiAgentsParticules.core.enums.TypeOfAgentEnum;

public class Empty extends Agent {

	public Empty(int positionX, int positionY) {
		super(positionX, positionY,0);
		setType(TypeOfAgentEnum.EMPTY);
	}

	public void doIt(boolean torique) {
		
	}

}
