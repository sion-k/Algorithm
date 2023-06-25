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
    vector<int> p, s;

    disjoint_set(int n) : p(n + 1), s(n + 1, 1) {}

    void merge(int u, int v) {
        u = find(u), v = find(v);

        if (u != v) {
            p[u] = v;
            s[v] += s[u];
        }
    }

    int find(int u) {
        if (p[u] == 0) return u;
        return p[u] = find(p[u]);
    }
};

int main() {
    FAST();

    int n, m, k;
    cin >> n >> m >> k;

    disjoint_set d(n);
    pair<int, int> e;
    for (int i = 1; i <= m; i++) {
        int u, v;
        cin >> u >> v;

        if (i == k) {
            e.first = u;
            e.second = v;
        } else {
            d.merge(u, v);
        }
    }

    auto [u, v] = e;
    u = d.find(u), v = d.find(v);
    if (u == v) {
        cout << 0 << "\n";
    } else {
        cout << (long long)d.s[u] * d.s[v] << "\n";
    }
}
