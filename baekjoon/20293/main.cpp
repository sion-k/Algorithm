#include <iostream>
#include <cstring>
using namespace std;

int r, c;
// (y, x)에 위치하는 주유소의 번호
int s[3001][3001];
int n;
// i번째 주유소의 연료량
int a[1001];

int dist(int y1, int x1, int y2, int x2) {
    return abs(y1 - y2) + abs(x1 - x2);
}

const int INF = 987654321;

int cache[3001][3001];

// (y, x)에서 (r, c)까지 도달하는데 필요한 최소 연료
int dp(int y, int x) {
    if (y == r && x == c) return 0;
    if (cache[y][x] != -1) return cache[y][x];
    int ret = INF;
    if (y + 1 <= r) ret = min(ret, 1 + dp(y + 1, x));
    if (x + 1 <= c) ret = min(ret, 1 + dp(y, x + 1));
    if (s[y][x]) ret = max(0, ret - a[s[y][x]]);
    return cache[y][x] = ret;
}

int main() {
    cin >> r >> c;
    cin >> n;
    for (int i = 1; i <= n; i++) {
        int y, x, f;
        cin >> y >> x >> f;
        s[y][x] = i;
        a[i] = f;
    }
    for (int y = 1; y <= r; y++) {
        memset(cache[y], -1, (c + 1) * sizeof(int));
    }
    cout << dp(1, 1) << endl;
}