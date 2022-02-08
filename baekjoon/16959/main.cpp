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
int s[10][10];

// (n번째 정점까지 방문, y, x, (나이트, 비숍, 룩))
// 정답은 min(dist[n]) (dist != -1)
int dist[101][10][10][3];

const int dy[3][8] = {
    {-2, -2, -1, -1, 1, 1, 2, 2}, {-1, -1, 1, 1}, {-1, 0, 0, 1}};

const int dx[3][8] = {
    {-1, 1, -2, 2, -2, 2, -1, 1},
    {-1, 1, -1, 1},
    {0, -1, 1, 0},
};

bool inRange(int y, int x) { return 0 <= y && y < n && 0 <= x && x < n; }

// (y, x)에서 t번 말로 이동할 수 있는 경우를 queue에 삽입하고 dist갱신
void move(queue<tuple<int, int, int, int>> &q, int k, int y, int x, int t) {
    if (t == 0) {
        for (int d = 0; d < 8; d++) {
            int ny = y + dy[t][d];
            int nx = x + dx[t][d];
            if (!inRange(ny, nx)) {
                continue;
            }
            int nk = k;
            if (s[ny][nx] == k + 1) {
                nk++;
            }
            if (dist[nk][ny][nx][t] != -1)
                continue;
            q.emplace(nk, ny, nx, t);
            dist[nk][ny][nx][t] = dist[k][y][x][t] + 1;
        }
        return;
    }
    for (int range = 1; range <= n; range++) {
        for (int d = 0; d < 4; d++) {
            int ny = y + range * dy[t][d];
            int nx = x + range * dx[t][d];
            if (!inRange(ny, nx)) {
                continue;
            }
            int nk = k;
            if (s[ny][nx] == k + 1) {
                nk++;
            }
            if (dist[nk][ny][nx][t] != -1) {
                continue;
            }
            q.emplace(nk, ny, nx, t);
            dist[nk][ny][nx][t] = dist[k][y][x][t] + 1;
        }
    }
}

void bfs() {
    memset(dist, -1, sizeof(dist));
    queue<tuple<int, int, int, int>> q;
    int sy = -1, sx = -1;
    for (int y = 0; y < n; y++) {
        for (int x = 0; x < n; x++) {
            if (s[y][x] == 1) {
                sy = y;
                sx = x;
            }
        }
    }
    for (int k = 0; k < 3; k++) {
        q.emplace(1, sy, sx, k);
        dist[1][sy][sx][k] = 0;
    }
    while (!q.empty()) {
        int k = get<0>(q.front());
        int y = get<1>(q.front());
        int x = get<2>(q.front());
        int t = get<3>(q.front());
        q.pop();
        // 말을 바꾸는 경우
        for (int nt = 0; nt < 3; nt++) {
            if (dist[k][y][x][nt] != -1)
                continue;
            q.emplace(k, y, x, nt);
            dist[k][y][x][nt] = dist[k][y][x][t] + 1;
        }
        // 움직이는 경우
        move(q, k, y, x, t);
    }
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
    bfs();
    int ey = -1, ex = -1;
    for (int y = 0; y < n; y++) {
        for (int x = 0; x < n; x++) {
            if (s[y][x] == n * n) {
                ey = y;
                ex = x;
            }
        }
    }
    int min = 987654321;
    for (int t = 0; t < 3; t++) {
        if (dist[n * n][ey][ex][t] != -1) {
            min = std::min(min, dist[n * n][ey][ex][t]);
        }
    }
    cout << min << "\n";
}
