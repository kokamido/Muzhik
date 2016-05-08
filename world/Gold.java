package world;

import muzhik.Muzhik;

public class Gold extends Cell {

	private int gold;
	
	public Gold(int x, int y, int gold) {
		super(x, y);
		this.gold = gold;
	}

	@Override
	void affect(Muzhik muzhik, int safe, World world){
		muzhik.setX(x);
		muzhik.setY(y);
		muzhik.addGold(gold);
		//халявы не будет
		gold = 0;
	}

	@Override
	void tic() {}
	
	@Override
	public String toString(){
		return "Gold "+ super.toString();
	}

}
