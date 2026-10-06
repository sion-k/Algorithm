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

int main() {
    FAST();

    int n;
    cin >> n;

    disjoint_set ds(n);
    for (int i = 0; i < n - 2; i++) {
        int u, v;
        cin >> u >> v;

        u--, v--;

        ds.merge(u, v);
    }

    for (int i = 1; i < n; i++) {
        if (ds.find(0) != ds.find(i)) {
            cout << 1 << " " << i + 1 << "\n";
            break;
        }
    }
}
