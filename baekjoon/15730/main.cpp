#include <bits/stdc++.h>
using namespace std;

int n, m;
int s[102][102];

const int dy[4] = { -1, 1, 0, 0 };
const int dx[4] = { 0, 0, -1, 1 };

bool inRange(int y, int x) { return 0 <= y && y < n && 0 <= x && x < m; }

int f(int k) {
    queue<pair<int, int>> q;
    bool booked[102][102] = { false };
    q.emplace(0, 0);
    booked[0][0] = true;
    while (!q.empty()) {
        int y = q.front().first;
        int x = q.front().second;
        q.pop();
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d]; int nx = x + dx[d];
            if (inRange(ny, nx) && s[ny][nx] <= k && !booked[ny][nx]) {
                q.emplace(ny, nx);
                booked[ny][nx] = true;
            }
        }
    }
    int sum = 0;
    for (int y = 1; y <= n - 2; y++) {
        for (int x = 1; x <= m - 2; x++) {
            if (!booked[y][x] && s[y][x] <= k) {
                sum++;
            }
        }
    }
    return sum;
}

int main() {
    cin >> n >> m;
    n += 2; m += 2;
    for (int y = 1; y <= n - 2; y++) {
        for (int x = 1; x <= m - 2; x++) {
            cin >> s[y][x];
        }
    }
    int sum = 0;
    for (int i = 0; i <= 10000; i++) {
        sum += f(i);
    }
    cout << sum << endl;
}
