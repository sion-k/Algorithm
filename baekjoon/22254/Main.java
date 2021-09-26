import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] S;
    
    // k개의 공정으로 t시간 안에 제작할 수 있는지 여부
    static boolean f(int k, int t) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++) pq.offer(0);
        for (int x : S) {
            int y = pq.poll() + x;
            if (y > t) return false;
            pq.offer(y);
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        S = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
        int lo = 0; int hi = N;
        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;
            if (f(mid, X)) hi = mid;
            else lo = mid;
        }
        System.out.println(hi);
    }

}
