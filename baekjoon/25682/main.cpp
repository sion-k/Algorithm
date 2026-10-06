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

int n, m, k;

bool in_range(int y, int x) {
    return 0 <= y && y < n && 0 <= x && x < m;
}

int sum(vector<vector<int>>& dp, int y1, int x1, int y2, int x2) {
    int sum = dp[y2][x2];

    if (in_range(y1 - 1, x2)) {
        sum -= dp[y1 - 1][x2];
    }

    if (in_range(y2, x1 - 1)) {
        sum -= dp[y2][x1 - 1];
    }

    if (in_range(y1 - 1, x1 - 1)) {
        sum += dp[y1 - 1][x1 - 1];
    }

    return sum;
}

int main() {
    FAST();

    cin >> n >> m >> k;

    vector<string> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    vector<vector<int>> b(n, vector<int>(m)), w(n, vector<int>(m));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            b[i][j] = ((i + j) % 2 == 0) ^ (a[i][j] == 'B');
            w[i][j] = ((i + j) % 2 == 0) ^ (a[i][j] == 'W');
        }
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (in_range(i - 1, j)) {
                b[i][j] += b[i - 1][j];
                w[i][j] += w[i - 1][j];
            }

            if (in_range(i, j - 1)) {
                b[i][j] += b[i][j - 1];
                w[i][j] += w[i][j - 1];
            }

            if (in_range(i - 1, j - 1)) {
                b[i][j] -= b[i - 1][j - 1];
                w[i][j] -= w[i - 1][j - 1];
            }
        }
    }

    int min = n * m;
    for (int y2 = 0; y2 < n; y2++) {
        for (int x2 = 0; x2 < m; x2++) {
            int y1 = y2 - k + 1, x1 = x2 - k + 1;

            if (in_range(y1, x1)) {
                min = ::min(min, sum(b, y1, x1, y2, x2));
                min = ::min(min, sum(w, y1, x1, y2, x2));
            }
        }
    }

    cout << min << "\n";
}
