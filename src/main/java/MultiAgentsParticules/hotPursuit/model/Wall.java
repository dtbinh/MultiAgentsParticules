package MultiAgentsParticules.hotPursuit.model;

import java.awt.Color;

import MultiAgentsParticules.core.Agent;
import MultiAgentsParticules.core.enums.TypeOfAgentEnum;

public class Wall extends Agent {

	public Wall(int positionX, int positionY) {
		super(positionX, positionY,0);
		setType(TypeOfAgentEnum.WALL);
		setColor(Color.BLACK);
	}

	public void doIt(boolean torique) {
		
	}

}
