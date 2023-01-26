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

vector<int> a, b;
vector<vector<int>> adj;

set<int> dfs(int here, int prev) {
    set<int> ret;
    ret.insert(a[here]);

    for (int there : adj[here]) if (there != prev) {
        auto cand = dfs(there, here);

        if (SIZE(ret) < SIZE(cand)) {
            swap(ret, cand);
        }

        for (auto c : cand) {
            ret.insert(c);
        }
    }

    b[here] = SIZE(ret);
    return ret;
}

int main() {
    FAST();

    int n;
    cin >> n;

    a.resize(n + 1);
    for (int i = 1; i <= n; i++) {
        cin >> a[i];
    }

    adj.resize(n + 1);
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;

        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    b.resize(n + 1);
    dfs(1, 1);

    for (int i = 1; i <= n; i++) {
        cout << b[i] << " \n"[i == n];
    }
}
