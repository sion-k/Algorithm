import java.util.*;
import java.io.*;

public class Main {

    static boolean f1(int y1, int x1, int y2, int x2) {
        return Math.abs(x1 - x2) <= Math.abs(y1 - y2);
    }

    static boolean f2(int y1, int x1, int y2, int x2) {
        return Math.abs(x1 - x2) >= Math.abs(y1 - y2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] S = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            S[i] = new int[] { y, x };
        }
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int y2 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        boolean flag = false;
        // 위
        boolean temp = true;
        for (int[] p : S) {
            int y1 = p[0]; int x1 = p[1];
            if (y1 > y2 && f1(y1, x1, y2, x2)) temp = false;
        }
        flag = flag | temp;
        // 아래
        temp = true;
        for (int[] p : S) {
            int y1 = p[0]; int x1 = p[1];
            if (y1 < y2 && f1(y1, x1, y2, x2)) temp = false;
        }
        flag = flag | temp;
        // 왼쪽
        temp = true;
        for (int[] p : S) {
            int y1 = p[0]; int x1 = p[1];
            if (x1 > x2 && f2(y1, x1, y2, x2)) temp = false;
        }
        flag = flag | temp;
        // 오른쪽
        temp = true;
        for (int[] p : S) {
            int y1 = p[0]; int x1 = p[1];
            if (x1 < x2 && f2(y1, x1, y2, x2)) temp = false;
        }
        flag = flag | temp;
        System.out.println(flag ? "YES" : "NO");
    }

}
