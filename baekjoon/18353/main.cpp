#include <bits/stdc++.h>
#define deb(x) cout << #x << " : " << x << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << x << ", " << y << ")\n"
#define deb_tuple(x, y, z)                                                     \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << x << ", " << y \
         << ", " << z << ")\n"
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int n;
int s[2000];
int dp[2000];

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> s[i];
        for (int j = 0; j < i; j++) {
            if (s[j] > s[i]) {
                dp[i] = max(dp[i], 1 + dp[j]);
            }
        }
    }
    cout << n - (*max_element(dp, dp + n) + 1) << "\n";
}
