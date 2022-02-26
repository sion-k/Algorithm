#include <bits/stdc++.h>
#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_tuple(x, y, z)                                                     \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", " << (y) \
         << ", " << (z) << ")\n"
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
using namespace std;

const int MOD = 998244353;

void solve() {
    int n;
    cin >> n;
    vector<int> s(n);
    for (int i = 0; i < n; i++) {
        cin >> s[i];
    }
    vector<long long> filled(n + 1);
    vector<long long> jumped(n + 1);
    vector<long long> fallen(n + 1);
    for (auto& x : s) {
        filled[x] = 2 * filled[x];
        if (x - 1 >= 0) {
            filled[x] += filled[x - 1];
        } else {
            filled[x] += 1;
        }
        jumped[x] = 2 * jumped[x];
        if (x - 2 >= 0) {
            jumped[x] += filled[x - 2] + fallen[x - 2];
        } else if (x == 1) {
            jumped[x] += 1;
        }
        fallen[x] = 2 * fallen[x];
        if (x + 2 <= n) {
            fallen[x] += jumped[x + 2];
        }
        filled[x] %= MOD;
        jumped[x] %= MOD;
        fallen[x] %= MOD;
    }
    long long ans = 0;
    for (int i = 0; i <= n; i++) {
        ans += filled[i] + jumped[i] + fallen[i];
        ans %= MOD;
    }
    cout << ans << "\n";
}

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    int tc;
    cin >> tc;
    while (tc--) {
        solve();
    }
}
