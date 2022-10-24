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

    Fenwick_Tree(int n) : t(n + 1) {}

    void add(int i, long long x) {
        i++;
        while (i < SIZE(t)) {
            t[i] += x;
            i += (i & -i);
        }
    }

    long long sum(int i) {
        i++;
        long long sum = 0;
        while (i > 0) {
            sum += t[i];
            i -= (i & -i);
        }
        return sum;
    }

};

const int MAX = 1e6;

int main() {
    FAST();

    int n;
    cin >> n;

    vector<pair<int, int>> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i].first;
    }

    for (int i = 0; i < n; i++) {
        cin >> a[i].second;
    }

    sort(ALL(a));

    vector<int> p(n), q(n);
    for (int i = 0; i < n; i++) {
        p[i] = a[i].first;
        q[i] = a[i].second;
    }

    // 현재 탐색중인 구간의 왼쪽, 오른쪽 펜윅 트리 tl, tr
    Fenwick_Tree tl(MAX + 1), tr(MAX + 1);
    for (int i = 0; i < n; i++) {
        tr.add(q[i], 1);
    }

    long long sum = 0;
    for (int i = 0; i < n; i++) {
        int j = i;
        // (p, q)에서 p_i가 같은 q_i들은 하나로 처리
        while (j < n && p[i] == p[j]) {
            tr.add(q[j], -1);
            j++;
        }

        for (int k = i; k < j; k++) {
            // 왼쪽에서 q[k]보다 작은 값의 개수
            long long left = q[k] ? tl.sum(q[k] - 1) : 0;
            // 오른쪽에서 q[k]보다 큰 값의 개수
            long long right = tr.sum(MAX) - tr.sum(q[k]);
            sum += left * right;
        }

        for (int k = i; k < j; k++) {
            tl.add(q[k], 1);
        }
        i = j - 1;
    }
    cout << sum << "\n";
}
