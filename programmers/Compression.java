package programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Compression {

    public int[] solution(String msg) {
        // ��� ���ĺ��� ���ؼ� map �ʱ�ȭ
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 26; i++)
            map.put(String.valueOf((char)('A' + i) + ""), i + 1);
        int newIndex = 27;
        char[] S = msg.toCharArray(); int N = S.length;
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringBuilder sub = new StringBuilder();
            // ������ �����ϴ� ���� �� ���ڿ� sub�� ã�´�
            while (i < N &&
                   map.get(new StringBuilder(sub).append(S[i]).toString()) != null) {
                sub.append(S[i]); i++;
            }
            // sub�� �ش��ϴ� ���� ��ȣ�� ���
            answer.add(map.get(sub.toString()));
            // ���� ���ڰ� ���� �ִٸ� ������ ���
            if (i < N) map.put(sub.append(S[i]).toString(), newIndex++);
            i--;
        }
        int[] ret = new int[answer.size()];
        for (int i = 0; i < ret.length; i++)
            ret[i] = answer.get(i);
        return ret;
    }

}