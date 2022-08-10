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

int main() {
    FAST();
    int n, k;
    cin >> n >> k;
    vector<int> b(n);
    for (auto &x : b) {
        cin >> x;
    }
    vector<int> a;
    a.insert(a.end(), ALL(b));
    a.insert(a.end(), ALL(b));
    int sum = 0, max = 0;
    queue<int> q;
    for (int i = 0; i < 2 * n; i++) {
        if (SIZE(q) == k) {
            sum -= q.front();
            q.pop();
        }
        q.push(a[i]);
        sum += a[i];
        max = ::max(max, sum);
    }
    cout << max << "\n";
}
