#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define FAST() cin.tie(0)->sync_with_stdio(0)
#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z)                                                   \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", "    \
         << (y) << ", " << (z) << ")\n"
#define deb_tuple(s)                                                           \
    for (int i = 0; i < SIZE(s); i++)                                          \
        cout << s[i] << " \n"[i == SIZE(s) - 1];
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

int main() {
    FAST();
    int n;
    cin >> n;
    Disjoint_set ds(n + 1);
    vector<tuple<int, int, int>> a;
    for (int i = 1; i <= n; i++) {
        int j, w;
        cin >> j >> w;
        a.emplace_back(w, i, j);
    }
    sort(ALL(a));
    reverse(ALL(a));
    long long sum = 0;
    for (auto x : a) {
        auto [w, u, v] = x;
        if (ds.find(u) != ds.find(v)) {
            sum += w;
            ds.merge(u, v);
        }
    }
    cout << sum << "\n";
}
