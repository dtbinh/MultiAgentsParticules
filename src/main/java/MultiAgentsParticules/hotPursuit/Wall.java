package MultiAgentsParticules.hotPursuit;

import java.awt.Color;

import MultiAgentsParticules.Agent;
import MultiAgentsParticules.enums.TypeOfAgentEnum;

public class Wall extends Agent {

	public Wall(int positionX, int positionY) {
		super(positionX, positionY);
		setType(TypeOfAgentEnum.WALL);
		setColor(Color.BLACK);
	}

	public void doIt(boolean torique) {
		
	}

}
