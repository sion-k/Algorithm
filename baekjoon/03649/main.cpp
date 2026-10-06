#include <bits/stdc++.h>

#define FAST() cin.tie(0)->sync_with_stdio(0)
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())

#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y) cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z) cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", " << (y) << ", " << (z) << ")\n"
#define deb_tuple(s) \
    cout << "["; \
    for (int __i = 0; __i < SIZE(s); __i++) { \
        cout << s[__i]; \
        if (__i != SIZE(s) - 1) cout << ", "; \
    } \
    cout << "]\n";

using namespace std;

void solve(int x) {
    int n;
    cin >> n;

    vector<int> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    sort(ALL(a));

    if (n <= 1) {
        cout << "danger" << '\n';
        return;
    }

    int head = 0, tail = n - 1;
    pair<int, int> max;
    while (head < tail) {
        if (a[head] + a[tail] > x) {
            tail--;
        } else if (a[head] + a[tail] < x) {
            head++;
        } else {
            if (max.first == 0 || abs(max.first - max.second) < abs(a[head] - a[tail])) {
                max.first = a[head], max.second = a[tail];
            }
            head++, tail--;
        }
    }

    if (max.first) {
        cout << "yes" << ' ' << max.first << ' ' << max.second << '\n';
    } else {
        cout << "danger" << '\n';
    }
}

int main() {
    FAST();

    while (true) {
        int x;
        cin >> x;

        if (cin.eof()) {
            break;
        }

        x *= 10'000'000;
        solve(x);
    }
}
