import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        char[] S = br.readLine().toCharArray();
        int[] P = new int[2 * N];
        int[] parent = new int[N + 1];
        int k = 1;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2 * N; i++) {
            if (S[i] == '0') {
                stack.push(k); P[i] = k;
                k++;
            } else {
                P[i] = stack.pop();
            }
        }
        k = 0;
        stack = new Stack<>();
        stack.push(0);
        for (int i = 0; i < 2 * N; i++) {
            if (S[i] == '0') {
                parent[k + 1] = stack.peek();
                stack.push(k + 1);
                k++;
            } else {
                stack.pop();
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int u = P[Integer.parseInt(st.nextToken()) - 1];
        int v = P[Integer.parseInt(st.nextToken()) - 1];
        boolean[] visit = new boolean[N + 1];
        while (u != 0) {
            visit[u] = true;
            u = parent[u];
        }
        while (!visit[v]) {
            v = parent[v];
        }
        int min = 2 * N; int max = 0;
        for (int i = 0; i < 2 * N; i++) {
            if (P[i] == v) {
                min = Math.min(min, i);
                max = Math.max(max, i);
            }
        }
        bw.append(String.format("%d %d\n", min + 1, max + 1));
        System.out.print(bw);
    }

}
