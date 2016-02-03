package MultiAgentsParticules;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import MultiAgentsParticules.enums.DirectionEnum;
import MultiAgentsParticules.enums.TypeOfAgentEnum;

public abstract class Agent {

	protected int id;
	protected int positionX;
	protected int positionY;
	protected Color couleur;
	protected int r;
	protected int g;
	protected int b;
	/**
	 * DIRECTION X and Y HAUT | 0 | 1 | HAUT DROITE | 1 | 1 | DROITE | 1 | 0 |
	 * BAS DROITE | 1 | -1 | BAS | 0 | -1 | BAS GAUCHE | -1 | -1 | GAUCHE | -1 |
	 * 0 | HAUT GAUCHE | -1 | 1 |
	 */
	protected DirectionEnum direction;
	protected boolean initialisation = true;
	protected Environnement environnement = SMA.getEnvironnement();
	protected Color color;
	protected TypeOfAgentEnum type;
	protected int nbCycleReproduction;

	public Agent(int positionX, int positionY) {
		this.setPositionX(positionX);
		this.setPositionY(positionY);
	}

	public abstract void doIt(boolean torique);

	public void deplacement(DirectionEnum direction, boolean torique) {
		switch (direction) {
		case NORTH:
			if (torique)
				positionY = (positionY + 1) % environnement.getHeight();
			else
				this.positionY += 1;
			break;
		case NORTH_EAST:
			if (torique) {
				this.positionX = (positionX + 1) % environnement.getWidth();
				this.positionY = (positionY + 1) % environnement.getHeight();
			} else {
				this.positionX += 1;
				this.positionY += 1;
			}
			break;
		case EAST:
			if (torique) {
				this.positionX = (positionX + 1) % environnement.getWidth();
			} else {
				this.positionX += 1;
			}
			break;
		case SOUTH_EAST:
			if (torique) {
				this.positionX = (positionX + 1) % environnement.getWidth();
				if(positionY == 0)
					this.positionY = environnement.getHeight() - 1;
				else
					this.positionY -= 1;
			} else {
				this.positionX += 1;
				this.positionY -= 1;
			}
			break;
		case SOUTH:
			if (torique) {
				if(positionY == 0)
					this.positionY = environnement.getHeight() - 1;
				else
					this.positionY -= 1;

			} else {
				this.positionY -= 1;
			}
			break;
		case SOUTH_WEST:
			if (torique) {
				if (positionX == 0)
					this.positionX = environnement.getWidth() - 1;
				else
					this.positionX -= 1;
				if(positionY == 0)
					this.positionY = environnement.getHeight() - 1;
				else
					this.positionY -= 1;

			} else {
				this.positionX -= 1;
				this.positionY -= 1;
			}
			break;
		case WEST:
			if (torique) {
				if (positionX == 0)
					this.positionX = environnement.getWidth() - 1;
				else
					this.positionX -= 1;
			} else {
				this.positionX -= 1;
			}
			break;
		case NORTH_WEST:
			if (torique) {
				if (positionX == 0)
					this.positionX = environnement.getWidth() - 1;
				else
					this.positionX -= 1;
				this.positionY = (positionY + 1) % environnement.getHeight();
			} else {
				this.positionX -= 1;
				this.positionY += 1;
			}
			break;
		default:
			;

		}
	}

	public DirectionEnum generateInitDirection() {
		Random r = new Random();
		return DirectionEnum.values()[r.nextInt(DirectionEnum.values().length - 1)];
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
		return "I am the " + this.id + " " + type + " in [" + this.positionX + "," + this.positionY + "]" + " -> "
				+ this.direction;
	}

	public Environnement getEnvironnement() {
		return environnement;
	}

	public void setEnvironnement(Environnement environnement) {
		this.environnement = environnement;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public boolean isTaken(int width, int height) {
		return (this.environnement.getEspace()[width][height] != null && this.environnement.getEspace()[width][height].getType() != TypeOfAgentEnum.EMPTY);
	}

	public TypeOfAgentEnum getType() {
		return this.type;
	}

	public void setType(TypeOfAgentEnum type) {
		this.type = type;
	}

	public void setNbCycleReproduction(int nbCycleReproduction) {
		this.nbCycleReproduction = nbCycleReproduction;
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
	
	public void setDirection(DirectionEnum direction){
		this.direction = direction;
	}
}
