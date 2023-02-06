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

bool inRange(int y, int x, int n) {
    return 0 <= y && y < n && 0 <= x && x < n;
}

int sum(vector<vector<int>>& p, int i, int l, int r, int n) {
    if (!(0 <= i && i < n)) return 0;
    l = ::max(l, 0);
    r = ::min(r, n - 1);

    int sum = p[i][r];

    if (l - 1 >= 0) {
        sum -= p[i][l - 1];
    }

    return sum;
}

int main() {
    FAST();

    int n, m;
    cin >> n >> m;

    vector<vector<int>> a(n, vector<int>(n));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> a[i][j];
        }
    }

    vector<vector<int>> p(n, vector<int>(n));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            p[i][j] = a[i][j];

            if (j - 1 >= 0) {
                p[i][j] += p[i][j - 1];
            }
        }
    }

    int max = 0;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            int sum = ::sum(p, i, j - m, j + m, n);

            for (int k = 0; k < m; k++) {
                sum += ::sum(p, i - m + k, j - k, j + k, n);
                sum += ::sum(p, i + m - k, j - k, j + k, n);
            }

            max = ::max(max, sum);
        }
    }

    cout << max << "\n";
}
