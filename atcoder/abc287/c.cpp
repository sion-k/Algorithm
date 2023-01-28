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

int main() {
    FAST();

    int n, m;
    cin >> n >> m;

    vector<vector<int>> adj(n + 1);
    vector<int> deg(n + 1);

    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;

        adj[u].push_back(v);
        adj[v].push_back(u);

        deg[u]++, deg[v]++;
    }

    queue<int> q;
    vector<int> booked(n + 1);

    q.push(1);
    booked[1] = true;

    while (!q.empty()) {
        int here = q.front();
        q.pop();

        for (int there : adj[here]) if (!booked[there]) {
            q.push(there);
            booked[there] = true;
        }
    }

    bool flag = true;
    for (int i = 1; i <= n; i++) {
        if (!booked[i]) {
            flag = false;
        }
    }

    map<int, int> cnt;
    for (int i = 1; i <= n; i++) {
        cnt[deg[i]]++;
    }

    cout << (flag && cnt[1] == 2 && cnt[2] == n - 2 ? "Yes" : "No") << "\n";
}
