#include <bits/stdc++.h>
using namespace std;

int n;
int s[20][2];
int dp[100001];

int main() {
    int c;
    cin >> c >> n;
    for (int i = 0; i < n; i++) {
        cin >> s[i][0] >> s[i][1];
    }
    for (int i = 1; i <= 100000; i++) {
        for (int j = 0; j < n; j++) {
            if (i - s[j][0] >= 0) {
                dp[i] = max(dp[i], s[j][1] + dp[i - s[j][0]]);
            }
        }
    }
    int lo = 0; int hi = 100000;
    while (lo + 1 < hi) {
        int mid = (lo + hi) / 2;
        if (dp[mid] >= c) hi = mid;
        else lo = mid;
    }
    cout << hi << endl;
}
