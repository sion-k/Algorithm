#include <iostream>
#include <vector>
using namespace std;

int n;
vector<vector<int>> s;

const int INF = 987654321;

int diff(int mask) {
	vector<int> c(2);
	for (int i = 0; i < n; i++) {
		for (int j = i + 1; j < n; j++) {
			bool a = mask & (1 << i), b = mask & (1 << j);
			if (!(a ^ b)) {
				c[a] += s[i][j];
				c[a] += s[j][i];
			} 
		}
	}
	return abs(c[0] - c[1]);
}

int bfc(int here, int mask) {
	int pick = __builtin_popcount(mask);
	if (here == n) {
		return pick == n / 2 ? diff(mask) : INF;
	}
	int min = bfc(here + 1, mask);
	if (pick < n / 2) {
		min = ::min(min, bfc(here + 1, mask | (1 << here)));
	}
	return min;
}

int main() {
	cin >> n;
	s = vector<vector<int>>(n, vector<int>(n));
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> s[i][j];
		}
	}
	cout << bfc(0, 0) << "\n";
}
