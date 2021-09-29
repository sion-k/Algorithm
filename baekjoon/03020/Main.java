import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[] C1 = new int[H + 2]; // 꼭대기의 높이가 x이상인 석순
        int[] C2 = new int[H + 2]; // 맨바닥의 높이가 x이하인 종유석
        for (int i = 0; i < N / 2; i++) {
            C1[Integer.parseInt(br.readLine())]++;
            C2[H - Integer.parseInt(br.readLine()) + 1]++;
        }
        for (int i = 1; i <= H; i++) {
            C1[H - i + 1] += C1[H - i + 2];
            C2[i] += C2[i - 1];
        }
        int min = N + 1; int minCount = 0;
        for (int i = 1; i <= H; i++) {
            int cand = C1[i] + C2[i];
            if (min >= cand) {
                if (min == cand) minCount++;
                else minCount = 1;
                min = cand;
            }
        }
        System.out.println(String.format("%d %d\n", min, minCount));
    }

}
