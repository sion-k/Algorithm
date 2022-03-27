#include <bits/stdc++.h>
#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_tuple(x, y, z)                                                     \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", "    \
         << (y) << ", " << (z) << ")\n"
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
#define OPEN(t) freopen("data.txt", (t), stdin)
using namespace std;

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    int a, b;
    cin >> a >> b;
    if (abs(a - b) % 3 == 0) {
        cout << a / 3 << " " << a % 3 << " " <<  b / 3 << "\n";
    } else {
        cout << -1 << "\n";
    }
}
