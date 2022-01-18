#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int n, k;
bool flag;

const int dy[5] = { 0, 0, 0, -1, 1 };
const int dx[5] = { 0, 1, -1, 0, 0 };
// 정 반대 방향
const int dr[5] = { 0, 2, 1, 4, 3 };

bool inRange(int y, int x) { return 1 <= y && y <= n && 1 <= x && x <= n; }

class Piece;

// 체스판의 색깔
int s[13][13];
// (y, x)에 위치한 Piece의 포인터
Piece* piece[13][13];

class Piece {
public:
    int y, x;
    deque<pair<int, int>> dq;

    Piece() {}

    Piece(int y, int x, int k, int d) : y(y), x(x), dq() {
        dq.emplace_back(k, d);
    }

    void move() {
        int d = dq.front().second;
        int ny = y + dy[d]; int nx = x + dx[d];
        // cout << "(ny, nx) = (" << ny << ", " << nx << ")\n";
        // cout << "s[ny][nx] = " << s[ny][nx] <<"\n";
        // 파란색인 경우
        if (!inRange(ny, nx) || s[ny][nx] == 2) {
            reverseBottom();
            d = dq.front().second;
            ny = y + dy[d]; nx = x + dx[d];
            // 파란색이 아닌 경우만 방향을 바꿨으니 이동한다
            if (inRange(ny, nx) && s[ny][nx] != 2) {
                move();
            }
        // 흰색인 경우
        } else if (s[ny][nx] == 0) {
            piece[y][x] = NULL;
            // 이미 말이 존재하는 경우
            if (piece[ny][nx]) {
                piece[ny][nx]->append(*this);
            } else {
                y = ny; x = nx;
                piece[y][x] = this;
            }
        // 빨간색인 경우
        } else if (s[ny][nx] == 1) {
            piece[y][x] = NULL;
            reverseAll();
            // 이미 말이 존재하는 경우
            if (piece[ny][nx]) {
                piece[ny][nx]->append(*this);
            } else {
                y = ny; x = nx;
                piece[y][x] = this;
            }
        }
    }

    void reverseAll() {
        reverse(ALL(dq));
    }

    void reverseBottom() {
        dq.front().second = dr[dq.front().second];    
    }

    void append(Piece& o) {
        while (!o.dq.empty()) {
            dq.push_back(o.dq.front());
            o.dq.pop_front();
        }
        if (dq.size() >= 4) flag = true;
    }

    friend ostream& operator<<(ostream&, const Piece&);
};

ostream& operator<<(ostream& os, const Piece& p) {
    os << "(" << p.dq.front().first << ", " << p.dq.front().second << ")";
    return os;
}

void print() {
    for (int y = 1; y <= n; y++) {
        for (int x = 1; x <= n; x++) {
            if (piece[y][x] && piece[y][x]->dq.size()) {
                cout << *piece[y][x] << " ";
            } else {
                cout << "(0, 0)" << " ";
            }
        }
        cout << "\n";
    }
}

void move(int here) {
    for (int y = 1; y <= n; y++) {
        for (int x = 1; x <= n; x++) {
            if (piece[y][x] && piece[y][x]->dq.front().first == here) {
                piece[y][x]->move();
                return;
            }
        }
    }
}

void moveAll() {
    for (int here = 1; here <= k; here++) {
        move(here);
        // print();
        // cout << "\n";
    }
}

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n >> k;
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            cin >> s[i][j];
        }
    }
    for (int i = 1; i <= k; i++) {
        int y, x, d;
        cin >> y >> x >> d;
        piece[y][x] = new Piece(y, x, i, d);
    }
    int ret = -1;
    for (int t = 1; t <= 1000; t++) {
        moveAll();
        if (flag) {
            ret = t;
            break;
        }
    }
    cout << ret << "\n";
}
