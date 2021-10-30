import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = 1000000;
        int[] S = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = i; j <= N; j += i) {
                S[j] += i;
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int badness = Integer.parseInt(st.nextToken());
        int TC = 1;
        while (start != 0 || end != 0 || badness != 0) {
            int cnt = 0;
            for (int i = start; i <= end; i++) {
                if (Math.abs(S[i] - i - i) <= badness) {
                    cnt++;
                }
            }
            bw.append(String.format("Test %d: %d\n", TC, cnt));
            TC++;
            st = new StringTokenizer(br.readLine(), " ");
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            badness = Integer.parseInt(st.nextToken());
        }
        System.out.print(bw);
    }

}
