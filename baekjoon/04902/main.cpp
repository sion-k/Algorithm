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

int t;
vector<vector<int>> b, dp;

bool in_range(int x, int y) {
    return 0 <= x && x < t && 0 <= y && y < t;
}

void dfs(int x, int y) {
    b[x][y] = true;

    if (x % 2 == 0 && in_range(x + 1, y)) {
        dfs(x + 1, y);
    } else {
        if (in_range(x + 1, y - 1) && !b[x + 1][y - 1]) {
            dfs(x + 1, y - 1);
        }

        if (in_range(x + 1, y + 1) && !b[x + 1][y + 1]) {
            dfs(x + 1, y + 1);
        }
    }
}

int sum(int x1, int y1, int x2, int y2) {
    int sum = dp[x2][y2];

    if (x1 - 1 >= 0) {
        sum -= dp[x1 - 1][y2];
    }

    if (y1 - 1 >= 0) {
        sum -= dp[x2][y1 - 1];
    }

    if (x1 - 1 >= 0 && y1 - 1 >= 0) {
        sum += dp[x1 - 1][y1 - 1];
    }

    return sum;
}

int solve(int n) {
    vector<int> a;
    for (int i = 1; i <= n; i++) {
        for (int j = 0; j < 2 * i - 1; j++) {
            int x;
            cin >> x;

            a.push_back(x);
        }
    }

    t = 2 * n - 1;
    b = vector<vector<int>>(2 * n - 1, vector<int>(2 * n - 1));
    dfs(0, n - 1);

    dp = vector<vector<int>>(2 * n - 1, vector<int>(2 * n - 1));
    int k = 0;

    for (int i = 0; i < t; i += 2) {
        for (int j = 0; j < t; j++) {
            if (b[i][j]) {
                dp[i][j] = a[k];
                k++;
                if (in_range(i - 1, j + 1) && b[i - 1][j + 1]) {
                    i--;
                } else if (in_range(i + 1, j + 1) && b[i + 1][j + 1]) {
                    i++;
                }
            }
        }
    }


    for (int i = 0; i < t; i++) {
        for (int j = 0; j < t; j++) {
            if (i - 1 >= 0) {
                dp[i][j] += dp[i - 1][j];
            }

            if (j - 1 >= 0) {
                dp[i][j] += dp[i][j - 1];
            }

            if (i - 1 >= 0 && j - 1 >= 0) {
                dp[i][j] -= dp[i - 1][j - 1];
            }
        }
    }

    int max = -987654321;

    for (int i = 0; i < t; i++) {
        for (int j = 0; j < t; j++) {
            if (b[i][j] && i % 2 == 0) {
                for (int k = 1; k <= n; k++) {
                    int x1 = i, y1 = j - k + 1;
                    int x2 = x1 + 2 * (k - 1), y2 = y1 + 2 * (k - 1);

                    if (in_range(x1, y1) && in_range(x2, y2)) {
                        max = ::max(max, sum(x1, y1, x2, y2));
                    }
                }
            } else if (b[i][j] && i % 2 == 1) {
                for (int k = 1; k <= n; k++) {
                    int x2 = i, y2 = j + k - 1;
                    int x1 = i - 2 * (k - 1), y1 = y2 - 2 * (k - 1);

                    if (in_range(x1, y1) && in_range(x2, y2)) {
                        max = ::max(max, sum(x1, y1, x2, y2));
                    }
                }
            }
        }
    }

    return max;
}

int main() {
    FAST();

    int tc = 1;
    while (true) {
        int n;
        cin >> n;

        if (n == 0) break;
        cout << tc << ". " << solve(n) << "\n";
        tc++;
    }
}
