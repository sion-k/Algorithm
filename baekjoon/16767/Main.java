import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        ArrayList<Tuple> S = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int p = i;
            S.add(new Tuple(a, t, p));
        }
        Collections.sort(S, (Tuple u, Tuple v) -> u.a - v.a);
        PriorityQueue<Tuple> pq = new PriorityQueue<>((Tuple u, Tuple v) -> u.p - v.p);
        pq.offer(S.get(0));
        int time = S.get(0).a;
        int max = 0;
        int i = 1;
        while (!pq.isEmpty() || i < N) {
            while (i < N && S.get(i).a <= time) {
                pq.offer(S.get(i));
                i++;
            }
            if (pq.isEmpty()) {
                if (i < N) time = S.get(i).a;
            } else {
                Tuple x = pq.poll();
                max = Math.max(max, time - x.a);
                time += x.t;
            }
        }
        bw.append(max);
        System.out.println(bw);
    }

}
class Tuple {
    int a, t, p;
    
    Tuple(int a, int t, int p) {
        this.a = a; this.t = t; this.p = p;
    }
}
