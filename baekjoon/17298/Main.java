import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        // i번째 원소의 오등큰수
        int[] S = new int[N];
        Arrays.fill(S, -1);
        // 스택을 value 기준 내림차순으로 유지
        Stack<Pair> R = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            while (!R.isEmpty() && R.peek().value < x) S[R.pop().index] = x;
            R.push(new Pair(i, x));
        }
        for (int x : S) bw.append(x).append(" ");
        System.out.println(bw);
    }

}
class Pair {
    int index, value;

    Pair (int i, int v) { index = i; value = v; }
}
