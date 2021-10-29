import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        Node root = new Node("");
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] S = br.readLine().split("\\\\");
            root.insert(0, S);
        }
        root.print(-1, bw);
        System.out.print(bw);
    }

}
class Node implements Comparable<Node> {
    String name;
    PriorityQueue<Node> children;

    Node(String n) {
        name = n;
        children = new PriorityQueue<>();
    }

    // 현재 노드의 자식에 S[i]를 삽입
    void insert(int i, String[] S) {
        if (i == S.length) return;
        // 이미 자식에 존재하면 재귀적으로 S[i + 1]을 삽입
        for (Node ch : children) if (S[i].equals(ch.name)) {
            ch.insert(i + 1, S);
            return;
        }
        // 발견하지 못하면 새로 삽입
        children.offer(new Node(S[i]));
        insert(i, S);
    }

    void print(int i, StringBuilder bw) {
        for (int j = 0; j < i; j++) bw.append(" ");
        if (i != -1) bw.append(name).append("\n");
        PriorityQueue<Node> nq = new PriorityQueue<>();
        while (!children.isEmpty()) {
            Node ch = children.poll();
            ch.print(i + 1, bw);
            nq.offer(ch);
        }
        children = nq;
    }

    @Override
    public int compareTo(Node o) {
        return name.compareTo(o.name);
    }

}