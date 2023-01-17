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

class Disjoint_Set {
public:
    vector<int> p, s;

    Disjoint_Set(int n): p(n), s(n, 1) {}

    void merge(int u, int v) {
        u = find(u), v = find(v);
        if (u == v) return;

        p[u] = v;
        s[v] += s[u];
    }

    int find(int u) {
        if (p[u] == 0) return u;
        return p[u] = find(p[u]);
    }
};

int main() {
    FAST();

    int n, q;
    cin >> n >> q;

    vector<tuple<int, int, int>> edge;
    for (int i = 0; i < n - 1; i++) {
        int u, v, w;
        cin >> u >> v >> w;

        edge.emplace_back(w, u, v);
    }
    sort(ALL(edge));

    vector<tuple<int, int, int>> query;
    for (int i = 0; i < q; i++) {
        int k, v;
        cin >> k >> v;

        query.emplace_back(k, v, i);
    }
    sort(ALL(query));
    reverse(ALL(query));

    Disjoint_Set ds(n + 1);

    vector<pair<int, int>> ans;
    for (int i = 0; i < q; i++) {
        auto [k, v, t] = query[i];

        while (!edge.empty() && get<0>(edge.back()) >= k) {
            auto [w, p, q] = edge.back();
            edge.pop_back();

            ds.merge(p, q);
        }

        int s = ds.s[ds.find(v)];

        ans.emplace_back(t, s);
    }

    sort(ALL(ans));

    for (int i = 0; i < q; i++) {
        cout << ans[i].second - 1 << "\n";
    }
}
