#include <bits/stdc++.h>
#define deb(x) cout << #x << " : " << x << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << x << ", " << y << ")\n"
#define deb_tuple(x, y, z)                                                     \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << x << ", " << y \
         << ", " << z << ")\n"
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int root_1;
int root_2;
int lft_1[26];
int lft_2[26];
int rht_1[26];
int rht_2[26];
vector<int> s1;
vector<int> s2;

void f(int here, int left[], int right[], vector<int>& s) {
    right[here] = s.back();
    s.pop_back();
    if (right[here] != -1) {
        f(right[here], left, right, s);
    }
    left[here] = s.back();
    s.pop_back();
    if (left[here] != -1) {
        f(left[here], left, right, s);
    }
}

bool g(int u, int v) {
    if (u == -1 && v == -1) {
        return true;
    }
    if (u == v && g(lft_1[u], lft_2[v]) && g(rht_1[u], rht_2[v])) {
        return true;
    }
    if (u == v && g(lft_1[u], rht_2[v]) && g(rht_1[u], lft_2[v])) {
        return true;
    }
    return false;
}

void pre(int here, int left[], int right[]) {
    if (left[here] != -1) {
        pre(left[here], left, right);
    }
    if (right[here] != -1) {
        pre(right[here], left, right);
    }
}

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    int tc;
    cin >> tc;
    while (tc--) {
        memset(lft_1, -1, sizeof(lft_1));
        memset(rht_1, -1, sizeof(rht_1));
        s1.clear();
        while (true) {
            string x;
            cin >> x;
            if (x == "end") break;
            if (x == "nil") {
                s1.push_back(-1);
            } else {
                s1.push_back((int)x[0] - 'A');
            }
        }
        root_1 = s1.back();
        s1.pop_back();
        memset(lft_2, -1, sizeof(lft_2));
        memset(rht_2, -1, sizeof(rht_2));
        s2.clear();
        while (true) {
            string x;
            cin >> x;
            if (x == "end") break;
            if (x == "nil") {
                s2.push_back(-1);
            } else {
                s2.push_back((int)x[0] - 'A');
            }
        }
        root_2 = s2.back();
        s2.pop_back();
        if (root_1 == -1 && root_2 == -1) {
            cout << "true" << "\n";
            continue;
        } else if (root_1 == -1 || root_2 == -1) {
            cout << "false" << "\n";
            continue;
        }
        f(root_1, lft_1, rht_1, s1);
        f(root_2, lft_2, rht_2, s2);
        cout << (g(root_1, root_2) ? "true" : "false") << "\n";
    }
}
