import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] S;
    static ArrayList<ArrayList<Integer>> adj;
    static ArrayList<ArrayList<Integer>> children;
    
    static void dfs(int here, int prev) {
        for (int there : adj.get(here)) if (there != prev) {
            children.get(here).add(there);
            dfs(there, here);
        }
    }

    static int[][][] cache;

    static final int MOD = 1000000007;

    // here번째 정점을 루트로 하는 서브 트리에서
    // 가장 마지막으로 고른 정점에 적힌 정수가 prev이고 (안 골랐어도 0)
    // here번째 정점을 골랐는지 여부 flag가 주어질 때,
    // 오름차순으로 정점을 고르는 경우의 수
    static int dp(int here, int prev, int flag) {
        // 리프 노드인 경우 here 정점을 고르는 경우만 경우를 하나 찾은 것
        if (children.get(here).isEmpty()) return flag;
        if (cache[here][prev][flag] != -1) return cache[here][prev][flag];
        // here 정점을 선택하고 here의 자손으로부터는 안 고르는 경우 1
        int sum = flag;
        for (int there : children.get(here)) {
            // there을 고를 수 있으면 고르는 경우
            if (prev <= S[there]) sum += dp(there, S[there], 1);
            if (sum >= MOD) sum -= MOD;
            // 고르지 않는 경우
            sum += dp(there, prev, 0);
            if (sum >= MOD) sum -= MOD;
        }
        return cache[here][prev][flag] = sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) S[i] = Integer.parseInt(st.nextToken());
        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj.get(u).add(v); adj.get(v).add(u);
        }
        children = new ArrayList<>();
        for (int i = 0; i <= N; i++) children.add(new ArrayList<>());
        dfs(1, 1);
        cache = new int[N + 1][10][2];
        for (int i = 0; i < cache.length; i++)
            for (int j = 0; j < cache[i].length; j++)
                Arrays.fill(cache[i][j], -1);
        System.out.println((dp(1, 0, 0) + dp(1, S[1], 1)) % MOD);
    }

}
