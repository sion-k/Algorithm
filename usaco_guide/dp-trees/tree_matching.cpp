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

vector<vector<int>> adj, cache;

int dp(int here, int prev, int pick) {
    if (cache[pick][here] != -1) return cache[pick][here];

    int sum = 0;
    for (int there : adj[here]) if (there != prev) {
        sum += dp(there, here, false);
    }

    int max = sum;
    if (!pick) {
        for (int there : adj[here]) if (there != prev) {
            max = ::max(max, sum - dp(there, here, false) + 1 + dp(there, here, true));
        }
    }

    return cache[pick][here] = max;
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

    cache = vector<vector<int>>(2, vector<int>(n + 1, -1));
    cout << dp(1, 1, 0) << "\n";
}
