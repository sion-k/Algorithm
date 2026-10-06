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

    vector<long long> c(n);
    for (int i = 0; i < n; i++) {
        cin >> c[i];
    }

    int p;
    cin >> p;
    vector<vector<pair<int, int>>> adj(n);
    for (int i = 0; i < p; i++) {
        int u, v, w;
        cin >> u >> v >> w;
        u--, v--;

        adj[u].emplace_back(v, w);
    }

    priority_queue<pair<long long, int>, vector<pair<long long, int>>, greater<pair<long long, int>>> pq;
    vector<long long> d = c;
    for (int u = 0; u < n; u++) {
        for (auto [v, w] : adj[u]) {
            d[v] += w;
        }
    }

    for (int i = 0; i < n; i++) {
        pq.emplace(d[i], i);
    }

    vector<int> b(n);
    long long max = pq.top().first;
    int k = 0;

    while (!pq.empty()) {
        auto [x, u] = pq.top();
        pq.pop();

        if (d[u] < x) {
            continue;
        }

        b[u] = true;

        max = ::max(max, x);
        k++;

        if (k == m) {
            cout << max << '\n';
            return 0;
        }

        for (auto [v, w] : adj[u]) if (!b[v]) {
            d[v] = d[v] - w;
            pq.emplace(d[v], v);
        }
    }
}
