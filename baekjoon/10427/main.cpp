#include <iostream>
#include <vector>
#include <algorithm>
#define ALL(x) (x).begin(), (x).end()
using namespace std;

const int INF = 987654321;

void solve() {
	int n;
	cin >> n;
	vector<int> s(n);
	for (int i = 0; i < n; i++) {
		cin >> s[i];
	}
	sort(ALL(s));
	for (int i = 1; i < n; i++) {
		s[i] += s[i - 1];
	}
	long long ret = 0;
	for (int k = 1; k <= n; k++) {
		int min = INF;
		for (int i = 0; i + k - 1 < n; i++) {
			int max = s[i + k - 1];
			if (i + k - 2 >= 0) max -= s[i + k - 2];
			int sum = s[i + k - 1];
			if (i - 1 >= 0) sum -= s[i - 1];
			long long cand = (long long)max * k - sum;
			if (min > cand) {
				min = cand;
			}
		}
		ret += min;
	}
	cout << ret << "\n";
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
