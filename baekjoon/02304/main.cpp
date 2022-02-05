#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int n;
vector<pair<int, int>> s;

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n;
    int lft_range = 1001; int rht_range = 0;
    for (int i = 0; i < n; i++) {
        int x, h;
        cin >> x >> h;
        s.emplace_back(h, x);
        lft_range = min(lft_range, x);
        rht_range = max(rht_range, x + 1);
    }
    sort(s.rbegin(), s.rend());
    int ret = s[0].first;
    int lft = s[0].second ; int rht = lft + 1;
    int here = 1;
    while (lft_range < lft || rht < rht_range) {
        if (s[here].second < lft) {
            ret += (lft - s[here].second) * s[here].first;
            lft = s[here].second;
        } else if (s[here].second >= rht) {
            ret += (s[here].second + 1 - rht) * s[here].first;
            rht = s[here].second + 1;
        }
        here++;
    }
    cout << ret << "\n";
}
