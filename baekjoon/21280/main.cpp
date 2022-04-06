#include <bits/stdc++.h>
#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_tuple(x, y, z)                                                     \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", "    \
         << (y) << ", " << (z) << ")\n"
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
#define OPEN(t) freopen("data.txt", (t), stdin)
using namespace std;

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    int n;
    cin >> n;
    vector<int> s(n);
    for (auto &x : s) {
        cin >> x;
    }
    pair<int, int> ret;
    for (int i = 1; i < n; i++) {
        int dif = abs(s[i] - s[i - 1]);
        if (s[i] - s[i - 1] > 0) {
            ret.second += dif;
        } else {
            ret.first += dif;
        }
    }
    cout << ret.first << " " << ret.second << "\n";
}
