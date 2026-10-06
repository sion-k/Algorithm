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

class Disjoint_set {
public:
    vector<int> p;

    Disjoint_set(int n) : p(n, -1) {}

    int find(int u) {
        if (p[u] == -1) return u;
        return p[u] = find(p[u]);
    }

    void merge(int u, int v) {
        u = find(u), v = find(v);
        if (u == v) {
            return;
        }

        p[u] = v;
    }
};

int main() {
    FAST();

    int n;
    cin >> n;

    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    vector<tuple<int, int, int>> edge;

    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            edge.emplace_back(a[i] ^ a[j], i, j);
        }
    }

    sort(ALL(edge));
    reverse(ALL(edge));

    Disjoint_set ds(n);
    long long sum = 0;
    for (auto [w, u, v] : edge) {
        u = ds.find(u), v = ds.find(v);

        if (u != v) {
            ds.merge(u, v);
            sum += w;
        }
    }

    cout << sum << '\n';
}
