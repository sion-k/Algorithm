#include <bits/stdc++.h>
#define deb(x) cout << #x << " : " << x << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << x << ", " << y << ")\n"
#define deb_tuple(x, y, z)                                                     \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << x << ", " << y \
         << ", " << z << ")\n"
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int n;
vector<pair<int, int>> s;

bool compare(pair<int, int> &u, pair<int, int> v) {
    return u.second < v.second;
}

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n;
    for (int i = 0; i < n; i++) {
        int u, v;
        cin >> u >> v;
        if (u > v) {
            swap(u, v);
        }
        s.emplace_back(u, v);
    }
    sort(ALL(s), compare);
    int d;
    cin >> d;
    int max = 0;
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    for (auto &x : s) {
        if (x.second - x.first > d) continue;
        while (!pq.empty() && x.second - pq.top().first > d) {
            pq.pop();
        }
        pq.push(x);
        max = std::max(max, (int)pq.size());
    }
    cout << max << "\n";
}
