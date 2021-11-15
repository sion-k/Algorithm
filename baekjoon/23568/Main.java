import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<Integer, Integer> adj = new HashMap<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int d = st.nextToken().equals("L") ? -1 : 1;
            int k = Integer.parseInt(st.nextToken());
            adj.put(u, u + k * d);
        }
        int here = Integer.parseInt(br.readLine());
        while (adj.get(here) != null) {
            here = adj.get(here);
        }
        System.out.println(here);
    }
}