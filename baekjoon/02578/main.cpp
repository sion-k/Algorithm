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
    vector<vector<int>> s(5, vector<int>(5));
    vector<pair<int, int>> p(26);
    for (int y = 0; y < 5; y++) {
        for (int x = 0; x < 5; x++) {
            cin >> s[y][x];
            p[s[y][x]] = make_pair(y, x);
        }
    }
    vector<int> row(5);
    vector<int> col(5);
    int diag = 0, diag2 = 0;
    int cnt = 0, ret = 0;
    for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
            int t;
            cin >> t;
            int y = p[t].first; int x = p[t].second;
            row[y]++;
            col[x]++;
            if (y == x) diag++;
            if (y + x == 4) diag2++;
            if (diag == 5) {
                cnt++;
                diag++;
            }
            if (diag2 == 5) {
                cnt++;
                diag2++;
            }
            if (row[y] == 5) {
                cnt++;
                row[y]++;
            }
            if (col[x] == 5) {
                cnt++;
                col[x]++;
            }
            if (cnt >= 3 && ret == 0) {
                ret = 5 * i + j + 1;
            }
        }
    }
    cout << ret << "\n";
}
