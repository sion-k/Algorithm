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

int n, k;
vector<int> a;
vector<vector<vector<int>>> cache;

int dp(int i, int j, int k) {
    if (i == n) return 0;
    if (cache[j][k][i] != -1) return cache[j][k][i];

    int max = (a[i] == j) + dp(i + 1, j, k);

    if (k > 0) {
        for (int t = 0; t < 3; t++) {
            max = ::max(max, (a[i] == t) + dp(i + 1, t, k - 1));
        }
    }

    return cache[j][k][i] = max;
}

int main() {
    FAST();

    cin >> n >> k;
    a.resize(n);

    for (int i = 0; i < n; i++) {
        string s;
        cin >> s;

        if (s[0] == 'H') {
            a[i] = 0;
        } else if (s[0] == 'P') {
            a[i] = 1;
        } else {
            a[i] = 2;
        }
    }

    cache = vector<vector<vector<int>>>(3, vector<vector<int>>(k + 1, vector<int>(n, -1)));

    int max = 0;
    for (int t = 0; t < 3; t++) {
        max = ::max(max, dp(0, t, k));
    }

    cout << max << "\n";
}
