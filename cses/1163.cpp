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

    int x, n;
    cin >> x >> n;

    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    set<int> s;
    s.insert(0), s.insert(x);

    multiset<int> ms;
    ms.insert(x);

    for (auto& x : a) {
        s.insert(x);

        int left = *(--s.find(x));
        int right = *(++s.find(x));

        ms.erase(ms.find(right - left));
        ms.insert(x - left), ms.insert(right - x);

        cout << *(ms.rbegin()) << " ";
    }

}
