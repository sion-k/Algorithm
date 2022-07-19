#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define FAST() cin.tie(0)->sync_with_stdio(0)
#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z)                                                   \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", "    \
         << (y) << ", " << (z) << ")\n"
#define deb_tuple(s)                                                           \
    for (int i = 0; i < SIZE(s); i++)                                          \
        cout << s[i] << " \n"[i == SIZE(s) - 1];
using namespace std;

bool compare(const pair<int, int> &u, const pair<int, int> &v) {
    int a = u.first + u.second, b = v.first + v.second;
    return u.first + u.second < v.first + v.second;
}

bool compare2(const pair<int, int> &u, const pair<int, int> &v) {
    if (u.first == v.first) return u.second > v.second;
    return u.first < v.first;
}

int main() {
    FAST();
    int n;
    cin >> n;
    vector<pair<int, int>> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i].first >> a[i].second;
    }
    sort(ALL(a), compare);
    vector<int> dp(n * 100 + 1);
    for (int i = 0; i < n; i++) {
        for (int j = SIZE(dp) - 1; j >= a[i].first; j--) {
            if (j - a[i].first < a[i].second) {
                dp[j] = max(dp[j], 1 + dp[j - a[i].first]);
            }
        }
    }
    pair<int, int> ret;
    for (int i = 0; i < SIZE(dp); i++) {
        pair<int, int> cand(dp[i], i);
        ret = max(ret, cand, compare2);
    }
    cout << ret.first << " " << ret.second;
}
