#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int n, q;
int a[200000];
int p[200000];

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n >> q;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    int sum = 0;
    for (int i = 0; i < n; i++) {
        int product = 1;
        for (int j = 0; j < 4; j++) {
            product *= a[(i + j) % n];
        }
        p[i] = product;
        sum += p[i];
    }
    for (int i = 0; i < q; i++) {
        int x;
        cin >> x;
        x--;
        for (int j = 0; j < 4; j++) {
            sum -= 2 * p[(x - j + n) % n];
            p[(x - j + n) % n] *= -1;
        }
        cout << sum << "\n";
    }
}
