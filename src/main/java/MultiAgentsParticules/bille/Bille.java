package MultiAgentsParticules.bille;

import java.util.Random;

import MultiAgentsParticules.Agent;
import MultiAgentsParticules.enums.DirectionEnum;
import MultiAgentsParticules.enums.TypeOfAgentEnum;

public class Bille extends Agent {
	
	public Bille(int positionX, int positionY) {
		super(positionX, positionY);
		generateColor();
		this.setType(TypeOfAgentEnum.BILLE);
	}

	public void doIt(boolean torique) {
		if (initialisation || direction == DirectionEnum.NONE) {
			// au depart, on choisit une direction pour l'agent
			this.direction = generateInitDirection();
			initialisation = false;
		}

		// System.out.println(this);

		// NORTH
		if (direction == DirectionEnum.NORTH) {
			// verification de case vide + verification si on est sur le bord
			if (positionY + 1 == environnement.getHeight()) {
				if (torique) {
					if (isTaken(positionX, 0)) // si la case en X,0 est prise,
												// on ne bouge pas, sinon la
												// direction reste North
						direction = DirectionEnum.NONE;
				} else {
					if (!isTaken(positionX, positionY - 1))
						direction = DirectionEnum.SOUTH;
					else
						direction = DirectionEnum.NONE;
				}
			} else if (environnement.getEspace()[positionX][positionY + 1] != null) {
				if (!isTaken(positionX, positionY + 1))
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
				if ((positionX) > 0 && (positionY + 1) < environnement.getHeight()
						&& !isTaken(positionX - 1, positionY + 1))
					direction = DirectionEnum.NORTH_WEST;
				else
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
					// si on tape sur le coté droit
					if ((environnement.getWidth() == (positionX + 1))
							&& (environnement.getHeight() != (positionY + 1))) {
						direction = DirectionEnum.NORTH_WEST;
					}

					// si on tape sur le haut
					if ((environnement.getHeight() == (positionY + 1))
							&& (environnement.getWidth() != (positionX + 1))) {
						direction = DirectionEnum.SOUTH_EAST;
					}

					// si on tape le coin haut droit
					if ((environnement.getWidth() == (positionX + 1))
							&& (environnement.getHeight() == (positionY + 1))) {
						direction = DirectionEnum.SOUTH_WEST;
					}
				}
			}
		}

		// EAST
		else if (direction == DirectionEnum.EAST) {
			// verification de case vide + verification si on est sur le bord
			if ((positionX + 1 < environnement.getWidth())
					&& (environnement.getEspace()[positionX + 1][positionY] != null)) {
				if (positionX > 0 && !isTaken(positionX - 1, positionY))
					direction = DirectionEnum.WEST;
				else
					direction = DirectionEnum.NONE;
			}
			if ((positionX + 1) == environnement.getWidth()) {
				if (torique) {
					if (isTaken(0, positionY))
						direction = DirectionEnum.NONE;
				} else {
					if (!isTaken(positionX - 1, positionY))
						direction = DirectionEnum.WEST;
					else
						direction = DirectionEnum.NONE;
				}
			}
		}

