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

int sum(vector<vector<int>>& p, int y1, int x1, int y2, int x2) {
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

    return sum;
}

int main() {
    FAST();

    int n, m;
    cin >> n >> m;

    vector<string> a(2 * n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        a[i] += a[i];

        a[i + n] = a[i];
    }

    n = 2 * n, m = 2 * m;

    vector<vector<vector<int>>> p(26, vector<vector<int>>(n, vector<int>(m)));
    for (int k = 0; k < 26; k++) {
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                if (a[y][x] == 'A' + k) {
                    p[k][y][x] = 1;
                }

                if (y - 1 >= 0) {
                    p[k][y][x] += p[k][y - 1][x];
                }
                if (x - 1 >= 0) {
                    p[k][y][x] += p[k][y][x - 1];
                }
                if (y - 1 >= 0 && x - 1 >= 0) {
                    p[k][y][x] -= p[k][y - 1][x - 1];
                }
            }
        }
    }

    for (int k = 0; k < 26; k++) {
        long long cnt = 0;
        for (int y1 = 0; y1 < n; y1++) {
            for (int x1 = 0; x1 < m; x1++) {
                for (int y2 = y1; y2 < n; y2++) {
                    for (int x2 = x1; x2 < m; x2++) {
                        cnt += sum(p[k], y1, x1, y2, x2);
                    }
                }
            }
        }
        cout << cnt << '\n';
    }
}
