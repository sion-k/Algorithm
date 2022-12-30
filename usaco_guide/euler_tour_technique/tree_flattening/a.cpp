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

pair<int, int> dfs(int here, int prev) {
    pair<int, int> ret(0, here);

    for (int there : adj[here]) if (there != prev) {
        pair<int, int> cand = dfs(there, here);
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

    pair<int, int> p = dfs(1, 1);

    cout << 3 * dfs(p.second, p.second).first << "\n";
}
