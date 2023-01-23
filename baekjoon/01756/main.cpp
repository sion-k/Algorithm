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

    int d, n;
    cin >> d >> n;

    vector<int> a(d);
    for (int i = 0; i < d; i++) {
        cin >> a[i];
    }

    vector<int> p(d);
    for (int i = 0; i < d; i++) {
        p[i] = a[i];

        if (i - 1 >= 0) {
            p[i] = min(p[i], p[i - 1]);
        }
    }

    vector<int> b(n);
    for (int i = 0; i < n; i++) {
        cin >> b[i];
    }

    int j = -1, k = 0;
    for (int i = d - 1; i >= 0; i--) {
        if (k < n && p[i] >= b[k]) {
            j = i;
            k++;
        }
    }

    cout << (k == n ? j + 1 : 0) << "\n";
}
