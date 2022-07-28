#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define FAST() cin.tie(0)->sync_with_stdio(0)
#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z)                                                   \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", "    \
         << (y) << ", " << (z) << ")\n"
#define deb_tuple(s)                                                           \
    for (int i = 0; i < SIZE(s); i++)                                          \
        cout << s[i] << " \n"[i == SIZE(s) - 1];
using namespace std;

int dist(pair<int, int> u, pair<int, int> v) {
    return abs(u.first - v.first) + abs(u.second - v.second);
}

const int INF = 987654321;

// p[here]에서 가장 가까운 특별한 도시까지 거리
// 특별한 도시가 없다면 INF
int f(int n, vector<pair<int, int>> &p, vector<int> &a, int here) {
    int min = INF;
    for (int there = 0; there < n; there++) if (there != here && a[there]) {
        min = ::min(min, dist(p[here], p[there]));
    }
    return min;
}

int main() {
    FAST();
    int n, t;
    cin >> n >> t;
    vector<int> a(n);
    vector<pair<int, int>> p(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i] >> p[i].first >> p[i].second;
    }
    int m;
    cin >> m;
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        u--, v--;
        int min = dist(p[u], p[v]);
        int warp = t;
        if (!a[u]) {
            warp += f(n, p, a, u);
        }
        if (!a[v]) {
            warp += f(n, p, a, v);
        }
        min = ::min(min, warp);
        cout << min << "\n";
    }
}
