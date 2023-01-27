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

    int n, m;
    long long k;
    cin >> n >> m >> k;

    vector<vector<long long>> a(n, vector<long long>(m));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> a[i][j];
        }
    }

    vector<vector<vector<long long>>> b(n, vector<vector<long long>>(m));

    int t = ((n + m - 2) + 1) / 2;
    for (int s = 0; s < (1 << t); s++) {
        int c = __builtin_popcount(s);

        if (!(t - n < c && c < m)) {
            continue;
        }

        int y = 0, x = 0;
        long long sum = 0;

        for (int i = 0; i < t; i++) {
            sum ^= a[y][x];

            if (s & (1 << i)) {
                x++;
            } else {
                y++;
            }
        }

        sum ^= a[y][x];
        b[y][x].push_back(sum);
    }

    for (int y = 0; y < n; y++) {
        for (int x = 0; x < m; x++) {
            sort(ALL(b[y][x]));
        }
    }

    long long cnt = 0;
    t = (n + m - 2) / 2;
    for (int s = 0; s < (1 << t); s++) {
        int c = __builtin_popcount(s);

        if (!(t - n < c && c < m)) {
            continue;
        }

        int y = n - 1, x = m - 1;
        long long sum = 0;

        for (int i = 0; i < t; i++) {
            sum ^= a[y][x];

            if (s & (1 << i)) {
                x--;
            } else {
                y--;
            }
        }

        auto start = lower_bound(ALL(b[y][x]), k ^ sum);
        auto end = upper_bound(ALL(b[y][x]), k ^ sum);

        cnt += end - start;
    }

    cout << cnt << "\n";
}
