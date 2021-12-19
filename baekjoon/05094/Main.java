import java.util.*;
import java.io.*;

public class Main {
    // len[k] = S(k)의 길이
    static int[] len = new int[29];

    // S(i)에서 n번째 글자를 반환
    static char f(int i, int n) {
        if (i == 0) return n == 1 ? 'm' : 'o';
        if (n <= len[i - 1]) return f(i - 1, n);
        if (n == len[i - 1] + 1) return 'm';
        if (len[i - 1] + 1 < n && n <= len[i - 1] + i + 3) return 'o';
        return f(i - 1, n - (len[i - 1] + i + 3));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        len[0] = 3;
        for (int i = 1; i <= 28; i++) {
            len[i] = 2 * len[i - 1] + i + 3;
        }
        int k = 0;
        for (int i = 0; i < len.length - 1; i++) {
            if (len[i] < N && N <= len[i + 1]) {
                k = i + 1; break;
            }
        }
        if (k - 1 >= 0) N -= len[k - 1];
        char ret = 'o';
        if (N <= k + 3) {
            if (N == 1) ret = 'm';
        } else {
            ret = f(k - 1, N - (k + 3));
        }
        bw.append(ret).append("\n");
        System.out.print(bw);
    }

}
