package union;

public class UnionF {
	private int[] id;
	//instantiate the array
	UnionF(int N){
		id = new int[N];
		for(int i=0;i<N ; i++) {
			id[i] =i;
		}
	}
	//if its a union, take the id of the q and put it in all the elements with id equivalent to p.
	public void union(int p, int q){
		int temp = id[p];
		for(int i:id) {
			if(temp == id[i]) {
				id[i]= id[q];
			}
		}
	}
	//if its a connected? check if they have the same id's?
	
	public boolean connected(int p, int q) {
		return id[p]==id[q];
	}
	public static void main(String[] args) {
		
	}

}
