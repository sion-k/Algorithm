#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int dp[1000001];

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    int n, m;
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        int w, v;
        cin >> w >> v;
        for (int j = 1000000; j >= w; j--) {
            dp[j] = max(dp[j], v + dp[j - w]);
        }
    }
    pair<long long, long long> ret = make_pair(0, 1);
    int ans = 1;
    for (int i = 0; i < m; i++) {
        long long k;
        cin >> k;
        long long a = ret.first * k;
        long long b = dp[k] * ret.second;
        if (a < b) {
            ret.first = dp[k];
            ret.second = k;
            ans = i + 1;
        }
    }
    cout << ans << "\n";
}
