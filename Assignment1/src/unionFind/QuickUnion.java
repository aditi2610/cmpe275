package unionFind;

public class QuickUnion {
	int[] id;
	int[] size;
	public QuickUnion(int N) {
		for(int i=0;i< N; i++) {
			id[i]=i;
			size[i]= 1;
		}
	}

	public boolean connected(int p, int q) { 
		return findRoot(p)== findRoot(q);
	}

	public int findRoot(int p) {
		while(p != id[p])
			p = id[p];
		return p;
	}

	public void union(int p, int q) {
		int i = findRoot(p);
		int j = findRoot(q);
		if (size[p]< size[q]) {
			id[i] = j;	
			size[q] += size[p];
		}
		else if(size [p]> size [q])
		{
			id[j] = i;	
			size[p] += size[q];
		}
	}
}
