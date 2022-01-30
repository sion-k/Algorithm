#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int n;
int s[20][20];
int p[401][4];

const int dy[4] = { -1, 1, 0, 0 };
const int dx[4] = { 0, 0, -1, 1 };

bool inRange(int y, int x) { return 0 <= y && y < n && 0 <= x && x < n; }

// 좋아하는 학생의 개수와 비어있는 칸의 개수
pair<int, int> count(int y, int x, int k) {
    pair<int, int> ret = make_pair(0, 0);
    for (int d = 0; d < 4; d++) {
        int ny = y + dy[d]; int nx = x + dx[d];
        if (inRange(ny, nx)) {
            if (!s[ny][nx]) ret.second++;
            bool flag = false;
            for (int i = 0; i < 4; i++) {
                if (s[ny][nx] == p[k][i]) {
                    flag = true;
                }
            }
            if (flag) ret.first++;
        }
    }
    return ret;
}

void place(int k) {
    pair<int, int> cmp = make_pair(-1, -1);
    int sy = 0, sx = 0;
    for (int y = 0; y < n; y++) {
        for (int x = 0; x < n; x++) {
            if (s[y][x]) continue;
            pair<int, int> cand = count(y, x, k);
            if (cmp < cand) {
                cmp = cand;
                sy = y; sx = x;
            }
        }
    }
    s[sy][sx] = k;
}

int value() {
    int ret = 0;
    for (int y = 0; y < n; y++) {
        for (int x = 0; x < n; x++) {
            int temp = count(y, x, s[y][x]).first;
            if (temp) {
                ret += pow(10, temp - 1);
            }
        }
    }
    return ret;
}

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n;
    for (int i = 0; i < n * n; i++) {
        int k;
        cin >> k;
        for (int j = 0; j < 4; j++) {
            cin >> p[k][j];
        }
        place(k);
    }
    // for (int y = 0; y < n; y++) {
    //     for (int x = 0; x < n; x++) {
    //         cout << s[y][x] << " ";
    //     }
    //     cout << "\n";
    // }
    cout << value() << "\n";
}
