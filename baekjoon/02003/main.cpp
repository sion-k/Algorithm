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

    int n, m;
    cin >> n >> m;

    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    int cnt = 0;
    int head = 0, tail = 0;
    // sum = [head, tail) 구간의 합
    int sum = 0;

    while (head < n) {
        // a[head]에서 시작하는 합이 m을 넘지 않는 최대한 긴 후보 구간
        while (tail < n && sum + a[tail] <= m) {
            sum += a[tail];
            tail++;
        }

        if (sum == m) {
            cnt++;
        }

        // head 한 칸 전진
        sum -= a[head];
        head++;
    }

    cout << cnt << "\n";
}
