#include <bits/stdc++.h>
#define deb(x) cout << #x << " : " << x << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << x << ", " << y << ")\n"
#define deb_tuple(x, y, z)                                                     \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << x << ", " << y \
         << ", " << z << ")\n"
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int n, m, fuel;

const int dy[8] = {-1, 1, 0, 0, -1, -1, 1, 1};
const int dx[8] = {0, 0, -1, 1, -1, 1, -1, 1};

bool inRange(int y, int x) { return 0 <= y && y < n && 0 <= x && x < n; }

// i번째 승객의 출발지와 목적지
pair<int, int> p[400][2];
// 벽이 존재하는지 여부
bool s[20][20];
// (y, x)에 위치하는 승객의 번호
// 승객을 목적지에 데려다 준 경우 -1로 변경
int passenger_number[20][20];

const int INF = 987654321;

// (y, x)에서 가장 가까운 승객의 번호와 그 곳까지의 거리
pair<int, int> next_passenger(int sy, int sx) {
    queue<pair<int, int>> q;
    int dist[20][20];
    memset(dist, -1, sizeof(dist));
    q.emplace(sy, sx);
    dist[sy][sx] = 0;
    while (!q.empty()) {
        int y = q.front().first;
        int x = q.front().second;
        q.pop();
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d]; int nx = x + dx[d];
            if (!inRange(ny, nx) || s[ny][nx] || dist[ny][nx] != -1) continue;
            q.emplace(ny, nx);
            dist[ny][nx] = dist[y][x] + 1;
        }
    }
    int min = INF, num = -1;
    for (int y = 0; y < n; y++) {
        for (int x = 0; x < n; x++) {
            if (dist[y][x] != -1 && passenger_number[y][x] != -1 && min > dist[y][x]) {
                min = dist[y][x];
                num = passenger_number[y][x];
            }
        }
    }
    return make_pair(num, min);
}

// i번째 승객을 배송하는데 드는 비용, 불가능하면 INF반환
int dist(int i) {
    int sy = p[i][0].first; int sx = p[i][0].second;
    queue<pair<int, int>> q;
    int dist[20][20];
    memset(dist, -1, sizeof(dist));
    q.emplace(sy, sx);
    dist[sy][sx] = 0;
    while (!q.empty()) {
        int y = q.front().first;
        int x = q.front().second;
        q.pop();
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d]; int nx = x + dx[d];
            if (!inRange(ny, nx) || s[ny][nx] || dist[ny][nx] != -1) continue;
            q.emplace(ny, nx);
            dist[ny][nx] = dist[y][x] + 1;
        }
    }
    int ey = p[i][1].first; int ex = p[i][1].second;
    return dist[ey][ex] == -1 ? INF : dist[ey][ex];
}

// (sy, sx)에서 다음 승객을 운송한다
// 운송 가능하면 도착한 지점을 반환
// 아니라면 (-1, -1)을 반환
pair<int, int> transfer(int sy, int sx) {
    pair<int, int> ret = make_pair(-1, -1);
    pair<int, int> next = next_passenger(sy, sx);
    deb_pair(next.first, next.second);
    // 다음 승객을 찾을 수 없는 경우
    if (next.first == -1) return ret;
    // 다음 승객까지 도달 불가능한 경우
    if (fuel < next.second) return ret;
    fuel -= next.second;
    // 다음 승객을 운송중에 연료가 바닥나거나 도달할 수 없는 경우
    int cost = dist(next.first);
    if (fuel < cost) return ret;
    // deb_pair(next.second, cost);
    fuel += cost;
    for (int y = 0; y < n; y++) {
        for (int x = 0; x < n; x++) {
            if (passenger_number[y][x] == next.first) {
                passenger_number[y][x] = -1;
            }
        }
    }
    return p[next.first][1];
}

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n >> m >> fuel;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> s[i][j];
        }
    }
    memset(passenger_number, -1, sizeof(passenger_number));
    int y, x;
    cin >> y >> x;
    y--; x--;
    for (int i = 0; i < m; i++) {
        int y1, x1, y2, x2;
        cin >> y1 >> x1 >> y2 >> x2;
        y1--; x1--; y2--; x2--;
        p[i][0] = make_pair(y1, x1);
        p[i][1] = make_pair(y2, x2);
        passenger_number[y1][x1] = i;
    }
    while (true) {
        pair<int, int> next = transfer(y, x);
        y = next.first; x = next.second;
        if (y == -1 && x == -1) break;
    }
    int ret = fuel;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (passenger_number[i][j] != -1) {
                ret = -1;
            }
        }
    }
    cout << ret << "\n";
}
