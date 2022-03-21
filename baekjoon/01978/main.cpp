#include <iostream>
using namespace std;

bool isPrime(int x) {
	if (x == 1) return false;
	for (int i = 2; i * i <= x; i++) {
		if (x % i == 0) {
			return false;
		}
	}
	return true;
}

int main() {
	int n;
	cin >> n;
	int cnt = 0;
	for (int i = 0; i < n; i++) {
		int x;
		cin >> x;
		if (isPrime(x)) {
			cnt++;
		}
	}
	cout << cnt << "\n";
}