import java.util.*;
import java.io.*;

public class Main {
    static int N, K, L;
    static ArrayList<Integer> S;

    // L길이의 통나무를 최대 K번 자르되,
    // 가장 긴 조각의 길이가 x이하가 되게 가능한지 여부 반환
    static boolean f(int x) {
        // 모든 부분을 잘라도 x이하로 하는것이 불가능한지 확인
        int max = Math.max(S.get(0), L - S.get(S.size() - 1));
        for (int i = 1; i < N; i++) max = Math.max(max, S.get(i) - S.get(i - 1));
        if (max > x) return false;
        int last = 0; int cnt = 0;
        for (int i = 0; i < N; i++) {
            // S[i]를 안자르면 S[i + 1] - last > x인지 확인
            // 다음 위치가 없다면 통나무의 끝이라고 가정
            int next = i == N - 1 ? L : S.get(i + 1);
            // 반드시 S[i]를 잘라야 하는 경우
            if (next - last > x) {
                // 더 이상 자를 수 없는 경우
                // S[i] = L이면 못자르더라도 괜찮다.
                if (cnt == K) return false; 
                cnt++;
                last = S.get(i);
            }
        }
        return L - last <= x;
    }

    // 최적해가 x일때 가장 처음으로 가장 긴 조각의 위치
    static int reconstruct(int x) {
        int last = L; int cnt = 0;
        for (int i = 0; i < N; i++) {
            // S[i]를 안자르면 S[i + 1] - last > x인지 확인
            // 다음 위치가 없다면 통나무의 끝이라고 가정
            int next = i == N - 1 ? 0 : S.get(i + 1);
            // 반드시 S[i]를 잘라야 하는 경우
            if (last - next > x) {
                // 더 이상 자를 수 없는 경우
                // S[i] = 0이면 못자르더라도 괜찮다.
                if (cnt == K && S.get(i) != 0) break; 
                cnt++;
                last = S.get(i);
            }
        }
        return cnt < K ? S.get(S.size() - 1) : last;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = new ArrayList<>(N);
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) S.add(Integer.parseInt(st.nextToken()));
        Collections.sort(S);
        // f(lo) = false, f(hi) = true인 hi를 찾는다
        int lo = 0; int hi = L;
        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;
            if (f(mid)) hi = mid;
            else lo = mid;
        }
        Collections.sort(S, Collections.reverseOrder());
        bw.append(String.format("%d %d\n", hi, reconstruct(hi)));
        System.out.print(bw);
    }

}
