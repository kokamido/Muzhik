package muzhik;

public class Muzhik {
	private int hp;
	private int gold;
	private int x;
	private int y;
	
	public Muzhik(int hp, int x, int y){
		this.hp = hp;
		this.x = x;
		this.y = y;
		gold = 0;
	}
	
	public int getHP(){
		return hp;
	}
	
	public int getGold(){
		return gold;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void addGold(int gold){
		this.gold+=gold;
	}
	
	public void attack(int dmg){
		hp-=dmg;
	}
	
	
}
