package MultiAgentsParticules.hotPursuit;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;

import MultiAgentsParticules.Agent;
import MultiAgentsParticules.Empty;
import MultiAgentsParticules.enums.TypeOfAgentEnum;

public class Hunter extends Agent {

	public Hunter(int positionX, int positionY) {
		super(positionX, positionY);
		this.setType(TypeOfAgentEnum.HUNTER);
		setColor(Color.RED);
	}

	public void doIt(boolean torique) {
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
			System.exit(0);
		}
		environnement.getEspace()[positionX][positionY] = this;
	}

}
