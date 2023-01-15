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
vector<pair<int, int>> a;
vector<vector<int>> cache;

const int INF = 987654321;

int dp(int i, int j) {
    if (i == n) return j == 0 ? 0 : INF;
    if (cache[i][j]) return cache[i][j];

    int min = dp(i + 1, j);

    if (j - a[i].second >= 0) {
        min = ::min(min, a[i].first + dp(i + 1, j - a[i].second));
    }

    return cache[i][j] = min;
}

int main() {
    FAST();

    int m, k;
    cin >> n >> m >> k;

    for (int i = 0; i < n; i++) {
        int x, y;
        cin >> x >> y;
        a.emplace_back(x, y);
    }

    for (int i = 0; i < n; i++) {
        for (int j = 1; j <= 100; j++) {
            a.emplace_back(a[i].first + k * j, a[i].second);
        }
    }

    n = SIZE(a);
    cache = vector<vector<int>>(n, vector<int>(m + 1));

    cout << dp(0, m) << "\n";
}
