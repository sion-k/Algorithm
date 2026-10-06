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

#define pii pair<int, int>

using namespace std;

bool compare(pii u, pii v) {
    if (u.first == v.first) {
        return u.second > v.second;
    }

    return u.first < v.first;
}

vector<int> a, per;
vector<vector<int>> adj, ch;

void dfs(int here, int prev) {
    for (int there : adj[here]) if (there != prev) {
        ch[here].push_back(there);
        per[there] = here;
        dfs(there, here);
    }
}

const int INF = 987654321;

vector<pii> cache1, cache2;

pii f(int x) {
    if (ch[x].empty()) return { -INF, x };
    if (cache1[x].first != -INF) return cache1[x];

    vector<pii> cand;
    for (int c : ch[x]) {
        cand.emplace_back(a[c], c);

        if (f(c).first != -INF) {
            cand.push_back(f(c));
        }
    }
    assert(!cand.empty());

    cache1[x] = *max_element(ALL(cand), compare);
    cache1[x].first--;

    return cache1[x];
}

pii g(int x) {
    if (!per[x]) return { -INF, x };
    if (cache2[x].first != -INF) return cache2[x];

    vector<pii> cand;
    cand.emplace_back(a[per[x]] + 1, per[x]);

    pii t = g(per[x]);

    if (t.first != -INF) {
        t.first++;
        cand.push_back(t);
    }

    for (int c : ch[per[x]]) if (c != x && f(c).first != -INF) {
        cand.push_back(f(c));
    }

    assert(!cand.empty());

    cache2[x] = *max_element(ALL(cand), compare);
    cache2[x].first -= 2;

    return cache2[x];
}

pii h(int x) {
    vector<pii> cand;
    if (f(x).first != -INF) {
        cand.push_back(f(x));
    }

    if (g(x).first != -INF) {
        cand.push_back(g(x));
    }

    assert(!cand.empty());

    return *max_element(ALL(cand), compare);
}

int main() {
    FAST();

    int n;
    long long k;
    cin >> n >> k;

    a.resize(n + 1);
    for (int i = 1; i <= n; i++) {
        cin >> a[i];
    }

    adj.resize(n + 1);
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;

        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    ch.resize(n + 1);
    per.resize(n + 1);
    dfs(1, 1);

    cache1.resize(n + 1, { -INF, -INF });
    cache2.resize(n + 1, { -INF, -INF });

    // int here = 1;
    // vector<int> visit(n + 1);

    // while (k > 0) {
    //     visit[here] = true;
    //     int there = h(here).second;

    //     if (visit[there]) {
    //         k--;
    //         vector<int> path;

    //         for (int i = there; i != here; i = h(i).second) {
    //             path.push_back(i);
    //         }

    //         path.push_back(here);
    //         here = path[k % SIZE(path)];
    //         break;
    //     } else {
    //         k--;
    //         here = there;
    //     }
    // }

    int here = 1;
    for (int i = 0; i < k; i++) {
        here = h(here).second;
    }

    cout << here << "\n";
}
