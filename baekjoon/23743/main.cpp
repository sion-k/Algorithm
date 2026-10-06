#include <bits/stdc++.h>

#define FAST() cin.tie(0)->sync_with_stdio(0)
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())

#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y) cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z) cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", " << (y) << ", " << (z) << ")\n"
#define deb_tuple(s) \
    cout << "["; \
    for (int __i = 0; __i < SIZE(s); __i++) { \
        cout << s[__i]; \
        if (__i != SIZE(s) - 1) cout << ", "; \
    } \
    cout << "]\n";

using namespace std;

class disjoint_set {
public:
    vector<int> p;
    disjoint_set(int n) : p(n + 1, -1) {}

    void merge(int u, int v) {
        u = find(u), v = find(v);
        if (u != v) {
            p[u] = v;
        }
    }

    int find(int u) {
        if (p[u] == -1) return u;
        return p[u] = find(p[u]);
    }
};

int main() {
    FAST();

    int n, m;
    cin >> n >> m;

    vector<tuple<int, int, int>> e;
    for (int i = 0; i < m; i++) {
        int u, v, w;
        cin >> u >> v >> w;

        e.emplace_back(w, u, v);
    }

    for (int u = 1; u <= n; u++) {
        int w;
        cin >> w;
        e.emplace_back(w, u, 0);
    }

    sort(ALL(e));

    disjoint_set ds(n);
    int sum = 0;
    for (auto [w, u, v] : e) {
        u = ds.find(u), v = ds.find(v);

        if (u != v) {
            ds.merge(u, v);
            sum += w;
        }
    }

    cout << sum << "\n";
}
