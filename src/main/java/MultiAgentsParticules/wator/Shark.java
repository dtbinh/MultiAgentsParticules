package MultiAgentsParticules.wator;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import MultiAgentsParticules.Agent;
import MultiAgentsParticules.SMA;
import MultiAgentsParticules.enums.DirectionEnum;
import MultiAgentsParticules.enums.TypeOfAgentEnum;

public class Shark extends Agent {
	private List<DirectionEnum> toBeEaten;
	private List<DirectionEnum> list;

	private int nbCycleDeath;
	private int compteurNbCycleDeath;
	private static int compteurReproduction = 0;

	private static final int nbShark = 0;
	private static final int starv = 0;
	
	public Shark(int positionX, int positionY, int nbCycleReproduction, int nbCycleDeath) {
		super(positionX, positionY);
		this.setColor(Color.BLACK);
		r = Color.BLACK.getRed();
		g = Color.BLACK.getGreen();
		b = Color.BLACK.getBlue();
		this.setType(TypeOfAgentEnum.SHARK);
		this.setNbCycleReproduction(nbCycleReproduction);
		this.setNbCycleDeath(nbCycleDeath);
		compteurNbCycleDeath = nbCycleDeath;
	}

	/*
	 * Deplacement
	 * je me deplace a partir d'une direction
	 * nord
	 * sud
	 * est
	 * west
	 */
	
	/*
	 * Reproduction
	 * au bout d'un nombre de deplacement donné je me reproduit
	 */
	
	/*
	 * Manger
	 * si je trouve un poisson dans une des cases adjascente, je prend sa place et le poisson disparait
	 * si je mange pas pendant un temps donné ou un nombre de deplacement donné je meurs(je disparrait)
	 * 
	 */
	public void doIt(boolean torique) {
		Random r = new Random();
		if (compteurNbCycleDeath == 0) {
			environnement.getEspace()[positionX][positionY] = null;
			SMA.getAgents().remove(this);
		} else {
			generatePossibilities(torique);

			// Le requin mange un poisson dans ce cas : si toBeEaten n'est pas
			// vide
			// c'est qu'il y a un ou des poissons a côté
			if (!toBeEaten.isEmpty()) {
				int randomIndex = r.nextInt(toBeEaten.size());
				direction = toBeEaten.get(randomIndex);

				// on libere la case et on laisse le bébé si on a atteint le
				// compteur et on le remet à 0
				if (compteurReproduction >= nbCycleReproduction) {
					compteurReproduction = 0;
					Agent baby = new Shark(positionX, positionY, nbCycleReproduction, nbCycleDeath);
					environnement.getEspace()[positionX][positionY] = baby;
					SMA.getAgents().add(baby);
					// on se déplace en mangeant le poisson
					deplacement(direction, torique);
					SMA.getAgents().remove(environnement.getEspace()[positionX][positionY]);
					environnement.getEspace()[positionX][positionY] = this;
				}
				// sinon on ne fait que libérer la case et on incrémente le
				// compteur
				else {
					environnement.getEspace()[positionX][positionY] = null;
					compteurReproduction++;
					// on se déplace en mangeant le poisson
					deplacement(direction, torique);
					SMA.getAgents().remove(environnement.getEspace()[positionX][positionY]);
					environnement.getEspace()[positionX][positionY] = this;
				}

				// compteurNbCycleDeath++;
				//compteurNbCycleDeath--;

			} else if (list.isEmpty()) {
				direction = DirectionEnum.NONE;
				compteurReproduction++;
				compteurNbCycleDeath--;
			} else {
				int randomIndex = r.nextInt(list.size());
				direction = list.get(randomIndex);

				// on libere la case
				if (compteurReproduction >= nbCycleReproduction) {
					compteurReproduction = 0;
					Agent baby = new Shark(positionX, positionY, nbCycleReproduction, nbCycleDeath);
					environnement.getEspace()[positionX][positionY] = baby;
					deplacement(direction, torique);
					environnement.getEspace()[positionX][positionY] = this;
					SMA.getAgents().add(baby);
				} else {
					environnement.getEspace()[positionX][positionY] = null;
					compteurReproduction++;
					deplacement(direction, torique);
					environnement.getEspace()[positionX][positionY] = this;
				}
				compteurNbCycleDeath--;
			}
		}
	}

