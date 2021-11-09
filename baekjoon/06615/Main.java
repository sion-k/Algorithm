import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            long u = Long.parseLong(st.nextToken());
            long v = Long.parseLong(st.nextToken());
            if (u == 0 && v == 0) break;
            long a = u; long b = v;
            Map<Long, Integer> visit = new HashMap<>();
            int t = 0;
            while (true) {
                visit.put(u, t++);
                if (u == 1) break;
                if (u % 2 == 0) {
                    u /= 2;
                } else {
                    u = 3 * u + 1;
                }
            }
            t = 0;
            while (visit.get(v) == null) {
                if (v == 1) break;
                if (v % 2 == 0) {
                    v /= 2;
                } else {
                    v = 3 * v + 1;
                }
                t++;
            }
            bw.append(String.format("%d needs %d steps, %d needs %d steps, they meet at %d\n", a, visit.get(v), b, t, v));
        }
        System.out.print(bw);
    }

}
