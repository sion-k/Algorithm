import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int[] S = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
            if (i - 1 >= 0) S[i] += S[i - 1];
        }
        int max = 0; 
        for (int end = 0; end < N; end++) {
            int start = Math.max(0, end - X + 1);
            int sum = S[end];
            if (start - 1 >= 0) sum -= S[start - 1];
            max = Math.max(max, sum);
        }
        int cnt = 0;
        for (int end = 0; end < N; end++) {
            int start = Math.max(0, end - X + 1);
            int sum = S[end];
            if (start - 1 >= 0) sum -= S[start - 1];
            if (sum == max) cnt++;
        }
        if (max == 0) {
            bw.append("SAD");
        } else {
            bw.append(max).append("\n");
            bw.append(cnt);
        }
        System.out.println(bw);
    }

}
