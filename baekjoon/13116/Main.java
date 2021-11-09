import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            boolean[] visit = new boolean[1024];
            visit[0] = true;
            while (u != 0) {
                visit[u] = true;
                u /= 2;
            }
            while (!visit[v]) {
                v /= 2;
            }
            bw.append(v * 10).append("\n");
        }
        System.out.print(bw);
    }

}
