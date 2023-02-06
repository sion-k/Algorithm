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

    vector<vector<int>> p(1001, vector<int>(1001));
    for (int i = 0; i < n; i++) {
        int y1, x1, y2, x2;
        cin >> y1 >> x1 >> y2 >> x2;

        p[y1][x1]++;
        p[y1][x2]--;
        p[y2][x1]--;
        p[y2][x2]++;
    }

    for (int i = 0; i <= 1000; i++) {
        for (int j = 1; j <= 1000; j++) {
            p[i][j] += p[i][j - 1];
        }
    }

    for (int j = 0; j <= 1000; j++) {
        for (int i = 1; i <= 1000; i++) {
            p[i][j] += p[i - 1][j];
        }
    }

    int cnt = 0;

    for (int i = 0; i <= 1000; i++) {
        for (int j = 0; j <= 1000; j++) {
            if (p[i][j] == k) {
                cnt++;
            }
        }
    }

    cout << cnt << "\n";
}
