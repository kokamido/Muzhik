package Test;

import world.*;

import java.lang.reflect.InvocationTargetException;

import muzhik.Muzhik;

public class SimpleTest {
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		Cell[][] map = new Cell[5][5];
		map[0][0] = new Wall(1,1);
		map[0][1] = new Trap(1,2,2,8);
		map[0][2] = new Wall(1,3);
		map[0][3] = new Gold(1,4,100);
		map[0][4] = new	Trap(1,5,2,8);
		map[1][0] = new	Wall(2,1);
		map[1][1] = new	Empty(2,2);
		map[1][2] = new Empty(2,3);
		map[1][3] = new Empty(2,4);
		map[1][4] = new Wall(2,5);
		map[2][0] = new	Wall(3,1);
		map[2][1] = new	Gold(3,2,100);
		map[2][2] = new Wall(3,3);
		map[2][3] = new Empty(3,4);
		map[2][4] = new Wall(3,5);
		map[3][0] = new	Wall(4,1);
		map[3][1] = new	Empty(4,2);
		map[3][2] = new Wall(4,3);
		map[3][3] = new Empty(4,4);
		map[3][4] = new Wall(4,5);
		map[4][0] = new	Gold(5,1,100);
		map[4][1] = new	Empty(5,2);
		map[4][2] = new Wall(5,3);
		map[4][3] = new Empty(5,4);
		map[4][4] = new Gold(5,5,100);
		World world = new World(map);
		world.go(new Muzhik(10,2,2));
	}
}
