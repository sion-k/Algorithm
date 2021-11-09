import java.util.*;
import java.io.*;

public class Main {
    static int[] in;
    static int[] post;

    // in에서 n의 위치(0-based)
    static int[] posIn;
    // post에서 n의 위치 (0-based)
    static int[] posPost;

    static void pre(StringBuilder bw, int i, int j, int k) {
        if (i > j) return;
        bw.append(in[k]).append(" ");
        int r = posPost[in[k]];
        if (r - (j - k) - 1 >= 0){
            pre(bw, i, k - 1, posIn[post[r - (j - k) - 1]]);
        }
        if (r - 1 >= 0) {
            pre(bw, k + 1, j, posIn[post[r - 1]]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        in = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) in[i] = Integer.parseInt(st.nextToken());
        posIn = new int[N + 1];
        for (int i = 0; i < N; i++) posIn[in[i]] = i;
        post = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) post[i] = Integer.parseInt(st.nextToken());
        posPost = new int[N + 1];
        for (int i = 0; i < N; i++) posPost[post[i]] = i;
        pre(bw, 0, N - 1, posIn[post[N - 1]]);
        System.out.println(bw);
    }

}
