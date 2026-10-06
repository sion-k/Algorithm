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

int n;
vector<int> a, b;
vector<vector<int>> c;

const int MOD = 998244353;

int dp(int here, int flip) {
    if (here == n) return 1;
    if (c[flip][here] != -1) return c[flip][here];

    int sum = 0, prev = 0;
    if (here - 1 >= 0 && flip) {
        prev = b[here - 1];
    } else if (here - 1 >= 0) {
        prev = a[here - 1];
    }

    if (a[here] != prev) {
        sum = (sum + dp(here + 1, 0)) % MOD;
    }

    if (b[here] != prev) {
        sum = (sum + dp(here + 1, 1)) % MOD;
    }

    return c[flip][here] = sum;
}

int main() {
    FAST();

    cin >> n;

    a.resize(n);
    b.resize(n);

    for (int i = 0; i < n; i++) {
        cin >> a[i] >> b[i];
    }

    c = vector<vector<int>>(2, vector<int>(n, -1));

    cout << dp(0, 0) << "\n";
}
