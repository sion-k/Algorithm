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

class Fenwick_Tree {
public:
    vector<long long> t;

    Fenwick_Tree(int n): t(n) {}

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

}
