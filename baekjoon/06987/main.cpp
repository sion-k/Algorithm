#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int s[6][3];

// i번째 팀이 j번째 팀과 경기를 할 차례일 때,
// 가능한 결과표인지 반환
bool btk(int i, int j) {
    if (j == 6) return btk(i + 1, i + 2);
    if (i == 6) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                if (s[i][j]) return false;
            }
        }
        return true;
    }
    // cout << "(" << i << ", " << j << ")\n";
    // i번째 팀이 승리
    if (s[i][0] && s[j][2]) {
        s[i][0]--; s[j][2]--;
        if (btk(i, j + 1)) return true;
        s[i][0]++; s[j][2]++;
    }
    // 무승부
    if (s[i][1] && s[j][1]) {
        s[i][1]--; s[j][1]--;
        if (btk(i, j + 1)) return true;
        s[i][1]++; s[j][1]++;
    }
    // i번째 팀 패배
    if (s[i][2] && s[j][0]) {
        s[i][2]--; s[j][0]--;
        if (btk(i, j + 1)) return true;
        s[i][2]++; s[j][0]++;
    }
    return false;
}

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    for (int k = 0; k < 4; k++) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                cin >> s[i][j];
            }
        }
        cout << btk(0, 1) << " ";
    }
}
