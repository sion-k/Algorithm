#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
#define deb(x) cout << #x << " : " << x << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << x << ", " << y << ")\n";
using namespace std;

// i번째 지형의 험준도
int n, h, w;
int difficulty[10];

// (y, x)의 지형 번호
int terrain[500][500];
// (y, x)에 위치한 유닛의 참조
tuple<int, int, int, int> *occup[500][500];

// 유닛
int m;
tuple<int, int, int, int> unit[62501];

const int INF = 987654321;
int dist[500][500];

const int dy[4] = {-1, 1, 0, 0};
const int dx[4] = {0, 0, -1, 1};

bool inRange(int y, int x) { return 0 <= y && y < h && 0 <= x && x < w; }

// (y, x)의 인접한 칸에 team과 다른 세력이 있는 지 여부
bool adj(int team, int y, int x) {
    for (int d = 0; d < 4; d++) {
        int ny = y + dy[d];
        int nx = x + dx[d];
        if (!inRange(ny, nx))
            continue;
        if (occup[ny][nx] && team != get<1>(*occup[ny][nx])) {
            return true;
        }
    }
    return false;
}

bool reachable(int u, int y1, int x1, int y2, int x2) {
    priority_queue<tuple<int, int, int>> pq;
    for (int y = 0; y < h; y++) {
        for (int x = 0; x < w; x++) {
            dist[y][x] = INF;
        }
    }
    int range = get<0>(unit[u]);
    int team = get<1>(unit[u]);
    pq.emplace(0, y1, x1);
    dist[y1][x1] = 0;
    // 이미 전투 중인 유닛이라면 약진으로 벗어날 수 있다.
    if (adj(team, y1, x1)) {
        int y = y1;
        int x = x1;
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (!inRange(ny, nx)) {
                continue;
            }
            // 다른 세력의 위치가 아니여야 한다
            if (occup[ny][nx] && team != get<1>(*occup[ny][nx])) {
                continue;
            }
            int thereDist = difficulty[terrain[ny][nx]];
            if (thereDist <= range && dist[ny][nx] > thereDist) {
                dist[ny][nx] = thereDist;
                pq.emplace(-thereDist, ny, nx);
            }
        }
    }
    while (!pq.empty()) {
        int y = get<1>(pq.top());
        int x = get<2>(pq.top());
        int hereDist = -get<0>(pq.top());
        pq.pop();
        // 다른 세력과 인접하면 움직일 수 없다.
        if (adj(team, y, x))
            continue;
        if (dist[y][x] < hereDist)
            continue;
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (!inRange(ny, nx))
                continue;
            int thereDist = hereDist + difficulty[terrain[ny][nx]];
            if (thereDist <= range && dist[ny][nx] > thereDist) {
                dist[ny][nx] = thereDist;
                pq.emplace(-thereDist, ny, nx);
            }
        }
    }
    return dist[y2][x2] <= range;
}

bool move(int u, int y2, int x2) {
    int &y1 = get<2>(unit[u]);
    int &x1 = get<3>(unit[u]);
    // 목표 지점에 다른 유닛이 있거나,
    if (occup[y2][x2]) {
        return false;
    }
    // 목표 지점에 도달하는 경로가 존재하지 않는 경우
    if (!reachable(u, y1, x1, y2, x2)) {
        return false;
    }
    // (y1, x1)에 위치한 unit[u]를 (y2, x2)로 이동
    occup[y2][x2] = occup[y1][x1];
    occup[y1][x1] = 0;
    y1 = y2;
    x1 = x2;
    return true;
}

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n >> h >> w;
    for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
            cin >> terrain[i][j];
        }
    }
    for (int i = 1; i <= n; i++) {
        cin >> difficulty[i];
        if (difficulty[i] == -1)
            difficulty[i] = 21;
    }
    cin >> m;
    for (int i = 1; i <= m; i++) {
        int p, t, a, b;
        cin >> p >> t >> a >> b;
        unit[i] = make_tuple(p, t, a, b);
        occup[a][b] = &unit[i];
    }
    int k;
    cin >> k;
    for (int i = 0; i < k; i++) {
        int u, a, b;
        cin >> u >> a >> b;
        move(u, a, b);
    }
    for (int i = 1; i <= m; i++) {
        cout << get<2>(unit[i]) << " " << get<3>(unit[i]) << "\n";
    }
}
