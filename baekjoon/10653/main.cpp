#include <bits/stdc++.h>
using namespace std;

int n;
int s[500][2];
int cache[500][500];

int dist(int u, int v) {
    return abs(s[u][0] - s[v][0]) + abs(s[u][1] - s[v][1]);
}

int dp(int here, int skip) {
    if (here == n - 1) return 0;
    if (cache[here][skip] != -1) return cache[here][skip];
    int ret = dist(here, here + 1) + dp(here + 1, skip);
    for (int jump = 1; jump <= skip && here + jump + 1 < n; jump++) {
        int there = here + jump + 1;
        ret = min(ret, dist(here, there) + dp(there, skip - jump));
    }
    return cache[here][skip] = ret;
}

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    int k;
    cin >> n >> k;
    for (int i = 0; i < n; i++) {
        cin >> s[i][0] >> s[i][1];
    }
    memset(cache, -1, sizeof(cache));
    cout << dp(0, k);
}
