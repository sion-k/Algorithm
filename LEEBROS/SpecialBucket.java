package LEEBROS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SpecialBucket {
    static int N; static int[][] B; // �������� ���(k, c)�� ������� ����
    static ArrayList<Deque<Integer>> col; // [1, 4]���� �����ϴ� ������ ����Ʈ, �ٴں��� �� ����
    
    // [1, 8]�� ���� �켱������ ���� ������ ������ ����
    static int[][] priority;
    
    static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    
    static boolean inRange(int y, int x) {return 0 <= y && y <= 100 && 1 <= x && x <= 4;}
    
    // i��° ��Ϻ��� ���� ��, ������ �ִ� ����
    static int btk(int i) {
    	int score = getScore();
        moveAll();
        score += getScore();
        if (i == N) return score;
        int k = B[i][0]; int c = B[i][1];
        // ���� ��ġ�� ���ؾ� �� ��� j:[1, 4]��ġ�� ���� ��� �õ�
        if (c == 0) {
            int max = 0; 
            for (int j = 1; j <= 4; j++) {
                ArrayList<Deque<Integer>> temp = deepCopy();
                col.get(j).offerLast(k);
                max = Math.max(max, btk(i + 1));
                col = temp;
            }
            return score + max;
        } else {
            col.get(c).offerLast(k);
            return score + btk(i + 1);
        }
    }
    
    // �� �̻� ������ ���� �� ���� �� ���� ����ؼ� ����߸��� ������ ��´�
    // ������ �ٴڿ� ������ ������ ���� ��, ������ ��� ���� ����
    static int getScore() {
        int sum = 0;
        while (aligned()) {
            for (int j = 1; j <= 4; j++)
                col.get(j).pollFirst();
            sum++;
        }
        return sum;
    }
    
    static boolean aligned() {
        for (int j = 1; j <= 4; j++)
        	if (col.get(j).isEmpty()) return false;
        return true;
    }
    
    // ������ Ư�� ���ǿ� �°� ��� �̵���Ű�� col�� ���� �ʱ�ȭ
    static void moveAll() {
        int[][] S = new int[101][5]; // 101 * 5 ũ���� Bucket, ����Ⱑ 0, �ٴ��� 100, �� ������ 1, �� �������� 4
        for (int i = 0; i < 101; i++) Arrays.fill(S[i], 9);
        for (int j = 1; j <= 4; j++)
            for (int i = 0; !col.get(j).isEmpty(); i++)
                move(S, 100 - i, j, col.get(j).pollFirst());
        for (int i = 0; i <= 100; i++)
            for (int j = 1; j <= 4; j++)
                if (S[i][j] != 9) col.get(j).offerFirst(S[i][j]);
    }
    
    // (y, x)�� ��ġ�� n�� ���� �̵���Ű��, S�� ����
    static void move(int[][] S, int y, int x, int n) {
        // �켱������ ���� ���� ������ �̵�
        for (int i = 0; i < 8; i++) {
            int d = priority[n][i];
            int ny = y + dy[d]; int nx = x + dx[d];
            if (inRange(ny, nx)) {
                S[ny][nx] = Math.min(S[ny][nx], n);
                return;
            }
        }
    }
    
    // col�� ���� ���纻 ��ȯ
    static ArrayList<Deque<Integer>> deepCopy() {
        ArrayList<Deque<Integer>> ret = new ArrayList<>();
        ret.add(new LinkedList<>());
        for (int j = 1; j <= 4; j++) {
            ret.add(new LinkedList<>());
            for (Integer x : col.get(j))
                ret.get(j).offerLast((int)(x));
        }
        return ret;
    }
    
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());
        priority = new int[9][8];
        for (int i = 1; i <= 8; i++) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            for (int j = 0; j < 8; j++)
                priority[i][j] = Integer.parseInt(st.nextToken()) - 1;
        }
        B = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            int k = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            B[i][0] = k; B[i][1] = c;
        }
        col = new ArrayList<>();
        col.add(new LinkedList<>());
        for (int j = 1; j <= 4; j++) col.add(new LinkedList<>());
        System.out.println(btk(0));
    }
}