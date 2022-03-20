#include <algorithm>
#include <iostream>
using namespace std;

int solve(int n, string& ball, char pick) {
	int cnt = 0; bool blocking = false;
	for (int i = 0; i < n; i++) {
		if (ball[i] == pick) {
			if (blocking) {
				cnt++;
			}
		} else {
			blocking = true;
		}
	}
	return cnt;
}

int main() {
	cin.tie(0);
	ios_base::sync_with_stdio(0);
	int n; string s;
	cin >> n >> s;
	int min = ::min(solve(n, s, 'R'), solve(n, s, 'B'));
	reverse(s.begin(), s.end());
	min = ::min(min, ::min(solve(n, s, 'R'), solve(n, s, 'B')));
	cout << min << "\n";
}