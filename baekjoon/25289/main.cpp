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

const int AI_MAX = 100;

int solve(int n, vector<int>& a) {
    // 마지막 항이 i이고, 공차가 j인 등차 부분수열의 최대 길이
    vector<vector<int>> dp(AI_MAX + 1, vector<int>(AI_MAX + 1));

    for (int i = 0; i < n; i++) {
        for (int d = a[i] - 1; d >= 0; d--) {
            dp[a[i]][d] = max(dp[a[i]][d], 1 + dp[a[i] - d][d]);
        }
        for (int d = 0; d <= AI_MAX; d++) {
            dp[a[i]][d] = max(dp[a[i]][d], 1);
        }
    }

    int max = 0;
    for (int ai = 1; ai <= 100; ai++) {
        for (int d = 0; d < 100; d++) {
            max = ::max(max, dp[ai][d]);
        }
    }
    return max;
}

int main() {
    FAST();
    int n;
    cin >> n;
    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    int max = solve(n, a);
    reverse(ALL(a));
    max = ::max(max, solve(n, a));
    cout << max << "\n";
}
