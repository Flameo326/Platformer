package Timers;

import GraphicsObject.GraphicsObject;
import javafx.animation.AnimationTimer;

public class BackgroundMovement extends AnimationTimer{
	
	private GraphicsObject obj;
	
	public BackgroundMovement(GraphicsObject go){
		obj = go;
	}

	@Override
	public void handle(long now) {
		if(obj.getXPosition() + obj.getWidth() <= 0 ) { obj.update(obj.getWidth(), 0); }
		obj.move(-1, 0);
	}

}
