package MultiAgentsParticules.wator.model;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import MultiAgentsParticules.core.Agent;
import MultiAgentsParticules.core.SMA;
import MultiAgentsParticules.core.enums.DirectionEnum;
import MultiAgentsParticules.core.enums.TypeOfAgentEnum;

public class Fish extends Agent {

	private static int compteurReproduction = 0;

	public Fish(int positionX, int positionY, int nbCycleReproduction, int roundForSpeak) {
		super(positionX, positionY,roundForSpeak);
		this.setColor(Color.CYAN);
		r = Color.CYAN.getRed();
		g = Color.CYAN.getGreen();
		b = Color.CYAN.getBlue();
		this.setType(TypeOfAgentEnum.FISH);
		this.setNbCycleReproduction(nbCycleReproduction);
	}

	/*
	 * reproduction donner un temps fix de reproduction, si ce temps est
	 * depassé, le poisson se deplace et un autre poisson se crée a ca place
	 * avec sa propre direction
	 */

	/*
	 * deplacement si je trouve en poisson ou un shark je change de dirrecion si
	 * je trouve un bord je change de direction
	 * 
	 */
	public void doIt(boolean torique) {
		List<DirectionEnum> list = possibilities(torique);
		if (list.isEmpty()) {
			direction = DirectionEnum.NONE;
			compteurReproduction++;
		} else {
			Random r = new Random();
			int randomIndex = r.nextInt(list.size());
			direction = list.get(randomIndex);

			// on libere la case
			if (compteurReproduction >= nbCycleReproduction) {
				compteurReproduction = 0;
				Agent baby = new Fish(positionX, positionY, nbCycleReproduction,roundForSpeak);
				SMA.getAgents().add(baby);
				environnement.getEspace()[positionX][positionY] = baby;
				deplacement(direction, torique);
				environnement.getEspace()[positionX][positionY] = this;
			} else {
				environnement.getEspace()[positionX][positionY] = null;
				deplacement(direction, torique);
				environnement.getEspace()[positionX][positionY] = this;
				compteurReproduction++;
			}
		}
	}

