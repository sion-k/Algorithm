import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] S = new int[N];
        // C[x] = x의 등장 횟수
        int[] C = new int[1000001];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
            C[S[i]]++;
        }
        Stack<Pair> R = new Stack<>();
        int[] ret = new int[N];
        Arrays.fill(ret, -1);
        for (int i = 0; i < N; i++) {
            while (!R.isEmpty() && R.peek().value < C[S[i]]) {
                ret[R.pop().index] = S[i];
            }
            R.push(new Pair(i, C[S[i]]));
        }
        for (int x : ret) bw.append(x).append(" ");
        System.out.println(bw);
    }

}
class Pair {
    int index, value;

    Pair (int i, int v) { index = i; value = v; }
}
