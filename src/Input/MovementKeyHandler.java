package Input;

import java.util.ArrayList;

import GraphicsObject.Player;
import javafx.animation.AnimationTimer;

// Should i make this in the Player class?
// Could I have a variable for a KeyHandler and MouseHandler and adjust them according to the game. 
// Like if there were fights the WASD could move the arrow for what action to take. And I would need enter.
public class MovementKeyHandler extends AnimationTimer{
	
	private final ArrayList<String> keyInput;
	private Player hero;
	private boolean jumped;
	
	public MovementKeyHandler(ArrayList<String> keyInput, Player hero){
		this.keyInput = keyInput;
		this.hero = hero;
	}

	@Override
	public void handle(long now) {
		// need to regulate gravity better.
		// jump falls faster...
		hero.move(0, 1);
		if(keyInput.contains("D")){
			hero.move(1, 0);
		}
		if(keyInput.contains("A")){
			hero.move(-1, 0);			
		}
		if(jumped){
			jumped = hero.continueJump(now);
		}
		if(keyInput.contains("SPACE") && !jumped){
			hero.beginJump(now);
			jumped = true;
			// If i wanted jumps to not conitue by holding down space the  
			// i would need to set jumped to false after releasing SPACE
		}
		System.out.println(hero.getXPosition() + " " + hero.getYPosition() + " " + now);
	}

}
