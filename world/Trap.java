package world;

import muzhik.Muzhik;
import world.TrapCore;

public class Trap extends Cell {
	
	private TrapCore core;
	
	public Trap(int x, int y,int dmg, int cd) {
		super(x, y);
		core = new TrapCore(dmg,cd);
	}

	
	@Override
	void affect(Muzhik muzhik, int safe, World world){
		muzhik.attack(core.doDamage());
		muzhik.setX(x);
		muzhik.setY(y);
	}

	@Override
	void tic() {
		core.tic();
	}
	
	@Override
	public String toString(){
		return "Trap "+ super.toString();
	}

}