	public void generatePossibilities(boolean torique) {
		list = new LinkedList<DirectionEnum>();
		toBeEaten = new LinkedList<DirectionEnum>();;
		// TOUCHE A DROITE
		if ((positionX + 1) == environnement.getWidth() && (positionY + 1) < environnement.getHeight()
				&& positionY > 0) {
			// REGARDE HAUT
			if (!isTaken(positionX, positionY + 1)) {
				list.add(DirectionEnum.NORTH);
			} else if (environnement.getEspace()[positionX][positionY + 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.NORTH);
			}
			// REGARDE HAUT GAUCHE
			if (!isTaken(positionX - 1, positionY + 1)) {
				list.add(DirectionEnum.NORTH_WEST);
			} else if (environnement.getEspace()[positionX - 1][positionY + 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.NORTH_WEST);
			}
			// REGARDE GAUCHE
			if (!isTaken(positionX - 1, positionY)) {
				list.add(DirectionEnum.WEST);
			} else if (environnement.getEspace()[positionX - 1][positionY].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.WEST);
			}
			// REGARDE BAS GAUCHE
			if (!isTaken(positionX - 1, positionY - 1)) {
				list.add(DirectionEnum.SOUTH_WEST);
			} else if (environnement.getEspace()[positionX - 1][positionY - 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.SOUTH_WEST);
			}
			// REGARDE BAS
			if (!isTaken(positionX, positionY - 1)) {
				list.add(DirectionEnum.SOUTH);
			} else if (environnement.getEspace()[positionX][positionY - 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.SOUTH);
			}
			// TORIQUE
			// BAS DROITE
			if (torique) {
				if (!isTaken(0, positionY - 1)) {
					list.add(DirectionEnum.SOUTH_EAST);
				} else if (environnement.getEspace()[0][positionY - 1].getType().equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.SOUTH_EAST);
				}
				// DROITE
				if (!isTaken(0, positionY)) {
					list.add(DirectionEnum.EAST);
				} else if (environnement.getEspace()[0][positionY].getType().equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.EAST);
				}
				// HAUT DROITE
				if (!isTaken(0, positionY + 1)) {
					list.add(DirectionEnum.NORTH_EAST);
				} else if (environnement.getEspace()[0][positionY + 1].getType().equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.NORTH_EAST);
				}
			}
		}
		// TOUCHE EN HAUT A DROITE
		else if ((positionX + 1) == environnement.getWidth() && (positionY + 1) == environnement.getHeight()) {
			// REGARDE GAUCHE
			if (!isTaken(positionX - 1, positionY)) {
				list.add(DirectionEnum.WEST);
			} else if (environnement.getEspace()[positionX - 1][positionY].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.WEST);
			}
			// REGARDE BAS GAUCHE
			if (!isTaken(positionX - 1, positionY - 1)) {
				list.add(DirectionEnum.SOUTH_WEST);
			} else if (environnement.getEspace()[positionX - 1][positionY - 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.SOUTH_WEST);
			}
			// REGARDE BAS
			if (!isTaken(positionX, positionY - 1)) {
				list.add(DirectionEnum.SOUTH);
			} else if (environnement.getEspace()[positionX][positionY - 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.SOUTH);
			}
			// TORIQUE
			if (torique) {
				// BAS DROITE
				if (!isTaken(0, positionY - 1)) {
					list.add(DirectionEnum.SOUTH_EAST);
				} else if (environnement.getEspace()[0][positionY - 1].getType().equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.SOUTH_EAST);
				}
				// DROITE
				if (!isTaken(0, positionY)) {
					list.add(DirectionEnum.EAST);
				} else if (environnement.getEspace()[0][positionY].getType().equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.EAST);
				}
				// HAUT DROITE
				if (!isTaken(0, 0)) {
					list.add(DirectionEnum.NORTH_EAST);
				} else if (environnement.getEspace()[0][0].getType().equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.NORTH_EAST);
				}
				// HAUT
				if (!isTaken(positionX, 0)) {
					list.add(DirectionEnum.NORTH);
				} else if (environnement.getEspace()[positionX][0].getType().equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.NORTH);
				}
				// HAUT GAUCHE
				if (!isTaken(positionX - 1, 0)) {
					list.add(DirectionEnum.NORTH_WEST);
				} else if (environnement.getEspace()[positionX - 1][0].getType().equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.NORTH_WEST);
				}
			}
		}
		// TOUCHE EN HAUT
		else if ((positionY + 1) == environnement.getHeight() && (positionX + 1) < environnement.getWidth()
				&& positionX > 0) {
			// REGARDE GAUCHE
			if (!isTaken(positionX - 1, positionY)) {
				list.add(DirectionEnum.WEST);
			} else if (environnement.getEspace()[positionX - 1][positionY].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.WEST);
			}
			// REGARDE BAS GAUCHE
			if (!isTaken(positionX - 1, positionY - 1)) {
				list.add(DirectionEnum.SOUTH_WEST);
			} else if (environnement.getEspace()[positionX - 1][positionY - 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.SOUTH_WEST);
			}
			// REGARDE BAS
			if (!isTaken(positionX, positionY - 1)) {
				list.add(DirectionEnum.SOUTH);
			} else if (environnement.getEspace()[positionX][positionY - 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.SOUTH);
			}
			// REGARDE BAS DROITE
			if (!isTaken(positionX + 1, positionY - 1)) {
				list.add(DirectionEnum.SOUTH_EAST);
			} else if (environnement.getEspace()[positionX + 1][positionY - 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.SOUTH_EAST);
			}
			// REGARDE DROITE
			if (!isTaken(positionX + 1, positionY)) {
				list.add(DirectionEnum.EAST);
			} else if (environnement.getEspace()[positionX + 1][positionY].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.EAST);
			}
			// TORIQUE
			if (torique) {
				// HAUT DROITE
				if (!isTaken(positionX + 1, 0)) {
					list.add(DirectionEnum.NORTH_EAST);
				} else if (environnement.getEspace()[positionX + 1][0].getType().equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.NORTH_EAST);
				}
				// HAUT
				if (!isTaken(positionX, 0)) {
					list.add(DirectionEnum.NORTH);
				} else if (environnement.getEspace()[positionX][0].getType().equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.NORTH);
				}
				// HAUT GAUCHE
				if (!isTaken(positionX - 1, 0)) {
					list.add(DirectionEnum.NORTH_WEST);
				} else if (environnement.getEspace()[positionX - 1][0].getType().equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.NORTH_WEST);
				}
			}
		}
		// TOUCHE EN HAUT A GAUCHE
		else if ((positionY + 1) == environnement.getHeight() && positionX == 0) {
			// REGARDE BAS
			if (!isTaken(positionX, positionY - 1)) {
				list.add(DirectionEnum.SOUTH);
			} else if (environnement.getEspace()[positionX][positionY - 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.SOUTH);
			}
			// REGARDE BAS DROITE
			if (!isTaken(positionX + 1, positionY - 1)) {
				list.add(DirectionEnum.SOUTH_EAST);
			} else if (environnement.getEspace()[positionX + 1][positionY - 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.SOUTH_EAST);
			}
			// REGARDE DROITE
			if (!isTaken(positionX + 1, positionY)) {
				list.add(DirectionEnum.EAST);
			} else if (environnement.getEspace()[positionX + 1][positionY].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.EAST);
			}
			// TORIQUE
			if (torique) {
				// HAUT DROITE
				if (!isTaken(1, 0)) {
					list.add(DirectionEnum.NORTH_EAST);
				} else if (environnement.getEspace()[1][0].getType().equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.NORTH_EAST);
				}
				// HAUT
				if (!isTaken(0, 0)) {
					list.add(DirectionEnum.NORTH);
				} else if (environnement.getEspace()[0][0].getType().equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.NORTH);
				}
				// HAUT GAUCHE
				if (!isTaken(environnement.getWidth() - 1, 0)) {
					list.add(DirectionEnum.NORTH_WEST);
				} else if (environnement.getEspace()[environnement.getWidth() - 1][0].getType()
						.equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.NORTH_WEST);
				}
				// GAUCHE
				if (!isTaken(environnement.getWidth() - 1, positionY)) {
					list.add(DirectionEnum.WEST);
				} else if (environnement.getEspace()[environnement.getWidth() - 1][positionY].getType()
						.equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.WEST);
				}
				// BAS GAUCHE
				if (!isTaken(environnement.getWidth() - 1, positionY - 1)) {
					list.add(DirectionEnum.SOUTH_WEST);
				} else if (environnement.getEspace()[environnement.getWidth() - 1][positionY - 1].getType()
						.equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.SOUTH_WEST);
				}
			}
		}
		// TOUCHE A GAUCHE
		else if (positionX == 0 && (positionY + 1) < environnement.getHeight() && positionY > 0) {
			// REGARDE HAUT
			if (!isTaken(positionX, positionY + 1)) {
				list.add(DirectionEnum.NORTH);
			} else if (environnement.getEspace()[positionX][positionY + 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.NORTH);
			}
			// REGARDE HAUT DROITE
			if (!isTaken(positionX + 1, positionY + 1)) {
				list.add(DirectionEnum.NORTH_EAST);
			} else if (environnement.getEspace()[positionX + 1][positionY + 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.NORTH_EAST);
			}
			// REGARDE DROITE
			if (!isTaken(positionX + 1, positionY)) {
				list.add(DirectionEnum.EAST);
			} else if (environnement.getEspace()[positionX + 1][positionY].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.EAST);
			}
			// REGARDE BAS DROITE
			if (!isTaken(positionX + 1, positionY - 1)) {
				list.add(DirectionEnum.SOUTH_EAST);
			} else if (environnement.getEspace()[positionX + 1][positionY - 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.SOUTH_EAST);
			}
			// REGARDE BAS
			if (!isTaken(positionX, positionY - 1)) {
				list.add(DirectionEnum.SOUTH);
			} else if (environnement.getEspace()[positionX][positionY - 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.SOUTH);
			}
			// TORIQUE
			if (torique) {
				// BAS GAUCHE
				if (!isTaken(environnement.getWidth() - 1, positionY - 1)) {
					list.add(DirectionEnum.SOUTH_WEST);
				} else if (environnement.getEspace()[environnement.getWidth() - 1][positionY - 1].getType()
						.equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.SOUTH_WEST);
				}
				// GAUCHE
				if (!isTaken(environnement.getWidth() - 1, positionY)) {
					list.add(DirectionEnum.WEST);
				} else if (environnement.getEspace()[environnement.getWidth() - 1][positionY].getType()
						.equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.WEST);
				}
				// HAUT GAUCHE
				if (!isTaken(environnement.getWidth() - 1, positionY + 1)) {
					list.add(DirectionEnum.NORTH_WEST);
				} else if (environnement.getEspace()[environnement.getWidth() - 1][positionY + 1].getType()
						.equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.NORTH_WEST);
				}
			}
		}
		// TOUCHE EN BAS A GAUCHE
		else if (positionX == 0 && positionY == 0) {
			// REGARDE DROITE
			if (!isTaken(positionX + 1, positionY)) {
				list.add(DirectionEnum.EAST);
			} else if (environnement.getEspace()[positionX + 1][positionY].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.EAST);
			}
			// REGARDE HAUT DROITE
			if (!isTaken(positionX + 1, positionY + 1)) {
				list.add(DirectionEnum.NORTH_EAST);
			} else if (environnement.getEspace()[positionX + 1][positionY + 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.NORTH_EAST);
			}
			// REGARDE HAUT
			if (!isTaken(positionX, positionY + 1)) {
				list.add(DirectionEnum.NORTH);
			} else if (environnement.getEspace()[positionX][positionY + 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.NORTH);
			}
			// TORIQUE
			if (torique) {
				// HAUT GAUCHE
				if (!isTaken(environnement.getWidth() - 1, positionY + 1)) {
					list.add(DirectionEnum.NORTH_WEST);
				} else if (environnement.getEspace()[environnement.getWidth() - 1][positionY + 1].getType()
						.equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.NORTH_WEST);
				}
				// GAUCHE
				if (!isTaken(environnement.getWidth() - 1, positionY)) {
					list.add(DirectionEnum.WEST);
				} else if (environnement.getEspace()[environnement.getWidth() - 1][positionY].getType()
						.equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.WEST);
				}
				// BAS GAUCHE
				if (!isTaken(environnement.getWidth() - 1, environnement.getHeight() - 1)) {
					list.add(DirectionEnum.SOUTH_WEST);
				} else if (environnement.getEspace()[environnement.getWidth() - 1][environnement.getHeight() - 1]
						.getType().equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.SOUTH_WEST);
				}
				// BAS
				if (!isTaken(positionX, environnement.getHeight() - 1)) {
					list.add(DirectionEnum.SOUTH);
				} else if (environnement.getEspace()[positionX][environnement.getHeight() - 1].getType()
						.equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.SOUTH);
				}
				// BAS DROITE
				if (!isTaken(positionX + 1, environnement.getHeight() - 1)) {
					list.add(DirectionEnum.SOUTH_EAST);
				} else if (environnement.getEspace()[positionX + 1][environnement.getHeight() - 1].getType()
						.equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.SOUTH_EAST);
				}
			}
		}
		// TOUCHE EN BAS
		else if (positionY == 0 && (positionX + 1) < environnement.getWidth() && positionX > 0) {
			// REGARDE GAUCHE
			if (!isTaken(positionX - 1, positionY)) {
				list.add(DirectionEnum.WEST);
			} else if (environnement.getEspace()[positionX - 1][positionY].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.WEST);
			}
			// REGARDE HAUT GAUCHE
			if (!isTaken(positionX - 1, positionY + 1)) {
				list.add(DirectionEnum.NORTH_WEST);
			} else if (environnement.getEspace()[positionX - 1][positionY + 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.NORTH_WEST);
			}
			// REGARDE HAUT
			if (!isTaken(positionX, positionY + 1)) {
				list.add(DirectionEnum.NORTH);
			} else if (environnement.getEspace()[positionX][positionY + 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.NORTH);
			}
			// REGARDE HAUT DROITE
			if (!isTaken(positionX + 1, positionY + 1)) {
				list.add(DirectionEnum.NORTH_EAST);
			} else if (environnement.getEspace()[positionX + 1][positionY + 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.NORTH_EAST);
			}
			// REGARDE DROITE
			if (!isTaken(positionX + 1, positionY)) {
				list.add(DirectionEnum.EAST);
			} else if (environnement.getEspace()[positionX + 1][positionY].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.EAST);
			}
			// TORIQUE
			if (torique) {
				// BAS DROITE
				if (!isTaken(positionX + 1, environnement.getHeight() - 1)) {
					list.add(DirectionEnum.SOUTH_EAST);
				} else if (environnement.getEspace()[positionX + 1][environnement.getHeight() - 1].getType()
						.equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.SOUTH_EAST);
				}
				// BAS
				if (!isTaken(positionX, environnement.getHeight() - 1)) {
					list.add(DirectionEnum.SOUTH);
				} else if (environnement.getEspace()[positionX][environnement.getHeight() - 1].getType()
						.equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.SOUTH);
				}
				// BAS GAUCHE
				if (!isTaken(positionX - 1, environnement.getHeight() - 1)) {
					list.add(DirectionEnum.SOUTH_WEST);
				} else if (environnement.getEspace()[positionX - 1][environnement.getHeight() - 1].getType()
						.equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.SOUTH_WEST);
				}
			}
		}
		// TOUCHE EN BAS A DROITE
		else if ((positionX + 1) == environnement.getWidth() && positionY == 0) {
			// REGARDE GAUCHE
			if (!isTaken(positionX - 1, positionY)) {
				list.add(DirectionEnum.WEST);
			} else if (environnement.getEspace()[positionX - 1][positionY].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.WEST);
			}
			// REGARDE HAUT GAUCHE
			if (!isTaken(positionX - 1, positionY + 1)) {
				list.add(DirectionEnum.NORTH_WEST);
			} else if (environnement.getEspace()[positionX - 1][positionY + 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.NORTH_WEST);
			}
			// REGARDE HAUT
			if (!isTaken(positionX, positionY + 1)) {
				list.add(DirectionEnum.NORTH);
			} else if (environnement.getEspace()[positionX][positionY + 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.NORTH);
			}
			// TORIQUE
			if (torique) {
				// HAUT DROITE
				if (!isTaken(0, positionY + 1)) {
					list.add(DirectionEnum.NORTH_EAST);
				} else if (environnement.getEspace()[0][positionY + 1].getType().equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.NORTH_EAST);
				}
				// DROITE
				if (!isTaken(0, positionY)) {
					list.add(DirectionEnum.EAST);
				} else if (environnement.getEspace()[0][positionY].getType().equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.EAST);
				}
				// BAS DROITE
				if (!isTaken(0, environnement.getHeight() - 1)) {
					list.add(DirectionEnum.SOUTH_EAST);
				} else if (environnement.getEspace()[0][environnement.getHeight() - 1].getType()
						.equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.SOUTH_EAST);
				}
				// BAS
				if (!isTaken(positionX, environnement.getHeight() - 1)) {
					list.add(DirectionEnum.SOUTH);
				} else if (environnement.getEspace()[positionX][environnement.getHeight() - 1].getType()
						.equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.SOUTH);
				}
				// BAS GAUCHE
				if (!isTaken(positionX - 1, environnement.getHeight() - 1)) {
					list.add(DirectionEnum.SOUTH_WEST);
				} else if (environnement.getEspace()[positionX - 1][environnement.getHeight() - 1].getType()
						.equals(TypeOfAgentEnum.FISH)) {
					toBeEaten.add(DirectionEnum.SOUTH_WEST);
				}
			}
		} else {
			// REGARDE HAUT
			if (!isTaken(positionX, positionY + 1)) {
				list.add(DirectionEnum.NORTH);
			} else if (environnement.getEspace()[positionX][positionY + 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.NORTH);
			}
			// REGARDE HAUT DROITE
			if (!isTaken(positionX + 1, positionY + 1)) {
				list.add(DirectionEnum.NORTH_EAST);
			} else if (environnement.getEspace()[positionX + 1][positionY + 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.NORTH_EAST);
			}
			// REGARDE DROITE
			if (!isTaken(positionX + 1, positionY)) {
				list.add(DirectionEnum.EAST);
			} else if (environnement.getEspace()[positionX + 1][positionY].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.EAST);
			}
			// REGARDE BAS DROITE
			if (!isTaken(positionX + 1, positionY - 1)) {
				list.add(DirectionEnum.SOUTH_EAST);
			} else if (environnement.getEspace()[positionX + 1][positionY - 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.SOUTH_EAST);
			}
			// REGARDE BAS
			if (!isTaken(positionX, positionY - 1)) {
				list.add(DirectionEnum.SOUTH);
			} else if (environnement.getEspace()[positionX][positionY - 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.SOUTH);
			}
			// REGARDE BAS GAUCHE
			if (!isTaken(positionX - 1, positionY - 1)) {
				list.add(DirectionEnum.SOUTH_WEST);
			} else if (environnement.getEspace()[positionX - 1][positionY - 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.SOUTH_WEST);
			}
			// REGARDE GAUCHE
			if (!isTaken(positionX - 1, positionY)) {
				list.add(DirectionEnum.WEST);
			} else if (environnement.getEspace()[positionX - 1][positionY].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.WEST);
			}
			// REGARDE HAUT GAUCHE
			if (!isTaken(positionX - 1, positionY + 1)) {
				list.add(DirectionEnum.NORTH_WEST);
			} else if (environnement.getEspace()[positionX - 1][positionY + 1].getType().equals(TypeOfAgentEnum.FISH)) {
				toBeEaten.add(DirectionEnum.NORTH_WEST);
			}
		}
	}

	public void setNbCycleDeath(int nbCycleDeath) {
		this.nbCycleDeath = nbCycleDeath;
	}
}
