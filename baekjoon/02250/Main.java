import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] left; static int[] right;
	static int[] min; static int[] max;
	static int col = 1;

	static void inorder(int root, int level) {
		if (left[root] != -1) inorder(left[root], level + 1);
		min[level] = Math.min(min[level], col);
		max[level] = Math.max(min[level], col);
		col++;
		if (right[root] != -1) inorder(right[root], level + 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		left = new int[N + 1]; right = new int[N + 1];
		min = new int[N + 1]; max = new int[N + 1];
		Arrays.fill(min, 10001);
		int[] parent = new int[N + 1];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			left[p] = l; right[p] = r;
			if (l != -1) parent[l] = p;
			if (r != -1) parent[r] = p;
		}
		int root = 1;
		while (parent[root] != 0) {root++;}
		inorder(root, 1);
		int level = 1; int maxLevel = 1; int maxWidth = 1;
		while (level <= N && max[level] != 0) {
			int width = max[level] - min[level] + 1;
			if (width > maxWidth) {
				maxLevel = level; maxWidth = width;
			}
			level++;
		}
		System.out.println(maxLevel + " " + maxWidth);
	}

}
