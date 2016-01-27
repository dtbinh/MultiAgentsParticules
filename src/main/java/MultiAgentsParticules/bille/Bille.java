package MultiAgentsParticules.bille;

import MultiAgentsParticules.Agent;
import MultiAgentsParticules.Direction;

public class Bille extends Agent {

	public Bille(int positionX, int positionY) {
		super(positionX, positionY);
	}

	public boolean isTaken(int width, int height){
		return this.environnement.getEspace()[width][height];
	}
 
	
	public void doIt() {
		if (initialisation || direction == Direction.NONE) {
			// au depart, on choisit une direction pour l'agent
			this.direction = generateInitDirection();
			initialisation = false;
		}

		//System.out.println(this);

		// NORTH
		if (direction == Direction.NORTH) {
			// verification de case vide + verification si on est sur le bord
			if ((environnement.getHeight() == (positionY + 1))
					|| (environnement.getEspace()[positionX][positionY + 1])) {
				if((positionY) > 0 && !isTaken(positionX,positionY - 1))
					direction = Direction.SOUTH;
				else
					direction = Direction.NONE;
			}
		}

		// NORTH_EAST
		else if (direction == Direction.NORTH_EAST) {

			// verification de case vide
			if ((positionX + 1 < environnement.getWidth()) && (positionY + 1 < environnement.getHeight())
					&& (environnement.getEspace()[positionX + 1][positionY + 1])) {
				if((positionX) > 0 && (positionY + 1) < environnement.getHeight() && !isTaken(positionX - 1,positionY + 1))
					direction = Direction.NORTH_WEST;
				else
					direction = Direction.NONE;
				
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
		else if (direction == Direction.EAST) {
			// verification de case vide + verification si on est sur le bord
			if (((positionX + 1) == environnement.getWidth())
					|| (environnement.getEspace()[positionX + 1][positionY])) {
				if((positionX) > 0 && !isTaken(positionX - 1, positionY))
					direction = Direction.WEST;
				else
					direction = Direction.NONE;
			}
		}

		// SOUTH_EAST
		else if (direction == Direction.SOUTH_EAST) {

			// verification de case vide
			if ((positionY > 0) && ((positionX + 1) < environnement.getWidth())
					&& (environnement.getEspace()[positionX + 1][positionY - 1])) {
				if((positionX + 1) < environnement.getWidth() && (positionY) > 0 && !isTaken(positionX + 1,positionY - 1))
					direction = Direction.NORTH_WEST;
				else
					direction = Direction.NONE;
			} else {
				// si on tape sur le coté droit
				if ((environnement.getWidth() == (positionX + 1)) && (positionY != 0)) {
					direction = Direction.SOUTH_WEST;
				}
				// si on tape sur le bas
				if ((positionY == 0) && (environnement.getWidth() != (positionX + 1))) {
					direction = Direction.NORTH_EAST;
				}
				// si on tape le coin du bas droite
				if ((environnement.getWidth() == (positionX + 1)) && (positionY == 0)) {
					direction = Direction.NORTH_WEST;
				}
			}
		}

		// SOUTH
		else if (direction == Direction.SOUTH) {
			// verification de case vide + verification si on est sur le bord
			if ((positionY == 0) || (environnement.getEspace()[positionX][positionY - 1])) {
				if((positionY + 1) < environnement.getHeight() && !isTaken(positionX, positionY + 1))
					direction = Direction.NORTH;
				else
					direction = Direction.NONE;
			}
		}

		// SOUTH_WEST
		else if (direction == Direction.SOUTH_WEST) {
			// verification de case vide
			if ((positionX > 0) && (positionY > 0) && (environnement.getEspace()[positionX - 1][positionY - 1])) {
				if((positionX + 1) < environnement.getWidth() && (positionY + 1) < environnement.getHeight() && !isTaken(positionX + 1, positionY + 1))
					direction = Direction.NORTH_EAST;
				else
					direction = Direction.NONE;
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
		else if (direction == Direction.WEST) {
			// verification de case vide + verification si on est sur le bord
			if ((positionX == 0) || (environnement.getEspace()[positionX - 1][positionY])) {
				if((positionX + 1) < environnement.getWidth() && !isTaken(positionX + 1, positionY))
					direction = Direction.EAST;
				else
					direction = Direction.NONE;
			}
		}

		// NORTH_WEST
		else if (direction == Direction.NORTH_WEST) {

			// verification de case vide
			if ((positionX > 0) && ((positionY + 1) < environnement.getHeight())
					&& (environnement.getEspace()[positionX - 1][positionY + 1])) {
				if((positionY + 1) < environnement.getHeight() && (positionX) > 0 && !isTaken(positionX - 1, positionY + 1))
					direction = Direction.SOUTH_EAST;
				else
					direction = Direction.NONE;

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
		//System.out.println(direction);
		deplacement(direction);
		//System.out.println(this);
		environnement.getEspace()[positionX][positionY] = true;
	}
	
	/**
	 ******** Toric
	 */
	public void doItToric() {
		if (initialisation || direction == Direction.NONE) {
			// au depart, on choisit une direction pour l'agent
			this.direction = generateInitDirection();
			initialisation = false;
		}

//		System.out.println(direction);
		
		// NORTH
		if (direction == Direction.NORTH) {
			// verification de case vide + verification si on est sur le bord
			if(positionY + 1 == environnement.getHeight()){
				environnement.getEspace()[positionX][positionY] = false;
				positionY = 0;
			}
			
			if(environnement.getEspace()[positionX][positionY + 1]){
				if((positionY + 1) < environnement.getHeight() && !isTaken(positionX, positionY + 1)){
					direction = Direction.SOUTH;
				}
				else
					direction = Direction.NONE;
			}
		}

		// NORTH_EAST
		else if (direction == Direction.NORTH_EAST) {
			// verification de case vide
			if ((positionX + 1 < environnement.getWidth()) && (positionY + 1 < environnement.getHeight())
					&& (environnement.getEspace()[positionX + 1][positionY + 1])) {
				if((positionX) > 0 && (positionY + 1) < environnement.getHeight() && !isTaken(positionX - 1,positionY + 1))
					direction = Direction.NORTH_WEST;
				else
					direction = Direction.NONE;
				
			} else {
				// si on tape sur le coté droit
				if ((environnement.getWidth() == (positionX + 1)) && (environnement.getHeight() != (positionY + 1))) {
					environnement.getEspace()[positionX][positionY] = false;
					positionX = 0;
				}

				// si on tape sur le haut
				if ((environnement.getHeight() == (positionY + 1)) && (environnement.getWidth() != (positionX + 1))) {
					environnement.getEspace()[positionX][positionY] = false;
					positionY = 0;
				}

				// si on tape le coin haut droit
				if ((environnement.getWidth() == (positionX + 1)) && (environnement.getHeight() == (positionY + 1))) {
					environnement.getEspace()[positionX][positionY] = false;
					positionX = 0;
					positionY = 0;
				}
			}
		}

		// EAST
		else if (direction == Direction.EAST) {
			// verification de case vide + verification si on est sur le bord
			if ((positionX + 1) == environnement.getWidth()){
				environnement.getEspace()[positionX][positionY] = false;
				setPositionX(0);
			}
			if(environnement.getEspace()[positionX + 1][positionY]){
				if((positionX) > 0 && !isTaken(positionX - 1, positionY))
					direction = Direction.WEST;
				else
					direction = Direction.NONE;
			}
		}

		// SOUTH_EAST
		else if (direction == Direction.SOUTH_EAST) {

			// verification de case vide
			if ((positionY > 0) && ((positionX + 1) < environnement.getWidth())
					&& (environnement.getEspace()[positionX + 1][positionY - 1])) {
				if((positionX + 1) < environnement.getWidth() && (positionY) > 0 && !isTaken(positionX + 1,positionY - 1))
					direction = Direction.NORTH_WEST;
				else
					direction = Direction.NONE;
			} else {
				// si on tape sur le coté droit
				if ((environnement.getWidth() == (positionX + 1)) && (positionY != 0)) {
					environnement.getEspace()[positionX][positionY] = false;
					positionX = 0;
				}
				// si on tape sur le bas
				if ((positionY == 0) && (environnement.getWidth() != (positionX + 1))) {
					environnement.getEspace()[positionX][positionY] = false;
					positionY = environnement.getHeight() - 1;
				}
				// si on tape le coin du bas droite
				if ((environnement.getWidth() == (positionX + 1)) && (positionY == 0)) {
					environnement.getEspace()[positionX][positionY] = false;
					positionX = 0;
					positionY = environnement.getHeight() - 1;
				}
			}
		}

		// SOUTH
		else if (direction == Direction.SOUTH) {
			if(positionY == 0){
				environnement.getEspace()[positionX][positionY] = false;
				positionY = environnement.getHeight() - 1;
			}
			else if(environnement.getEspace()[positionX][positionY - 1]) {
				if((positionY + 1) < environnement.getHeight() && !isTaken(positionX, positionY + 1))
					direction = Direction.NORTH;
				else
					direction = Direction.NONE;
			}
				
		}

		// SOUTH_WEST
		else if (direction == Direction.SOUTH_WEST) {
			// verification de case vide
			if ((positionX > 0) && (positionY > 0) && (environnement.getEspace()[positionX - 1][positionY - 1])) {
				if((positionX + 1) < environnement.getWidth() && (positionY + 1) < environnement.getHeight() && !isTaken(positionX + 1, positionY + 1))
					direction = Direction.NORTH_EAST;
				else
					direction = Direction.NONE;
			} else {
				// si on tape dans le coté gauche
				if ((positionX == 0) && (positionY != 0)) {
					environnement.getEspace()[positionX][positionY] = false;
					positionX = environnement.getWidth() - 1;
				}
				// si on tape sur le bas
				if ((positionY == 0) && (positionX != 0)) {
					environnement.getEspace()[positionX][positionY] = false;
					positionY = environnement.getHeight() - 1;
				}
				// si on tape dans le coin bas gauche
				if ((positionX == 0) && (positionY == 0)) {
					environnement.getEspace()[positionX][positionY] = false;
					positionY = environnement.getHeight() - 1;
					positionX = environnement.getWidth() - 1;
				}
			}
		}
		// WEST
		else if (direction == Direction.WEST) {
			// verification de case vide + verification si on est sur le bord
			if(positionX == 0){
				environnement.getEspace()[positionX][positionY] = false;
				positionX = environnement.getWidth() - 1;
			}
			if(environnement.getEspace()[positionX - 1][positionY]){
				if((positionX + 1) < environnement.getWidth() && !isTaken(positionX + 1, positionY))
					direction = Direction.EAST;
				else
					direction = Direction.NONE;
			}
		}

		// NORTH_WEST
		else if (direction == Direction.NORTH_WEST) {

			// verification de case vide
			if ((positionX > 0) && ((positionY + 1) < environnement.getHeight())
					&& (environnement.getEspace()[positionX - 1][positionY + 1])) {
				if((positionY + 1) < environnement.getHeight() && (positionX) > 0 && !isTaken(positionX - 1, positionY + 1))
					direction = Direction.SOUTH_EAST;
				else
					direction = Direction.NONE;

			} else {
				// si on tape sur le coté gauche
				if ((positionX == 0) && (environnement.getHeight() != (positionY + 1))) {
					environnement.getEspace()[positionX][positionY] = false;
					positionX = environnement.getWidth() - 1;
				}
				// si on tape sur le haut
				if ((positionX != 0) && (environnement.getHeight() == (positionY + 1))) {
					environnement.getEspace()[positionX][positionY] = false;
					positionY = 0;
				}
				// si on tape dans le coin haut gauche
				if ((positionX == 0) && (environnement.getHeight() == (positionY + 1))) {
					environnement.getEspace()[positionX][positionY] = false;
					positionX = environnement.getWidth() - 1;
					positionY = 0;
				}
			}
		}
		// on met la case en non occupée
		environnement.getEspace()[positionX][positionY] = false;
		//System.out.println(direction);
		deplacement(direction);
		//System.out.println(this);
		environnement.getEspace()[positionX][positionY] = true;
	}
	
}
