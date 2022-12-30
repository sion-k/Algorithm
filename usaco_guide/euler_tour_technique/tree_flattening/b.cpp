#include <bits/stdc++.h>

#define FAST() cin.tie(0)->sync_with_stdio(0)
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())

#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y) cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z) cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", " << (y) << ", " << (z) << ")\n"
#define deb_tuple(s) for (int i = 0; i < SIZE(s); i++) cout << s[i] << " \n"[i == SIZE(s) - 1];

using namespace std;

vector<vector<int>> adj;

pair<int, int> dfs(int here, int prev, vector<int>& dist) {
    pair<int, int> ret(0, here);

    for (int there : adj[here]) if (there != prev) {
        dist[there] = dist[here] + 1;
        pair<int, int> cand = dfs(there, here, dist);
        cand.first++;

        ret = max(ret, cand);
    }

    return ret;
}

int main() {
    FAST();

    int n;
    cin >> n;

    adj = vector<vector<int>>(n + 1);
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;

        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    vector<int> dist1 = vector<int>(n + 1), dist2 = vector<int>(n + 1);
    int p = dfs(1, 1, dist1).second;

    dist1[p] = 0;
    auto [d, q] = dfs(p, p, dist1);

    dfs(q, q, dist2);

    for (int i = 1; i <= n; i++) {
        int max = ::max(dist1[i], dist2[i]);

        cout << (max == d ? d + 1 : d) << "\n";
    }
}
