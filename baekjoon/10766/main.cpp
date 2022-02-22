#include <bits/stdc++.h>
#define deb(x) cout << #x << " : " << x << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << x << ", " << y << ")\n"
#define deb_tuple(x, y, z)                                                     \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << x << ", " << y \
         << ", " << z << ")\n"
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
using namespace std;

int n;
vector<pair<int, int>> s;

bool compare(pair<int, int>& u, pair<int, int>& v) {
    return u.second < v.second;
}

int f(int here) {
    // 못 지나가는 짚더미 left, right
    int left = here; int right = here + 1;
    while (0 <= left && right < n) {
        bool flag = false;
        int dist = s[right].second - s[left].second;
        if (dist > s[left].first) {
            left--;
            flag = true;
        }
        if (dist > s[right].first) {
            right++;
            flag = true;
        }
        if (!flag) {
            return s[here + 1].second - s[here].second;
        }
    }
    return 0;
}

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n;
    for (int i = 0; i < n; i++) {
        int size, pos;
        cin >> size >> pos;
        s.emplace_back(size, pos);
    }
    sort(ALL(s), compare);
    int ret = 0;
    for (int here = 0; here < n - 1; here++) {
        ret += f(here);
    }
    cout << ret << "\n";
}
