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

    int n, m;
    cin >> n >> m;

    string s;
    cin >> s;

    long long ret = 0;
    int cnt = 0;

    for (int i = 0; i < m; i++) {
        if (cnt % 2 == 0 && s[i] == 'I' || cnt % 2 == 1 && s[i] == 'O') {
            cnt++;
        } else {
            ret += max(0, (cnt - 1) / 2 - n + 1);
            cnt = s[i] == 'I';
        }
    }

    ret += max(0, (cnt - 1) / 2 - n + 1);
    cout << ret << "\n";
}
