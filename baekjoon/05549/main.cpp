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

int n, m;
vector<string> a;
vector<vector<vector<int>>> dp;
vector<char> t = { 'J', 'O', 'I' };

tuple<int, int, int> sum(int x1, int y1, int x2, int y2) {
    vector<int> sum(3);

    for (int k = 0; k < 3; k++) {
        int temp = dp[k][x2][y2];

        if (x1 - 1 >= 0) {
            temp -= dp[k][x1 - 1][y2];
        }

        if (y1 - 1 >= 0) {
            temp -= dp[k][x2][y1 - 1];
        }

        if (x1 - 1 >= 0 && y1 - 1 >= 0) {
            temp += dp[k][x1 - 1][y1 - 1];
        }

        sum[k] = temp;
    }

    return make_tuple(sum[0], sum[1], sum[2]);
}

int main() {
    FAST();

    int k;
    cin >> n >> m >> k;

    a.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    dp.resize(3, vector<vector<int>>(n, vector<int>(m)));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            for (int k = 0; k < 3; k++) {
                dp[k][i][j] = a[i][j] == t[k];

                if (i - 1 >= 0) {
                    dp[k][i][j] += dp[k][i - 1][j];
                }

                if (j - 1 >= 0) {
                    dp[k][i][j] += dp[k][i][j - 1];
                }

                if (i - 1 >= 0 && j - 1 >= 0) {
                    dp[k][i][j] -= dp[k][i - 1][j - 1];
                }
            }
        }
    }

    for (int _i = 0; _i < k; _i++) {
        int x1, y1, x2, y2;
        cin >> x1 >> y1 >> x2 >> y2;
        x1--, y1--, x2--, y2--;

        auto [j, o, i] = sum(x1, y1, x2, y2);

        cout << j << " " << o << " " << i << "\n";
    }
}
