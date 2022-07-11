#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define FAST() cin.tie(0)->sync_with_stdio(0)
#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z)                                                   \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", "    \
         << (y) << ", " << (z) << ")\n"
#define deb_tuple(s)                                                           \
    for (int i = 0; i < SIZE(s); i++)                                          \
        cout << s[i] << " \n"[i == SIZE(s) - 1];
using namespace std;

int n, m;
vector<string> a;
vector<vector<int>> b;
vector<vector<int>> c;

const int dy[8] = {-1, 1, 0, 0, -1, -1, 1, 1};
const int dx[8] = {0, 0, -1, 1, -1, 1, -1, 1};

bool inRange(int y, int x) { return 0 <= y && y < n && 0 <= x && x < m; }

vector<pair<int, int>> bfs(vector<pair<int, int>> &p) {
    queue<pair<int, int>> q;
    for (auto [y, x] : p) {
        b[y][x] = true;
        q.emplace(y, x);
    }
    vector<pair<int, int>> r;
    while (!q.empty()) {
        auto [y, x] = q.front();
        q.pop();
        for (int d = 0; d < 8; d++) {
            int ny = y + dy[d], nx = x + dx[d];
            if (!inRange(ny, nx) || b[ny][nx]) continue;
            if (a[ny][nx] == '.') {
                q.emplace(ny, nx);
                b[ny][nx] = true;
            } else {
                c[ny][nx]++;
                if (c[ny][nx] == a[ny][nx] - '0') {
                    r.emplace_back(ny, nx);
                }
            }
        }
    }
    for (auto [y, x] : r) {
        a[y][x] = '.';
    }
    return r;
}

int main() {
    FAST();
    cin >> n >> m;
    a = vector<string>(n);
    for (int y = 0; y < n; y++) {
        cin >> a[y];
    }
    b = vector<vector<int>>(n, vector<int>(m));
    c = vector<vector<int>>(n, vector<int>(m));
    int cnt = 0;
    vector<pair<int, int>> p;
    for (int y = 0; y < n; y++) {
        for (int x = 0; x < m; x++) {
            if (a[y][x] == '.') {
                p.emplace_back(y, x);
            }
        }
    }
    while (true) {
        p = bfs(p);
        if (!p.empty()) {
            cnt++;   
        } else {
            break;
        }
    }
    cout << cnt << "\n";
}
