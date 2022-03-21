#include <iostream>
#include <vector>
using namespace std;

void solve() {
	int n;
	cin >> n;
	vector<int> s(n);
	vector<vector<int>> dp = vector<vector<int>>(n, vector<int>(n, 0));
	for (int i = 0; i < n; i++) {
		cin >> s[i];
		if (i - 1 >= 0) s[i] += s[i - 1];
	}
	for (int length = 2; length <= n; length++) {
		for (int start = 0; start < n; start++) {
			int end = min(start + length - 1, n - 1);
			if (start == end) continue;
			int min = dp[start][start] + dp[start + 1][end];
			for (int mid = start + 1; mid <= end; mid++) {
				min = ::min(min, dp[start][mid - 1] + dp[mid][end]);
			}
			int sum = s[end];
			if (start - 1 >= 0) sum -= s[start - 1];
			dp[start][end] = sum + min;
		}
	}
	cout << dp[0][n - 1] << "\n";
}

int main() {
	cin.tie(0);
	ios_base::sync_with_stdio(0);
	int tc;
	cin >> tc;
	while (tc--) {
		solve();
	}
}
