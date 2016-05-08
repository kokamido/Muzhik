package world;

import muzhik.Muzhik;

public class Empty extends Cell {

	public Empty(int x, int y) {
		super(x, y);
	}

	@Override
	void affect(Muzhik muzhik, int safe, World world){
		muzhik.setX(x);
		muzhik.setY(y);
	}

	@Override
	void tic() {}
	
	@Override
	public String toString(){
		return "Empty "+ super.toString();
	}

}
