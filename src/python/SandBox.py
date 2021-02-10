# 벽 부수고 이동하기
import sys
from collections import deque

input = sys.stdin.readline

# get input & make maze
(N, M) = list(map(int, input().split()))
maze = [[2 for i in range(M + 2)]]
for _ in range(N):
    temp = list(map(int, list(input().strip())))
    temp.insert(0, 2)
    temp.append(2)
    maze.append(temp)
maze.append([2 for i in range(M + 2)])

# compute with BFS
queue = deque([(1, 1, True, 1)])  # n, m, can break, time

result = -1
visited = [[False for m in range(M + 2)] for n in range(N + 2)]
visited[1][1] = True


def bfs(n, m, b, v, t):
    if not visited[n][m]:
        if maze[n][m] == t:
            if t == 1:
                if b:
                    queue.append((n, m, False, v + 1))
            else:
                queue.append((n, m, b, v + 1))
            visited[n][m] = True


while len(queue) > 0:
    (n, m, b, v) = queue.popleft()
    before = len(queue)
    if n == N and m == M:
        result = v
        break

    bfs(n - 1, m, b, v, 0)
    bfs(n + 1, m, b, v, 0)
    bfs(n, m - 1, b, v, 0)
    bfs(n, m + 1, b, v, 0)

    bfs(n - 1, m, b, v, 1)
    bfs(n + 1, m, b, v, 1)
    bfs(n, m - 1, b, v, 1)
    bfs(n, m + 1, b, v, 1)

print(result)