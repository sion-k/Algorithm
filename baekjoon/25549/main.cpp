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

int n;
vector<int> a, b;
vector<vector<int>> children;

// 이분 탐색을 통해 mex(s) 반환
int binary_search(set<int>& s) {
    if (!s.count(0)) {
        return 0;
    }

    int lo = 0, hi = n;
    while (lo + 1 < hi) {
        int mid = (lo + hi) / 2;

        if (s.count(mid)&&) {
            lo = mid;
        } else {
            hi = mid;
        }
    }

    return hi;
}

set<int> dfs(int here) {
    set<int> ret;
    ret.insert(a[here]);

    b[here] = a[here] == 0;

    for (int ch : children[here]) {
        auto cand = dfs(ch);

        if (SIZE(ret) < SIZE(cand)) {
            swap(ret, cand);
        }

        for (auto c : cand) {
            ret.insert(c);
        }

    }


    return ret;
}

int main() {
    FAST();

    cin >> n;

    children.resize(n + 1);
    int root = 1;
    for (int here = 1; here <= n; here++) {
        int parent;
        cin >> parent;

        children[parent].push_back(here);
        if (parent == -1) {
            root = here;
        }
    }

    a.resize(n + 1), b.resize(n + 1);
    for (int i = 1; i <= n; i++) {
        cin >> a[i];
    }

    dfs(root);

    for (int i = 1; i <= n; i++) {
        cout << b[i] << " \n"[i == n];
    }
}
