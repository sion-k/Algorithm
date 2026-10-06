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

const int INF = 987654321;

int a, b, c, k;
vector<vector<int>> cache;

int dp(int i, int j) {
    if (j == k) return i == 0 ? 0 : INF;
    if (cache[i][j] != -1) return cache[i][j];

    int min = INF;
    if (j + a <= k) {
        min = ::min(min, 1 + dp((i - 1 + 4) % 4, j + a));
    }

    if (j + b <= k) {
        min = ::min(min, 1 + dp((i + 1) % 4, j + b));
    }

    if (j + c <= k) {
        min = ::min(min, 1 + dp((i + 2) % 4, j + c));
    }

    return cache[i][j] = min;
}

int main() {
    FAST();

    cin >> a >> b >> c >> k;

    cache.resize(4, vector<int>(k + 1, -1));

    int min = dp(0, 0);
    cout << (min == INF ? -1 : min) << "\n";
}
