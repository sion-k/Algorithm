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

const string CW = "NESWN";
const string CCW = "NWSEN";

void solve() {
    string s;
    cin >> s;
    s.push_back(s.front());
    int cw = 0, ccw = 0;
    for (int i = 0; i < s.length() - 1; i++) {
        if (CW.find(s.substr(i, 2)) != -1) {
            cw++;
        } else if (CCW.find(s.substr(i, 2)) != -1) {
            ccw++;
        }
    }
    cout << (cw > ccw ? "CW" : "CCW") << "\n";
}

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    int tc;
    cin >> tc;
    while (tc--) {
        solve();
    }
}
