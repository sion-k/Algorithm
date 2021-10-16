import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            char[] temp = br.readLine().toCharArray();
            int N = temp.length;
            int[] S = new int[N];
            int[] R = new int[N];
            for (int i = 0; i < N; i++) R[i] = temp[i] - '0';
            int sum = 0;
            // S -> R로 변화시키는 것으로 생각한다.
            for (int i = 0; i < N; i++) {
                if (S[i] == R[i]) continue;
                sum += (1 << (N - 1 - i));
                if (i + 1 < N) S[i + 1] = 1;
            }
            bw.append(sum).append("\n");
        }
        System.out.print(bw);
    }

}
