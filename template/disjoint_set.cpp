#include <vector>
using namespace std;

class Disjoint_set {
public:
    int n;
    vector<int> parent, rank, size;

    Disjoint_set(int n) : n(n), parent(n), rank(n), size(n, 1) {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    void merge(int u, int v) {
        u = find(u); v = find(v);
        if (u == v) return;
        if (rank[u] > rank[v]) swap(u, v);
        parent[u] = v;
        size[v] += size[u];
        if (rank[u] == rank[v]) rank[v]++;
    }

    int find(int u) {
        if (u == parent[u]) return u;
        return parent[u] = find(parent[u]);
    }
};
