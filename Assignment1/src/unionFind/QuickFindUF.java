package unionFind;

public class QuickFindUF {
	
	int[] id;
	QuickFindUF(int N){
		id = new int[N];
		for(int i=0; i< N; i++) {
			id[i] = i;
		}
	}
	
	
	public boolean connected(int p, int q) {
		return id[p]== id[q];
	}
}
