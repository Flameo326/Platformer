package Enum;

public enum Direction {
	
	NULL(0, 0),
	NORTH(0, -1),
	SOUTH(0, 1),
	EAST(1, 0),
	WEST(-1, 0),
	NORTHEAST(1, -1),
	NORTHWEST(-1, -1),
	SOUTHEAST(1, 1),
	SOUTHWEST(-1, 1);
	
	private int x, y;
	
	Direction(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public static Direction getDirection(int x, int y){
		int value = 0;
		if(y >= 1){
			value += 2; // South
		} else if(y <= -1){
			value += 1; // North
		}
		
		if(x >= 1){
			value *= 2;
			value += 3; // East
		} else if(x <= -1){
			value *= 2;
			value += 4; // West
		}
		// -1, -1  = 1 * 2 + 4 = 6 NORTHWEST...
		// 0, 0 NULL
		// 1, 1 = 2 * 2 + 3 = 7 SOUTHEAST
		// 0, 1 = 2 = South
		// 0, -1 = 1 = NORTH
		// 1, 0 = 3 = EAST
		// -1, 0 = 4 = WEST
		// -1, 1 = 2 * 2 + 4 = 8 SOUTHWEST
		
		return Direction.values()[value];
	}
	
	public int getInverseX(){
		return -x;
	}
	
	public int getInverseY(){
		return -y;
	}
}
