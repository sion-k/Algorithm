import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        ArrayList<Pair> S = new ArrayList<>(2 * N);
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            S.add(new Pair(l, h));
            S.add(new Pair(r, -h));
        }
        // 작은 x좌표부터 확인한다.
        Collections.sort(S);
        Heap h = new Heap();
        int lastIndex = S.get(0).index;
        int lastValue = -1;
        int i = 0;
        while (i < S.size()) {
            // x값이 S[i].index 지점에 대해서 건물의 높이들을 갱신한다.
            // 건물의 오른쪽 끝의 x좌표가 
            // S[i].index라면 그 건물은 끝났으므로 Heap에서 제거
            // 건물의 왼쪽 끝의 x좌표가 
            // S[i].index라면 그 건물이 시작하므로 Heap에 추가
            while (i < S.size() && S.get(i).index == lastIndex) {
                int index = S.get(i).index;
                int value = S.get(i).value;
                if (value > 0) h.offer(value);
                // 오른쪽 끝의 value는 음수를 붙여서 저장했으므로
                // 다시 음수를 붙인 것
                else h.remove(-value);
                i++;
            }
            // lastIndex를 지나는 건물들은 모두 힙에 추가되었다.
            // 거기서 가장 큰 값을 찾는다
            int value = h.peek();
            // 그 값이 이전의 y값과 동일하다면 건물이 평행하게 이어진 것
            // 달라진 경우만 추가하면 된다. 
            if (value != lastValue) {
                // lastIndex가 되는 순간 높이가 바뀐것이므로 x값읜 lastIndex
                bw.append(String.format("%d %d ", lastIndex, value));
                lastValue = value;
            }
            // lastIndex 갱신
            if (i < S.size()) lastIndex = S.get(i).index;
        }
        System.out.println(bw);
    }

}
class Pair implements Comparable<Pair> {
    int index, value;
    Pair (int i, int v) { index = i; value = v; }

    @Override
    public int compareTo(Pair o) { return index - o.index; }
}
class Heap {
    PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> del = new PriorityQueue<>(Collections.reverseOrder());
    
    void offer(int x) {
        max.offer(x);
    }
    
    int peek() {
        while (!max.isEmpty() && !del.isEmpty() && max.peek().equals(del.peek())) {
            max.poll(); del.poll();
        }
        return max.isEmpty() ? 0 : max.peek();
    }

    void remove(int x) {
        del.offer(x);
    }
}
