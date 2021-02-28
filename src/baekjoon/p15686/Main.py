import sys
input = sys.stdin.readline
N, M = map(int, input().split())
MAP = [[0] * N for i in range(N)]
chicken = []
home = []
for i in range(N):
    row = list(map(int, input().split()))
    for j in range(N):
        MAP[i][j] = row[j]
        if MAP[i][j] == 1 : home.append((i, j))
        if MAP[i][j] == 2 : chicken.append((i, j))

# p1과 p2사이의 거리를 반환
def dist(p1, p2) : return abs(p1[0] - p2[0]) + abs(p1[1] - p2[1])

# 좌표 p에서 가장 가까운 치킨 집 까지의 거리 반환
def minDist(p):
    ret = 100
    for c in chicken : ret = min(ret, dist(p, c))
    return ret

# 도시의 치킨 거리 반환
def chickenDist():
    sum = 0
    for h in home : sum += minDist(h)
    return sum

# i번째 치킨집 부터 k개를 폐업하는 경우 도시의 최소 치킨 거리
def btk(i, k):
    if i == len(chicken) : return