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

vector<int> booked;
vector<vector<int>> adj;

vector<int> bfs(int start) {
    queue<int> q;
    q.push(start);
    booked[start] = true;

    vector<int> ret;

    while (!q.empty()) {
        int here = q.front();
        q.pop();

        ret.push_back(here);

        for (int there : adj[here]) {
            if (!booked[there]) {
                q.push(there);
                booked[there] = true;
            }
        }
    }

    return ret;
}

int main() {
    FAST();

    int n, m;
    cin >> n >> m;

    adj.resize(n + 1);
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;

        adj[u].emplace_back(v);
        adj[v].emplace_back(u);
    }

    booked.resize(n + 1);

    bool flag = true;

    for (int start = 1; start <= n; start++) {
        if (!booked[start]) {
            vector<int> a = bfs(start);

            int edge = 0;
            for (auto x : a) {
                edge += adj[x].size();
            }

            if (SIZE(a) != edge / 2) {
                flag = false;
            }
        }
    }

    cout << (flag ? "Yes" : "No") << "\n";
}
