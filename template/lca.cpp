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

class LCA {
public:
    const int MAX = 20;

    int n;
    vector<int> depth;
    vector<vector<int>> adj, parent;

    LCA(int n, vector<vector<int>> adj) : n(n), adj(adj), depth(n) {
        parent = vector<vector<int>>(MAX + 1, vector<int>(n, -1));
        dfs(0, 0);

        for (int i = 1; i <= MAX; i++) {
            for (int j = 0; j < n; j++) {
                if (parent[i - 1][j] != -1) {
                    parent[i][j] = parent[i - 1][parent[i - 1][j]];
                }
            }
        }
    }

    void dfs(int here, int prev) {
        for (int there : adj[here]) {
            if (there != prev) {
                depth[there] = depth[here] + 1;
                parent[0][there] = here;
                dfs(there, here);
            }
        }
    }

    int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            swap(u, v);
        }

        for (int i = 0; i <= MAX; i++) {
            if ((depth[u] - depth[v]) & (1 << i)) {
                u = parent[i][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int i = MAX; i >= 0; i--) {
            if (parent[i][u] != parent[i][v]) {
                u = parent[i][u];
                v = parent[i][v];
            }
        }

        return parent[0][u];
    }
};

int main() {
    FAST();

    int n, q;
    cin >> n >> q;

    vector<vector<int>> adj(n);
    for (int u = 1; u < n; u++) {
        int v;
        cin >> v;

        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    LCA lca(n, adj);
    for (int i = 0; i < q; i++) {
        int u, v;
        cin >> u >> v;

        cout << lca.lca(u, v) << '\n';
    }
}
