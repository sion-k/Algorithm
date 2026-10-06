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
vector<int> parent, dist;

void dfs(int here, int prev) {
    for (int there : adj[here]) if (there != prev) {
        parent[there] = here;
        dfs(there, here);
    }
}

bool escape(int here, int prev, int hereDist) {
    int degree = 0;

    for (int there : adj[here]) {
        if (there != prev && dist[there] > hereDist + 1 && escape(there, here, hereDist + 1)) {
            return true;
        }
        degree++;
    }

    return degree == 1;
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

    int a, b, c;
    cin >> a >> b >> c;

    parent.resize(n + 1);
    dfs(a, a);

    dist.resize(n + 1, 987654321);

    int d = 0;
    while (b != a) {
        dist[b] = d;
        d++;
        b = parent[b];
    }

    d = 0;
    while (a != c) {
        dist[c] = d;
        d++;
        c = parent[c];
    }

    cout << (escape(a, a, 0) ? "YES" : "NO") << "\n";
}
