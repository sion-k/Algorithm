import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        ArrayList<Integer> S = new ArrayList<>();
        for (int i = 0; i < 2 * N; i++) S.add(Integer.parseInt(st.nextToken()));
        Collections.sort(S);
        int head = 0; int tail = 2 * N - 1;
        int min = Integer.MAX_VALUE;
        while (head < tail) {
            min = Math.min(min, S.get(head) + S.get(tail));
            head++; tail--;
        }
        System.out.println(min);
    }

}
