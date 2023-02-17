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

vector<int> parent;
vector<vector<int>> adj;

void dfs(int here, int prev) {
    for (int there : adj[here]) if (there != prev) {
        parent[there] = here;
        dfs(there, here);
    }
}

class Disjoint_set {
public:
    vector<int> p, s;

    Disjoint_set(int n): p(n, -1), s(n, 1) {}

    void merge(int u, int v) {
        u = find(u), v = find(v);
        if (u == v) return;

        p[u] = v;
        s[v] += s[u];
    }

    int find(int here) {
        if (p[here] == -1) return here;
        return p[here] = find(p[here]);
    }
};

int main() {
    FAST();

    int n;
    cin >> n;

    adj.resize(n + 1);
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;

        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    parent.resize(n + 1);
    dfs(1, 1);

    int q;
    cin >> q;

    for (int i = 0; i < q; i++) {
        int k;
        cin >> k;

        vector<int> s(k + 1);
        map<int, int> m;
        for (int j = 1; j <= k; j++) {
            cin >> s[j];
            m[s[j]] = j;
        }

        Disjoint_set ds(k + 1);
        for (int j = 1; j <= k; j++) {
            int u = s[j], v = parent[u];

            if (u != 1 && m.count(v)) {
                u = m[u], v = m[v];
                ds.merge(u, v);
            }
        }

        long long sum = 0;
        for (int j = 1; j <= k; j++) if (ds.p[j] == -1) {
            sum += (long long)ds.s[j] * (ds.s[j] - 1) / 2;
        }

        cout << sum << "\n";
    }
}
