import java.util.*;
import java.io.*;

public class Main {
    static int N, T;
    static int[] L, H;

    static boolean f(int x) {
        int lo = 0; int hi = 0;
        for (int i = 0; i < N; i++) {
            lo += L[i];
            hi += Math.min(x, H[i]);
        }
        return lo <= T && T <= hi;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        L = new int[N]; H = new int[N];
        int lo = 0; int hi = 1000000;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            L[i] = Integer.parseInt(st.nextToken());
            lo = Math.max(lo, L[i]);
            H[i] = Integer.parseInt(st.nextToken());
        }
        lo--;
        // f(lo) = false f(hi) = true인 지점을 찾는다
        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;
            if (f(mid)) hi = mid;
            else lo = mid;
        }
        if (!f(1000000)) {
            bw.append(-1);
        } else {
            bw.append(hi);
        }
        System.out.println(bw);
    }

}
