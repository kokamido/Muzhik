package world;

import muzhik.Muzhik;

public class Wall extends Cell {

	public Wall(int x, int y) {
		super(x, y);
	}

	@Override
	void affect(Muzhik muzhik, int safe, World world) {}

	@Override
	void tic() {}
	
	@Override
	public String toString(){
		return "Wall "+ super.toString();
	}

}
