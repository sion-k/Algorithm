#include <bits/stdc++.h>
#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_tuple(x, y, z)                                                     \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", " << (y) \
         << ", " << (z) << ")\n"
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
using namespace std;

vector<vector<int>> adj;
vector<int> visited(2 * 1000 + 1);
vector<int> matched(1000 + 1);

bool matches(int here) {
    if (visited[here]) return false;
    visited[here] = true;
    for (int there : adj[here]) {
        if (matched[there] == 0 || matches(matched[there])) {
            matched[there] = here;
            return true;
        }
    }
    return false;
}

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    int n, m;
    cin >> n >> m;
    for (int here = 0; here <= 2 * n; here++) {
        adj.push_back(vector<int>());
    }
    for (int here = 1; here <= n; here++) {
        int k;
        cin >> k;
        for (int i = 0; i < k; i++) {
            int there;
            cin >> there;
            adj[here].push_back(there);
            adj[n + here].push_back(there);
        }
    }
    int cnt = 0;
    for (int here = 1; here <= 2 * n; here++) {
        fill(ALL(visited), 0);
        if (matches(here)) {
            cnt++;
        }
    }
    cout << cnt << "\n";
}
