package Entry;

import java.util.ArrayList;

import GraphicsObject.CollideableObject;
import GraphicsObject.GraphicsObject;
import GraphicsObject.Player;
import Input.MovementKeyHandler;
import Timers.BackgroundMovement;
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

	// I could just make it so they're all collideable but backgrounds and people aren't????
	private GraphicsObject background, background2;
	private ArrayList<GraphicsObject> backgroundObjects;
	private ArrayList<CollideableObject> people;
	private ArrayList<CollideableObject> objects;

	// Currently hero will  not fall through objects it is placed on unless it is placed less than 5 of its height, 
	// i.e. 20 means 16 would fall through...
	private Player hero;

	public Platformer(){
		keyInput = new ArrayList<String>();

		Image img = new Image("file:///C:/Users/Flameo326/Pictures/Gimp/FootprintGreen.png");
		hero = new Player(img, 50, 300);
		hero.setTimer(new MovementKeyHandler(keyInput, hero));

		background = new GraphicsObject(new Image("file:///C:/Users/Flameo326/Pictures/Theme Pictures/LightningFF13.jpg"), 0, 0);
		background.setTimer(new BackgroundMovement(background));
		background2 = new GraphicsObject(new Image("file:///C:/Users/Flameo326/Pictures/Theme Pictures/LightningFF13.jpg"), background.getWidth(), 0);
		background2.setTimer(new BackgroundMovement(background2));
		
		backgroundObjects = new ArrayList<GraphicsObject>();
		people = new ArrayList<CollideableObject>();
		objects = new ArrayList<CollideableObject>();
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

		// Add key input
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
		
		createLevel();
		hero.getColliders().addAll(objects);
		hero.getColliders().addAll(people);

		// Graphics display
		animation = new AnimationTimer(){
			@Override
			public void handle(long now) {
				g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				
				g.drawImage(background.getImage(), background.getXPosition(), background.getYPosition());
				g.drawImage(background2.getImage(), background2.getXPosition(), background2.getYPosition());
				for(GraphicsObject obj : backgroundObjects){
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

	private void createLevel(){
		// BackgroundObjects


		// People

		//Objects
		CollideableObject surface;
		surface = newCollideableObject(Color.BROWN, 100, 20, 50, 300);
		surface.setIsCollideable(true);
		addObject(surface);

		CollideableObject movingPlatform = newCollideableObject(Color.RED, 100, 20, 380, 300);
		movingPlatform.setIsCollideable(true);
		movingPlatform.setTimer(new AnimationTimer(){
			private int initialX = movingPlatform.getXPosition();
			private boolean movingLeft = true;
			@Override
			public void handle(long now) {
				if(movingLeft){
					movingPlatform.move(-1, 0);
					if(movingPlatform.getXPosition() <= initialX-movingPlatform.getWidth()){
						movingLeft = false;
					}
				} else {
					movingPlatform.move(1, 0);
					if(movingPlatform.getXPosition() >= initialX+movingPlatform.getWidth()){
						movingLeft = true;
					}
				}
			}
		});
		addObject(movingPlatform);
	}

	public CollideableObject newCollideableObject(Color c, int width, int height, int xPos, int yPos){
		WritableImage tempObj = new WritableImage(width, height);
		PixelWriter pxW = tempObj.getPixelWriter();
		for(int i = 0; i < tempObj.getWidth(); i++){
			for(int y = 0; y < tempObj.getHeight(); y++){
				pxW.setColor(i, y, c);
			}
		}
		return new CollideableObject(tempObj, xPos, yPos);
	}

	public void addBackground(GraphicsObject obj){
		if(obj != null){
			backgroundObjects.add(obj);
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
