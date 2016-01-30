package MultiAgentsParticules;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ViewJFX extends Application implements Observer{
	
	private SMA sma;
	private Pane canvas;
	private Scene scene;
	public static List<Circle> circle;
    public static ObservableList<Circle> lstCircle;
	
	public ViewJFX() {
		
		
		
		sma = Main.getSma();
		sma.addObserver(this);
		canvas = new Pane();
        scene = new Scene(canvas, sma.getEnvironnement().getWidth(), sma.getEnvironnement().getHeight(), Color.WHITE);
        
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		circle = new ArrayList<Circle>();

        lstCircle = FXCollections.observableArrayList(circle);

		primaryStage.setScene(scene);
//		sma.run(100000);
		
		for(Agent a : sma.getAgents()){
			Circle point = new Circle();
	        point.setCenterX(a.getPositionX());
	        point.setCenterY(a.getPositionY());
	        point.setRadius(1.5);
	        point.setFill(Color.rgb(a.getR(), a.getG(), a.getB()));
	        MapAgent.mapAgent.put(a, point);
	        circle.add(point);
		}
		
//        for(int i = 0 ; i < sma.getAgents().size() ; i++){
//			Agent a = sma.getAgents().get(i);
//			Circle point = new Circle();
//	        point.setCenterX(a.getPositionX());
//	        point.setCenterY(a.getPositionY());
//	        point.setRadius(1.5);
//	        
//	        point.setFill(Color.rgb(a.getR(), a.getG(), a.getB(), .99));
//	        MapAgent.mapAgent.put(a, point);
//	        circle.add(point);
//	        
//		}
        lstCircle.addAll(circle);
        canvas.getChildren().addAll(lstCircle);
        primaryStage.show();
        this.launch();
	}
	public void launch() throws InterruptedException{
        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent t) {
                try {
					sma.run(true);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        }));
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
	}
	
	public void show() throws InterruptedException {
	    Application.launch(ViewJFX.class);
	   
	}

	public void update(Observable o, Object arg) {
		
//		for(int i = 0 ; i < sma.getAgents().size() ; i++){
//			MapAgent.mapAgent.get(sma.getAgents().get(i)).relocate(sma.getAgents().get(i).getPositionX(), sma.getAgents().get(i).getPositionY());
//	  	}
		for(Agent a : sma.getAgents()){
			MapAgent.mapAgent.get(a).relocate(a.getPositionX(), a.getPositionY());
		}

	}

}
