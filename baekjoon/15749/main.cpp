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

int n, b;
vector<int> f, s, d;
vector<vector<int>> c;

int dp(int i, int j) {
    if (i == n - 1) {
        return j;
    }
    if (c[i][j] != -1) {
        return c[i][j];
    }

    int min = INF;

    if (j != b - 1) {
        min = ::min(min, dp(i, j + 1));
    }

    for (int k = i + 1; k <= ::min(i + d[j], n - 1); k++) {
        if (f[i] <= s[j] && f[k] <= s[j]) {
            min = ::min(min, dp(k, j));
        }
    }

    return c[i][j] = min;
}

int main() {
    FAST();

    cin >> n >> b;

    f.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> f[i];
    }

    s.resize(n), d.resize(n);
    for (int i = 0; i < b; i++) {
        cin >> s[i] >> d[i];
    }

    c.resize(n, vector<int>(b, -1));

    cout << dp(0, 0) << '\n';
}
