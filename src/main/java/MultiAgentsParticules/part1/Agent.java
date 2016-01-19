package MultiAgentsParticules.part1;

import java.util.Random;

public class Agent {

	private int id;
	private int positionX;
	private int positionY;
	/**
	 * DIRECTION X and Y 
	 * HAUT | 0 | 1 | 
	 * HAUT DROITE | 1 | 1 | 
	 * DROITE | 1 | 0 | 
	 * BAS DROITE | 1 | -1 | 
	 * BAS | 0 | -1 | 
	 * BAS GAUCHE | -1 | -1 |
	 * GAUCHE | -1 | 0 | 
	 * HAUT GAUCHE | -1 | 1 |
	 */
	private Direction direction;
	private boolean initialisation = true;
	private Environnement environnement = SMA.getEnvironnement();

	public Agent(int positionX, int positionY) {
		this.setPositionX(positionX);
		this.setPositionY(positionY);
	}

	public void doIt() {

		if (initialisation) {
			// au depart, on choisit une direction pour l'agent
			this.direction = generateInitDirection();
			initialisation = false;
		}

		// HAUT
		if(direction == Direction.NORTH){
			// verification de case vide + verification si on est sur le bord
			if(environnement.getEspace()[positionX][positionY + 1] || environnement.getHeight() == positionY + 1){
				direction = Direction.SOUTH;
			}
		}

		// HAUT DROITE
		if(direction == Direction.NORTH_EAST){
			
			// verification de case vide 
			if(environnement.getEspace()[positionX + 1][positionY + 1]){
				if(environnement.getHeight() == positionY + 1){
					direction = Direction.SOUTH_EAST;
				}
			}else{
				//si on tape sur le coté droit
				if(environnement.getWidth() == positionX + 1 && environnement.getHeight() != positionY + 1){
					direction = Direction.NORTH_WEST;
				}
				
				// si on tape sur le haut
				if(environnement.getHeight() == positionY + 1 && environnement.getWidth() != positionX + 1){
					direction = Direction.SOUTH_EAST;
				}
				
				// si on tape le coin haut droit
				if(environnement.getWidth() == positionX + 1 && environnement.getHeight() == positionY + 1){
					direction = Direction.SOUTH_WEST;
				}
			}
		}
		
		// DROITE
		if(direction == Direction.EAST){
			// verification de case vide + verification si on est sur le bord
			if(environnement.getEspace()[positionX + 1][positionY] || environnement.getWidth() == positionX + 1){
				direction = Direction.WEST;
			}
		}
		
		
		// BAS DROITE
		if(direction == Direction.SOUTH_EAST){
			
			// verification de case vide 
			if(environnement.getEspace()[positionX + 1][positionY - 1]){
				if(positionY - 1 == 0){
					direction = Direction.NORTH_WEST;
				}
			}else{
				//si on tape sur le coté droit
				if(environnement.getWidth() == positionX + 1 && environnement.getHeight() != positionY - 1){
					direction = Direction.NORTH_WEST;
				}
				
				// si on tape sur le bas
				if(environnement.getHeight() == positionY - 1 && environnement.getWidth() != positionX + 1){
					direction = Direction.SOUTH_EAST;
				}
				
				// si on tape le coin bas droite
				if(environnement.getWidth() == positionX + 1  && environnement.getHeight() == positionY - 1){
					direction = Direction.NORTH_WEST;
				}
			}
		}
		
		// BAS
		if(direction == Direction.SOUTH){
			// verification de case vide + verification si on est sur le bord
			if(environnement.getEspace()[positionX][positionY - 1] || positionY - 1 == 0){
				direction = Direction.NORTH;
			}
		}
		
		// BAS GAUCHE
		if (environnement.getEspace()[positionX - 1][positionY - 1]) {

		}
		
		// GAUCHE
		if(direction == Direction.WEST){
			// verification de case vide + verification si on est sur le bord
			if(environnement.getEspace()[positionX - 1][positionY] || positionX - 1 == 0){
				direction = Direction.EAST;
			}
		}
		
		// HAUT GAUCHE
		if(direction == Direction.NORTH_WEST){
			
			// verification de case vide 
			if(environnement.getEspace()[positionX - 1][positionY + 1]){
				if(environnement.getHeight() == positionY + 1){
					direction = Direction.SOUTH_WEST;
				}
			}else{
				//si on tape sur le coté droit
				if(environnement.getWidth() == positionX + 1){
					direction = Direction.NORTH_EAST;
				}
				
				// si on tape sur le haut
				if(environnement.getHeight() == positionY + 1){
					direction = Direction.SOUTH_WEST;
				}
			}
		}

		System.out.println(this);
	}

	public Direction generateInitDirection(){
		Random r = new Random();
		return Direction.values()[r.nextInt(Direction.values().length)];
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString() {
		return "I am " + this.id + " in [" + this.positionX + "," + this.positionY + "]";
	}

	public Environnement getEnvironnement() {
		return environnement;
	}

	public void setEnvironnement(Environnement environnement) {
		this.environnement = environnement;
	}
}
