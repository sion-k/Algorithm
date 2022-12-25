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

    int n, k;
    cin >> n >> k;

    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    vector<long long> p(n);
    for (int i = 0; i < n; i++) {
        p[i] = a[i];

        if (i - 1 >= 0) {
            p[i] += p[i - 1];
        }
    }

    long long sum = 0;
    for (int i = 0; i < n; i++) if (p[i] == (long long)k * (i + 1)) {
        sum++;
    }

    map<long long, int> m;

    for (int i = 0; i < n; i++) {
        long long g = p[i] - (long long)k * i;

        sum += m[g];
        m[g]++;
    }

    cout << sum << "\n";
}
