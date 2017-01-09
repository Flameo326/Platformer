package GraphicsObject;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Player extends CollideableObject{
	
	private long jumpTime;
	private int jumpSpeed;
	private long jumpOccurence;

	public Player(Image img) {
		this(img, 0, 0);
	}
	
	public Player(Image img, double x, double y){
		this(img, x, y, null);
	}
	
	public Player(Image img, double x, double y, ArrayList<CollideableObject> colliders){
		super(img, x, y, colliders);
		setSpeed(5);
		setJumpTime(3);
		setJumpSpeed(25);
	}
	
	public void beginJump(long time){
		setJumpOccurence(time);
		//continueJump(time);
	}
	
	// I should fix this so it records where you are and where you should be at any point in a jump...
	// also implement a better system of gravity?
	public boolean continueJump(long time){
		long timeBetween = time - getJumpOccurence();
		double distance = ((getJumpTime() - timeBetween)/ (double)getJumpTime()) * getJumpSpeed();
		
		System.out.println(distance + " " + timeBetween);
		
		if(timeBetween >= getJumpTime() * 2){
			return false;
		}
		update(0, -distance);
		return true; // would return after finished umped or more realistically when they collide...
	}
	
	public void setJumpTime(int time){
		jumpTime = time * 100000000l;
		
	}
	
	public void setJumpOccurence(long time){
		jumpOccurence = time;
	}
	
	public void setJumpSpeed(int speed){
		jumpSpeed = speed;
	}
	
	public long getJumpTime(){
		return jumpTime;
	}
	
	public long getJumpOccurence(){
		return jumpOccurence;
	}
	
	public int getJumpSpeed(){
		return jumpSpeed;
	}

}
