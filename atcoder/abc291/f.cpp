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
vector<string> s;
vector<vector<int>> cache;

const int INF = 987654321;

int dp(int here, int dist) {
    if (here == n) return 0;
    if (cache[dist][here] != -1) return cache[dist][here];

    int min = INF;
    for (int there = 1; there <= m; there++) {
        if (there != dist && s[here][there - 1] == '1') {
            min = ::min(min, 1 + dp(here + there, max(0, dist - there)));
        }
    }

    return cache[dist][here] = min;
}

vector<int> bfs() {
    queue<int> q;
    vector<int> d(n + 1, -1);

    q.push(1);
    d[1] = 0;

    while (!q.empty()) {
        int here = q.front();
        q.pop();

        for (int there = 1; there <= m; there++) {
            if (s[here][there - 1] == '1' && d[here + there] == -1) {
                q.push(here + there);
                d[here + there] = d[here] + 1;
            }
        }
    }

    return d;
}

int main() {
    FAST();

    cin >> n >> m;

    s.resize(n + 1);
    for (int i = 1; i <= n; i++) {
        cin >> s[i];
    }

    cache = vector<vector<int>>(m + 1, vector<int>(n + 1, -1));
    vector<int> dist = bfs();

    for (int k = 2; k <= n - 1; k++) {
        int min = INF;
        for (int i = 1; i <= m; i++) {
            if (1 <= k - i && dist[k - i] != -1) {
                min = ::min(min, dist[k - i] + dp(k - i, i));
            }
        }

        cout << (min == INF ? -1 : min) << " \n"[k == n - 1];
    }
}
