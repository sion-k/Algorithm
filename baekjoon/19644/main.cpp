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

    int ml, mk;
    cin >> ml >> mk;

    int c;
    cin >> c;

    vector<int> z(n + 1);
    for (int i = 1; i <= n; i++) {
        cin >> z[i];
    }

    // 해당 위치에 입힌 데미지
    int d = 0;
    vector<int> p(n + 1);
    for (int i = 1; i <= n; i++) {
        d += p[i];

        // 지금 당장 공격한다고 해도 해당 좀비를 처리할 수 없어서
        // 수평 세열 지향성 지뢰를 사용해야 하는 경우
        if (z[i] - (d + mk) > 0) {
            c--;
        } else {
            if (i + 1 <= n) {
                p[i + 1] += mk;
            }
            if (i + ml <= n) {
                p[i + ml] -= mk;
            }
        }
    }

    cout << (c >= 0 ? "YES" : "NO") << "\n";
}
