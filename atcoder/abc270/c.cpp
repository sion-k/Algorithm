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

int main() {
    FAST();
    int n, x, y;
    cin >> n >> x >> y;

    vector<vector<int>> adj(n + 1);

    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    vector<int> prev(n + 1);
    queue<int> q;

    q.emplace(x);
    prev[x] = x;

    while (!q.empty()) {
        int here = q.front();
        q.pop();

        for (int there : adj[here]) if (prev[there] == 0) {
            q.emplace(there);
            prev[there] = here;
        }
    }

    vector<int> path;
    while (x != y) {
        path.emplace_back(y);
        y = prev[y];
    }
    path.emplace_back(x);

    reverse(ALL(path));

    for (auto& p : path) {
        cout << p << " ";
    }
}
