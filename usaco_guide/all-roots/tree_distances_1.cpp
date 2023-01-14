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
vector<int> cache;

int f(int here, int prev) {
    if (cache[here] != -1) return cache[here];

    int max = 0;
    for (int there : adj[here]) if (there != prev) {
        max = ::max(max, 1 + f(there, here));
    }

    return cache[here] = max;
}

int main() {
    FAST();

    int n;
    cin >> n;

    adj.resize(n + 1);
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;

        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    cache.resize(n + 1, -1);
    f(1, 1);

    queue<int> q;
    q.emplace(1);

    vector<int> g(n + 1, -1);
    g[1] = 0;

    while (!q.empty()) {
        int here = q.front();
        q.pop();

        priority_queue<pair<int, int>> pq;

        for (int there : adj[here]) if (g[there] == -1) {
            pq.emplace(f(there, there), there);
        }

        for (int there : adj[here]) if (g[there] == -1) {
            g[there] = g[here] + 1;

            if (!pq.empty() && pq.top().second != there) {
                g[there] = ::max(g[there], pq.top().first + 2);
            } else if (!pq.empty()) {
                auto temp = pq.top();
                pq.pop();

                if (!pq.empty()) {
                    g[there] = ::max(g[there], pq.top().first + 2);
                }

                pq.push(temp);
            }

            q.push(there);
        }
    }

    for (int i = 1; i <= n; i++) {
        int max = ::max(f(i, i), g[i]);

        cout << max << " \n"[i == n];
    }
}
