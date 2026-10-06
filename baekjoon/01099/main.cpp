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

string s;
int n, m;
vector<string> a;
vector<int> c;

const int INF = 987654321;

bool compare(string u, string v) {
    sort(ALL(u));
    sort(ALL(v));

    return u == v;
}

int cost(string& u, string& v) {
    int ret = 0;

    for (int i = 0; i < SIZE(u); i++) {
        if (u[i] != v[i]) {
            ret++;
        }
    }

    return ret;
}

int dp(int i) {
    if (i == m) return 0;
    if (c[i] != -1) return c[i];

    int min = INF;

    for (int j = 0; j < n; j++) {
        if (i + SIZE(a[j]) <= m) {
            string u = a[j], v = s.substr(i, SIZE(a[j]));
            if (compare(u, v)) {
                min = ::min(min, cost(u, v) + dp(i + SIZE(a[j])));
            }
        }
    }

    return c[i] = min;
}

int main() {
    FAST();

    cin >> s;
    cin >> n;

    a.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    m = SIZE(s);
    c.resize(m, -1);

    int ret = dp(0);
    cout << (ret == INF ? -1 : ret) << "\n";
}
