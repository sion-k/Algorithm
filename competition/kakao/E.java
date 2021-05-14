package competition.kakao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class E {
	static int[][] adj;
    static int[] node;
    
    static final int INF = 10001;
    
    // root�� ��Ʈ�� �ϴ� ����Ʈ���� �� �׷쿡 �ִ� bound��ŭ ���� �� ���� ��
    // ���� �������� ����� ��, (�׷��� ��, ����Ʈ���� ���� �׷��� �Ը�) ��ȯ
    static int[] group(int root, int bound) {
        if (node[root] > bound) return new int[] { INF, INF };
        int[] left = {0, 0};
        if (adj[root][0] != -1) left = group(adj[root][0], bound);
        int[] right = {0, 0};
        if (adj[root][1] != -1) right = group(adj[root][1], bound);
        // ���� ����� ���
        if (adj[root][0] == -1 && adj[root][1] == -1) {return new int[] { 1, node[root] };}
        // ���� �ʴ� ���
        int[] max = { left[0] + right[0] + 1, node[root] };
        // ��� ��� ���
        if (adj[root][0] != -1 && adj[root][1] != -1 && node[root] + left[1] + right[1] <= bound)
            if (node[root] + left[1] + right[1] > max[1])
                max = new int[] { left[0] + right[0] - 1, node[root] + left[1] + right[1] };
        // ���ʸ� ��� ���
        if (adj[root][0] != -1 && node[root] + left[1] <= bound)
            if (node[root] + left[1] > max[1])
                max = new int[] { left[0] + right[0], node[root] + left[1] };
        // �����ʸ� ��� ���
        if (adj[root][1] != -1 && node[root] + right[1] <= bound)
            if (node[root] + right[1] > max[1])
                max = new int[] { left[0] + right[0], node[root] + right[1] };
        return max;
    }
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		adj = new int[N][2];
		node = new int[N];
		for (int i = 0; i < N; i++) node[i] = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			adj[i][0] = Integer.parseInt(st.nextToken());
			adj[i][1] = Integer.parseInt(st.nextToken());
		}
        boolean[] hasParent =new boolean[adj.length];
        for (int i = 0; i < adj.length; i++) {
        	if (adj[i][0] != -1) hasParent[adj[i][0]] = true;
        	if (adj[i][1] != -1) hasParent[adj[i][1]] = true;
        }
        int root = 0;
        for (int i = 0; i < adj.length; i++)
            if (!hasParent[i]) {root = i; break;}
        int lo = 0; int hi = 100000000;
        // f(x) = K�� �̻��� �׷��� �� �׷��� �ִ� ũ�Ⱑ x�� �ǰ� ���� �� �ִ���
        // f(lo) == false && f(hi) == true�� hi�� ��ȯ
        while (lo + 1 < hi) {
        	int mid = (lo + hi) / 2;
        	if (group(root, mid)[0] <= K) hi = mid;
        	else lo = mid;
        }
        System.out.println(hi);
	}
	
}