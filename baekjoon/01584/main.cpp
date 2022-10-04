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

bool death[501][501] = {};
bool danger[501][501] = {};

const int dy[8] = { -1, 1, 0, 0, -1, -1, 1, 1 };
const int dx[8] = { 0, 0, -1, 1, -1, 1, -1, 1 };

bool inRange(int y, int x) { return 0 <= y && y <= 500 && 0 <= x && x <= 500; }

int bfs() {
    deque<pair<int, int>> dq;
    vector<vector<int>> dist(501, vector<int>(501, -1));

    dq.emplace_back(0, 0);
    dist[0][0] = 0;

    while (!dq.empty()) {
        auto [y, x] = dq.front();
        dq.pop_front();

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d], nx = x + dx[d];
            if (inRange(ny, nx) && !death[ny][nx] && dist[ny][nx] == -1) {
                if (danger[ny][nx]) {
                    dq.emplace_back(ny, nx);
                    dist[ny][nx] = dist[y][x] + 1;
                } else {
                    dq.emplace_front(ny, nx);
                    dist[ny][nx] = dist[y][x];
                }
            }
        }
    }

    return dist[500][500];
}

int main() {
    FAST();

    int n;
    cin >> n;

    for (int i = 0; i < n; i++) {
        int y1, x1, y2, x2;
        cin >> y1 >> x1 >> y2 >> x2;

        for (int y = min(y1, y2); y <= max(y1, y2); y++) {
            for (int x = min(x1, x2); x <= max(x1, x2); x++) {
                danger[y][x] = true;
            }
        }
    }

    int m;
    cin >> m;

    for (int i = 0; i < m; i++) {
        int y1, x1, y2, x2;
        cin >> y1 >> x1 >> y2 >> x2;

        for (int y = min(y1, y2); y <= max(y1, y2); y++) {
            for (int x = min(x1, x2); x <= max(x1, x2); x++) {
                death[y][x] = true;
            }
        }
    }

    cout << bfs() << "\n";
}
