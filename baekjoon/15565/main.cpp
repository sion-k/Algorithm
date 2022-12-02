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

int main() {
    FAST();

    int n, k;
    cin >> n >> k;

    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    int head = 0, tail = 0;
    int sum = 0, min = n + 1;

    while (head < n) {
        while (tail < n && sum < k) {
            sum += a[tail] == 1;
            tail++;
        }

        if (sum == k) {
            min = ::min(min, tail - head);
        }

        sum -= a[head] == 1;
        head++;
    }

    cout << (min == n + 1 ? -1 : min) << "\n";
}
