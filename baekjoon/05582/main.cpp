#include <iostream>
#include <vector>
#define SIZE(x) ((int)(x).size())
using namespace std;

int main() {
	cin.tie(0);
	ios_base::sync_with_stdio(0);
	int n, m;
	string a, b;
	cin >> a >> b;
	n = SIZE(a); m = SIZE(b);
	vector<vector<int>> dp(2, vector<int>(m));
	int max = 0;
	for (int i = 0; i < n; i++) {
		int here = i & 1; int prev = !here;
		for (int j = 0; j < m; j++) {
			dp[here][j] = 0;
			if (a[i] == b[j]) {
				if (i - 1 >= 0 && j - 1 >= 0) {
					dp[here][j] += dp[prev][j - 1];
				}
				dp[here][j]++;
				max = ::max(max, dp[here][j]);
			}
		}
	}
	cout << max << "\n";
}
