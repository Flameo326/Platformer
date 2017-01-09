package Entry;

import java.util.ArrayList;

import GraphicsObject.CollideableObject;
import GraphicsObject.GraphicsObject;
import GraphicsObject.Player;
import Input.MovementKeyHandler;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

// Object should have an animation timer attached to them
// This animation timer will control their movement if there is any...
public class Platformer extends Application {
	
	private Canvas canvas;
	private GraphicsContext g;
	private AnimationTimer animation;
	
	private ArrayList<String> keyInput;
	
	// I could just make it so 
	private ArrayList<GraphicsObject> background;
	private ArrayList<CollideableObject> people;
	private ArrayList<CollideableObject> objects;
	
	private MovementKeyHandler mvmKey;
	private Player hero;
	
	public Platformer(){
		keyInput = new ArrayList<String>();
//		mvmKey = new MovementKeyHandler(keyInput, hero);
//		mvmKey.start();
		
		Image img = new Image("file:///C:/Users/Flameo326/Pictures/Gimp/FootprintGreen.png");
		hero = new Player(img, 50, 305);
		hero.setTimer(new MovementKeyHandler(keyInput, hero));
		
		background = new ArrayList<GraphicsObject>();
		addBackground(new GraphicsObject(new Image("file:///C:/Users/Flameo326/Pictures/Theme Pictures/LightningFF13.jpg"), 0, 0));
		
		people = new ArrayList<CollideableObject>();
		
		objects = new ArrayList<CollideableObject>();
		WritableImage tempObj = new WritableImage(100, 10);
		PixelWriter pxW = tempObj.getPixelWriter();
		for(int i = 0; i < tempObj.getWidth(); i++){
			for(int y = 0; y < tempObj.getHeight(); y++){
				pxW.setColor(i, y, Color.BROWN);
			}
		}
		CollideableObject surface = new CollideableObject(tempObj, 50, 300);
		surface.setIsCollideable(true);
		addObject(surface);
		hero.getColliders().add(surface);
	}

	public static void main(String[] args){
		Application.launch(args);
	}

	@Override
	public void start(Stage stage){
		stage.setTitle("Game");
		
		Group root = new Group();
		
		canvas = new Canvas(500, 500);
		g = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		
		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				String code = event.getCode().toString();
				if(!keyInput.contains(code)){
					keyInput.add(code);
				}
			}
		});
		
		canvas.setOnKeyReleased(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				String code = event.getCode().toString();
				if(keyInput.contains(code)){
					keyInput.remove(code);
				}
			}
		});
		
		animation = new AnimationTimer(){
			@Override
			public void handle(long now) {
				g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				for(GraphicsObject obj : background){
					g.drawImage(obj.getImage(), obj.getXPosition(), obj.getYPosition());
				}
				for(GraphicsObject obj : objects){
					g.drawImage(obj.getImage(), obj.getXPosition(), obj.getYPosition());
				}
				for(GraphicsObject obj : people){
					g.drawImage(obj.getImage(), obj.getXPosition(), obj.getYPosition());
				}
				g.drawImage(hero.getImage(), hero.getXPosition(), hero.getYPosition());
			}
		};
		animation.start();
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
	}
	
	public void addBackground(GraphicsObject obj){
		if(obj != null){
			background.add(obj);
		}
	}
	
	public void addPeople(CollideableObject obj){
		if(obj != null){
			people.add(obj);
		}
	}
	
	public void addObject(CollideableObject obj){
		if(obj != null){
			objects.add(obj);
		}
	}
	
}
