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

    int n, m, c;
    cin >> n >> m >> c;

    vector<int> a(n + 1);
    for (int i = 1; i <= n; i++) {
        cin >> a[i];
    }

    vector<vector<int>> adj(n + 1);
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;

        adj[v].push_back(u);
    }

    vector<vector<int>> dp(n + 1, vector<int>(1000, -1));
    dp[1][0] = 0;

    for (int t = 1; t < 1000; t++) {
        for (int i = 1; i <= n; i++) {
            dp[i][t] = dp[i][t - 1];

            for (int j : adj[i]) if (dp[j][t - 1] != -1) {
                dp[i][t] = ::max(dp[i][t], a[i] + dp[j][t - 1]);
            }
        }
    }

    int max = 0;

    for (int t = 0; t < 1000; t++) {
        max = ::max(max, dp[1][t] - c * t * t);
    }

    cout << max << "\n";
}
