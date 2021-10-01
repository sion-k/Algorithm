import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Long> S = new ArrayList<>();

    static void dfs(int i, int k, Stack<Integer> st) {
        if (i != 0) {
            StringBuilder bw = new StringBuilder();
            for (int x : st) bw.append(x);
            S.add(Long.parseLong(bw.reverse().toString()));
        }
        if (i == 10) return;
        for (int x = k; x <= 9; x++) {
            st.push(x);
            dfs(i + 1, x + 1, st);
            st.pop();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dfs(0, 0, new Stack<>());
        Collections.sort(S);
        System.out.println(N < 1023 ? S.get(N) : -1);
    }

}
