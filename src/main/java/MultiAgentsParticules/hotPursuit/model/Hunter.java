package MultiAgentsParticules.hotPursuit.model;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;

import MultiAgentsParticules.core.Agent;
import MultiAgentsParticules.core.enums.TypeOfAgentEnum;

public class Hunter extends Agent {
	
	private int cmptRoundForSpeak; 
	
	public Hunter(int positionX, int positionY, int roundForSpeak) {
		super(positionX, positionY, roundForSpeak);
		this.setType(TypeOfAgentEnum.HUNTER);
		setColor(Color.RED);
		cmptRoundForSpeak = roundForSpeak;
	}

	public void doIt(boolean torique) throws GameOverExcception {
		if(cmptRoundForSpeak != 1 && cmptRoundForSpeak > 0){
			cmptRoundForSpeak --;
			return;
		}
		cmptRoundForSpeak = roundForSpeak;
		
		List<Agent> neighbors = environnement.getNeighbors(positionX, positionY);
		Iterator<Agent> it = neighbors.iterator();
		int[][] matrice = environnement.matriceDijkstra;
		int value = matrice[positionX][positionY];
		
		environnement.getEspace()[positionX][positionY] = new Empty(positionX,positionY);

		while (it.hasNext()) {
			Agent tmp = it.next();
			TypeOfAgentEnum type = environnement.getEspace()[tmp.getPositionX()][tmp.getPositionY()].getType();
			if (type != TypeOfAgentEnum.HUNTER && type != TypeOfAgentEnum.WALL && matrice[tmp.getPositionX()][tmp.getPositionY()] < value) {
				positionX = tmp.getPositionX();
				positionY = tmp.getPositionY();
			}
		}
		
		if(isTaken(positionX,positionY) && environnement.getEspace()[positionX][positionY].getType() == TypeOfAgentEnum.CHASED){
			//System.exit(0);
			new GameOverExcception();
		}
		environnement.getEspace()[positionX][positionY] = this;
	}

}
