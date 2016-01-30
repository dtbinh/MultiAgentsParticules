package MultiAgentsParticules;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class View implements Observer {

	private SMA sma;
	private Grid grid;
	private JFrame frame;
	
	/* TODO
	 * Rendre paramétrable dans la vue :
	 * - hauteur, largeur
	 * - nombre de bille
	 * - vitesse
	 * - taille des billes
	 * - durée de l'exécution
	 */
	
	public View(SMA sma) throws InterruptedException {
		this.sma = sma;
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(sma.getEnvironnement().getWidth() * 5 + 30, sma.getEnvironnement().getHeight() * 5 + 30));
		grid = new Grid(sma.getEnvironnement().getWidth() * 5,sma.getEnvironnement().getHeight() * 5);
		frame.add(grid);
		frame.setVisible(true);
		//List<Agent> list = new ArrayList<Agent>(sma.getAgents());
		for(int i = 0 ; i < sma.getAgents().size() ; i++){
			Agent a = sma.getAgents().get(i);
			grid.fillCell(a.getPositionX(),a.getPositionY(),a.getColor());
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void launch() throws InterruptedException{
		sma.run(true);
	}

	public void update(Observable o, Object arg) {
		grid.clean();
		for(int i = 0 ; i < sma.getAgents().size() ; i++){
			Agent a = sma.getAgents().get(i);
			//System.out.println(a.getPositionX() + " " + a.getPositionY() + " " + a.getColor());
			grid.fillCell(a.getPositionX(),a.getPositionY(),a.getColor());
		}
		sma.nbTypeOfAgent();
		grid.repaint();		
		//frame.add(grid);
	}

	public static class Grid extends JPanel {

		private List<Point> fillCells;
		private int width;
		private int height;
		private Map<Point,Color> mapColors;
		public Grid(int width, int height) {
			fillCells = new ArrayList<Point>();
			this.width = width;
			this.height = height; 
			mapColors = new HashMap<Point,Color>();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (int i = 0 ; i < fillCells.size() ; i++) {
				Point fillCell = fillCells.get(i);
				//System.out.println(fillCell);
				Color c = mapColors.get(fillCell);
				int cellX = 5 + (fillCell.x * 5);
				int cellY = 5 + (fillCell.y * 5);
				//Random r = new Random();
				//Color couleur = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
				g.setColor(c);
				g.fillOval(cellX, cellY, 5, 5);
			}
			g.setColor(Color.BLACK);
			g.drawRect(5, 5, width, height);

			for (int i = 10; i <= width; i += 10) {
				g.drawLine(i, 10, i, height + 10);
			}

			for (int i = 10; i <= height; i += 10) {
				g.drawLine(10, i, width + 10, i);
			}
		}
		
		public void clean(){
			fillCells = new ArrayList<Point>();
			//repaint();
		}
		
		public void fillCell(int x, int y, Color color) {
			Point p = new Point(x, y);
			fillCells.add(p);
			mapColors.put(p,color);
		}

		public void repaint(){
			super.repaint();
		}
	}
}
