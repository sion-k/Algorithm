import java.util.*;

class Solution {
    
    public int solution(int n, int[][] edge) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for (int[] p : edge) { adj.get(p[0]).add(p[1]); adj.get(p[1]).add(p[0]); }
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        int[] dist = new int[n + 1]; Arrays.fill(dist, -1);
        dist[1] = 0;
        int max = 0;
        while (!q.isEmpty()) {
            int here = q.poll();
            for (int there : adj.get(here)) if (dist[there] == -1) {
                q.offer(there);
                dist[there] = dist[here] + 1;
                max = Math.max(max, dist[there]);
            }
        }
        int answer = 0;
        for (int i = 1; i <= n; i++)
            if (dist[i] == max) answer++;
        return answer;
    }
    
}
