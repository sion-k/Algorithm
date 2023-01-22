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

int main() {
    FAST();

    int n, a, b;
    cin >> n >> a >> b;

    string s;
    cin >> s;

    deque<int> dq;
    for (int i = 0; i < n; i++) {
        dq.push_back(s[i]);
    }

    long long min = (long long)b * n;

    for (int i = 0; i < n; i++) {
        long long sum = (long long)a * i;

        for (int j = 0; j < n / 2; j++) {
            if (dq[j] != dq[n - j - 1]) {
                sum += b;
            }
        }

        min = ::min(min, sum);

        dq.push_back(dq.front());
        dq.pop_front();
    }

    cout << min << "\n";
}
