#include <bits/stdc++.h>

#define FAST() cin.tie(0)->sync_with_stdio(0)
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())

#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y) cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z) cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", " << (y) << ", " << (z) << ")\n"
#define deb_tuple(s) for (int i = 0; i < SIZE(s); i++) cout << s[i] << " \n"[i == SIZE(s) - 1];

using namespace std;

int main() {
    FAST();

    int n, q;
    cin >> n >> q;

    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    int m = log2(n);

    vector<vector<int>> dp(m + 1, vector<int>(n));
    for (int i = 0; i < n; i++) {
        dp[0][i] = a[i];
    }

    for (int k = 1; k <= m; k++) {
        for (int i = 0; i <= n - (1 << k); i++) {
            dp[k][i] = min(dp[k - 1][i], dp[k - 1][i + (1 << (k - 1))]);
        }
    }

    // [u, v)
    for (int i = 0; i < q; i++) {
        int u, v;
        cin >> u >> v;

        int k = log2(v - u);

        int min = ::min(dp[k][u], dp[k][v - (1 << k)]);

        cout << min << "\n";
    }
}
