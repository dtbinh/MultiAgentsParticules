package MultiAgentsParticules;

import java.awt.Color;
import java.util.Random;

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
	protected Direction direction;
	protected boolean initialisation = true;
	protected Environnement environnement = SMA.getEnvironnement();
	protected Color color;

	public Agent(int positionX, int positionY) {
		this.setPositionX(positionX);
		this.setPositionY(positionY);

		this.setColor(generateColor());
		Random rand = new Random();
		r = rand.nextInt(256);
		g = rand.nextInt(256);
		b = rand.nextInt(256);
	}

	// TODO : vérifier à l'initiation que 2 agents ne sont pas côtes à côtes
	// sinon il se peut qu'un agent soit déja au bord et qu'il veuille
	// aller dans le sens contraire
	public abstract void doIt();
	public abstract void doItToric();

	public void deplacement(Direction direction) {
		switch (direction) {
		case NORTH:
			this.positionY += 1;
			break;
		case NORTH_EAST:
			this.positionX += 1;
			this.positionY += 1;
			break;
		case EAST:
			this.positionX += 1;
			break;
		case SOUTH_EAST:
			this.positionX += 1;
			this.positionY -= 1;
			break;
		case SOUTH:
			this.positionY -= 1;
			break;
		case SOUTH_WEST:
			this.positionX -= 1;
			this.positionY -= 1;
			break;
		case WEST:
			this.positionX -= 1;
			break;
		case NORTH_WEST:
			this.positionX -= 1;
			this.positionY += 1;
			break;
		default:
			;

		}
	}

	public Direction generateInitDirection() {
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
		return "I am " + this.id + " in [" + this.positionX + "," + this.positionY + "]" + " -> " + this.direction;
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
	
	public int getR(){
		return r;
	}
	
	public int getG(){
		return g;
	}
	
	public int getB(){
		return b;
	}

	private Color generateColor() {
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		return new Color(r, g, b);
	}
}
