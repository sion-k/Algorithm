package LEEBROS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SpecialBucket {
    static int N; static int[][] B; // 떨어지는 블록(k, c)을 순서대로 저장
    static ArrayList<Deque<Integer>> col; // [1, 4]열에 존재하는 블럭들의 리스트, 바닥부터 위 순서
    
    // [1, 8]번 블럭의 우선순위가 높은 순으로 방향을 저장
    static int[][] priority;
    
    static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    
    static boolean inRange(int y, int x) {return 0 <= y && y <= 100 && 1 <= x && x <= 4;}
    
    // i번째 블록부터 놓을 때, 가능한 최대 점수
    static int btk(int i) {
    	int score = getScore();
        moveAll();
        score += getScore();
        if (i == N) return score;
        int k = B[i][0]; int c = B[i][1];
        // 블럭의 위치를 정해야 할 경우 j:[1, 4]위치에 대해 모두 시도
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
    
    // 더 이상 점수를 얻을 수 없을 때 까지 계속해서 떨어뜨리고 점수를 얻는다
    // 블럭들이 바닥에 완전히 떨어져 있을 때, 점수를 얻고 블럭을 제거
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
    
    // 블럭들을 특정 조건에 맞게 모두 이동시키고 col을 새로 초기화
    static void moveAll() {
        int[][] S = new int[101][5]; // 101 * 5 크기의 Bucket, 꼭대기가 0, 바닥이 100, 맨 왼쪽이 1, 맨 오른쪽이 4
        for (int i = 0; i < 101; i++) Arrays.fill(S[i], 9);
        for (int j = 1; j <= 4; j++)
            for (int i = 0; !col.get(j).isEmpty(); i++)
                move(S, 100 - i, j, col.get(j).pollFirst());
        for (int i = 0; i <= 100; i++)
            for (int j = 1; j <= 4; j++)
                if (S[i][j] != 9) col.get(j).offerFirst(S[i][j]);
    }
    
    // (y, x)에 위치한 n번 블럭을 이동시키고, S에 저장
    static void move(int[][] S, int y, int x, int n) {
        // 우선순위가 높은 방향 순으로 이동
        for (int i = 0; i < 8; i++) {
            int d = priority[n][i];
            int ny = y + dy[d]; int nx = x + dx[d];
            if (inRange(ny, nx)) {
                S[ny][nx] = Math.min(S[ny][nx], n);
                return;
            }
        }
    }
    
    // col의 깊은 복사본 반환
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