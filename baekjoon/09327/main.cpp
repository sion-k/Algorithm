#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define FAST() cin.tie(0)->sync_with_stdio(0)
#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z)                                                   \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", "    \
         << (y) << ", " << (z) << ")\n"
#define deb_tuple(s)                                                           \
    for (int i = 0; i < SIZE(s); i++)                                          \
        cout << s[i] << " \n"[i == SIZE(s) - 1];
using namespace std;

void solve() {
    int n, e;
    cin >> n >> e;
    vector<int> a(n);
    for (auto &x : a) {
        cin >> x;
    }
    vector<int> dp(2000 * n + 1);
    for (int i = 0; i < n; i++) {
        for (int j = 2000 * n; j >= a[i]; j--) {
            dp[j] = max(dp[j], dp[j - a[i]] + 2 * a[i]);
        }
    }
    for (int i = 0; i <= 2000 * n; i++) {
        if (dp[i] >= e) {
            cout << i << "\n";
            return;
        }
    }
    cout << "FULL" << "\n";
}

int main() {
    FAST();
    int tc;
    cin >> tc;
    while (tc--) {
        solve();
    }
}
