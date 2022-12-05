#include <bits/stdc++.h>

#define FAST() cin.tie(0)->sync_with_stdio(0)
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())

#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y) cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z) cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", " << (y) << ", " << (z) << ")\n"
#define deb_tuple(s) for (int i = 0; i < SIZE(s); i++) cout << s[i] << " \n"[i == SIZE(s) - 1];

using namespace std;

const int dy[4] = { 1, 0, -1, 0 };
const int dx[4] = { 0, 1, 0, -1 };

int n;

bool inRange(int y, int x) { return 0 <= y && y < n && 0 <= x && x < n; }

int main() {
    FAST();

    int m;
    cin >> n >> m;

    vector<vector<int>> a(n, vector<int>(n));
    int y = 0, x = 0, d = 0;

    int s = n * n;

    while (s >= 1) {
        a[y][x] = s;
        s--;

        int ny = y + dy[d], nx = x + dx[d];
        if (!inRange(ny, nx) || a[ny][nx]) {
            d = (d + 1) % 4;
            ny = y + dy[d], nx = x + dx[d];
        }

        y = ny, x = nx;
    }

    pair<int, int> p;
    for (int y = 0; y < n; y++) {
        for (int x = 0; x < n; x++) {
            cout << a[y][x] << " \n"[x == n - 1];
            if (a[y][x] == m) {
                p = { y, x };
            }
        }
    }

    cout << p.first + 1 << " " << p.second + 1 << "\n";
}
