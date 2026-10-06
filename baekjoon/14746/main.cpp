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

const int INF = 987654321;

int n, m;
int c1, c2;
vector<int> p, q;

int main() {
    FAST();

    cin >> n >> m;
    cin >> c1 >> c2;

    p.resize(n), q.resize(m);
    for (int i = 0; i < n; i++) {
        cin >> p[i];
    }

    for (int i = 0; i < m; i++) {
        cin >> q[i];
    }

    sort(ALL(p)), sort(ALL(q));

    int min = INF;
    int h = 0, t = 0;
    while (h < n) {
        while (t < m && p[h] > q[t]) {
            t++;
        }

        if (t < m) {
            min = ::min(min, abs(p[h] - q[t]));
        }

        if (t - 1 >= 0) {
            min = ::min(min, abs(p[h] - q[t - 1]));
        }

        h++;
    }

    int cnt = 0;
    h = 0, t = 0;
    while (h < n) {
        while (t < m && p[h] > q[t]) {
            t++;
        }

        if (t < m && abs(p[h] - q[t]) == min) {
            cnt++;
        }

        if (t - 1 >= 0 && abs(p[h] - q[t - 1]) == min) {
            cnt++;
        }

        h++;
    }

    cout << min + abs(c1 - c2) << ' ' << cnt << '\n';
}
