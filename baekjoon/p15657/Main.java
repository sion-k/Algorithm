package baekjoon.p15657;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] S;
    static ArrayList<Integer> picked = new ArrayList<Integer>();
    static BufferedWriter bw;

    static void print() throws IOException {
        for (int i = 0; i < picked.size(); i++) {
            bw.write(String.valueOf(picked.get(i)));
            if (i != picked.size() - 1) {bw.write(" ");}
        }
        bw.newLine();
    }

    static void BFC(int lastPick, int toPick) throws IOException {
        if (toPick == 0) {print(); return;}
        for (int i = lastPick; i < N; i++) {
            picked.add(S[i]);
            BFC(i, toPick - 1);
            picked.remove(picked.size() - 1);
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        S = new int[N];
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {S[i] = Integer.parseInt(st.nextToken());}
        Arrays.sort(S);
        BFC(0, M);
        bw.close();
    }
}