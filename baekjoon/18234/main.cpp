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

    int n, t;
    cin >> n >> t;

    long long sum = 0;

    vector<int> a(n);
    for (int i = 0; i < n; i++) {
        int w;
        cin >> w >> a[i];

        sum += w;
    }

    sort(ALL(a));
    reverse(ALL(a));

    for (int i = 0; i < n; i++) {
        sum += (long long)a[i] * (t - i - 1);
    }

    cout << sum << "\n";
}
