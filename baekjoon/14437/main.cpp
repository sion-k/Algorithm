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

const int MOD = 1e9 + 7;

int sum(vector<int> &a, int start, int end) {
    int sum = a[end];
    if (start - 1 >= 0) {
        sum += MOD;
        sum -= a[start - 1];
    }
    if (sum >= MOD) sum -= MOD;
    return sum;
}

int main() {
    FAST();
    int a;
    cin >> a;
    string s;
    cin >> s;
    int n = SIZE(s);
    vector<vector<int>> dp(2, vector<int>(a + 1));
    for (int j = 0; j <= min(25, a); j++) {
        dp[0][j] = 1;
    }
    for (int i = 1; i < SIZE(s); i++) {
        int here = i & 1, prev = here ^ 1;
        for (int j = 1; j <= a; j++) {
            dp[prev][j] += dp[prev][j - 1];
            if (dp[prev][j] >= MOD) dp[prev][j] -= MOD;
        }
        for (int j = 0; j <= a; j++) {
            dp[here][j] = sum(dp[prev], max(0, j - 25), j);
            if (dp[here][j] >= MOD) dp[here][j] -= MOD;
        }
    }
    cout << dp[(SIZE(s) - 1) & 1][a] << "\n";
}
