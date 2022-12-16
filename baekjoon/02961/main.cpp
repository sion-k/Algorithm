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

    int n;
    cin >> n;

    vector<int> s(n), b(n);
    for (int i = 0; i < n; i++) {
        cin >> s[i] >> b[i];
    }

    int min = 1e9;

    for (int mask = 1; mask < (1 << n); mask++) {
        int product = 1, sum = 0;

        for (int i = 0; i < n; i++) if (mask & (1 << i)) {
            product *= s[i];
            sum += b[i];
        }

        min = ::min(min, abs(product - sum));
    }

    cout << min << "\n";
}
