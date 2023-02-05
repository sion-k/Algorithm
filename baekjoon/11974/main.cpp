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

    int n;
    cin >> n;

    vector<long long> p(n);
    vector<int> r(7, -1);
    for (int i = 0; i < n; i++) {
        cin >> p[i];

        if (i - 1 >= 0) {
            p[i] += p[i - 1];
        }

        if (r[p[i] % 7] == -1) {
            r[p[i] % 7] = i;
        }
    }

    int max = 0;

    for (int i = 0; i < n; i++) {
        int j = r[p[i] % 7];

        if (p[i] % 7 == 0) {
            max = ::max(max, i + 1);
        } else if (j != -1 && j < i) {
            max = ::max(max, i - j);
        }
    }

    cout << max << "\n";
}
