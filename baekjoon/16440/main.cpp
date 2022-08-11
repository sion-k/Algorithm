#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define FAST() cin.tie(0)->sync_with_stdio(0)
#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z)                                                   \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", "    \
         << (y) << ", " << (z) << ")\n"
#define deb_tuple(s)                                                           \
    for (int i = 0; i < SIZE(s); i++)                                          \
        cout << s[i] << " \n"[i == SIZE(s) - 1];
using namespace std;

int main() {
    FAST();
    int n;
    cin >> n;
    string s;
    cin >> s;
    vector<int> p(n);
    for (int i = 0; i < n; i++) {
        p[i] = s[i] == 's';
        if (i - 1 >= 0) {
            p[i] += p[i - 1];
        }
    }
    if (p[n / 2 - 1] == n / 4) {
        cout << 1 << "\n" << n / 2 << "\n";
    } else {
        for (int i = n / 2 - 1; i < n; i++) {
            int j = i - n / 2;
            int sum = p[i];
            if (j >= 0) {
                sum -= p[j];
            }
            if (sum == n / 4) {
                cout << 2 << "\n";
                cout << j + 1 << " " << i + 1 << "\n";
                return 0;
            }
        }
    }
}
