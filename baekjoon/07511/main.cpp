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

    disjoint_set(int n) : p(n, -1) {}

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

void solve() {
    int n, k;
    cin >> n >> k;

    disjoint_set ds(n);
    for (int i = 0; i < k; i++) {
        int u, v;
        cin >> u >> v;

        ds.merge(u, v);
    }

    int m;
    cin >> m;

    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;

        cout << (ds.find(u) == ds.find(v)) << "\n";
    }
}

int main() {
    FAST();

    int tc;
    cin >> tc;
    for (int i = 1; i <= tc; i++) {
        cout << "Scenario " << i << ":\n";

        solve();

        if (i != tc) {
            cout << "\n";
        }
    }
}
