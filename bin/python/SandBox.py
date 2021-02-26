# 아기 상어
import sys
from collections import deque

input = sys.stdin.readline

N = int(input())
world = [[10 for i in range(N + 2)]]
for n in range(N):
    temp = list(map(int, input().split()))
    temp.insert(0, 10)
    temp.append(10)
    world.append(temp)
world.append([10 for i in range(N + 2)])

size = 2
grow = 2
time = 0
now = [-1, -1, 0]

for i in range(1, N + 1):
    for j in range(1, N + 1):
        if world[i][j] == 9:
            now = [i, j, 0]
            world[i][j] = 0

while True:
    visited = [[False for i in range(N + 2)] for j in range(N + 2)]
    queue = deque([now])
    visited[now[0]][now[1]] = True
    best = 401
    while len(queue) > 0:
        temp = queue.popleft()
        if best < temp[2]:
            break
        if world[temp[0]][temp[1]] > 0 and world[temp[0]][temp[1]] < size:
            if now[2] == 0 or now[0] > temp[0] or (now[0] == temp[0] and now[1] > temp[1]):
                now = temp
                best = temp[2]
            continue
        x = [temp[0] - 1, temp[0], temp[0], temp[0] + 1]
        y = [temp[1], temp[1] - 1, temp[1] + 1, temp[1]]
        for i in range(4):
            if not visited[x[i]][y[i]]:
                visited[x[i]][y[i]] = True
                if world[x[i]][y[i]] <= size:
                    queue.append([x[i], y[i], temp[2] + 1])
    if now[2] == 0:
        break
    time += now[2]
    now[2] = 0
    grow -= 1
    if grow == 0:
        size += 1
        grow = size
    world[now[0]][now[1]] = 0
print(time)