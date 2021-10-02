import java.util.*;
import java.io.*;

public class Main {

    static String solve(long B, int N) {
        int cnt = Long.bitCount(B);
        if (cnt < N) return "-1";
        StringBuilder bw = new StringBuilder();
        int range = cnt - N + 1;
        for (int i = 0; i < range; i++) B -= (B & -B);
        bw.append(B).append(" ");
        while (B > 0) {
            B -= (B & -B);
            bw.append(B).append(" ");
        }
        return bw.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long B = Long.parseLong(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        bw.append(solve(B, N)); 
        System.out.println(bw);
    }

}
