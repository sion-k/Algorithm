#include <bits/stdc++.h>

#define FAST() cin.tie(0)->sync_with_stdio(0)
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())

#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y) cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z) cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", " << (y) << ", " << (z) << ")\n"
#define deb_tuple(adj) \
    cout << "["; \
    for (int __i = 0; __i < SIZE(adj); __i++) { \
        cout << adj[__i]; \
        if (__i != SIZE(adj) - 1) cout << ", "; \
    } \
    cout << "]\n";

using namespace std;

vector<int> a;
vector<set<int>> adj;

bool dfs(int here) {
    if (a.back() != here) {
        return false;
    }
    a.pop_back();

    while (!adj[here].empty()) {
        int there = a.back();
        adj[there].erase(here);

        if (adj[here].count(there) == 0 || !dfs(there)) {
            return false;
        }

        adj[here].erase(there);
    }

    return true;
}

int main() {
    FAST();

    int n;
    cin >> n;

    adj.resize(n + 1);
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;

        adj[u].insert(v);
        adj[v].insert(u);
    }

    a.resize(n + 1);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    reverse(ALL(a));

    cout << dfs(1) << '\n';
}
