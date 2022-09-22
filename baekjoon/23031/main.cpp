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

// S는 꺼진 형광등, T는 켜진 형광등, O는 빈 칸
int n;
vector<string> s;

int m;
string a;

// 좀비의 위치와 바라보는 방향
vector<tuple<int, int, int>> z;

const int dy[8] = { -1, 1, 0, 0, -1, -1, 1, 1 };
const int dx[8] = { 0, 0, -1, 1, -1, 1, -1, 1 };
const int dl[4] = { 2, 3, 1, 0 };
const int dr[4] = { 3, 2, 0, 1 };

bool inRange(int y, int x) { return 0 <= y && y < n && 0 <= x && x < n; }

// 근처에 켜진 형광등이 있는지 확인
bool adj(int y, int x) {
    if (s[y][x] == 'T') return true;
    for (int d = 0; d < 8; d++) {
        int ny = y + dy[d], nx = x + dx[d];
        if (inRange(ny, nx) && s[ny][nx] == 'T') {
            return true;
        }
    }
    return false;
}

// 모든 좀비들을 바라보는 방향으로 전진
void zombie() {
    vector<tuple<int, int, int>> nz;
    for (auto& [y, x, d] : z) {
        int ny = y + dy[d], nx = x + dx[d], nd = d;
        if (!inRange(ny, nx)) {
            ny = y, nx = x, nd = dl[dl[nd]];
        }
        nz.emplace_back(ny, nx, nd);
    }
    z = nz;
}

// 아리를 a[i]번째 행동을 시키고 새로운 위치를 반환
tuple<int, int, int> ahri(tuple<int, int, int> t, int i) {
    auto [y, x, d] = t;
    if (a[i] == 'F') {
        int ny = y + dy[d], nx = x + dx[d];
        if (!inRange(ny, nx)) {
            ny = y, nx = x;
        }
        return { ny, nx, d };
    } else if (a[i] == 'L') {
        d = dl[d];
        return { y, x, d };
    } else {
        d = dr[d];
        return { y, x, d };
    }
}

void turn_on(tuple<int, int, int> t) {
    auto [y, x, d] = t;
    if (s[y][x] == 'S') s[y][x] = 'T';
}

bool ahri_zombie_collapse(tuple<int, int, int> t) {
    auto [y1, x1, d1] = t;
    for (auto [y2, x2, d2] : z) {
        if (y1 == y2 && x1 == x2 && !adj(y1, x1)) return true;
    }
    return false;
}

int main() {
    FAST();
    cin >> n;

    cin >> a;
    m = SIZE(a);

    s = vector<string>(n);
    for (int i = 0; i < n; i++) {
        cin >> s[i];
    }

    for (int y = 0; y < n; y++) {
        for (int x = 0; x < n; x++) {
            if (s[y][x] == 'Z') {
                z.emplace_back(y, x, 1);
                s[y][x] = 'O';
            }
        }
    }

    tuple<int, int, int> t = { 0, 0, 1 };
    for (int i = 0; i < m; i++) {
        t = ahri(t, i);
        turn_on(t);
        if (ahri_zombie_collapse(t)) {
            cout << "Aaaaaah!" << "\n";
            return 0;
        }
        zombie();
        if (ahri_zombie_collapse(t)) {
            cout << "Aaaaaah!" << "\n";
            return 0;
        }
    }
    cout << "Phew..." << "\n";
}
