#include <bits/stdc++.h>
#define deb(x) cout << #x << " : " << x << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << x << ", " << y << ")\n"
#define deb_tuple(x, y, z)                                                     \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << x << ", " << y \
         << ", " << z << ")\n"
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
using namespace std;

// a / b보다 같거나 크지않은 최대의 x / d를 반환 
int bs(int a, int b, int d) {
    int lo = 0; int hi = 32767;
    while (lo + 1 < hi) {
        int mid = (lo + hi) / 2;
        if (a * d > mid * b) lo = mid;
        else hi = mid;
    }
    return lo;
}

int gcd(int a, int b) {
    if (b == 0) return a;
    return gcd(b, a % b);
}

bool compare(pair<int, int>& u, pair<int, int>& v) {
    return (double)u.first / u.second < (double)v.first / v.second;
}

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    int a, b;
    cin >> a >> b;
    vector<pair<int, int>> cand;
    for (int j = 1; j <= 32767; j++) {
        int i = bs(a, b, j);
        for (int k = 0; k <= 2; k++) {
            if (i + k != 0) {
                int t = gcd(i + k, j);
                cand.emplace_back((i + k) / t, j / t);
            }
        }
    }
    sort(ALL(cand), compare);
    cand.erase(unique(ALL(cand)), cand.end());
    for (int i = 0; i < SIZE(cand); i++) {
        if (cand[i].first == a && cand[i].second == b) {
            cand.erase(cand.begin() + i);
        }
    }
    pair<int, int> ret = { 1, 1 };
    double min = 987654321;
    for (int i = 0; i < SIZE(cand); i++) {
        double diff = abs((double)a / b - (double)cand[i].first / cand[i].second);
        if (min > diff) {
            min = diff;
            ret.first = cand[i].first;
            ret.second = cand[i].second;
        }
    }
    int t = gcd(ret.first, ret.second);
    ret.first /= t; ret.second /= t;
    std::cout << ret.first << " " << ret.second << "\n";
}
