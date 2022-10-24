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

// 주어진 n * m 행렬 a의 값을 반환
int value(int n, int m, vector<vector<int>>& a) {
    int min = INT_MAX;

    for (int i = 0; i < n; i++) {
        min = ::min(min, accumulate(ALL(a[i]), 0));
    }

    return min;
}

const int dy[8] = { 0, 1, 0, -1, -1, -1, 1, 1 };
const int dx[8] = { 1, 0, -1, 0, -1, 1, -1, 1 };

// (y, x)를 시작으로 사각형을 그리면서 위치 좌표를 순서대로 반환
deque<pair<int, int>> traversal(vector<vector<int>>& a, int y, int x, int s) {
    deque<pair<int, int>> p;

    int d = 0;
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 2 * s; j++) {
            p.emplace_back(y, x);
            y += dy[d], x += dx[d];
        }
        d++;
    }

    return p;
}

// a에 대해서 연산 (y, x, s)를 시행
void rotate(vector<vector<int>>& a, int y, int x, int s) {
    for (int i = 1; i <= s; i++) {
        deque<pair<int, int>> p = traversal(a, y - i, x - i, i);
        p.push_back(p.front());
        p.pop_front();

        for (int j = SIZE(p) - 2; j >= 0; j--) {
            auto [y1, x1] = p[j];
            auto [y2, x2] = p[j + 1];
            swap(a[y1][x1], a[y2][x2]);
        }
    }
}

// a에 주어진 대로 연산을 시행하고 새로운 a'를 반환
vector<vector<int>> simulate(int n, int m, vector<vector<int>>& a, vector<tuple<int, int, int>>& b) {
    vector<vector<int>> na(n, vector<int>(m));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            na[i][j] = a[i][j];
        }
    }

    for (int i = 0; i < SIZE(b); i++) {
        auto [y, x, s] = b[i];
        ::rotate(na, y, x, s);
    }

    return na;
}

int main() {
    FAST();

    int n, m, k;
    cin >> n >> m >> k;

    vector<vector<int>> a(n, vector<int>(m));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> a[i][j];
        }
    }

    vector<tuple<int, int, int>> b(k);
    for (int i = 0; i < k; i++) {
        auto& t = b[i];
        cin >> get<0>(t) >> get<1>(t) >> get<2>(t);
        get<0>(t)--;
        get<1>(t)--;
    }

    sort(ALL(b));

    int min = INT_MAX;

    do {
        vector<vector<int>> na = simulate(n, m, a, b);
        int cand = value(n, m, na);
        min = ::min(min, cand);
    } while (next_permutation(ALL(b)));

    cout << min << "\n";
}
