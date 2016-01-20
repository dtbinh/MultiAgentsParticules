package MultiAgentsParticules.part1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class View implements Observer {

	private SMA sma;
	private Grid grid;
	private JFrame frame;
	
	public View(SMA sma) throws InterruptedException {
		this.sma = sma;
		frame = new JFrame();
		frame.setPreferredSize(
				new Dimension(sma.getEnvironnement().getWidth() * 11, sma.getEnvironnement().getHeight() * 11));

		grid = new Grid(sma.getEnvironnement().getWidth() * 10,sma.getEnvironnement().getHeight() * 10);
		frame.add(grid);
		frame.setVisible(true);
		
		for(int i = 0 ; i < sma.getAgents().size() ; i++){
			Agent a = sma.getAgents().get(i);
			grid.fillCell(a.getPositionX(),a.getPositionY());
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void launch() throws InterruptedException{
		sma.run(10000);
	}

	public void update(Observable o, Object arg) {
		grid.clean();
		for(int i = 0 ; i < sma.getAgents().size() ; i++){
			Agent a = sma.getAgents().get(i);
			grid.fillCell(a.getPositionX(),a.getPositionY());
		}
		grid.repaint();
		frame.add(grid);
	}

	public static class Grid extends JPanel {

		private List<Point> fillCells;
		private int width;
		private int height;
		public Grid(int width, int height) {
			fillCells = new ArrayList<Point>();
			this.width = width;
			this.height = height; 
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (int i = 0 ; i < fillCells.size() ; i++) {
				Point fillCell = fillCells.get(i);
				int cellX = 10 + (fillCell.x * 10);
				int cellY = 10 + (fillCell.y * 10);
				g.setColor(Color.RED);
				g.fillOval(cellX, cellY, 10, 10);
			}
			g.setColor(Color.BLACK);
			g.drawRect(10, 10, width, height);

			for (int i = 10; i <= width; i += 10) {
				//g.drawLine(i, 10, i, height + 10);
			}

			for (int i = 10; i <= height; i += 10) {
				//g.drawLine(10, i, width + 10, i);
			}
		}
		
		public void clean(){
			fillCells.clear();
			//repaint();
		}
		
		public void fillCell(int x, int y) {
			fillCells.add(new Point(x, y));
		}

		public void repaint(){
			super.repaint();
		}
	}
}
