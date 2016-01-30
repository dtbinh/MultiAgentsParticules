package MultiAgentsParticules.bille;

import MultiAgentsParticules.Agent;
import MultiAgentsParticules.DirectionEnum;
import MultiAgentsParticules.TypeOfAgentEnum;

public class Bille extends Agent {

	public Bille(int positionX, int positionY) {
		super(positionX, positionY);
		this.setColor(generateColor());
		this.setType(TypeOfAgentEnum.BILLE);
	}

	public void doIt() {
		if (initialisation || direction == DirectionEnum.NONE) {
			// au depart, on choisit une direction pour l'agent
			this.direction = generateInitDirection();
			initialisation = false;
		}

		//System.out.println(this);

		// NORTH
		if (direction == DirectionEnum.NORTH) {
			// verification de case vide + verification si on est sur le bord
			if ((environnement.getHeight() == (positionY + 1))
					|| (environnement.getEspace()[positionX][positionY + 1] != null)) {
				if((positionY) > 0 && !isTaken(positionX,positionY - 1))
					direction = DirectionEnum.SOUTH;
				else
					direction = DirectionEnum.NONE;
			}
		}

		// NORTH_EAST
		else if (direction == DirectionEnum.NORTH_EAST) {

			// verification de case vide
			if ((positionX + 1 < environnement.getWidth()) && (positionY + 1 < environnement.getHeight())
					&& (environnement.getEspace()[positionX + 1][positionY + 1] != null)) {
				if((positionX) > 0 && (positionY + 1) < environnement.getHeight() && !isTaken(positionX - 1,positionY + 1))
					direction = DirectionEnum.NORTH_WEST;
				else
					direction = DirectionEnum.NONE;
				
			} else {
				// si on tape sur le coté droit
				if ((environnement.getWidth() == (positionX + 1)) && (environnement.getHeight() != (positionY + 1))) {
					direction = DirectionEnum.NORTH_WEST;
				}

				// si on tape sur le haut
				if ((environnement.getHeight() == (positionY + 1)) && (environnement.getWidth() != (positionX + 1))) {
					direction = DirectionEnum.SOUTH_EAST;
				}

				// si on tape le coin haut droit
				if ((environnement.getWidth() == (positionX + 1)) && (environnement.getHeight() == (positionY + 1))) {
					direction = DirectionEnum.SOUTH_WEST;
				}
			}
		}

		// EAST
		else if (direction == DirectionEnum.EAST) {
			// verification de case vide + verification si on est sur le bord
			if (((positionX + 1) == environnement.getWidth())
					|| (environnement.getEspace()[positionX + 1][positionY] != null)) {
				if((positionX) > 0 && !isTaken(positionX - 1, positionY))
					direction = DirectionEnum.WEST;
				else
					direction = DirectionEnum.NONE;
			}
		}

		// SOUTH_EAST
		else if (direction == DirectionEnum.SOUTH_EAST) {

			// verification de case vide
			if ((positionY > 0) && ((positionX + 1) < environnement.getWidth())
					&& (environnement.getEspace()[positionX + 1][positionY - 1] != null)) {
				if((positionX + 1) < environnement.getWidth() && (positionY) > 0 && !isTaken(positionX + 1,positionY - 1))
					direction = DirectionEnum.NORTH_WEST;
				else
					direction = DirectionEnum.NONE;
			} else {
				// si on tape sur le coté droit
				if ((environnement.getWidth() == (positionX + 1)) && (positionY != 0)) {
					direction = DirectionEnum.SOUTH_WEST;
				}
				// si on tape sur le bas
				if ((positionY == 0) && (environnement.getWidth() != (positionX + 1))) {
					direction = DirectionEnum.NORTH_EAST;
				}
				// si on tape le coin du bas droite
				if ((environnement.getWidth() == (positionX + 1)) && (positionY == 0)) {
					direction = DirectionEnum.NORTH_WEST;
				}
			}
		}

		// SOUTH
		else if (direction == DirectionEnum.SOUTH) {
			// verification de case vide + verification si on est sur le bord
			if ((positionY == 0) || (environnement.getEspace()[positionX][positionY - 1] != null)) {
				if((positionY + 1) < environnement.getHeight() && !isTaken(positionX, positionY + 1))
					direction = DirectionEnum.NORTH;
				else
					direction = DirectionEnum.NONE;
			}
		}

		// SOUTH_WEST
		else if (direction == DirectionEnum.SOUTH_WEST) {
			// verification de case vide
			if ((positionX > 0) && (positionY > 0) && (environnement.getEspace()[positionX - 1][positionY - 1] != null)) {
				if((positionX + 1) < environnement.getWidth() && (positionY + 1) < environnement.getHeight() && !isTaken(positionX + 1, positionY + 1))
					direction = DirectionEnum.NORTH_EAST;
				else
					direction = DirectionEnum.NONE;
			} else {
				// si on tape dans le coté gauche
				if ((positionX == 0) && (positionY != 0)) {
					direction = DirectionEnum.SOUTH_EAST;
				}
				// si on tape sur le bas
				if ((positionY == 0) && (positionX != 0)) {
					direction = DirectionEnum.NORTH_WEST;
				}
				// si on tape dans le coin bas gauche
				if ((positionX == 0) && (positionY == 0)) {
					direction = DirectionEnum.NORTH_EAST;
				}
			}
		}

		// WEST
		else if (direction == DirectionEnum.WEST) {
			// verification de case vide + verification si on est sur le bord
			if ((positionX == 0) || (environnement.getEspace()[positionX - 1][positionY] != null)) {
				if((positionX + 1) < environnement.getWidth() && !isTaken(positionX + 1, positionY))
					direction = DirectionEnum.EAST;
				else
					direction = DirectionEnum.NONE;
			}
		}

		// NORTH_WEST
		else if (direction == DirectionEnum.NORTH_WEST) {

			// verification de case vide
			if ((positionX > 0) && ((positionY + 1) < environnement.getHeight())
					&& (environnement.getEspace()[positionX - 1][positionY + 1] != null)) {
				if((positionY + 1) < environnement.getHeight() && (positionX) > 0 && !isTaken(positionX - 1, positionY + 1))
					direction = DirectionEnum.SOUTH_EAST;
				else
					direction = DirectionEnum.NONE;

			} else {
				// si on tape sur le coté gauche
				if ((positionX == 0) && (environnement.getHeight() != (positionY + 1))) {
					direction = DirectionEnum.NORTH_EAST;
				}
				// si on tape sur le haut
				if ((positionX != 0) && (environnement.getHeight() == (positionY + 1))) {
					direction = DirectionEnum.SOUTH_WEST;
				}
				// si on tape dans le coin haut gauche
				if ((positionX == 0) && (environnement.getHeight() == (positionY + 1))) {
					direction = DirectionEnum.SOUTH_EAST;
				}
			}
		}
		// on met la case en non occupée
		environnement.getEspace()[positionX][positionY] = null;
		//System.out.println(direction);
		deplacement(direction);
		//System.out.println(this);
		environnement.getEspace()[positionX][positionY] = this;
	}
}
