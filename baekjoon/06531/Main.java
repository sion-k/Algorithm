import java.util.*;
import java.io.*;

public class Main {
    static char[] S;
    static int[][] cache;

    // S[start, end]가 Set인지 반환 (start <= end)
    static int isSet(int start, int end) {
        if (S[start] != '{' || S[end] != '}') return 0;
        // Empty Set
        if (start + 1 == end) return 1;
        // isList
        if (isList(start + 1, end - 1) == 1) return 1;
        return 0;
    }

    static int isList(int start, int end) {
        if (isElement(start, end)) return 1;
        for (int mid = start; mid <= end; mid++) if (S[mid] == ',') {
            if ((mid - 1) - start + 1 >= 1 && end - (mid + 1) + 1 >= 1) {
                if (isElement(start, mid - 1) && isList(mid + 1, end) == 1) {
                    return cache[start][end] = 1;
                }
            }
        }
        return cache[start][end] = 0;
    }

    static boolean isElement(int start, int end) {
        return start == end || isSet(start, end) == 1; 
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            S = br.readLine().toCharArray();
            cache = new int[S.length][S.length];
            for (int i = 0; i < S.length; i++) Arrays.fill(cache[i], -1);
            bw.append(String.format("Word #%d: %s\n", tc, isSet(0, S.length - 1) == 1 ? "Set" : "No Set"));
        }
        System.out.print(bw);
    }

}
