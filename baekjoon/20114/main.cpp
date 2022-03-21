#include <iostream>
#include <vector>
using namespace std;

int main() {
	cin.tie(0);
	ios_base::sync_with_stdio(0);
	int n, h, w;
	cin >> n >> h >> w;
	vector<string> s(h);
	for (int i = 0; i < h; i++) {
		cin >> s[i];
	}
	string ret;
	for (int k = 0; k < n * w; k += w) {
		int y = 0, x = k;
		bool flag = false;
		for (int dy = 0; dy < h; dy++) {
			for (int dx = 0; dx < w; dx++) {
				int ny = y + dy, nx = x + dx;
				if (s[ny][nx] != '?') {
					flag = true;
					ret.push_back(s[ny][nx]);
				}
				if (flag) {
					break;
				}
			}
			if (flag) {
				break;
			}
		}
		if (!flag) {
			ret.push_back('?');
		}
	}
	cout << ret << "\n";
}