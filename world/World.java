package world;

import world.Cell;
import world.Wall;
import muzhik.Muzhik;
import java.util.Vector;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class World {
	private Cell[][] map;
	private int[][] manMap;
	private HashMap<Vector<Integer>,Vector<Vector<Integer>>> graph;
	private HashSet<Vector<Integer>> visitedD = new HashSet<Vector<Integer>>();
	private Stack<Vector<Integer>> path;
	//!
	private int numVis;
	
	//Обношу загруженную карту стенами, дабы не думать
	//о выходе за границу массива при обработке смежных клеток.
	//так их для любой клетки изначальной карты всегда 8.
	//Заодно создаю карту проходимости
	public World(Cell[][] map){
		Cell[][] newMap = new Cell[map.length+2][map[0].length+2];
		manMap = new int[map.length+2][map[0].length+2];
		for(int x = 0; x <= map.length+1; x++){
			newMap[x][0] = new Wall(x,0);
			newMap[x][map[0].length+1] = new Wall(x,map[0].length+1);
		}
		for(int x = 0; x <= map[0].length+1; x++){
			newMap[0][x] = new Wall(0,x);
			newMap[map.length+1][x] = new Wall(map.length+1,x);
		}
		for(int x = 1; x <= map.length; x++){
			for(int y = 1; y<= map[0].length; y++){
				newMap[x][y] = map[x-1][y-1];
				boolean crutch = true;
				while(crutch && !(newMap[x][y] instanceof Wall)){
					manMap[x][y] = 1;
					crutch = false;
				}
			}
		}
		this.map = newMap;
	}
	
	public void go(Muzhik muzhik){
		graph = getGraph(manMap);
		path = new Stack<Vector<Integer>>();
		Vector<Integer> start = new Vector<Integer>();
		start.add(muzhik.getX());
		start.add(muzhik.getY());
		DFS(muzhik, start);
		System.out.println(numVis);
	}
	
	//Поиск в глубину
	private void DFS(Muzhik hero, Vector<Integer> node){
		boolean crutch = true;
		//если мужик не мёртв и вершина не была посещена - иди в неё
		while(crutch && !visitedD.contains(node) && hero.getHP()>0){
			tic();
			numVis++;
			boolean kostil = true;
			//если поиск в глубину "скакнул" через несколько клеток, то
			//на мужика свалятся все тяготы честного пути туда.
			//путь прокладывается в обратном направлении. Поворот куда надо
			//осуществляется с первой смежной клетки, которая точно есть
			while(kostil && !path.isEmpty() && !(areNeighbours(path.peek(), node))){
				Vector<Integer> buf = path.pop();
				map[buf.get(0)][buf.get(1)].affect(hero, 1, this);
				System.out.println(map[buf.get(0)][buf.get(1)]+" HP: "+hero.getHP()+
						" Gold: "+hero.getGold() + " X: "+hero.getX()+" Y: "+hero.getY());
				tic();
			}
			//записываю вершины в отдельный стек, чтобы корректно считать
			//тяготы пути при телепортах, вызванных алгоритмом поиска
			path.push(node);
			map[node.get(0)][node.get(1)].affect(hero, 1, this);
			System.out.println(map[node.get(0)][node.get(1)]+" HP: "+hero.getHP()+
					" Gold: "+hero.getGold() + " X: "+hero.getX()+" Y: "+hero.getY());
			visitedD.add(node);
			for(Vector<Integer> next : graph.get(node)){
				DFS(hero, next);
			}
			crutch = false;
		}
	}
	
	//Превращаю карту в удобный граф в виде списков смежности
	private HashMap<Vector<Integer>,Vector<Vector<Integer>>> getGraph(int[][] map){
		HashMap<Vector<Integer>,Vector<Vector<Integer>>> res = 
				new HashMap<Vector<Integer>,Vector<Vector<Integer>>>();
		for(int i = 1; i < map.length-1; i++){
			for(int j = 1; j < map[0].length-1; j++){
				Vector<Integer> node = new Vector<Integer>();
				node.add(i);
				node.add(j);
				Vector<Integer> bufNode = new Vector<Integer>();
				Vector<Vector<Integer>> bufArr = new Vector<Vector<Integer>>();
				//хехе
				boolean crutch = true;
				while(crutch && map[i-1][j-1] == 1){
					bufNode.add(i-1);
					bufNode.add(j-1);
					bufArr.add(bufNode);
					bufNode = new Vector<Integer>();
					crutch = false;
				}
				crutch = true;
				while(crutch && map[i-1][j] == 1){
					bufNode.add(i-1);
					bufNode.add(j);
					bufArr.add(bufNode);
					bufNode = new Vector<Integer>();
					crutch = false;
				}
				crutch = true;
				while(crutch && map[i-1][j+1] == 1){
					bufNode.add(i-1);
					bufNode.add(j+1);
					bufArr.add(bufNode);
					bufNode = new Vector<Integer>();
					crutch = false;
				}
				crutch = true;
				while(crutch && map[i][j-1] == 1){
					bufNode.add(i);
					bufNode.add(j-1);
					bufArr.add(bufNode);
					bufNode = new Vector<Integer>();
					crutch = false;
				}
				crutch = true;
				while(crutch && map[i][j+1] == 1){
					bufNode.add(i);
					bufNode.add(j+1);
					bufArr.add(bufNode);
					bufNode = new Vector<Integer>();
					crutch = false;
				}
				crutch = true;
				while(crutch && map[i+1][j-1] == 1){
					bufNode.add(i+1);
					bufNode.add(j-1);
					bufArr.add(bufNode);
					bufNode = new Vector<Integer>();
					crutch = false;
				}
				crutch = true;
				while(crutch && map[i+1][j] == 1){
					bufNode.add(i+1);
					bufNode.add(j);
					bufArr.add(bufNode);
					bufNode = new Vector<Integer>();
					crutch = false;
				}
				crutch = true;
				while(crutch && map[i+1][j+1] == 1){
					bufNode.add(i+1);
					bufNode.add(j+1);
					bufArr.add(bufNode);
					bufNode = new Vector<Integer>();
					crutch = false;
				}
				res.put(node, bufArr);
			}
		}
		return res;
	}
	
	//Смежны ли вершины
	private boolean areNeighbours(Vector<Integer> node1, Vector<Integer> node2){
		return Math.abs(node1.get(0)-node2.get(0))<=1 && Math.abs(node1.get(1)-node2.get(1))<=1;
	}
	
	private void tic(){
		for(Cell[] cellArr: map){
			for(Cell cell : cellArr){
				cell.tic();
			}
		}
	}
}
