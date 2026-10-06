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

int main() {
    FAST();

    int n;
    cin >> n;

    string s;
    cin >> s;

    vector<int> dp(n);
    for (int i = 1; i < n; i++) {
        if (s[i - 1] == '(' && s[i] == ')') {
            dp[i] = 2 + dp[i - 2];
        } else if (s[i] == ')' && dp[i - 1] > 0 &&
            i - dp[i - 1] - 1 >= 0 && s[i - dp[i - 1] - 1] == '(') {
            dp[i] = 2 + dp[i - 1];
            if (i - dp[i - 1] - 2 >= 0) {
                dp[i] += dp[i - dp[i - 1] - 2];
            }
        }
    }

    cout << *max_element(ALL(dp)) << "\n";
}
