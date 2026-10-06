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

vector<vector<int>> adj;
vector<int> d;

bool bfs(int start) {
    queue<int> q;
    q.push(start);
    d[start] = 0;

    while (!q.empty()) {
        int here = q.front();
        q.pop();

        for (int there : adj[here]) {
            if (d[there] == -1) {
                q.push(there);
                d[there] = d[here] + 1;
            } else if (!((d[here] % 2 == 0) ^ (d[there] % 2 == 0))) {
                return false;
            }
        }
    }

    return true;
}

int main() {
    FAST();

    int n, m;
    cin >> n >> m;

    adj.resize(n + 1);
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;

        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    d.resize(n + 1, -1);


    bool flag = true;
    for (int here = 1; here <= n; here++) if (d[here] == -1) {
        flag &= bfs(here);
    }

    cout << flag << '\n';
}
