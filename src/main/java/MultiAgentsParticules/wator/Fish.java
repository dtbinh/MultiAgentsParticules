package MultiAgentsParticules.wator;

import MultiAgentsParticules.Agent;
import MultiAgentsParticules.Direction;

public class Fish extends Agent {

	public Fish(int positionX, int positionY) {
		super(positionX, positionY);
	}
	
	public void doIt() {

		if (initialisation) {
			// au depart, on choisit une direction pour l'agent
			this.direction = generateInitDirection();
			initialisation = false;
		}
		
		/* deplacement
		 * si je trouve en poisson ou un shark je change de dirrecion
		 * si je trouve un bord je change de direction
		 * 
		 */
		// NORTH
		if (direction == Direction.NORTH) {
			// verification de case vide + verification si on est sur le bord
			if ((environnement.getHeight() == (positionY + 1))
					|| (environnement.getEspace()[positionX][positionY + 1])) {
				direction = Direction.SOUTH;
			}
		}
		
		
		
		/* reproduction
		 * donner un temps fix de reproduction, si ce temps est depassé, le poisson se deplace et
		 * un autre poisson se crée a ca place avec sa propre direction
		 */
		
	}

}
