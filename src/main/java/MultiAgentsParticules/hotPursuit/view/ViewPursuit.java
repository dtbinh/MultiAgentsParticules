package MultiAgentsParticules.hotPursuit.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import MultiAgentsParticules.core.Agent;
import MultiAgentsParticules.core.SMA;
import MultiAgentsParticules.core.enums.DirectionEnum;
import MultiAgentsParticules.core.enums.TypeOfAgentEnum;
import MultiAgentsParticules.hotPursuit.model.GameOverExcception;
import MultiAgentsParticules.launcher.Main;

public class ViewPursuit implements KeyListener, Observer {

	private SMA sma;
	private Grid grid;
	private JFrame frame;
	private Agent agentChased;
	private int[][] matrice = sma.getEnvironnement().getMatriceDijkstra();
	private static boolean torique;
	private static int speed;
	private static int sizeAgent;

	/*
	 * TODO Rendre paramétrable dans la vue : - hauteur, largeur - nombre de
	 * bille - vitesse - taille des billes - durée de l'exécution
	 */

	public ViewPursuit(boolean torique, int speed, int sizeAgent) throws InterruptedException {
		this.sma = Main.getSma();
		this.torique = torique;
		this.speed = speed;
		this.sizeAgent = sizeAgent;
		this.matrice = sma.getEnvironnement().getMatriceDijkstra();
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(sma.getEnvironnement().getWidth() * sizeAgent + 30,
				sma.getEnvironnement().getHeight() * sizeAgent + 30));
		grid = new Grid(sma.getEnvironnement().getWidth() * sizeAgent, sma.getEnvironnement().getHeight() * sizeAgent);
		frame.add(grid);
		frame.setVisible(true);
		// List<Agent> list = new ArrayList<Agent>(sma.getAgents());
		for (int i = 0; i < sma.getAgents().size(); i++) {
			Agent a = sma.getAgents().get(i);
			grid.fillCell(a.getPositionX(), a.getPositionY(), a.getColor(),
					matrice[a.getPositionX()][a.getPositionY()]);
			if (a.getType() == TypeOfAgentEnum.CHASED) {
				agentChased = a;
			}
		}
		frame.addKeyListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public void launch() throws InterruptedException, GameOverExcception {
		sma.run(torique, speed);
	}

	public void update(Observable o, Object arg) {
		grid.clean();
		this.matrice = sma.getEnvironnement().getMatriceDijkstra();
		for (int i = 0; i < sma.getAgents().size(); i++) {
			Agent a = sma.getAgents().get(i);
			grid.fillCell(a.getPositionX(), a.getPositionY(), a.getColor(),
					matrice[a.getPositionX()][a.getPositionY()]);

		}

		/*
		 * for (int i = 0; i < matrice.length; i++) { for (int j = 0; j <
		 * matrice[i].length; j++) { grid.fillCell(, y, color, value); } }
		 */
		grid.repaint();
	}

	public static class Grid extends JPanel {

		private List<Point> fillCells;
		private int width;
		private int height;
		private Map<Point, Color> mapColors;
		private Map<Point, Integer> mapValue;

		public Grid(int width, int height) {
			fillCells = new ArrayList<Point>();
			this.width = width;
			this.height = height;
			mapColors = new HashMap<Point, Color>();
			mapValue = new HashMap<Point, Integer>();
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (int i = 0; i < fillCells.size(); i++) {
				Point fillCell = fillCells.get(i);
				Color c = mapColors.get(fillCell);
				String value = "" + mapValue.get(fillCell);
				int cellX = 5 + (fillCell.x * sizeAgent);
				int cellY = 5 + (fillCell.y * sizeAgent);
				g.setColor(c);
				g.fillOval(cellX, cellY, sizeAgent, sizeAgent);
				// g.drawString(value, cellX, cellY);
			}
			g.setColor(Color.BLACK);
			g.drawRect(5, 5, width, height);
		}

		public void clean() {
			fillCells = new ArrayList<Point>();
		}

		public void fillCell(int x, int y, Color color, int value) {
			Point p = new Point(x, y);
			fillCells.add(p);
			mapColors.put(p, color);
			mapValue.put(p, value);
		}

		public void repaint() {
			super.repaint();
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		//System.out.println(e.getKeyCode());
		/*
		 * U I O
		 * J K L
		 * , ; :
		 */
		// i
		if (e.getKeyCode() == 73) {
			agentChased.setDirection(DirectionEnum.SOUTH);
		}
		// j
		if (e.getKeyCode() == 74) {
			agentChased.setDirection(DirectionEnum.WEST);
		}
		// l
		if (e.getKeyCode() == 76) {
			agentChased.setDirection(DirectionEnum.EAST);
		}
		// ;
		if (e.getKeyCode() == 44) {
			agentChased.setDirection(DirectionEnum.NORTH);
		}
		// u
		if (e.getKeyCode() == 85) {
			agentChased.setDirection(DirectionEnum.SOUTH_WEST);
		}
		// :
		if (e.getKeyCode() == 46) {
			agentChased.setDirection(DirectionEnum.NORTH_EAST);
		}
		// ,
		if (e.getKeyCode() == 77) {
			agentChased.setDirection(DirectionEnum.NORTH_WEST);
		}
		// o
		if (e.getKeyCode() == 79) {
			agentChased.setDirection(DirectionEnum.SOUTH_EAST);
		}
		// o
		if (e.getKeyCode() == 75) {
			agentChased.setDirection(DirectionEnum.NONE);
		}
	}

	public void keyReleased(KeyEvent e) {

	}
}
