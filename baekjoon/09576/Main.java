import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            Pair[] S = new Pair[M];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                S[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            Arrays.sort(S);
            int cnt = 0;
            boolean[] used = new boolean[N + 1];
            for (Pair p : S) {
                int start = p.start; int end = p.end;
                for (int i = start; i <= end; i++) if (!used[i]) {
                    cnt++; used[i] = true; break;
                }
            }
            bw.append(cnt).append("\n");
        }
        System.out.print(bw);
    }

}
class Pair implements Comparable<Pair> {
    int start, end;

    Pair(int s, int e) { start = s; end = e; }

    @Override
    public int compareTo(Pair o) { return end == o.end ? start - o.start : end - o.end; }
}
