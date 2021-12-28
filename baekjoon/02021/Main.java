import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        // k번째 역이 속한 노선, 혹은 k번째 노선에 속한 역
        // 노선의 번호는 10e5 + k로 저장한다
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= 200000; i++) adj.add(new ArrayList<>());
        for (int i = 1; i <= L; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            while (st.hasMoreTokens()) {
                int x = Integer.parseInt(st.nextToken());
                if (x == -1) break;
                adj.get(x).add(100000 + i);
                adj.get(100000 + i).add(x);
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        Deque<Integer> q = new LinkedList<>();
        int[] dist = new int[200001];
        Arrays.fill(dist, -1);
        for (int i = 1; i <= 100000; i++) {
            boolean flag = false;
            for (int j = 0; j < adj.get(100000 + i).size(); j++) {
                if (adj.get(100000 + i).get(j) == start) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                q.offer(100000 + i);
                dist[100000 + i] = 0;
            }
        }
        while (!q.isEmpty()) {
            int here = q.poll();
            for (int there : adj.get(here)) if (dist[there] == -1) {
                // 다른 노선으로 환승하는 경우
                if (there >= 100001) {
                    q.offerLast(there);
                    dist[there] = dist[here] + 1;
                } else {
                    q.offerFirst(there);
                    dist[there] = dist[here];
                }
            }
        }
        bw.append(dist[end]);
        System.out.println(bw);
    }

}
