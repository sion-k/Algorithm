import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String line = "";
        ArrayList<PriorityQueue<Pair>> S = new ArrayList<>();
        for (int i = 0; i <= N; i++) S.add(new PriorityQueue<>());
        while (!(line = br.readLine()).equals("0 0")) {
            st = new StringTokenizer(line, " ");
            int x = Integer.parseInt(st.nextToken());
            String s = st.nextToken();
            if (S.get(x).size() < M) S.get(x).offer(new Pair(s));
        }
        for (int i = 1; i <= N; i += 2) {
            while (!S.get(i).isEmpty()) {
                bw.append(String.format("%d %s\n", i, S.get(i).poll().str));
            }
        } 
        for (int i = 2; i <= N; i += 2) {
            while (!S.get(i).isEmpty()) {
                bw.append(String.format("%d %s\n", i, S.get(i).poll().str));
            }
        }
        System.out.print(bw);
    }

}
class Pair implements Comparable<Pair> {
    String str;

    Pair (String s) { str = s; }

    @Override
    public int compareTo(Pair o) {
        if (str.length() == o.str.length()) return str.compareTo(o.str);
        return str.length() - o.str.length(); 
    }

    boolean eqauls(Pair o) { return str.equals(o.str); }

}
