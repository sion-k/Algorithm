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

vector<int> visited, traversal, scc;
vector<vector<int>> adj, adj_t;

void dfs(int here, int pass, int comp = 0) {
    visited[here] = true;

    for (int there : (pass == 1 ? adj[here] : adj_t[here])) {
        if (!visited[there]) {
            dfs(there, pass, comp);
        }
    }

    if (pass == 1) {
        traversal.push_back(here);
    } else {
        scc[here] = comp;
    }
}

bool compare(vector<int>& u, vector<int>& v) {
    return u[0] < v[0];
}

int main() {
    FAST();

    int n, m;
    cin >> n >> m;

    adj.resize(n + 1);
    adj_t.resize(n + 1);

    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;

        adj[u].push_back(v);
        adj_t[v].push_back(u);
    }

    visited.resize(n + 1);
    for (int here = 1; here <= n; here++) {
        if (!visited[here]) {
            dfs(here, 1);
        }
    }

    reverse(ALL(traversal));
    visited = vector<int>(n + 1);
    scc.resize(n + 1);

    int cnt = 0;
    for (int i = 0; i < n; i++) {
        if (!visited[traversal[i]]) {
            dfs(traversal[i], 2, cnt);
            cnt++;
        }
    }

    cout << cnt << "\n";

    vector<vector<int>> ret(n + 1);
    for (int i = 1; i <= n; i++) {
        ret[scc[i]].push_back(i);
    }

    vector<vector<int>> ret2;
    for (int i = 0; i <= n; i++) {
        if (!ret[i].empty()) {
            ret2.push_back(ret[i]);
        }
    }

    sort(ALL(ret2), compare);
    for (auto& r : ret2) {
        for (int i = 0; i < SIZE(r); i++) {
            cout << r[i] << " ";
        }
        cout << -1 << "\n";
    }
}
