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
    vector<int> s(4);
    for (int i = 0; i < 4; i++) {
        cin >> s[i];
    }
    int sum = accumulate(ALL(s), 0);
    int min = 10000;
    for (int i = 0; i < (1 << 4); i++) {
        int temp = 0;
        for (int j = 0; j < 4; j++) {
            if (i & (1 << j)) {
                temp += s[j];
            }
        }
        min = ::min(min, abs(temp - (sum - temp)));
    }
    cout << min << "\n";
}
