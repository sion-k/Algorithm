import java.util.*;
import java.io.*;

public class Main {
    static int[] left;
    static int[] right;
    static long[] size;
    static long[] cache;

    // here에서 시작해서 구슬 하나를 굴린 후 도착하는 지점 반환
    static int f(int here) {
        int l = left[here];
        int r = right[here];
        if (l == -1 && r == -1) return here;
        if (l == -1) return f(r);
        if (r == -1) return f(l);
        if (dp(l) <= dp(r)) return f(l);
        else return f(r);
    }

    // here에서 시작해서 k개를 한 번에 굴린다
    static void g(int here, long k) {
        int l = left[here];
        int r = right[here];
        if (l == -1 && r == -1) {
            size[here] = k;
        } else if (l == -1) {
            g(r, k);
        } else if (r == -1) {
            g(l, k);
        } else if (k % 2 == 0) {
            g(l, k / 2);
            g(r, k / 2);
        } else {
            g(l, k / 2 + 1);
            g(r, k / 2);
        }
    }

    // here을 루트로 하는 서브트리의 구슬의 개수 반환
    static long dp(int here) {
        int l = left[here];
        int r = right[here];
        if (l == -1 && r == -1) return size[here];
        if (cache[here] != -1) return cache[here];
        long sum = 0;
        if (l != -1) sum += dp(l);
        if (r != -1) sum += dp(r);
        return cache[here] = sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        left = new int[N + 1];
        right = new int[N + 1];
        size = new long[N + 1];
        cache = new long[N + 1];
        Arrays.fill(cache, -1);
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            left[i] = u;
            right[i] = v;
        }
        long K = Long.parseLong(br.readLine());
        g(1, K - 1);
        bw.append(f(1));
        System.out.println(bw);
    }

}
