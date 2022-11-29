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

    int n, d;
    cin >> n >> d;

    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    vector<int> b, r;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < i; j++) {
            int left = b[j] - (d - 1), right = b[j] + (d - 1);
            if (left <= a[i] && a[i] <= right) {
                a[i] = right + 1;
            }
        }

        r.push_back(a[i]);

        b.push_back(a[i]);
        sort(ALL(b));
    }

    for (int i = 0; i < n; i++) {
        cout << r[i] << " \n"[i == n - 1];
    }
}
