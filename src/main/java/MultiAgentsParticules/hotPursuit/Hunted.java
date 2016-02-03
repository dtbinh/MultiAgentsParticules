package MultiAgentsParticules.hotPursuit;

import java.awt.Color;

import MultiAgentsParticules.Agent;
import MultiAgentsParticules.Empty;
import MultiAgentsParticules.enums.DirectionEnum;
import MultiAgentsParticules.enums.TypeOfAgentEnum;

public class Hunted extends Agent {

	private boolean initialisation = true;

	public Hunted(int positionX, int positionY) {
		super(positionX, positionY);
		setColor(Color.green);
		this.setType(TypeOfAgentEnum.CHASED);
		direction = DirectionEnum.NONE;
	}

	/**
	 * il verifie la valeur de codeDirection, ensuite il fait un deplacement en
	 * changeant la valeur de direction
	 */
	public void doIt(boolean torique) {
		if (initialisation) {
			this.direction = generateInitDirection();
			initialisation = false;
		}
		// NORTH
		if (direction == DirectionEnum.NORTH) {
			// verification de case vide + verification si on est sur le
			// bord
			if (positionY + 1 == environnement.getHeight()) {
				if (torique) {
					if (isTaken(positionX, 0))
						direction = DirectionEnum.NONE;
				} else {
					direction = DirectionEnum.NONE;
				}
			} else if (isTaken(positionX, positionY + 1)) {
				direction = DirectionEnum.NONE;
			}
		}

		// NORTH_EAST
		else if (direction == DirectionEnum.NORTH_EAST) {

			if ((positionX + 1 < environnement.getWidth()) && (positionY + 1 < environnement.getHeight())
					&& (isTaken(positionX + 1, positionY + 1))) {
				direction = DirectionEnum.NONE;

			} else {
				if (torique) {
					// si on tape sur le coté droit
					if ((environnement.getWidth() == (positionX + 1))
							&& (environnement.getHeight() != (positionY + 1))) {
						positionX = 0;
						if (isTaken(0, positionY))
							direction = DirectionEnum.NONE;
					}

					// si on tape sur le haut
					if ((environnement.getHeight() == (positionY + 1))
							&& (environnement.getWidth() != (positionX + 1))) {
						if (isTaken(positionX, 0))
							direction = DirectionEnum.NONE;
					}

					// si on tape le coin haut droit
					if ((environnement.getWidth() == (positionX + 1))
							&& (environnement.getHeight() == (positionY + 1))) {
						if (isTaken(0, 0))
							direction = DirectionEnum.NONE;
					}
				} else {
					direction = DirectionEnum.NONE;
				}
			}
		}

		// EAST
		else if (direction == DirectionEnum.EAST) {
			if ((positionX + 1 < environnement.getWidth()) && (isTaken(positionX + 1, positionY))) {
				direction = DirectionEnum.NONE;
			}
			if ((positionX + 1) == environnement.getWidth()) {
				if (torique) {
					if (isTaken(0, positionY))
						direction = DirectionEnum.NONE;
				} else {
					direction = DirectionEnum.NONE;
				}
			}
		}

		// SOUTH_EAST
		else if (direction == DirectionEnum.SOUTH_EAST) {

			// verification de case vide
			if ((positionY > 0) && ((positionX + 1) < environnement.getWidth())
					&& (isTaken(positionX + 1, positionY - 1))) {
				direction = DirectionEnum.NONE;
			} else {
				if (torique) {
					// si on tape sur le coté droit
					if ((environnement.getWidth() == (positionX + 1)) && (positionY != 0)) {
						if (isTaken(positionX, 0))
							direction = DirectionEnum.NONE;
					}
					// si on tape sur le bas
					if ((positionY == 0) && (environnement.getWidth() != (positionX + 1))) {
						if (isTaken(positionX, environnement.getHeight() - 1))
							direction = DirectionEnum.NONE;
					}
					// si on tape le coin du bas droite
					if ((environnement.getWidth() == (positionX + 1)) && (positionY == 0)) {
						if (isTaken(0, environnement.getHeight() - 1))
							direction = DirectionEnum.NONE;
					}
				} else {
					direction = DirectionEnum.NONE;
				}
			}
		}

		// SOUTH
		else if (direction == DirectionEnum.SOUTH) {
			if (positionY == 0) {
				if (torique) {
					if (isTaken(positionX, environnement.getHeight() - 1))
						direction = DirectionEnum.NONE;
				} else {
					direction = DirectionEnum.NONE;
				}

			} else if (isTaken(positionX, positionY - 1)) {
				direction = DirectionEnum.NONE;
			}

		}

		// SOUTH_WEST
		else if (direction == DirectionEnum.SOUTH_WEST) {
			// verification de case vide
			if ((positionX > 0) && (positionY > 0) && (isTaken(positionX - 1, positionY - 1))) {
				direction = DirectionEnum.NONE;
			} else {
				if (torique) {
					if ((positionX == 0) && (positionY != 0)) {
						if (isTaken(environnement.getWidth() - 1, positionY))
							direction = DirectionEnum.NONE;
					}
					// si on tape sur le bas
					else if ((positionY == 0) && (positionX != 0)) {
						if (isTaken(positionX, environnement.getHeight() - 1))
							direction = DirectionEnum.NONE;
					}
					// si on tape dans le coin bas gauche
					else if ((positionX == 0) && (positionY == 0)) {
						if (isTaken(environnement.getWidth() - 1, environnement.getHeight() - 1))
							direction = DirectionEnum.NONE;
					}
				} else {
					direction = DirectionEnum.NONE;
				}
			}
		}

		// WEST
		else if (direction == DirectionEnum.WEST) {
			// verification de case vide + verification si on est sur le
			// bord
			if (positionX == 0) {
				if (torique) {
					if (isTaken(environnement.getWidth() - 1, positionY))
						direction = DirectionEnum.NONE;
				} else {
					direction = DirectionEnum.NONE;
				}
			} else if (positionX > 0 && (isTaken(positionX - 1, positionY))) {
				direction = DirectionEnum.NONE;
			}
		}

		// NORTH_WEST
		else if (direction == DirectionEnum.NORTH_WEST) {

			// verification de case vide
			if ((positionX > 0) && ((positionY + 1) < environnement.getHeight())
					&& (isTaken(positionX - 1, positionY + 1))) {
				direction = DirectionEnum.NONE;

			} else {
				if (torique) {
					if ((positionX == 0) && (environnement.getHeight() != (positionY + 1))) {
						if (isTaken(environnement.getWidth() - 1, positionY))
							direction = DirectionEnum.NONE;
					}
					// si on tape sur le haut
					if ((positionX != 0) && (environnement.getHeight() == (positionY + 1))) {
						if (isTaken(positionX, 0))
							direction = DirectionEnum.NONE;
					}
					// si on tape dans le coin haut gauche
					if ((positionX == 0) && (environnement.getHeight() == (positionY + 1))) {
						if (isTaken(environnement.getWidth() - 1, 0))
							direction = DirectionEnum.NONE;
					}
				} else {
					direction = DirectionEnum.NONE;
				}
			}
		}
		// on met la case en non occupée
		environnement.getEspace()[positionX][positionY] = new Empty(positionX, positionY);
		deplacement(direction, torique);
		environnement.getEspace()[positionX][positionY] = this;
		// on change la matrice
		environnement.initMatrice(this);
		environnement.updateMatrice(this.getPositionX(), this.getPositionY());

		/*for (int i = 0; i < environnement.matriceDijkstra.length; i++) {
			System.out.print("[");
			for (int j = 0; j < environnement.matriceDijkstra[i].length; j++) {
				System.out.print(environnement.matriceDijkstra[i][j] + " ");
			}
			System.out.println("]");
		}
		System.out.println("**************************");
		 */
	}
}
