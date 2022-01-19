#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int n;
int s[1000000][2];
int suffix_max[1000001];
int suffix_min[1000001];

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> s[i][0] >> s[i][1];
    }
    // suffix_max, suffix_min initialization
    suffix_max[n] = 0;
    suffix_min[n] = 1e9 + 1;
    for (int i = n - 1; i >= 0; i--) {
        int u = s[i][0] == 0 ? 0 : s[i][0];
        int v = s[i][1] == 0 ? 1e9 + 1 : s[i][1];
        suffix_max[i] = max(suffix_max[i + 1], u);
        suffix_min[i] = min(suffix_min[i + 1], v);
    }
    int ret = -1;
    // prfix_max, prefix_min initialization
    int prefix_max = 0;
    int prefix_min = 1e9 + 1;
    for (int i = 0; i < n - 1; i++) {
        int u = s[i][0] == 0 ? 1e9 + 1 : s[i][0];
        int v = s[i][1] == 0 ? 0 : s[i][1];
        prefix_min = min(prefix_min, u);
        prefix_max = max(prefix_max, v);
        if (prefix_min > suffix_max[i + 1] && prefix_max < suffix_min[i + 1]) {  
            ret = i + 1;
        }
    }
    cout << ret << "\n";
}
