#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int n;
vector<int> s;
priority_queue<int> pq;

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n;
    int cnt = 0;
    for (int i = 0; i < n; i++) {
        int x, y;
        cin >> x >> y;
        s.push_back(y);
    }
    s.push_back(0);
    for (auto& y : s) {
        while (!pq.empty() && pq.top() > y) {
            pq.pop();
            cnt++;
        }
        if (pq.empty() || pq.top() != y) {
            pq.push(y);
        }
    }
    cout << cnt << "\n";
}
