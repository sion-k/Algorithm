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

int solve(int n, vector<int>& a) {
    vector<int> p(n);
    for (int i = 0; i < n; i++) {
        p[i] = a[i];
        if (i - 1 >= 0) {
            p[i] += p[i - 1];
        }
    }

    int sum = p[n - 1] - p[0];

    int max = 0;
    for (int i = 1; i < n; i++) {
        max = ::max(max, p[n - 1] - p[i] - a[i]);
    }

    return sum + max;
}

int mid(int n, vector<int>& a) {
    vector<int> p(n);
    for (int i = 0; i < n; i++) {
        p[i] = a[i];
        if (i - 1 >= 0) {
            p[i] += p[i - 1];
        }
    }

    int max = 0;

    for (int i = 1; i < n - 1; i++) {
        max = ::max(max, p[i] - a[0] + p[n - 2] - p[i - 1]);
    }

    return max;
}

int main() {
    FAST();

    int n;
    cin >> n;

    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    int max = solve(n, a);

    reverse(ALL(a));

    max = ::max(max, solve(n, a));
    max = ::max(max, mid(n, a));

    cout << max << "\n";
}
