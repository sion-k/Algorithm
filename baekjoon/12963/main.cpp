#include <bits/stdc++.h>

#define FAST() cin.tie(0)->sync_with_stdio(0)
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())

#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y) cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z) cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", " << (y) << ", " << (z) << ")\n"
#define deb_tuple(s) for (int i = 0; i < SIZE(s); i++) cout << s[i] << " \n"[i == SIZE(s) - 1];

using namespace std;

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

const int MOD = 1e9 + 7;

int binpow(int a, int b) {
    if (b == 0)
        return 1;

    long long res = binpow(a, b / 2);

    if (b % 2)
        res = res * res * a;
    else
        res = res * res;

    return res % MOD;
}

int main() {
    FAST();

    int n, m;
    cin >> n >> m;

    vector<pair<int, int>> edge(m);
    for (int i = 0; i < m; i++) {
        cin >> edge[i].first >> edge[i].second;
    }

    int sum = 0;

    Disjoint_set ds(n);

    for (int i = m - 1; i >= 0; i--) {
        auto [u, v] = edge[i];

        u = ds.find(u), v = ds.find(v);
        int l = ds.find(0), r = ds.find(n - 1);
        if (u != v && (u == l && v == r || u == r && v == l)) {
            sum = (sum + binpow(3, i)) % MOD;
        } else {
            ds.merge(u, v);
        }
    }

    cout << sum << "\n";
}
