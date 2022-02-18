#include <bits/stdc++.h>
#define deb(x) cout << #x << " : " << x << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << x << ", " << y << ")\n"
#define deb_tuple(x, y, z)                                                     \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << x << ", " << y \
         << ", " << z << ")\n"
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int f['Z' + 1];
int sy, sx, ey, ex;

const int dy[8] = {-1, 1, 0, 0, -1, -1, 1, 1};
const int dx[8] = {0, 0, -1, 1, -1, 1, -1, 1};

bool s[4000][4000];

bool inRange(int y, int x) { return sy <= y && y <= ey && sx <= x && x <= ex; }

bool visited[4000][4000];

void dfs(int y, int x) {
    visited[y][x] = true;
    for (int d = 0; d < 4; d++) {
        int ny = y + dy[d]; int nx = x + dx[d];
        if (!inRange(ny, nx) || s[ny][nx] || visited[ny][nx]) continue;
        dfs(ny, nx);
    }
}

int dfsAll() {
    dfs(sy, sx);
    int cnt = 0;
    for (int y = sy; y <= ey; y++) {
        for (int x = sx; x <= ex; x++) {
            if (!visited[y][x] && !s[y][x]) {
                cnt++;
                dfs(y, x);
            }
        }
    }
    return cnt;
}

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    f['N'] = 0;
    f['S'] = 1;
    f['W'] = 2;
    f['E'] = 3;
    int n;
    string str;
    int y = 2002, x = 2002;
    sy = sx = ey = ex = 2002;
    cin >> n;
    cin >> str;
    for (int i = 0; i < n; i++) {
        sy = min(sy, y); ey = max(ey, y);
        sx = min(sx, x); ex = max(ex, x);
        int d = f[str[i]];
        s[y][x] = true;
        y += dy[d]; x += dx[d];
        s[y][x] = true;
        y += dy[d]; x += dx[d];
    }
    cout << dfsAll() << "\n";
}
