import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] S1 = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) S1[i] = Integer.parseInt(st.nextToken());
        int[] P1 = new int[N + 1];
        for (int i = 1; i <= N; i++) P1[S1[i]] = i;
        FenwickTree t1 = new FenwickTree(N + 1);
        long sum1 = 0;
        // 작은 수 부터 수열을 만들어나가면서 Inversion을 센다.
        // 만약 지금 넣은 수 왼쪽에 원소가 있다면 그 원소들은 모두 Inversion이 된다.
        for (int i = N; i >= 1; i--) {
            sum1 += t1.sum(P1[i]);
            t1.add(P1[i], 1);
        }
        int[] S2 = new int[N + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) S2[i] = Integer.parseInt(st.nextToken());
        int[] P2 = new int[N + 1];
        for (int i = 1; i <= N; i++) P2[S2[i]] = i;
        FenwickTree t2 = new FenwickTree(N + 1);
        long sum2 = 0;
        // 작은 수 부터 수열을 만들어나가면서 Inversion을 센다.
        // 만약 지금 넣은 수 왼쪽에 원소가 있다면 그 원소들은 모두 Inversion이 된다.
        for (int i = N; i >= 1; i--) {
            sum2 += t2.sum(P2[i]);
            t2.add(P2[i], 1);
        }
        System.out.println(((sum1 % 2) ^ (sum2 % 2)) == 0 ? "Possible" : "Impossible");
    }

}
class FenwickTree {
    long[] tree;
    
    FenwickTree(int n) { tree = new long[n + 1]; }

    void add(int pos, int val) {
        while (pos < tree.length) {
            tree[pos] += val;
            pos += (pos & -pos);
        }
    }

    long sum(int pos) {
        long ret = 0;
        while (pos > 0) {
            ret += tree[pos];
            pos -= (pos & -pos);
        }
        return ret;
    }

}
