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

    int n, k;
    cin >> n >> k;

    vector<vector<int>> p(2 * n - 1, vector<int>(2 * n - 1));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> p[i + j][n - 1 - i + j];
        }
    }

    for (int i = 0; i < 2 * n - 1; i++) {
        for (int j = 0; j < 2 * n - 1; j++) {
            if (i - 1 >= 0) {
                p[i][j] += p[i - 1][j];
            }

            if (j - 1 >= 0) {
                p[i][j] += p[i][j - 1];
            }

            if (i - 1 >= 0 && j - 1 >= 0) {
                p[i][j] -= p[i - 1][j - 1];
            }
        }
    }

    int max = 0;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            int y = i + j, x = n - 1 - i + j;
            int y1 = y - k, x1 = x - k;
            int y2 = y + k, x2 = x + k;

            y1 = ::max(y1, 0), x1 = ::max(x1, 0);
            y2 = ::min(y2, 2 * n - 2), x2 = ::min(x2, 2 * n - 2);

            int sum = p[y2][x2];

            if (y1 - 1 >= 0) {
                sum -= p[y1 - 1][x2];
            }

            if (x1 - 1 >= 0) {
                sum -= p[y2][x1 - 1];
            }

            if (y1 - 1 >= 0 && x1 - 1 >= 0) {
                sum += p[y1 - 1][x1 - 1];
            }

            max = ::max(max, sum);
        }
    }

    cout << max << "\n";
}
