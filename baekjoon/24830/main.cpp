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
    int n;
    cin >> n;
    long long p = 1;
    for (int i = 0; i < n; i++) {
        long long a, b;
        string o;
        cin >> a >> o >> b;
        long long ret = 0;
        if (o == "+") {
            ret = a + b - p;
        } else if (o == "-") {
            ret = (a - b) * p;
        } else if (o == "*") {
            ret = a * a * b * b;
        } else {
            if (a % 2) {
                ret = (a + 1) / 2;
            } else {
                ret = a / 2;
            }
        }
        cout << ret << "\n";
        p = ret;
    }
}
