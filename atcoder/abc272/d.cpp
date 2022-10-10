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

int n;

const int dty[8] = { -1, 1, 1, -1, -1, -1, 1, 1 };
const int dtx[8] = { 1, 1, -1, -1, -1, 1, -1, 1 };

bool inRange(int y, int x) { return 1 <= y && y <= n && 1 <= x && x <= n; }

int main() {
    FAST();

    long long m;
    cin >> n >> m;

    vector<int> dy, dx;
    for (long long y = 0; m - y * y >= 0; y++) {
        for (long long x = 0; m - y * y - x * x >= 0; x++) {
            if (y * y + x * x == m) {
                dy.push_back(y);
                dx.push_back(x);
            }
        }
    }

    queue<pair<int, int>> q;
    vector<vector<int>> dist(n + 1, vector<int>(n + 1, -1));

    q.emplace(1, 1);
    dist[1][1] = 0;

    while (!q.empty()) {
        auto [y, x] = q.front();
        q.pop();

        for (int d = 0; d < SIZE(dy); d++) {
            for (int t = 0; t < 4; t++) {
                int ny = y + dty[t] * dy[d], nx = x + dtx[t] * dx[d];
                if (inRange(ny, nx) && dist[ny][nx] == -1) {
                    q.emplace(ny, nx);
                    dist[ny][nx] = dist[y][x] + 1;
                }
            }
        }


    }

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            cout << dist[i][j] << " \n"[j == n];
        }
    }
}
