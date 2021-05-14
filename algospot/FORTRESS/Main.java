package algospot.FORTRESS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] y; static int[] x; static int[] r;
	static int longest;

	static int height(Node root) {
		int[] heights = new int[root.children.size()];
		for (int i = 0; i < heights.length; i++) {
			heights[i] = height(root.children.get(i));
		}
		Arrays.sort(heights);
		if (heights.length == 0) {return 0;}

		if (heights.length >= 2) {
			longest = Math.max(longest, 2 + heights[heights.length - 2]
					+ heights[heights.length - 1]);
		}
		return heights[heights.length - 1] + 1;
	}

	static Node getTree(int root) {
		Node ret = new Node();
		for (int ch = 0; ch < N; ch++) {
			if (isChild(root, ch)) {
				ret.children.add(getTree(ch));
			}
		}
		return ret;
	}

	static int dist(Node root) {
		longest = 0; int h = height(root);
		return Math.max(longest, h);
	}

	static int sqr(int a, int b) {
		return (int)Math.pow(y[a] - y[b], 2) + (int)Math.pow(x[a] - x[b], 2);
	}

	static boolean encloses(int a, int b) {
		return r[a] > r[b] && sqr(a, b) < Math.pow(r[a] - r[b], 2);
	}

	static boolean isChild(int parent, int child) {
		if (!encloses(parent, child)) {return false;}
		for (int i = 0; i < N; i++) {
			if (i != parent && i != child &&
					encloses(parent, i) && encloses(i, child)){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int C = Integer.parseInt(br.readLine());
		for (int c = 0; c < C; c++) {
			N = Integer.parseInt(br.readLine());
			y = new int[N]; x = new int[N]; r = new int[N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				y[i] = Integer.parseInt(st.nextToken());
				x[i] = Integer.parseInt(st.nextToken());
				r[i] = Integer.parseInt(st.nextToken());
			}
			Node castle = getTree(0);
			bw.write(String.valueOf(dist(castle)));
			bw.newLine();
		}
		bw.close();
	}

}

class Node {
	List<Node> children;
	public Node() {
		children = new ArrayList<>();
	}
}