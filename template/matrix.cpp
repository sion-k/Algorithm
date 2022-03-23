#include <bits/stdc++.h>
#define SIZE(x) (int)((x).size())
using namespace std;
const int MOD = 1e9 + 7;

vector<vector<int>> mul(const vector<vector<int>>& a, const vector<vector<int>>& b) {
    int n = SIZE(a);
    vector<vector<int>> ret = vector<vector<int>>(n, vector<int>(n));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                ret[i][j] = (ret[i][j] + (int)(((long long)a[i][k] * b[k][j]) % MOD)) % MOD;
            }
        }
    }
    return ret;
}

vector<vector<int>> pow(vector<vector<int>>& a, long long b) {
    if (b == 0) {
        int n = SIZE(a);
        vector<vector<int>> i(SIZE(a), vector<int>(SIZE(a)));
        for (int k = 0; k < n; k++) {
            i[k][k] = 1;
        }
        return i;
    }
    if (b % 2) {
        return mul(a, pow(a, b - 1));
    }
    vector<vector<int>> p = pow(a, b / 2);
    return mul(p, p);
}
