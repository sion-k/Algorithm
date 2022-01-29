#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int n;
int a[11];

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    memset(a, -1, sizeof(a));
    int sum = 0;
    cin >> n;
    for (int i = 0; i < n; i++) {
        int x, k;
        cin >> x >> k;
        if (a[x] == -1) {
            a[x] = k;
        } else if (a[x] != k) {
            sum++;
            a[x] = k;
        }
    }
    cout << sum << "\n";
}
