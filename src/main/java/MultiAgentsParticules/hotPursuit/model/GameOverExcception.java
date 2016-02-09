package MultiAgentsParticules.hotPursuit.model;

public class GameOverExcception extends Exception {

	public GameOverExcception(){
		System.out.println("Game Over !");
		System.exit(0);
	}
}