		// SOUTH_EAST
		else if (direction == DirectionEnum.SOUTH_EAST) {

			// verification de case vide
			if ((positionY > 0) && ((positionX + 1) < environnement.getWidth())
					&& (environnement.getEspace()[positionX + 1][positionY - 1] != null)) {
				if ((positionX + 1) < environnement.getWidth() && (positionY) > 0
						&& !isTaken(positionX + 1, positionY - 1))
					direction = DirectionEnum.NORTH_WEST;
				else
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
					// si on tape sur le coté droit
					if ((environnement.getWidth() == (positionX + 1)) && (positionY != 0)) {
						if (!isTaken(positionX - 1, positionY))
							direction = DirectionEnum.SOUTH_WEST;
						else
							direction = DirectionEnum.NONE;

					}
					// si on tape sur le bas
					if ((positionY == 0) && (environnement.getWidth() != (positionX + 1))) {
						if (!isTaken(positionX + 1, 1))
							direction = DirectionEnum.NORTH_EAST;
						else
							direction = DirectionEnum.NONE;
					}
					// si on tape le coin du bas droite
					if ((environnement.getWidth() == (positionX + 1)) && (positionY == 0)) {
						if (!isTaken(positionX - 1, positionY + 1))
							direction = DirectionEnum.NORTH_WEST;
						else
							direction = DirectionEnum.NONE;

					}
				}
			}
		}

		// SOUTH
		else if (direction == DirectionEnum.SOUTH) {
			if (positionY == 0) {
				if (torique) {
					if (isTaken(positionX, positionY + 1))
						direction = DirectionEnum.NONE;
				} else {
					if (!isTaken(positionX, positionY + 1))
						direction = DirectionEnum.NORTH;
					else
						direction = DirectionEnum.NONE;
				}

			}
			// verification de case vide + verification si on est sur le bord
			else if (environnement.getEspace()[positionX][positionY - 1] != null) {
				if ((positionY + 1) < environnement.getHeight() && !isTaken(positionX, positionY + 1))
					direction = DirectionEnum.NORTH;
				else
					direction = DirectionEnum.NONE;
			}
			
		}

		// SOUTH_WEST
		else if (direction == DirectionEnum.SOUTH_WEST) {
			// verification de case vide
			if ((positionX > 0) && (positionY > 0)
					&& (environnement.getEspace()[positionX - 1][positionY - 1] != null)) {
				if ((positionX + 1) < environnement.getWidth() && (positionY + 1) < environnement.getHeight()
						&& !isTaken(positionX + 1, positionY + 1))
					direction = DirectionEnum.NORTH_EAST;
				else
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
					// si on tape dans le coté gauche
					if ((positionX == 0) && (positionY != 0)) {
						if (!isTaken(1, positionY - 1))
							direction = DirectionEnum.SOUTH_EAST;
						else
							direction = DirectionEnum.NONE;

					}
					// si on tape sur le bas
					if ((positionY == 0) && (positionX != 0)) {
						if (!isTaken(positionX - 1, 1))
							direction = DirectionEnum.NORTH_WEST;
						else
							direction = DirectionEnum.NONE;
					}
					// si on tape dans le coin bas gauche
					if ((positionX == 0) && (positionY == 0)) {
						if (!isTaken(1, 1))
							direction = DirectionEnum.NORTH_EAST;
						else
							direction = DirectionEnum.NONE;
					}
				}
			}
		}

		// WEST
		else if (direction == DirectionEnum.WEST) {
			// verification de case vide + verification si on est sur le bord
			if (positionX == 0) {
				if (torique) {
					if (isTaken(environnement.getWidth() - 1, positionY))
						direction = DirectionEnum.NONE;
				} else {
					if (!isTaken(positionX + 1, positionY))
						direction = DirectionEnum.EAST;
					else
						direction = DirectionEnum.NONE;
				}
			} else if (positionX > 0 && (environnement.getEspace()[positionX - 1][positionY] != null)) {
				if (positionX + 1 < environnement.getWidth() && !isTaken(positionX + 1, positionY))
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
				if ((positionY + 1) < environnement.getHeight() && (positionX) > 0
						&& !isTaken(positionX - 1, positionY + 1))
					direction = DirectionEnum.SOUTH_EAST;
				else
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
					// si on tape sur le coté gauche
					if ((positionX == 0) && (environnement.getHeight() != (positionY + 1))) {
						if (!isTaken(1, positionY + 1))
							direction = DirectionEnum.NORTH_EAST;
						else
							direction = DirectionEnum.NONE;

					}
					// si on tape sur le haut
					if ((positionX != 0) && (environnement.getHeight() == (positionY + 1))) {
						if (!isTaken(positionX, positionY - 1))
							direction = DirectionEnum.SOUTH_WEST;
						else
							direction = DirectionEnum.NONE;

					}
					// si on tape dans le coin haut gauche
					if ((positionX == 0) && (environnement.getHeight() == (positionY + 1))) {
						if (!isTaken(positionX + 1, positionY - 1))
							direction = DirectionEnum.SOUTH_EAST;
						else
							direction = DirectionEnum.NONE;

					}
				}
			}
		}
		// on met la case en non occupée
		environnement.getEspace()[positionX][positionY] = null;
		// System.out.println(direction);
		deplacement(direction, torique);
		// System.out.println(this);
		environnement.getEspace()[positionX][positionY] = this;
	}

	public int getR(){
		return r;
	}
	
	public int getG(){
		return g;
	}
	
	public int getB(){
		return b;
	}

	protected void generateColor() {
		Random rand = new Random();
		r = rand.nextInt(256);
		g = rand.nextInt(256);
		b = rand.nextInt(256);
	}

}
