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

int n, m;
vector<vector<int>> a;

const int dy[8] = { -1, 1, 0, 0, -1, -1, 1, 1 };
const int dx[8] = { 0, 0, -1, 1, -1, 1, -1, 1 };

bool inRange(int y, int x) { return 0 <= y && y < n && 0 <= x && x < m; }

int count_adj(int y, int x) {
    int cnt = 0;

    for (int d = 0; d < 8; d++) {
        int ny = (y + dy[d] + n) % n, nx = (x + dx[d] + m) % m;
        if (inRange(ny, nx) && a[ny][nx]) {
            cnt++;
        }
    }

    return cnt;
}

vector<vector<int>> next(vector<vector<int>>& a) {
    vector<vector<int>> ret(n, vector<int>(m));

    for (int y = 0; y < n; y++) {
        for (int x = 0; x < m; x++) {
            int cnt = count_adj(y, x);

            switch (cnt) {
            case 0: case 1:
                ret[y][x] = 0;
                break;
            case 2:
                ret[y][x] = a[y][x];
                break;
            case 3:
                ret[y][x] = 1;
                break;
            default:
                ret[y][x] = 0;
                break;
            }
        }
    }

    return ret;
}

long long hash(vector<vector<int>>& a) {
    long long ret = 0;

    for (int i = 0; i < n * m; i++) {
        int y = i / m, x = i % m;

        ret |= ((long long)a[y][x] << i);
    }

    return ret;
}

void print(vector<vector<int>>& a) {
    for (int y = 0; y < n; y++) {
        for (int x = 0; x < m; x++) {
            cout << a[y][x] << " \n"[x == m - 1];
        }
    }
}

void solve() {
    cin >> n >> m;
    a = vector<vector<int>>(n, vector<int>(m));

    for (int i = 0; i < n; i++) {
        string line;
        cin >> line;
        for (int j = 0; j < m; j++) {
            a[i][j] = line[j] == 'o';
        }
    }

    unordered_map<long long, int> last;
    int t = 0;

    while (true) {
        long long hash = ::hash(a);
        if (last.count(hash)) {
            cout << t - last[hash] << "\n";
            break;
        } else {
            last[hash] = t;
        }
        a = next(a);
        t++;
    }
}

int main() {
    FAST();

    int tc;
    cin >> tc;
    while (tc--) {
        solve();
    }
}
