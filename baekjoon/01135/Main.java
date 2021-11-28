import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<ArrayList<Integer>> children;
    static int[] cache;

    // here을 루트로 하는 서브트리에 소식이 전달되었을 때
    // 모두 전달되는데 걸리는 최소 시간
    static int dp(int here) {
        if (cache[here] != -1) return cache[here];
        if (children.get(here).isEmpty()) return cache[here] = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int there : children.get(here)) pq.offer(dp(there));
        int max = 0; int size = pq.size();
        for (int i = 0; i < size; i++) max = Math.max(max, i + pq.poll() + 1);
        return cache[here] = max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        children = new ArrayList<>();
        for (int i = 0; i < N; i++) children.add(new ArrayList<>());
        cache = new int[N];
        Arrays.fill(cache, -1);
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        st.nextToken();
        for (int i = 1; i < N; i++) children.get(Integer.parseInt(st.nextToken())).add(i);
        bw.append(dp(0));
        System.out.println(bw);
    }

}
