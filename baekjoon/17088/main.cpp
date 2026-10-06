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

int f(int n, vector<int> a) {
    int d = a[1] - a[0];

    int ret = 0;
    for (int i = 2; i < n; i++) {
        if (a[i - 1] + d < a[i] - 1 || a[i] + 1 < a[i - 1] + d) {
            return INF;
        }

        if (a[i - 1] + d != a[i]) {
            ret++;
        }

        a[i] = a[i - 1] + d;
    }

    return ret;
}

int main() {
    FAST();

    int n;
    cin >> n;

    vector<int> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    if (n == 1) {
        cout << 0 << '\n';
    } else {
        int min = INF;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                a[0] += i;
                a[1] += j;
                min = ::min(min, abs(i) + abs(j) + f(n, a));
                a[0] -= i;
                a[1] -= j;
            }
        }

        cout << (min == INF ? -1 : min) << '\n';
    }
}
