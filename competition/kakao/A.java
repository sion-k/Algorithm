package competition.kakao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class A {
	
	static char[][] room;
    static ArrayList<int[]> people;
    
    static final int[] dy = {-1, 1, 0, 0}; 
    static final int[] dx = {0, 0, -1 ,1};
    
    static boolean inRange(int y, int x) {
        return 0 <= y && y < 5 && 0 <= x && x < 5;
    }
    
    static int bfs(int sy, int sx) {
        Queue<int[]> q = new LinkedList<>();
        int[][] booked = new int[5][5];
        for (int i = 0; i < 5; i++) Arrays.fill(booked[i], -1);
        q.offer(new int[] { sy, sx });
        booked[sy][sx] = 0;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int y = p[0]; int x = p[1];
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d]; int nx = x + dx[d];
                if (inRange(ny, nx) && room[ny][nx] != 'X' && booked[ny][nx] == -1) {
                    q.offer(new int[] { ny, nx });
                    booked[ny][nx] = booked[y][x] + 1;
                    if (room[ny][nx] == 'P') return booked[ny][nx];
                }
            }
        }
        return 100;
    }
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[][] places = new String[1][5];
		for (int i = 0; i < 1; i++)
			for (int j = 0; j < 5; j++)
				places[i][j] = br.readLine();
		int[] answer = new int[5];
        for (int r = 0; r < 1; r++) {
            room = new char[5][5];
            people = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++){
                    room[i][j] = places[r][i].charAt(j);
                    if (room[i][j] == 'P') people.add(new int[] { i, j });
                }
            }
            boolean ok = true;
            for (int[] p : people) {
                if (bfs(p[0], p[1]) <= 2) {
                    ok = false;
                    break;
                }
            }
            answer[r] = ok ? 1 : 0;
        }
        System.out.println(Arrays.toString(answer));
	}

}
