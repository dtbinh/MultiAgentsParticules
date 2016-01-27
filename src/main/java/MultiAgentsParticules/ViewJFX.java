package MultiAgentsParticules;

import java.awt.Paint;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Screen;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.scene.shape.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

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
        for(int i = 0 ; i < sma.getAgents().size() ; i++){
			Agent a = sma.getAgents().get(i);
			Circle point = new Circle();
	        point.setCenterX(a.getPositionX());
	        point.setCenterY(a.getPositionY());
	        point.setRadius(1.5);
	        
	        point.setFill(Color.rgb(a.getR(), a.getG(), a.getB(), .99));
	        MapAgent.mapAgent.put(a, point);
	        circle.add(point);
	        
		}
        lstCircle.addAll(circle);
        canvas.getChildren().addAll(lstCircle);
        primaryStage.show();
        this.launch();
	}
	public void launch() throws InterruptedException{
        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent t) {
                sma.round();
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
