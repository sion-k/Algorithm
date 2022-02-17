#include <bits/stdc++.h>
#define deb(x) cout << #x << " : " << x << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << x << ", " << y << ")\n"
#define deb_tuple(x, y, z)                                                     \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << x << ", " << y \
         << ", " << z << ")\n"
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int n;

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n;
    int x = 9, length = 1, ret = 0;
    while (n) {
        int sub = min(n, x);
        n -= sub;
        x *= 10;
        ret += length * sub;
        length++;
    }
    cout << ret << "\n";
}
