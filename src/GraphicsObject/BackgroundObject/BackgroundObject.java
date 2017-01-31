package GraphicsObject.BackgroundObject;

import GraphicsObject.GraphicsObject;
import javafx.scene.image.Image;

public class BackgroundObject extends GraphicsObject{

	public BackgroundObject(Image img){
		this(img, 0, 0);
	}

	public BackgroundObject(Image img, double x, double y){
		super(img, x, y);
	}

	@Override
	public void setImage(Image img){
		super.setImage(img);
	}

}
