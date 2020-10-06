package unionFind;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class UnionFind {

	Set[] list;
	public UnionFind(int N) {
		// TODO Auto-generated constructor stub
	}
	
	void union(int a, int b){
		boolean sepA = false; 
		boolean sepB = false;
		Set tempSet = new TreeSet<Integer>();
		for(int i=0; i<10; i++) {
			if(list[i]!= null) {
				if(list[i].contains(a)) {
					sepA = true;
				} 
				if(list[i].contains(b)) {
					sepB = true;
					tempSet= list[i];
					list[i]= null;
				}
			}
			else {
				list[i]= new TreeSet<Integer>(); // sorted and unique
				list[i].add(a);
				list[i].add(b);
				System.out.println("Set has been created {"+a+ "," +b+"}");
			}
		}
		
		if(sepA && sepB) {
			
		}
	}
	
	
	boolean connected(int a, int b){
		for(int i=0; i<3; i++) {
		if(list[i].contains(a) && list[i].contains(b))
			return true;
			break;
		}
	return false;
	}
	
	public static void main(String args[]) {
		int N = 10;
		UnionFind uf = new UnionFind(N);
		List[] pairs = enterPairs();
		for (int k =0; k< pairs.length; k++) {
			Iterator<Integer> i = pairs[k].iterator();
			while(i.hasNext()) {
				int p = (int)i.next();
				int q = (int)i.next();
				if(!uf.connected(p, q));
				{
					uf.union(p, q);
				}
			}
			
		}
	}

	public static List[] enterPairs() {
		ArrayList<Integer>[] pairs = (ArrayList<Integer>[])new ArrayList[11];;
		for(int j =0; j<=10; j++) {
			pairs[j] = new ArrayList<Integer>();
		}
		pairs[0].add(4);
		pairs[0].add(3);
		pairs[1].add(3);
		pairs[1].add(8);
		pairs[2].add(6);
		pairs[2].add(5);
		pairs[3].add(9);
		pairs[3].add(4);
		pairs[4].add(2);
		pairs[4].add(1);
		pairs[5].add(8);
		pairs[5].add(9);
		pairs[6].add(5);
		pairs[6].add(0);
		pairs[7].add(7);
		pairs[7].add(2);
		pairs[8].add(6);
		pairs[8].add(1);
		pairs[9].add(1);
		pairs[9].add(0);
		pairs[10].add(6);
		pairs[10].add(7);
		return pairs;
	}
}
