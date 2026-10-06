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

    vector<int> p(n + 1);
    for (int i = 1; i <= n; i++) {
        p[i] = i;
    }

    k = n - k;
    for (int i = k - 1; i >= 1; i--) {
        swap(p[i], p[i + 1]);
    }

    int c = 0;
    for (int i = 1; i <= n; i++) {
        if (gcd(i, p[i]) == 1) {
            c++;
        }
    }

    if (c != k) {
        cout << "Impossible" << '\n';
    } else {
        for (int i = 1; i <= n; i++) {
            cout << p[i] << " \n"[i == n];
        }
    }
}
