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

int dp(int m, vector<int>& a) {
    vector<int> dp(m);

    for (int i = 0; i < m; i++) {
        dp[i] = a[i];
        if (i - 1 >= 0) {
            dp[i] = max(dp[i], dp[i - 1] + a[i]);
        }
    }

    return *max_element(ALL(dp));
}

int bfc(vector<vector<int>>& a, int y1, int y2, int m) {
    vector<int> b(m);

    for (int x = 0; x < m; x++) {
        for (int y = y1; y <= y2; y++) {
            b[x] += a[y][x];
        }
    }

    return dp(m, b);
}

int main() {
    FAST();

    int n, m;
    cin >> n >> m;

    vector<vector<int>> a(n, vector<int>(m));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> a[i][j];
        }
    }

    int max = INT_MIN;

    for (int y1 = 0; y1 < n; y1++) {
        for (int y2 = y1; y2 < n; y2++) {
            max = ::max(max, bfc(a, y1, y2, m));
        }
    }

    cout << max << "\n";
}
