package MultiAgentsParticules.wator;

import MultiAgentsParticules.Agent;

public class Shark extends Agent {

	private static final int nbShark = 0;
	private static final int starv = 0;
	
	public Shark(int positionX, int positionY) {
		super(positionX, positionY);
	}

	public void doIt() {
		
		/*
		 * Deplacement
		 * je me deplace a partir d'une direction
		 * nord
		 * sud
		 * est
		 * west
		 * 
		 * 
		 * 
		 *  
		 */
		
		/*
		 * Reproduction
		 * au bout d'un nombre de deplacement donné je me reproduit
		 */
		
		/*
		 * manger
		 * si je trouve un poisson dans une des cases adjascente, je prend sa place et le poisson disparait
		 * si je mange pas pendant un temps donné ou un nombre de deplacement donné je meurt(je disparrait)
		 * 
		 */
	}

	@Override
	public void doItToric() {
		// TODO Auto-generated method stub
		
	}

}
