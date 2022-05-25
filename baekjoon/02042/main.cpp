#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define FAST() cin.tie(0)->sync_with_stdio(0)
#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z)                                                   \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", "    \
         << (y) << ", " << (z) << ")\n"
#define deb_tuple(s)                                                           \
    for (int i = 0; i < SIZE(s); i++)                                          \
        cout << s[i] << " \n"[i == SIZE(s) - 1];
using namespace std;

class Fenwick_Tree {
public:
    vector<long long> t;

    Fenwick_Tree(int n) : t(n) {}

    void add(int i, long long x) {
        while (i < SIZE(t)) {
            t[i] += x;
            i += (i & -i);
        }
    }

    long long sum(int i) {
        long long sum = 0;
        while (i > 0) {
            sum += t[i];
            i -= (i & -i);
        }
        return sum;
    }
};

int main() {
    FAST();
    int n, m, k;
    cin >> n >> m >> k;
    Fenwick_Tree t = Fenwick_Tree(n + 1);
    for (int i = 1; i <= n; i++) {
        long long x;
        cin >> x;
        t.add(i, x);
    }
    for (int i = 0; i < m + k; i++) {
        long long a, b, c;
        cin >> a >> b >> c;
        if (a == 1) {
            long long oldValue = t.sum(b) - t.sum(b - 1);
            t.add(b, c - oldValue);
        } else {
            long long sum = t.sum(c);
            if (b - 1 >= 1) sum -= t.sum(b - 1);
            cout << sum << "\n";
        }
    }
}
