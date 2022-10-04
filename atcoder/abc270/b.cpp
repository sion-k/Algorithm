#include <bits/stdc++.h>

#define FAST() cin.tie(0)->sync_with_stdio(0)
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())

#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y) cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z) cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", " << (y) << ", " << (z) << ")\n"
#define deb_tuple(s) for (int i = 0; i < SIZE(s); i++) cout << s[i] << " \n"[i == SIZE(s) - 1];

using namespace std;

int main() {
    FAST();
    int x, y, z;
    cin >> x >> y >> z;

    if (0 < y && y < x && y < z || x < y && y < 0 && z < y) {
        cout << -1 << "\n";
        return 0;
    }

    int sum = abs(x);

    if (0 < y && y < x && z < 0 || x < y && y < 0 && 0 < z) {
        sum += 2 * abs(z);
    }

    cout << sum << "\n";
}
