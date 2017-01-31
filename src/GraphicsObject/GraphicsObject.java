package GraphicsObject;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

public class GraphicsObject {

	private AnimationTimer timer;
	private Image img;
	private double posX, posY;
	private int width, height;
	private int speed;

	public GraphicsObject(Image img){
		this(img, 0, 0);
	}

	public GraphicsObject(Image img, double x, double y){
		setImage(img);
		setSpeed(1);
		posX += x;
		posY += y;
	}

	// I could just add a boolean instead of two methods.... 
	public void move(double x, double y){
		posX += x * speed;
		posY += y * speed;
	}

	public void update(double x, double y){
		posX = x;
		posY = y;
	}

	public void setTimer(AnimationTimer t){
		if(t != null){
			timer = t;
			timer.start();
		} else {
			timer.stop();
		}
	}

	public void setImage(Image img){
		this.img = img;
		width = (int)img.getWidth();
		height = (int)img.getHeight();
	}

	public void setSpeed(int val){
		speed = val;
	}

	public int getSpeed(){
		return speed;
	}

	public Image getImage(){
		return img;
	}

	public int getXPosition(){
		return (int)posX;
	}

	public int getYPosition(){
		return (int)posY;
	}

	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}
}
