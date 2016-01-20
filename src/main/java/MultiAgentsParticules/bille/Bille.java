package MultiAgentsParticules.bille;

import MultiAgentsParticules.Agent;
import MultiAgentsParticules.Direction;

public class Bille extends Agent {

	public Bille(int positionX, int positionY) {
		super(positionX, positionY);
	}

	public void doIt() {
		if (initialisation) {
			// au depart, on choisit une direction pour l'agent
			this.direction = generateInitDirection();
			initialisation = false;
		}

		System.out.println(this);

		// NORTH
		if (direction == Direction.NORTH) {
			// verification de case vide + verification si on est sur le bord
			if ((environnement.getHeight() == (positionY + 1))
					|| (environnement.getEspace()[positionX][positionY + 1])) {
				direction = Direction.SOUTH;
			}
		}

		// NORTH_EAST
		if (direction == Direction.NORTH_EAST) {

			// verification de case vide
			if ((positionX + 1 < environnement.getWidth()) && (positionY + 1 < environnement.getHeight())
					&& (environnement.getEspace()[positionX + 1][positionY + 1])) {
				direction = Direction.NORTH_WEST;
			} else {
				// si on tape sur le coté droit
				if ((environnement.getWidth() == (positionX + 1)) && (environnement.getHeight() != (positionY + 1))) {
					direction = Direction.NORTH_WEST;
				}

				// si on tape sur le haut
				if ((environnement.getHeight() == (positionY + 1)) && (environnement.getWidth() != (positionX + 1))) {
					direction = Direction.SOUTH_EAST;
				}

				// si on tape le coin haut droit
				if ((environnement.getWidth() == (positionX + 1)) && (environnement.getHeight() == (positionY + 1))) {
					direction = Direction.SOUTH_WEST;
				}
			}
		}

		// EAST
		if (direction == Direction.EAST) {
			// verification de case vide + verification si on est sur le bord
			if (((positionX + 1) == environnement.getWidth())
					|| (environnement.getEspace()[positionX + 1][positionY])) {
				direction = Direction.WEST;
			}
		}

		// SOUTH_EAST
		if (direction == Direction.SOUTH_EAST) {

			// verification de case vide
			if ((positionY > 0) && ((positionX + 1) < environnement.getWidth())
					&& (environnement.getEspace()[positionX + 1][positionY - 1])) {
				direction = Direction.NORTH_WEST;
			} else {
				// si on tape sur le coté droit
				if ((environnement.getWidth() == (positionX + 1)) && (environnement.getHeight() != (positionY + 1))) {
					direction = Direction.SOUTH_WEST;
				}

				// si on tape sur le bas
				if ((positionY == 0) && (environnement.getWidth() != (positionX + 1))) {
					direction = Direction.NORTH_EAST;
				}

				// si on tape le coin du bas droite
				if ((environnement.getWidth() == (positionX + 1)) && (environnement.getHeight() == (positionY + 1))) {
					direction = Direction.NORTH_WEST;
				}
			}
		}

		// SOUTH
		if (direction == Direction.SOUTH) {
			// verification de case vide + verification si on est sur le bord
			if ((positionY == 0) || (environnement.getEspace()[positionX][positionY - 1])) {
				direction = Direction.NORTH;
			}
		}

		// SOUTH_WEST
		if (direction == Direction.SOUTH_WEST) {
			// verification de case vide
			if ((positionX > 0) && (positionY > 0) && (environnement.getEspace()[positionX - 1][positionY - 1])) {
				direction = Direction.NORTH_EAST;
			} else {
				// si on tape dans le coté gauche
				if ((positionX == 0) && (positionY != 0)) {
					direction = Direction.SOUTH_EAST;
				}
				// si on tape sur le bas
				if ((positionY == 0) && (positionX != 0)) {
					direction = Direction.NORTH_WEST;
				}
				// si on tape dans le coin bas gauche
				if ((positionX == 0) && (positionY == 0)) {
					direction = Direction.NORTH_EAST;
				}
			}
		}

		// WEST
		if (direction == Direction.WEST) {
			// verification de case vide + verification si on est sur le bord
			if ((positionX == 0) || (environnement.getEspace()[positionX - 1][positionY])) {
				direction = Direction.EAST;
			}
		}

		// NORTH_WEST
		if (direction == Direction.NORTH_WEST) {

			// verification de case vide
			if ((positionX > 0) && ((positionY + 1) < environnement.getHeight())
					&& (environnement.getEspace()[positionX - 1][positionY + 1])) {
				direction = Direction.SOUTH_EAST;
			} else {
				// si on tape sur le coté gauche
				if ((positionX == 0) && (environnement.getHeight() != (positionY + 1))) {
					direction = Direction.NORTH_EAST;
				}

				// si on tape sur le haut
				if ((positionX != 0) && (environnement.getHeight() == (positionY + 1))) {
					direction = Direction.SOUTH_WEST;
				}
				// si on tape dans le coin haut gauche
				if ((positionX == 0) && (environnement.getHeight() == (positionY + 1))) {
					direction = Direction.SOUTH_EAST;
				}
			}
		}
		// on met la case en non occupée
		environnement.getEspace()[positionX][positionY] = false;
		deplacement(direction);
		System.out.println(this);
		// environnement.getEspace()[positionX][positionY] = true;
	}
}
