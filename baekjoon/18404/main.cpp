#include <iostream>
#include <vector>
#include <queue>
using namespace std;

const int dy[8] = { -2, -2, -1, -1, 1, 1, 2, 2 };
const int dx[8] = { -1, 1, -2, 2, -2, 2, -1, 1 };

bool inRange(int y, int x, int n) {
	return 1 <= y && y <= n && 1 <= x && x <= n;
}

int main() {
	cin.tie(0);
	ios_base::sync_with_stdio(0);
	int n, m;
	cin >> n >> m;
	queue<pair<int, int>> q;
	vector<vector<int>> dist = vector<vector<int>>(n + 1, vector<int>(n + 1, -1));
	int sy, sx;
	cin >> sy >> sx;
	q.emplace(sy, sx);
	dist[sy][sx] = 0;
	while (!q.empty()) {
		int y = q.front().first;
		int x = q.front().second;
		q.pop();
		for (int d = 0; d < 8; d++) {
			int ny = y + dy[d]; int nx = x + dx[d];
			if (!inRange(ny, nx, n) || dist[ny][nx] != -1) continue;
			q.emplace(ny, nx);
			dist[ny][nx] = dist[y][x] + 1;
		}
	}
	for (int i = 0; i < m; i++) {
		int y, x;
		cin >> y >> x;
		cout << dist[y][x] << " ";
	}
}
