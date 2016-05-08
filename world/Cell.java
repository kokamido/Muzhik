package world;

import muzhik.Muzhik;
import world.World;

public abstract class Cell {
	protected int x;
	protected int y;
	
	public Cell(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString(){
		return "("+x+", "+y+")";
	}
	
	abstract void affect(Muzhik muzhik, int safe, World world);
	abstract void tic();
}
