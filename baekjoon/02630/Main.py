import sys
dy = [0, 0, 1, 1]
dx = [0, 1, 0, 1]

input = sys.stdin.readline
N = int(input())
MAP = [list(map(int, input().split())) for i in range(N)]

# (y, x)를 왼쪽 위로 하는 한 변의 길이가 n인 정사각형 범위에서
# 잘라진 (하얀색 색종이의 수, 파란색 색종이의 수) 반환
def dc(y, x, n):
    # 기저 사례 1 : 더 이상 자를 수 없는 경우
    if n == 1 : return (0, 1) if MAP[y][x] else (1, 0)
    # 기저 사례 2 : 모두 색이 같을 경우
    merged = True
    for i in range(y, y + n):
        for j in range(x, x + n):
            if MAP[i][j] != MAP[y][x] : merged = False
    if merged : return (0, 1) if MAP[y][x] else (1, 0)
    # 주어진 범위를 4개로 나눠서 재귀 호출
    white, blue, half = 0, 0, n // 2
    for d in range(4):
        w, b = dc(y + half * dy[d], x + half * dx[d], half)
        white += w
        blue += b
    return white, blue
print("\n".join(map(str, dc(0, 0, N))))
