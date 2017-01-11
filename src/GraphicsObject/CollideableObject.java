package GraphicsObject;

import java.util.ArrayList;

import Enum.Direction;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

// Collision should be handled by a system of its own.
// I should pass in all the entities, The system checks and returns if any are colliding
// Another system would then handle those collision
public class CollideableObject extends GraphicsObject{

	private boolean isCollideable;
	private ArrayList<CollideableObject> colliders;
	
	public CollideableObject(Image img) {
		this(img, 0, 0);
	}
	
	public CollideableObject(Image img, double x, double y){
		this(img, x, y, null);	
	}
	
	public CollideableObject(Image img, double x, double y, ArrayList<CollideableObject> colliders){
		super(img, x, y);
		this.colliders = (colliders == null ? new ArrayList<>() : colliders);
		isCollideable = true;
	}
	
	@Override
	public void move(double x, double y){
		super.move(x, y);
		testCollisions(this, colliders, Direction.getDirection((int)x, (int)y));
	}
	
	@Override
	public void update(double x, double y){
		super.update(x, y);
		testCollisions(this, colliders, Direction.getDirection((int)x, (int)y));
	}
	
	public void testCollisions(CollideableObject obj, ArrayList<CollideableObject> colliders, Direction d){
		for(CollideableObject collider : colliders){
			if(collider.isCollideable()){
				while(obj.intersects(collider)){
					super.update(d.getInverseX(), d.getInverseY());
				}
			}
		}
	}
	
	// will be used to test if an event should happen, i.e. score, death, speed, etc.
	public void collide(){
		throw new UnsupportedOperationException("Should not be called unless by child class.");
	}
	
	public void setIsCollideable(boolean b){
		isCollideable = b;
	}
	
	public boolean isCollideable(){
		return isCollideable;
	}
	
	public ArrayList<CollideableObject> getColliders(){
		return colliders;
	}

	public Rectangle getRectangle(){
		return new Rectangle(getXPosition(), getYPosition(), getWidth(), getHeight());
	}
	
	// this will have the problem of addressing the entity as a specific rectangle and not the correct form.
	public boolean intersects(CollideableObject obj){
		// why it cant do this from rectangle to rectangle?
		return this.getRectangle().intersects(obj.getXPosition(), obj.getYPosition(), obj.getWidth(), obj.getHeight());
	}
}
