#include <iostream>
#include <algorithm>

using namespace std;

int main(void) {
	int s[3];
	for (int i = 0; i < 3; i++) cin >> s[i];
	sort(s, s + 3);
	int result = 0;
	if (s[0] == s[2]) {
		result = 10000 + s[0] * 1000;
	} else if (s[0] == s[1]) {
		result = 1000 + s[0] * 100;
	} else if (s[1] == s[2]) {
		result = 1000 + s[1] * 100;
	} else {
		result = s[2] * 100;
	}
	cout << result << endl;
	return 0;
}