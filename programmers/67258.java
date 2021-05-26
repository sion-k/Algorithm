import java.util.*;

class Solution {

    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>();
        for (String g : gems) set.add(g);
        int totalKind = set.size();
        HashMap<String, Integer> map = new HashMap<>();
        int head = 0; int tail = 0;
        int kind = 1; map.put(gems[0], 1);
        int start = 0; int min = gems.length; // 최적해의 시작 지점 start와 최적해의 길이 min
        while (head < gems.length) {
            // [head, tail]구간에서 모든 종류의 보석을 포함하는 가장 짧은 구간을 찾는다
            while (tail + 1 < gems.length && kind < totalKind) {
                if (map.get(gems[tail + 1]) == null) kind++;
                map.put(gems[tail + 1], map.getOrDefault(gems[tail + 1], 0) + 1);
                tail++;
            }
            if (kind == totalKind && min > tail - head + 1) {
                min = tail - head + 1; start = head;
            }
            int toRemove = map.get(gems[head]);
            if (toRemove == 1) { kind--; map.remove(gems[head]); }
            else map.put(gems[head], toRemove - 1);
            head++;
        }
        int[] answer = { start + 1, start + min };
        return answer;
    }

}
