import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        long[] S = new long[10];
        // 0이 될 수 없는 알파벳
        boolean[] Z = new boolean[10];
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String x = br.readLine();
            Z[x.charAt(0) - 'A'] = true;
            for (int j = x.length() - 1; j >= 0; j--) {
               S[x.charAt(j) - 'A'] += Math.pow(10, x.length() - 1 - j); 
            }
        }
        ArrayList<Long> T = new ArrayList<>();
        for (int i = 0; i <= 9; i++) T.add(S[i]);
        long min = Long.MAX_VALUE; int digit = 0;
        for (int i = 0; i <= 9; i++) if (!Z[i]) {
            if (min > T.get(i)) {
                min = T.get(i);
                digit = i;
            }
        }
        T.remove(digit);
        Collections.sort(T);
        long sum = 0;
        for (int i = 8; i >= 0; i--) sum += T.get(i) * (i + 1);
        bw.append(sum).append("\n");
        System.out.print(bw);
    }

}
