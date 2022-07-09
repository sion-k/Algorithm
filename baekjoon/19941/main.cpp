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
    int n, k;
    cin >> n >> k;
    string a;
    cin >> a;
    int sum = 0;
    for (int i = 0; i < n; i++) {
        if (a[i] == 'H') {
            for (int j = -k; j <= k; j++) {
                if (0 <= i + j && i + j < n && a[i + j] == 'P') {
                    a[i + j] = '.';
                    sum++;
                    break;
                }
            }
        }
    }
    cout << sum << "\n";
}
