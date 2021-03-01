import sys
input = sys.stdin.readline
N, M = map(int, input().split())
chicken = []
open = []
home = []
for i in range(N):
    row = list(map(int, input().split()))
    for j in row :
        if row[j] == 1 : home.append((i, j))
        if row[j] == 2:
            chicken.append((i, j))
            open.append(True)

# p1과 p2사이의 거리를 반환
def dist(p1, p2) : return abs(p1[0] - p2[0]) + abs(p1[1] - p2[1])


# 좌표 here에서 가장 가까운 치킨 집 까지의 거리 반환
def minDist(here):
    ret = 10000
    for i in range(len(chicken)):
        print(open)
        if open[i] : ret = min(ret, dist(here, chicken[i]))
    return ret


# 도시의 치킨 거리 반환
def chickenDist():
    sum = 0
    for h in home : sum += minDist(h)
    return sum


# i번째 치킨집 부터 k개를 폐업하는 경우 도시의 최소 치킨 거리
def btk(i, k):
    if i == len(chicken) : return chickenDist() if k == 0 else 987654321
    ret = btk(i + 1, k) # i번째 치킨집을 폐업하지 않는 경우
    if k :
        open[i] = False
        ret = min(ret, btk(i + 1, k - 1))
        open[i] = True
    return ret

print(chicken)
print(btk(0, M))
