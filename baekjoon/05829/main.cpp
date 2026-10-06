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

    int n, m, k;
    cin >> n >> m >> k;

    vector<int> l(n), r(n);
    for (int i = 0; i < n; i++) {
        cin >> l[i] >> r[i];
        l[i]--, r[i]--;
    }

    vector<string> a(m);
    for (int i = 0; i < m; i++) {
        cin >> a[i];
    }

    vector<vector<int>> dist(n, vector<int>(m, -1));
    dist[0][0] = 0;

    int i = 0, j = 0, t = 0, c = 0;
    while (t < (long long)m * k) {
        int ni;
        if (a[j] == "L") {
            ni = l[i];
        } else {
            ni = r[i];
        }

        int nj = (j + 1) % m;

        if (dist[ni][nj] == -1) {
            dist[ni][nj] = dist[i][j] + 1;
        } else {
            c = dist[i][j] - dist[ni][nj] + 1;
            i = ni, j = nj;
            t++;
            break;
        }
        i = ni, j = nj;
        t++;
    }

    if (c != 0) {
        int d = ((long long)m * k - t) % c;
        for (int _i = 0; _i < d; _i++) {
            if (a[j] == "L") {
                i = l[i];
            } else {
                i = r[i];
            }

            j = (j + 1) % m;
        }
    }

    cout << i + 1 << '\n';
}
