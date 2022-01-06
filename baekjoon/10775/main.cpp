#include <bits/stdc++.h>
using namespace std;

class DisjointSet {
public:
    int parent[100001];

    DisjointSet(int n) {
        for (int i = 1; i <= n; i++) parent[i] = i;
    }

    int find(int u) {
        if (parent[u] == u) return u;
        return parent[u] = find(parent[u]);
    }

    void merge(int u, int v) {
        u = find(u); v = find(v);
        if (u == v) return;
        parent[u] = v;
    }
};

int main() {
    int G, P;
    scanf("%d %d", &G, &P);
    DisjointSet ds(G);
    int cnt = 0;
    for (int i = 0; i < P; i++) {
        int g;
        scanf("%d", &g);
        g = ds.find(g);
        if (g != 0) {
            cnt++;
            ds.merge(g, g - 1);
        } else {
            break;
        }
    }
    printf("%d\n", cnt);
}
