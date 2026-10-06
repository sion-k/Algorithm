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

int n, m;
vector<int> l;

tuple<int, int, int> move(tuple<int, int, int> a) {
    auto [s, e, d] = a;

    if (d == 0) {
        s++, e++;
        if (e == m) {
            d = 1;
        }
    } else {
        s--, e--;
        if (s == 0) {
            d = 0;
        }
    }

    return make_tuple(s, e, d);
}

int overlap(tuple<int, int, int> a, tuple<int, int, int> b) {
    auto [s1, e1, d1] = a;
    auto [s2, e2, d2] = b;

    if (e2 < s1 || e1 < s2) {
        return false;
    }

    return true;
}

tuple<int, int, int> move_init(int t, int k, tuple<int, int, int> a) {
    if (l[k] == m) {
        return a;
    }

    for (int i = 0; i < t % (2 * (m - l[k])); i++) {
        a = move(a);
    }

    return a;
}

int cross(int t, int k, tuple<int, int, int> a, tuple<int, int, int> b) {
    a = move_init(t, k, a);
    b = move_init(t, k + 1, b);

    while (!overlap(a, b)) {
        a = move(a), b = move(b);
        t++;
    }

    return t;
}

int main() {
    FAST();

    cin >> n >> m;
    l.resize(n);

    vector<tuple<int, int, int>> a(n);
    for (int i = 0; i < n; i++) {
        int d;
        cin >> l[i] >> d;

        if (d == 0) {
            a[i] = make_tuple(0, l[i], d);
        } else {
            a[i] = make_tuple(m - l[i], m, d);
        }
    }

    int t = 0;
    for (int i = 0; i < n - 1; i++) {
        t = cross(t, i, a[i], a[i + 1]);
    }

    cout << t << '\n';
}
