package world;

public class TrapCore {	
	
	private int damage;
	private int cd;
	private int realCD = 0;
	
	public TrapCore(int dmg, int cd){
		damage = dmg;
		this.cd = cd;
	}
	
	public void tic(){
		realCD-=1;
		realCD = Math.max(0, realCD-1);
	};
	
	//динамический кулдаун на полиморфизмах либо имеет верхнюю границу
	//либо _очень_ костыльный
	public int doDamage(){
		int damage = (int) Math.max(this.damage - realCD*10000, 0);
		realCD = (int) Math.max(cd + Math.min(-realCD*Math.pow(10,5), 0),realCD);
		return damage;
	}
}
