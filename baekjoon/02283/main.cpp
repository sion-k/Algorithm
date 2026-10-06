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

    vector<int> a(1000002);
    for (int i = 0; i < n; i++) {
        int u, v;
        cin >> u >> v;

        a[u]++;
        a[v]--;
    }

    for (int i = 1; i <= 1000000; i++) {
        a[i] += a[i - 1];
    }

    int h = 0, t = 0, s = a[0];
    while (h <= 1000000) {
        while (t + 1 <= 1000000 && s < k) {
            s += a[t + 1];
            t++;
        }

        if (s == k) {
            cout << h << " " << t + 1 << "\n";
            return 0;
        }

        s -= a[h];
        h++;
    }

    cout << 0 << " " << 0 << "\n";
}
