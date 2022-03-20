#include <iostream>
#include <vector>
#include <algorithm>
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) ((int)((x).size()))

using namespace std;

void shift(string& left, string& right, char dir) {
	if (dir == '<') {
		if (!left.empty()) {
			right.push_back(left.back());
			left.pop_back();
		}
	} else {
		if (!right.empty()) {
			left.push_back(right.back());
			right.pop_back();
		}
	}
}

void solve() {
	string s, left, right;
	cin >> s;
	for (int i = 0; i < SIZE(s); i++) {
		if (s[i] == '<' || s[i] == '>') {
			shift(left, right, s[i]);
		} else if (s[i] == '-') {
			if (!left.empty()) {
				left.pop_back();
			}
		} else {
			left.push_back(s[i]);
		}
	}
	reverse(ALL(right));
	cout << left << right << "\n";
}
int main() {
	cin.tie(0);
	ios::sync_with_stdio(0);
	int tc;
	cin >> tc;
	while (tc--) {
		solve();
		
	}
	
}