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
    reverse(ALL(a));

    a.push_back(0);

    vector<int> b;
    int cnt = 0;
    for (int i = 0; i < n + 1; i++) {
        if (i - 1 >= 0 && a[i - 1] > a[i]) {
            b.push_back(cnt);
            cnt = 1;
        } else {
            cnt++;
        }
    }

    while (SIZE(b) < n) {
        b.push_back(0);
    }

    for (int i = 0; i < n; i++) {
        cout << b[i] << "\n";
    }
}
