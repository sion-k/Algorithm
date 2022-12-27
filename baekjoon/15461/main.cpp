#include <bits/stdc++.h>

#define FAST() cin.tie(0)->sync_with_stdio(0)
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())

#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y) cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z) cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", " << (y) << ", " << (z) << ")\n"
#define deb_tuple(s) for (int i = 0; i < SIZE(s); i++) cout << s[i] << " \n"[i == SIZE(s) - 1];

using namespace std;

int main() {
    FAST();

    int n, g;
    cin >> n >> g;

    vector<tuple<int, int, int>> a;
    map<int, int> m, c;
    multiset<int> ms;
    for (int i = 0; i < n; i++) {
        int t, x, d;
        cin >> t >> x >> d;

        a.emplace_back(t, x, d);

        if (!m[x]) {
            ms.insert(g);
        }

        m[x] = g;
        c[g]++;
    }

    sort(ALL(a));

    int cnt = 0;
    for (int i = 0; i < n; i++) {
        auto [t, x, d] = a[i];

        bool f1, f2;
        int c1, c2;
        f1 = *ms.rbegin() == m[x];

        ms.erase(ms.find(m[x]));
        c1 = c[m[x]];
        c[m[x]]--;

        m[x] += d;
        f2 = ms.empty() || *ms.rbegin() <= m[x];

        ms.insert(m[x]);
        c[m[x]]++;
        c2 = c[m[x]];

        cnt += f1 ^ f2;

        if (f1 && f2 && !(c1 == 1 && c2 == 1)) {
            cnt++;
        }
    }

    cout << cnt << "\n";
}

