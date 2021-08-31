public class MST {
	static int V;
	static ArrayList<Edge> edges;

	static int kruskal() {
		int ret = 0;
		Collections.sort(edges);
		DisjointSet s = new DisjointSet(V + 1);
		for (Edge e : edges) {
			int u = e.start; int v = e.end; int w = e.weight;
			if (s.find(u) == s.find(v)) continue;
			s.union(u, v);
			ret += w;
		}
		return ret;
	}

}

class Edge implements Comparable<Edge> {
	int start, end, weight;
	
	public Edge(int s, int e, int w) { start = s; end = e; weight = w; }
	
	@Override
	public int compareTo(Edge o) { return weight - o.weight; }
}
