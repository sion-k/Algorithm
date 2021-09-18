import java.util.*;
import java.io.*;

public class Main {
    static int ret = 0;
    static StringBuilder bw = new StringBuilder();
    static Map<String, Integer> m;
    static ArrayList<String> m2;
    static ArrayList<ArrayList<Integer>> adj;
    static int[] indegree;

    static Queue<Integer> next(Queue<Integer> q) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while (!q.isEmpty()) {
            int here = q.poll();
            bw.append(m2.get(here)).append("\n");
            ret++;
            for (int there : adj.get(here)) if (--indegree[there] == 0) {
                pq.offer(there);
            }
        }
        while (!pq.isEmpty()) q.offer(pq.poll());
        return q;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        ArrayList<String> tempSet = new ArrayList<>();
        String[][] temp = new String[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            temp[i][0] = st.nextToken();
            temp[i][1] = st.nextToken();
            tempSet.add(temp[i][0]);
            tempSet.add(temp[i][1]);
        }
        Collections.sort(tempSet);
        // (이름, 번호)
        m = new HashMap<>();
        // (번호, 이름)
        m2 = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < tempSet.size(); i++) {
            if (i - 1 >= 0 && tempSet.get(i - 1).equals(tempSet.get(i))) continue;
            m.put(tempSet.get(i), index++);
            m2.add(tempSet.get(i));
        }
        adj = new ArrayList<>();
        for (int i = 0; i < m2.size(); i++) adj.add(new ArrayList<>());
        indegree = new int[m2.size()];
        for (String[] edge : temp) {
            int u = m.get(edge[0]); int v = m.get(edge[1]);
            adj.get(u).add(v);
            indegree[v]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < m2.size(); i++) {
            if (indegree[i] == 0) q.offer(i);
        }
        while (!q.isEmpty()) q = next(q);
        if (ret < m2.size()) System.out.println(-1);
        else System.out.print(bw);
    }

}
