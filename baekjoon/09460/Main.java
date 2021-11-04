import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static ArrayList<Pair> S;

    // 최대 x의 비용으로 터널을 설치하는 것이 가능한지 여부
    // 터널은 최대 K개 지을 수 있음
    static boolean f(long x) {
        long min = S.get(0).y;
        long max = min;
        int t = 1; // 현재 사용한 수평 터널 개수
        for (int i = 0; i < N; i++) {
            min = Math.min(min, S.get(i).y);
            max = Math.max(max, S.get(i).y);
            if (2 * x < max - min) {
                if (t == K) return false;
                t++;
                min = S.get(i).y;
                max = min;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            S = new ArrayList<>(N);
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                long x = Long.parseLong(st.nextToken()) * 100;
                long y = Long.parseLong(st.nextToken()) * 100;
                S.add(new Pair(x, y));
            }
            Collections.sort(S);
            long lo = 0; long hi = 10000000000L;
            if (f(lo)) { bw.append("0.0").append("\n"); continue; }
            while (lo + 1 < hi) {
                long mid = (lo + hi) / 2;
                if (f(mid)) hi = mid;
                else lo = mid;
            }
            bw.append(String.format("%.1f\n", ((double)hi) / 100));
        }
        System.out.print(bw);
    }

}
class Pair implements Comparable<Pair> {
    long x, y;
    Pair (long x, long y) { this.x = x; this.y = y; }

    @Override
    public String toString() { return String.format("(%d %d)", x, y); }
    @Override
    public int compareTo(Pair o) { return Long.compare(x, o.x); }
}
