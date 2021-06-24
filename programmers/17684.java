package programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Compression {

    public int[] solution(String msg) {
        // 모든 알파벳에 대해서 map 초기화
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 26; i++)
            map.put(String.valueOf((char)('A' + i) + ""), i + 1);
        int newIndex = 27;
        char[] S = msg.toCharArray(); int N = S.length;
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringBuilder sub = new StringBuilder();
            // 사전에 존재하는 가장 긴 문자열 sub를 찾는다
            while (i < N &&
                   map.get(new StringBuilder(sub).append(S[i]).toString()) != null) {
                sub.append(S[i]); i++;
            }
            // sub에 해당하는 색인 번호를 출력
            answer.add(map.get(sub.toString()));
            // 다음 글자가 남아 있다면 사전에 등록
            if (i < N) map.put(sub.append(S[i]).toString(), newIndex++);
            i--;
        }
        int[] ret = new int[answer.size()];
        for (int i = 0; i < ret.length; i++)
            ret[i] = answer.get(i);
        return ret;
    }

}