import java.util.*;
import java.io.*;

public class Main {

    // LIS후보 L에 이분 탐색으로 x를 삽입한다.
    // x와 값이 크거나 같아지는 첫 번째 지점을 찾는다.
    // 그리고 그 위치의 값을 갱신시켜준다
    static void insert(ArrayList<Integer> L, int[] P, int x, int i) {
        // 반복문 불변식 S[lo] < x && x <= L[hi];
        int lo = 0; int hi = L.size() - 1;
        if (L.isEmpty()) { L.add(x); P[i] = 0; return; }
        if (L.get(lo) >= x) { L.set(lo, x); P[i] = 0; return; }
        if (x > L.get(hi)) { L.add(x); P[i] = L.size() - 1; return; }
        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;
            if (L.get(mid) >= x) hi = mid;
            else lo = mid;
        }
        L.set(hi, x);
        P[i] = hi;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] S = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
        ArrayList<Integer> L = new ArrayList<>();
        // P[i] = S[i]가 LIS에서 위치하는 인덱스
        int[] P = new int[N]; Arrays.fill(P, -1);
        for (int i = 0; i < N; i++) insert(L, P, S[i], i);
        bw.append(L.size()).append("\n");
        Stack<Integer> ret = new Stack<>();
        int x = L.size() - 1;
        for (int i = N - 1; i >= 0; i--) {
            if (P[i] == -1) continue;
            if (P[i] == x) { x--; ret.push(S[i]); }
        }
        while (!ret.isEmpty()) {
            bw.append(ret.pop());
            if (!ret.isEmpty()) bw.append(" ");
        }
        System.out.println(bw);
    }

}
class Pair implements Comparable<Pair> {
    int index, value;

    Pair (int i, int v) { index = i; value = v; }

    @Override
    public int compareTo(Pair o) { return value - o.value; }
}
