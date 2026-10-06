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

bool compare(tuple<int, int, int> u, tuple<int, int, int> v) {
    return get<1>(u) + get<2>(u) < get<1>(v) + get<2>(v);
}

int main() {
    FAST();

    int n, m;
    cin >> n >> m;

    vector<tuple<int, int, int>> a;
    for (int i = 0; i < n; i++) {
        int h, w, p;
        cin >> h >> w >> p;

        a.emplace_back(h, w, p);
    }

    sort(ALL(a), compare);
    reverse(ALL(a));

    int max = -1;

    for (int b = 1; b < (1 << n); b++) {
        int sum = 0;
        for (int i = 0; i < n; i++) if (b & (1 << i)) {
            sum += get<0>(a[i]);
        }

        if (sum < m) {
            continue;
        }

        vector<int> c;
        for (int i = 0; i < n; i++) if (b & (1 << i)) {
            for (int j = 0; j < SIZE(c); j++) {
                c[j] -= get<1>(a[i]);
            }

            c.push_back(get<2>(a[i]));
        }

        int min = *min_element(ALL(c));
        if (min < 0) {
            continue;
        }

        max = ::max(max, min);
    }

    if (max == -1) {
        cout << "Mark is too tall" << "\n";
    } else {
        cout << max << "\n";
    }
}
