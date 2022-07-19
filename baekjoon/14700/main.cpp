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
int n, m;
vector<vector<int>> cache;

bool on(int mask, int i) {
    return mask & (1 << i);
}

bool check(int mask) {
    return !(on(mask, 0) && on(mask, m - 1) && on(mask, m));
}

int clean(int mask) {
    return mask & ((1 << (m + 1)) - 1);
}

int dp(int here, int mask) {
    if (here == n * m) return 1;
    mask = clean(mask);
    if (cache[here][mask]) return cache[here][mask];
    int sum = dp(here + 1, mask << 1);
    if ((here % m == 0) || check(mask)) {
        sum += dp(here + 1, (mask << 1) | 1);
    }
    if (sum >= MOD) sum -= MOD;
    return cache[here][mask] = sum;
}

int main() {
    FAST();
    cin >> n >> m;
    if (n < m) swap(n, m);
    cache = vector<vector<int>>(n * m, vector<int>(1 << (m + 1)));
    cout << dp(0, 0) << "\n";
}
