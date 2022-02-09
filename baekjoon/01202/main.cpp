#include <bits/stdc++.h>
#define deb(x) cout << #x << " : " << x << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << x << ", " << y << ")\n"
#define deb_tuple(x, y, z)                                                     \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << x << ", " << y \
         << ", " << z << ")\n"
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int n, k;
vector<pair<int, int>> s;
vector<int> c;

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n >> k;
    for (int i = 0; i < n; i++) {
        int m, v;
        cin >> m >> v;
        s.emplace_back(m, v);
    }
    for (int i = 0; i < k; i++) {
        int x;
        cin >> x;
        c.push_back(x);
    }
    sort(s.rbegin(), s.rend());
    sort(c.rbegin(), c.rend());
    priority_queue<int, vector<int>, greater<int>> pq;
    int i = 0; int j = 0;
    while (i < n) {
        // 남는 가방이 없거나, 보석이 너무 무거운 경우
        if (pq.size() == k || s[i].first > c[j]) {
            // 기존의 가방에서 바꿀 가치가 있으면 바꾼다
            // 아니라면 보석을 버린다
            if (!pq.empty() && pq.top() < s[i].second) {
                pq.pop();
                pq.push(s[i].second);
            }
            i++;
        } else {
            pq.push(s[i].second);
            i++; j++;
        }
    }
    long long sum = 0;
    while (!pq.empty()) {
        sum += pq.top();
        pq.pop();
    }
    cout << sum << "\n";
}
