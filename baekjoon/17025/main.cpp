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
vector<string> a;
vector<vector<int>> b;

const int dy[8] = { -1, 1, 0, 0, -1, -1, 1, 1 };
const int dx[8] = { 0, 0, -1, 1, -1, 1, -1, 1 };

bool inRange(int y, int x) { return 0 <= y && y < n && 0 <= x && x < n; }

pair<int, int> bfs(int sy, int sx) {
    pair<int, int> ret;

    queue<pair<int, int>> q;
    q.emplace(sy, sx);

    b[sy][sx] = true;

    while (!q.empty()) {
        auto [y, x] = q.front();
        q.pop();

        ret.first++;

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d], nx = x + dx[d];

            if (inRange(ny, nx) && a[ny][nx] == '#' && !b[ny][nx]) {
                q.emplace(ny, nx);
                b[ny][nx] = true;
            } else if (!inRange(ny, nx) || a[ny][nx] == '.') {
                ret.second++;
            }
        }
    }

    return ret;
}

int main() {

    cin >> n;
    a = vector<string>(n);
    b = vector<vector<int>>(n, vector<int>(n));

    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    pair<int, int> max;

    for (int y = 0; y < n; y++) {
        for (int x = 0; x < n; x++) {
            if (a[y][x] == '#' && !b[y][x]) {
                pair<int, int> cand = bfs(y, x);

                if (max.first < cand.first) {
                    max = cand;
                } else if (max.first == cand.first && max.second > cand.second) {
                    max = cand;
                }
            }
        }
    }

    cout << max.first << " " << max.second << "\n";
}
