import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] S = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
        ArrayList<Integer> S1 = new ArrayList<>();
        ArrayList<Integer> S2 = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                S1.add(S[i]);
            } else {
                S2.add(S[i]);
            }
        }
        Collections.sort(S1);
        Collections.sort(S2);
        for (int i = 0; i < S1.size(); i++) S[2 * i] = S1.get(i);
        for (int i = 0; i < S2.size(); i++) S[2 * i + 1] = S2.get(i);
        boolean flag = true;
        for (int i = 0; i < N - 1; i++) {
            if (S[i] > S[i + 1]) {
                flag = false;
                break;
            }
        }
        bw.append(flag ? "YES" : "NO");
        System.out.print(bw);
    }

}
