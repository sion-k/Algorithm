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

pair<int, int> dfs(int here, int prev) {
    pair<int, int> ret = make_pair(0, here);
    for (int there : adj[here]) if (there != prev) {
        pair<int, int> cand = dfs(there, here);
        cand.first += 1;
        if (ret < cand) {
            ret = cand;
        }
    }
    return ret;
}

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    int n;
    cin >> n;
    adj = vector<vector<int>>(n + 1);
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    pair<int, int> a = dfs(1, 1);
    pair<int, int> b = dfs(a.second, a.second);
    cout << (b.first + 1) / 2 << "\n";
}
