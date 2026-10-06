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

vector<vector<pair<int, int>>> adj;
vector<long long> a, b, c;

void dfs1(int here, int prev) {
    for (auto [there, cost] : adj[here]) if (there != prev) {
        dfs1(there, here);
        a[here] += a[there];
        b[here] += b[there] + cost * a[there];
    }
}

void dfs2(int here, int prev) {
    c[here] = b[here];
    for (auto [there, cost] : adj[here]) if (there != prev) {
        a[here] -= a[there];
        b[here] -= b[there];
        b[here] -= cost * a[there];
        a[there] += a[here];
        b[there] += b[here];
        b[there] += cost * a[here];
        dfs2(there, here);
        a[there] -= a[here];
        b[there] -= b[here];
        b[there] -= cost * a[here];
        a[here] += a[there];
        b[here] += b[there];
        b[here] += cost * a[there];
    }
}

int main() {
    FAST();

    int n;
    cin >> n;

    adj.resize(n + 1);
    for (int i = 0; i < n - 1; i++) {
        int u, v, w;
        cin >> u >> v >> w;

        adj[u].emplace_back(v, w);
        adj[v].emplace_back(u, w);
    }

    a.resize(n + 1, 1);
    b.resize(n + 1);
    dfs1(1, 1);

    c.resize(n + 1);
    dfs2(1, 1);

    for (int i = 1; i <= n; i++) {
        cout << c[i] << " \n";
    }
}
