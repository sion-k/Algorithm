#include <bits/stdc++.h>

#define FAST() cin.tie(0)->sync_with_stdio(0)
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())

#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y) cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z) cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", " << (y) << ", " << (z) << ")\n"
#define deb_tuple(s) \
    cout << "["; \
    for (int __i = 0; __i < SIZE(s); __i++) { \
        cout << s[__i]; \
        if (__i != SIZE(s) - 1) cout << ", "; \
    } \
    cout << "]\n";

using namespace std;

int n;
vector<vector<int>> b;
vector<pair<int, int>> p;

const int dy[8] = { -2, -2, -1, -1, 1, 1, 2, 2 };
const int dx[8] = { -1, 1, -2, 2, -2, 2, -1, 1 };

bool inRange(int y, int x) { return 1 <= y && y <= n && 1 <= x && x <= n; }

void dfs(int y, int x) {
    p.emplace_back(y, x);
    b[y][x] = true;

    for (int d = 0; d < 8; d++) {
        int ny = y + dy[d], nx = x + dx[d];

        if (inRange(ny, nx) && !b[ny][nx]) {
            dfs(ny, nx);
            p.emplace_back(y, x);
        }
    }
}

int main() {
    FAST();

    cin >> n;

    b = vector<vector<int>>(n + 1, vector<int>(n + 1));

    int y, x;
    cin >> y >> x;

    dfs(y, x);

    bool flag = true;
    for (int y = 1; y <= n; y++) {
        for (int x = 1; x <= n; x++) {
            if (!b[y][x]) {
                flag = false;
            }
        }
    }

    if (!flag) {
        cout << -1 << "\n";
    } else {
        cout << SIZE(p) << "\n";
        for (auto [y, x] : p) {
            cout << y << " " << x << "\n";
        }
    }
}
