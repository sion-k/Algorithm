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
    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    sort(ALL(a));

    vector<int> b, c;
    for (int i = 0; i < n; i++) {
        if (i == 0 || a[i - 1] < a[i]) {
            b.push_back(a[i]);
        } else {
            c.push_back(a[i]);
        }
    }

    a = vector<int>();
    for (auto& x : b) {
        a.push_back(x);
    }
    for (auto& x : c) {
        a.push_back(x);
    }

    int j = 0, k = 1;
    for (int i = 0; i < n - j; i++) {
        if (a[i] != k) {
            // 남은게 2개 이상인 경우
            if (n - j - i >= 2) {
                i--;
                j += 2;
                k++;
            } else {
                break;
            }
        } else {
            k++;
        }
    }
    cout << k - 1 << "\n";
}
