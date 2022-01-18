#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int n, m;
long long k;
vector<tuple<int, int, int>> s;
int parent[100001];
int height[100001];

int find(int here) {
    if (parent[here] == here) return here;
    return parent[here] = find(parent[here]);
}

void merge(int u, int v) {
    u = find(u); v = find(v);
    if (u == v) return;
    if (height[u] < height[v]) swap(u, v);
    parent[v] = u;
    if (height[u] == height[v]) height[u]++;
}

void solve() {
    for (int i = 1; i <= n; i++) {
        parent[i] = i;
    }
    int cnt = 0; long long sum = 0;
    sort(ALL(s));
    for (auto& x : s) {
        int u = get<1>(x);
        int v = get<2>(x);
        int w = get<0>(x);
        u = find(u); v = find(v);
        if (u != v) {
            cnt++;
            sum += w;
            merge(u, v);
        }
    }
    cout << (cnt == n - 1 ? k - sum : -1) << "\n";
}

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n >> m;
    for (int i = 0; i < m; i++) {
        int u, v, w;
        cin >> u >> v >> w;
        s.emplace_back(w, u, v);
        s.emplace_back(w, v, u);
        k += w;
    }
    solve();
}
