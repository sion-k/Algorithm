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

vector<vector<int>> cache;
const int MOD = 998244353;

int dp(int n, int r) {
    if (n == r || r == 0) return 1;
    if (cache[n][r]) return cache[n][r];
    return cache[n][r] = ((long long)dp(n - 1, r - 1) + dp(n - 1, r)) % MOD;
}

void solve() {
    int n;
    cin >> n;
    vector<int> a(n + 1), b(n + 1);
    a[2] = 1;
    for (int x = 4; x <= n; x++) {
        a[x] = (dp(x - 1, x / 2) + b[x - 2]) % MOD;
        b[x] = (dp(x - 2, x / 2) + a[x - 2]) % MOD;
    }
    cout << a[n] << " " << b[n] << " " << "1" << "\n";
}

int main() {
    FAST();

    cache = vector<vector<int>>(61, vector<int>(61));

    int tc;
    cin >> tc;
    while (tc--) {
        solve();
    }
}
