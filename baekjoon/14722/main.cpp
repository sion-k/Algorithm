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
int s[1000][1000];

int cache[1000][1000][3];

int dp(int y, int x, int k) {
    if (y == n - 1 && x == n - 1) return s[y][x] == k;
    if (cache[y][x][k] != -1) return cache[y][x][k];
    int max = 0; int nk = s[y][x] == k ? (k + 1) % 3 : k;
    if (y + 1 < n) max = ::max(max, dp(y + 1, x, nk));
    if (x + 1 < n) max = ::max(max, dp(y, x + 1, nk));
    return cache[y][x][k] = (s[y][x] == k) + max;
}

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> s[i][j];
        }
    }
    memset(cache, -1, sizeof(cache));
    cout << dp(0, 0, 0) << "\n";
}
