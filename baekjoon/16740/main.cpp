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
    unordered_set<int> s;
    for (auto& x : a) {
        cin >> x;
        s.insert(x);
    }

    sort(ALL(a));

    int max = 0;

    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            int len = 2;
            int p = a[j], d = a[j] - a[i];

            while (s.count(p + d)) {
                p += d;
                len++;
            }

            max = ::max(max, len);
        }
    }

    cout << max << "\n";
}
