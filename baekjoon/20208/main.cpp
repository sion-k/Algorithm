#include <bits/stdc++.h>
using namespace std;

int n, m, h;
deque<pair<int, int>> dq;

const int INF = 987654321;

int dist(int u, int v) {
    return abs(dq[u].first - dq[v].first) + abs(dq[u].second - dq[v].second);
}

int btk(int here, int picked, int health) {
    int ret = dist(here, 0) <= health ? 0 : -INF;
    for (int there = 1; there < (int)dq.size(); there++) {
        if ((picked & (1 << there)) == 0 && dist(here, there) <= health) {
            ret = max(ret, 1 + btk(there, picked | (1 << there), health - dist(here, there) + h));
        }
    }
    return ret;
}

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n >> m >> h;
    for (int y = 0; y < n; y++) {
        for (int x = 0; x < n; x++) {
            int t;
            cin >> t;
            if (t == 1) {
                dq.emplace_front(y, x);
            } else if (t == 2) {
                dq.emplace_back(y, x);
            }
        }
    }
    cout << btk(0, 0, m) << "\n";
}
