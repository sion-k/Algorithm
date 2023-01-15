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

    int n;
    cin >> n;

    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    sort(ALL(a));

    int head = 0, tail = n - 1;
    int min = 2 * 1e9;
    pair<int, int> ret;

    while (head < n) {
        // 같은 용액을 두 번 고르며 안되니까 head < tail이 유지되도록 합니다
        while (tail - 1 > head && abs(a[head] + a[tail - 1]) <= abs(a[head] + a[tail])) {
            tail--;
        }

        int cand = abs(a[head] + a[tail]);
        // 같은 용액일 수도 있으니 확인합니다.
        if (head != tail && min > cand) {
            min = cand;
            ret = { head, tail };
        }

        head++;
    }

    cout << a[ret.first] << " " << a[ret.second] << "\n";
}
