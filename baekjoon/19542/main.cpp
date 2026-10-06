#include <bits/stdc++.h>
#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_tuple(x, y, z)                                                     \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", "    \
         << (y) << ", " << (z) << ")\n"
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
#define OPEN(t) freopen("data.txt", (t), stdin)
using namespace std;

vector<vector<int>> adj;
vector<int> leaf_dist;

int dfs(int here, int prev) {
    int max = 0;
    for (int there : adj[here]) if (there != prev) {
        max = ::max(max, 1 + dfs(there, here));
    }
    return leaf_dist[here] = max;
}

int ret = 0;

void dfs2(int here, int prev, int d) {
    for (int there : adj[here]) if (there != prev && leaf_dist[there] >= d) {
        ret += 2;
        dfs2(there, here, d);
    }
}

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    int n, s, d;
    cin >> n >> s >> d;
    adj = vector<vector<int>>(n + 1, vector<int>());
    leaf_dist = vector<int>(n + 1, -1);
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    dfs(s, s);
    dfs2(s, s, d);
    cout << ret << "\n";
}
