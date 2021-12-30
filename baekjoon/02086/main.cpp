#include <iostream>
using namespace std;

const int MOD = 1e9;

int** mul(int **a, int **b) {
    int **ret = new int*[2];
    for (int i = 0; i < 2; i++) ret[i] = new int[2];
    for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 2; j++) {
            ret[i][j] = 0;
        }
    }
    for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 2; k++) {
                ret[i][j] += (int)(((long long)(a[i][k]) * b[k][j]) % MOD);
                ret[i][j] %= MOD;
            }
        }
    }
    return ret;
}

int **pow(int **a, long long n) {
    if (n == 0) {
        int **ret = new int*[2];
        for (int i = 0; i < 2; i++) ret[i] = new int[2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                ret[i][j] = 0;
            }
        }
        ret[0][0] = ret[1][1] = 1;
        return ret;
    }
    if (n & 1) return mul(a, pow(a, n - 1));
    else {
        int **temp = pow(a, n / 2);
        return mul(temp, temp);
    }
}

int main() {
    long long a, b;
    cin >> a >> b;
    int **m = new int*[2];
    for (int i = 0; i < 2; i++) m[i] = new int[2];
    m[0][0] = m[0][1] = m[1][0] = 1;
    m[1][1] = 0;
    int** ret = pow(m, b + 2);
    int** ret2 = pow(m, a + 1);
    cout << ((ret[1][0] - ret2[1][0]) % MOD + MOD) % MOD << endl;
}