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

    int n, k;
    cin >> n >> k;

    vector<int> a(n);
    for (int i = 0; i < n; i++) {
        string s;
        cin >> s;

        for (int j = 0; j < k; j++) {
            a[i] |= ((s[j] == '1') << j);
        }
    }

    int u, v;
    cin >> u >> v;
    u--, v--;

    queue<int> q;
    set<int> s, b;
    map<int, int> p, c;

    q.emplace(a[u]);

    for (int i = 0; i < n; i++) {
        s.insert(a[i]);
        c[a[i]] = i;
    }

    b.insert(a[u]);

    while (!q.empty()) {
        int here = q.front();
        q.pop();

        for (int i = 0; i < k; i++) {
            int there = here ^ (1 << i);

            if (s.count(there) && !b.count(there)) {
                q.push(there);
                b.insert(there);
                p[there] = here;
            }
        }
    }

    if (!b.count(a[v])) {
        cout << -1 << "\n";
        return 0;
    }

    vector<int> path;

    int here = a[v];
    while (p.count(here) && here != p[here]) {
        path.push_back(c[here]);

        here = p[here];
    }

    path.push_back(u);

    reverse(ALL(path));

    for (int i = 0; i < SIZE(path); i++) {
        cout << path[i] + 1 << " \n"[i == SIZE(path) - 1];
    }
}