	public List<DirectionEnum> possibilities(boolean torique) {
		List<DirectionEnum> list = new LinkedList<DirectionEnum>();
		// TOUCHE A DROITE
		if ((positionX + 1) == environnement.getWidth() && (positionY + 1) < environnement.getHeight()
				&& positionY > 0) {
			// REGARDE HAUT
			if (!isTaken(positionX, positionY + 1)) {
				list.add(DirectionEnum.NORTH);
			}
			// REGARDE HAUT GAUCHE
			if (!isTaken(positionX - 1, positionY + 1)) {
				list.add(DirectionEnum.NORTH_WEST);
			}
			// REGARDE GAUCHE
			if (!isTaken(positionX - 1, positionY)) {
				list.add(DirectionEnum.WEST);
			}
			// REGARDE BAS GAUCHE
			if (!isTaken(positionX - 1, positionY - 1)) {
				list.add(DirectionEnum.SOUTH_WEST);
			}
			// REGARDE BAS
			if (!isTaken(positionX, positionY - 1)) {
				list.add(DirectionEnum.SOUTH);
			}

			// TORIQUE
			// BAS DROITE
			if (torique) {
				if (!isTaken(0, positionY - 1)) {
					list.add(DirectionEnum.SOUTH_EAST);
				}
				// DROITE
				if (!isTaken(0, positionY)) {
					list.add(DirectionEnum.EAST);
				}
				// HAUT DROITE
				if (!isTaken(0, positionY + 1)) {
					list.add(DirectionEnum.NORTH_EAST);
				}
			}
		}
		// TOUCHE EN HAUT A DROITE
		else if ((positionX + 1) == environnement.getWidth() && (positionY + 1) == environnement.getHeight()) {
			// REGARDE GAUCHE
			if (!isTaken(positionX - 1, positionY)) {
				list.add(DirectionEnum.WEST);
			}
			// REGARDE BAS GAUCHE
			if (!isTaken(positionX - 1, positionY - 1)) {
				list.add(DirectionEnum.SOUTH_WEST);
			}
			// REGARDE BAS
			if (!isTaken(positionX, positionY - 1)) {
				list.add(DirectionEnum.SOUTH);
			}

			// TORIQUE
			if (torique) {
				// BAS DROITE
				if (!isTaken(0, positionY - 1)) {
					list.add(DirectionEnum.SOUTH_EAST);
				}
				// DROITE
				if (!isTaken(0, positionY)) {
					list.add(DirectionEnum.EAST);
				}
				// HAUT DROITE
				if (!isTaken(0, 0)) {
					list.add(DirectionEnum.NORTH_EAST);
				}
				// HAUT
				if (!isTaken(positionX, 0)) {
					list.add(DirectionEnum.NORTH);
				}
				// HAUT GAUCHE
				if (!isTaken(positionX - 1, 0)) {
					list.add(DirectionEnum.NORTH_WEST);
				}
			}
		}
		// TOUCHE EN HAUT
		else if ((positionY + 1) == environnement.getHeight() && (positionX + 1) < environnement.getWidth()
				&& positionX > 0) {
			// REGARDE GAUCHE
			if (!isTaken(positionX - 1, positionY)) {
				list.add(DirectionEnum.WEST);
			}
			// REGARDE BAS GAUCHE
			if (!isTaken(positionX - 1, positionY - 1)) {
				list.add(DirectionEnum.SOUTH_WEST);
			}
			// REGARDE BAS
			if (!isTaken(positionX, positionY - 1)) {
				list.add(DirectionEnum.SOUTH);
			}
			// REGARDE BAS DROITE
			if (!isTaken(positionX + 1, positionY - 1)) {
				list.add(DirectionEnum.SOUTH_EAST);
			}
			// REGARDE DROITE
			if (!isTaken(positionX + 1, positionY)) {
				list.add(DirectionEnum.EAST);
			}
			// TORIQUE
			if (torique) {
				// HAUT DROITE
				if (!isTaken(positionX + 1, 0)) {
					list.add(DirectionEnum.NORTH_EAST);
				}
				// HAUT
				if (!isTaken(positionX, 0)) {
					list.add(DirectionEnum.NORTH);
				}
				// HAUT GAUCHE
				if (!isTaken(positionX - 1, 0)) {
					list.add(DirectionEnum.NORTH_WEST);
				}
			}
		}
		// TOUCHE EN HAUT A GAUCHE
		else if ((positionY + 1) == environnement.getHeight() && positionX == 0) {
			// REGARDE BAS
			if (!isTaken(positionX, positionY - 1)) {
				list.add(DirectionEnum.SOUTH);
			}
			// REGARDE BAS DROITE
			if (!isTaken(positionX + 1, positionY - 1)) {
				list.add(DirectionEnum.SOUTH_EAST);
			}
			// REGARDE DROITE
			if (!isTaken(positionX + 1, positionY)) {
				list.add(DirectionEnum.EAST);
			}
			// TORIQUE
			if (torique) {
				// HAUT DROITE
				if (!isTaken(1, 0)) {
					list.add(DirectionEnum.NORTH_EAST);
				}
				// HAUT
				if (!isTaken(0, 0)) {
					list.add(DirectionEnum.NORTH);
				}
				// HAUT GAUCHE
				if (!isTaken(environnement.getWidth() - 1, 0)) {
					list.add(DirectionEnum.NORTH_WEST);
				}
				// GAUCHE
				if (!isTaken(environnement.getWidth() - 1, positionY)) {
					list.add(DirectionEnum.WEST);
				}
				// BAS GAUCHE
				if (!isTaken(environnement.getWidth() - 1, positionY - 1)) {
					list.add(DirectionEnum.SOUTH_WEST);
				}
			}
		}
		// TOUCHE A GAUCHE
		else if (positionX == 0 && (positionY + 1) < environnement.getHeight() && positionY > 0) {
			// REGARDE HAUT
			if (!isTaken(positionX, positionY + 1)) {
				list.add(DirectionEnum.NORTH);
			}
			// REGARDE HAUT DROITE
			if (!isTaken(positionX + 1, positionY + 1)) {
				list.add(DirectionEnum.NORTH_EAST);
			}
			// REGARDE DROITE
			if (!isTaken(positionX + 1, positionY)) {
				list.add(DirectionEnum.EAST);
			}
			// REGARDE BAS DROITE
			if (!isTaken(positionX + 1, positionY - 1)) {
				list.add(DirectionEnum.SOUTH_EAST);
			}
			// REGARDE BAS
			if (!isTaken(positionX, positionY - 1)) {
				list.add(DirectionEnum.SOUTH);
			}
			// TORIQUE
			if (torique) {
				// BAS GAUCHE
				if (!isTaken(environnement.getWidth() - 1, positionY - 1)) {
					list.add(DirectionEnum.SOUTH_WEST);
				}
				// GAUCHE
				if (!isTaken(environnement.getWidth() - 1, positionY)) {
					list.add(DirectionEnum.WEST);
				}
				// HAUT GAUCHE
				if (!isTaken(environnement.getWidth() - 1, positionY + 1)) {
					list.add(DirectionEnum.NORTH_WEST);
				}
			}
		}
		// TOUCHE EN BAS A GAUCHE
		else if (positionX == 0 && positionY == 0) {
			// REGARDE DROITE
			if (!isTaken(positionX + 1, positionY)) {
				list.add(DirectionEnum.EAST);
			}
			// REGARDE HAUT DROITE
			if (!isTaken(positionX + 1, positionY + 1)) {
				list.add(DirectionEnum.NORTH_EAST);
			}
			// REGARDE HAUT
			if (!isTaken(positionX, positionY + 1)) {
				list.add(DirectionEnum.NORTH);
			}
			// TORIQUE
			if (torique) {
				// HAUT GAUCHE
				if (!isTaken(environnement.getWidth() - 1, positionY + 1)) {
					list.add(DirectionEnum.NORTH_WEST);
				}
				// GAUCHE
				if (!isTaken(environnement.getWidth() - 1, positionY)) {
					list.add(DirectionEnum.WEST);
				}
				// BAS GAUCHE
				if (!isTaken(environnement.getWidth() - 1, environnement.getHeight() - 1)) {
					list.add(DirectionEnum.SOUTH_WEST);
				}
				// BAS
				if (!isTaken(positionX, environnement.getHeight() - 1)) {
					list.add(DirectionEnum.SOUTH);
				}
				// BAS DROITE
				if (!isTaken(positionX + 1, environnement.getHeight() - 1)) {
					list.add(DirectionEnum.SOUTH_EAST);
				}
			}
		}
		// TOUCHE EN BAS
		else if (positionY == 0 && (positionX + 1) < environnement.getWidth() && positionX > 0) {
			// REGARDE GAUCHE
			if (!isTaken(positionX - 1, positionY)) {
				list.add(DirectionEnum.WEST);
			}
			// REGARDE HAUT GAUCHE
			if (!isTaken(positionX - 1, positionY + 1)) {
				list.add(DirectionEnum.NORTH_WEST);
			}
			// REGARDE HAUT
			if (!isTaken(positionX, positionY + 1)) {
				list.add(DirectionEnum.NORTH);
			}
			// REGARDE HAUT DROITE
			if (!isTaken(positionX + 1, positionY + 1)) {
				list.add(DirectionEnum.NORTH_EAST);
			}
			// REGARDE DROITE
			if (!isTaken(positionX + 1, positionY)) {
				list.add(DirectionEnum.EAST);
			}
			// TORIQUE
			if (torique) {
				// BAS DROITE
				if (!isTaken(positionX + 1, environnement.getHeight() - 1)) {
					list.add(DirectionEnum.SOUTH_EAST);
				}
				// BAS
				if (!isTaken(positionX, environnement.getHeight() - 1)) {
					list.add(DirectionEnum.SOUTH);
				}
				// BAS GAUCHE
				if (!isTaken(positionX - 1, environnement.getHeight() - 1)) {
					list.add(DirectionEnum.SOUTH_WEST);
				}
			}
		}
		// TOUCHE EN BAS A DROITE
		else if ((positionX + 1) == environnement.getWidth() && positionY == 0) {
			// REGARDE GAUCHE
			if (!isTaken(positionX - 1, positionY)) {
				list.add(DirectionEnum.WEST);
			}
			// REGARDE HAUT GAUCHE
			if (!isTaken(positionX - 1, positionY + 1)) {
				list.add(DirectionEnum.NORTH_WEST);
			}
			// REGARDE HAUT
			if (!isTaken(positionX, positionY + 1)) {
				list.add(DirectionEnum.NORTH);
			}
			// TORIQUE
			if (torique) {
				// HAUT DROITE
				if (!isTaken(0, positionY + 1)) {
					list.add(DirectionEnum.NORTH_EAST);
				}
				// DROITE
				if (!isTaken(0, positionY)) {
					list.add(DirectionEnum.EAST);
				}
				// BAS DROITE
				if (!isTaken(0, environnement.getHeight() - 1)) {
					list.add(DirectionEnum.SOUTH_EAST);
				}
				// BAS
				if (!isTaken(positionX, environnement.getHeight() - 1)) {
					list.add(DirectionEnum.SOUTH);
				}
				// BAS GAUCHE
				if (!isTaken(positionX - 1, environnement.getHeight() - 1)) {
					list.add(DirectionEnum.SOUTH_WEST);
				}
			}
		} else {
			// REGARDE HAUT
			if (!isTaken(positionX, positionY + 1)) {
				list.add(DirectionEnum.NORTH);
			}
			// REGARDE HAUT DROITE
			if (!isTaken(positionX + 1, positionY + 1)) {
				list.add(DirectionEnum.NORTH_EAST);
			}
			// REGARDE DROITE
			if (!isTaken(positionX + 1, positionY)) {
				list.add(DirectionEnum.EAST);
			}
			// REGARDE BAS DROITE
			if (!isTaken(positionX + 1, positionY - 1)) {
				list.add(DirectionEnum.SOUTH_EAST);
			}
			// REGARDE BAS
			if (!isTaken(positionX, positionY - 1)) {
				list.add(DirectionEnum.SOUTH);
			}
			// REGARDE BAS GAUCHE
			if (!isTaken(positionX - 1, positionY - 1)) {
				list.add(DirectionEnum.SOUTH_WEST);
			}
			// REGARDE GAUCHE
			if (!isTaken(positionX - 1, positionY)) {
				list.add(DirectionEnum.WEST);
			}
			// REGARDE HAUT GAUCHE
			if (!isTaken(positionX - 1, positionY + 1)) {
				list.add(DirectionEnum.NORTH_WEST);
			}
		}
		return list;
	}

}
