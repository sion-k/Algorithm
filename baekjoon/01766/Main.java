import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] indegree;
	static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        indegree = new int[N + 1];

        List<List<Integer>> list = new ArrayList<List<Integer>>();
        list.add(new ArrayList<>());
		for (int i = 1; i <= N; i++) {list.add(new ArrayList<>());}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list.get(start).add(end);
			indegree[end]++;
		}
        topologicalSort(list);
        System.out.println(ans.toString().trim());
    }

    static void topologicalSort(List<List<Integer>> list) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int i = 1; i <= N; i++) {if (indegree[i] == 0) {pq.offer(i);}}

        while(!pq.isEmpty()) {
            int here = pq.poll();
            for(Integer there : list.get(here)) {
                indegree[there]--;
                if(indegree[there] == 0) {pq.offer(there);}
            }
            ans.append(here); ans.append(" ");
        }
    }

}
