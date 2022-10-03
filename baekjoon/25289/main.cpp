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

const int AI_MIN = 1, AI_MAX = 100;

int solve(int n, vector<int>& a) {
    int ret = 0;
    for (int d = -AI_MAX + 1; d < AI_MAX; d++) {
        // 공차가 d일때 ai로 끝나는 등차 부분 수열의 최대 길이
        vector<int> dp(AI_MAX + 1);
        for (int i = 0; i < n; i++) {
            if (AI_MIN <= a[i] - d && a[i] - d <= AI_MAX) {
                dp[a[i]] = max(dp[a[i]], 1 + dp[a[i] - d]);
            }
            dp[a[i]] = max(dp[a[i]], 1);
        }
        ret = max(ret, *max_element(ALL(dp)));
    }

    return ret;
}

int main() {
    FAST();
    int n;
    cin >> n;
    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    cout << solve(n, a) << "\n";
}
