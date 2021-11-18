import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Queue<Pair> q = new LinkedList<>();
        ArrayList<ArrayList<Integer>> S = new ArrayList<>(M);
        for (int i = 0; i < M; i++) {
            S.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine(), " ");
            int K = Integer.parseInt(st.nextToken());
            for (int j = 0; j < K + 1; j++) {
                S.get(i).add(Integer.parseInt(st.nextToken()));
            }
            q.offer(new Pair(i, 0));
        }
        int L = Integer.parseInt(br.readLine());
        int cnt = L;
        st = new StringTokenizer(br.readLine(), " ");
        boolean[] R = new boolean[N + 1];
        for (int i = 0; i < L; i++) R[Integer.parseInt(st.nextToken())] = true;
        // i번째 물약이 있다면 Queue에 넣을 정점들의 리스트
        ArrayList<ArrayList<Pair>> booked = new ArrayList<>();
        for (int i = 0; i <= N; i++) booked.add(new ArrayList<>());
        while (!q.isEmpty()) {
            Pair p = q.poll();
            int x = S.get(p.y).get(p.x);
            if (p.x == S.get(p.y).size() - 1) {
                if (!R[x]) cnt++;
                R[x] = true;
                for (Pair n : booked.get(x)) {
                    q.offer(n);
                }
                booked.get(x).clear();
                continue;
            }
            // 만들 수 있는 물약이라면
            if (R[x]) {
                q.offer(new Pair(p.y, p.x + 1));
            } else {
                booked.get(x).add(p);
            }
        }
        bw.append(cnt).append("\n");
        for (int i = 1; i <= N; i++) if (R[i]) {
            bw.append(i).append(" ");
        }
        System.out.println(bw);
    }

}
class Pair {
    int y, x;

    Pair(int y, int x) { this.y = y; this.x = x; }
}