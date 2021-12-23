import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static long[] S;

    // S에서 k이상인 값의 개수 반환
    static int count(long k) {
        // S[lo] < k, S[hi] >= k  
        int lo = 0; int hi = N - 1;
        if (S[lo] >= k) return N;
        if (S[hi] < k) return 0;
        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;
            if (S[mid] >= k) hi = mid;
            else lo = mid;
        } 
        return N - hi;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        S = new long[N];
        for (int i = 0; i < N; i++) {
            S[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(S);
        long ret = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                ret += (count(S[j] + (S[j] - S[i])) - count(S[j] + 2 * (S[j] - S[i]) + 1));
            }
        }
        bw.append(ret);
        System.out.println(bw);
    }

}
