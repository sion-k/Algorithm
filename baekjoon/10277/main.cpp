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

class Segment_tree {
public:
    vector<int> tmin, tmax;
    vector<int> p;

    const int INF = INT_MAX;

    Segment_tree(int n): tmin(4 * n), tmax(4 * n), p(4 * n) {}

    int min(int v, int vl, int vr, int ql, int qr) {
        if (ql <= vl && vr <= qr) return tmin[v];
        if (qr < vl || vr < ql) return INF;
        push(v);
        int vm = (vl + vr) / 2;
        return ::min(min(2 * v, vl, vm, ql, qr), min(2 * v + 1, vm + 1, vr, ql, qr));
    }

    int max(int v, int vl, int vr, int ql, int qr) {
        if (ql <= vl && vr <= qr) return tmax[v];
        if (qr < vl || vr < ql) return -INF;
        push(v);
        int vm = (vl + vr) / 2;
        return ::max(max(2 * v, vl, vm, ql, qr), max(2 * v + 1, vm + 1, vr, ql, qr));
    }

    void add(int v, int vl, int vr, int ql, int qr, int d) {
        if (qr < vl || vr < ql) return;
        if (ql <= vl && vr <= qr) {
            tmin[v] += d, tmax[v] += d;
            p[v] += d;
        } else {
            push(v);
            int vm = (vl + vr) / 2;
            add(2 * v, vl, vm, ql, qr, d);
            add(2 * v + 1, vm + 1, vr, ql, qr, d);
            tmin[v] = ::min(tmin[2 * v], tmin[2 * v + 1]);
            tmax[v] = ::max(tmax[2 * v], tmax[2 * v + 1]);
        }
    }

    void push(int v) {
        tmin[2 * v] += p[v], tmin[2 * v + 1] += p[v];
        tmax[2 * v] += p[v], tmax[2 * v + 1] += p[v];
        p[2 * v] += p[v], p[2 * v + 1] += p[v];
        p[v] = 0;
    }
};

int main() {
    FAST();
    int c, n, o;
    cin >> c >> n >> o;
    vector<int> a(c);
    Segment_tree t(c);
    for (int i = 0; i < o; i++) {
        string op;
        cin >> op;
        if (op[0] == 'c') {
            int x, s;
            cin >> x >> s;
            a[x] = t.min(1, 0, c - 1, x, x);
            int v = s >= 0 ? min(n, a[x] + s) : max(0, a[x] + s);
            int d = v - a[x];
            a[x] = v;
            t.add(1, 0, c - 1, x, x, d);
            cout << d << "\n";
        } else if (op[0] == 'g') {
            int l, r, s;
            cin >> l >> r >> s;
            int m = s >= 0 ? t.max(1, 0, c - 1, l, r) : t.min(1, 0, c - 1, l, r);
            int v = s >= 0 ? min(n, m + s) : max(0, m + s);
            int d = v - m;
            t.add(1, 0, c - 1, l, r, d);
            cout << d << "\n";
        } else {
            int x;
            cin >> x;
            a[x] = t.min(1, 0, c - 1, x, x);
            cout << a[x] << "\n";   
        }
    }
}
