#include <iostream>
#include <vector>
using namespace std;

int main() {
	cin.tie(0);
	ios_base::sync_with_stdio(0);
	int n, k;
	cin >> n >> k;
	vector<long long> b(n);
	for (int i = 0; i < k; i++) {
		int x;
		cin >> x;
		b[x]++;
		b[0]++;
	}
	vector<long long> a(n);
	a[0] = b[0];
	for (int i = 1; i < n; i++) {
		if (b[i]) {
			for (int j = i; j < n; j += i) {
				a[j] += b[i];
			}
		}
	}
	for (int i = 1; i < n; i++) {
		a[i] += a[i - 1];
	}
	int q;
	cin >> q;
	for (int i = 0; i < q; i++) {
		int u, v;
		cin >> u >> v;
		long long sum = a[v];
		if (u - 1 >= 0) sum -= a[u - 1];
		cout << sum << "\n";
	}
}