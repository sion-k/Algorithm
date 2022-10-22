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

map<int, vector<int>> wy, wx;

// p에서 t보다 큰 최초의 지점 - 1
int upper(vector<int>& p, int t) {
    int lo = -1, hi = SIZE(p) - 1;

    while (lo + 1 < hi) {
        int mid = (lo + hi) / 2;
        if (p[mid] > t) hi = mid;
        else lo = mid;
    }

    return p[hi] - 1;
}

// p에서 t보다 작은 마지막 지점 + 1
int lower(vector<int>& p, int t) {
    int lo = 0, hi = SIZE(p);

    while (lo + 1 < hi) {
        int mid = (lo + hi) / 2;
        if (p[mid] >= t) hi = mid;
        else lo = mid;
    }

    return p[lo] + 1;
}

int main() {
    FAST();

    int h, w, rs, cs;
    cin >> h >> w >> rs >> cs;

    int n;
    cin >> n;
    for (int i = 0; i < n; i++) {
        int y, x;
        cin >> y >> x;

        vector<int>& p = wy[y];

        if (p.empty()) {
            p.push_back(0);
        }

        p.push_back(x);

        vector<int>& q = wx[x];

        if (q.empty()) {
            q.push_back(0);
        }

        q.push_back(y);
    }

    for (auto& a : wy) {
        vector<int>& p = a.second;
        p.push_back(w + 1);

        sort(ALL(p));
    }

    for (auto& a : wx) {
        vector<int>& p = a.second;
        p.push_back(h + 1);

        sort(ALL(p));
    }

    int q;
    cin >> q;
    for (int i = 0; i < q; i++) {
        string d; int l;
        cin >> d >> l;

        switch (d[0]) {
        case 'L':
            if (!wy[rs].empty()) {
                cs = max(max(cs - l, 1), lower(wy[rs], cs));
            } else {
                cs = max(cs - l, 1);
            }
            break;
        case 'R':
            if (!wy[rs].empty()) {
                cs = min(min(cs + l, w), upper(wy[rs], cs));
            } else {
                cs = min(cs + l, w);
            }
            break;
        case 'U':
            if (!wx[cs].empty()) {
                rs = max(max(rs - l, 1), lower(wx[cs], rs));
            } else {
                rs = max(rs - l, 1);
            }
            break;
        case 'D':
            if (!wx[cs].empty()) {
                rs = min(min(rs + l, h), upper(wx[cs], rs));

            } else {
                rs = min(rs + l, h);
            }
            break;
        }
        cout << rs << " " << cs << "\n";
    }
}
