from collections import deque

n, m = map(int, input().split())
a = [list(input()) for _ in range(n)]

k = int(input())
b = list(map(int, input().split()))

dx, dy = [0, 0, 1, -1], [1, -1, 0, 0]


def in_range(x, y):
    return 0 <= x < n and 0 <= y < m


def target(x, k):
    y = 0 if k == 0 else m - 1

    while in_range(x, y) and a[x][y] == '.':
        y += dy[k]

    return (x, y) if in_range(x, y) else (-1, -1)


def cluster(x, y):
    q = deque()
    b = [[False] * m for _ in range(n)]
    c = []

    q.append((x, y))
    b[x][y] = True

    while q:
        x, y = q.popleft()
        c.append((x, y))

        for d in range(4):
            nx, ny = x + dx[d], y + dy[d]

            if in_range(nx, ny) and a[nx][ny] == 'x' and not b[nx][ny]:
                q.append((nx, ny))
                b[nx][ny] = True

    return c


def height(cluster):
    height = 1

    col_max = [0] * m
    for x, y in cluster:
        col_max[y] = max(col_max[y], x)

    cluster = [(x, y) for x, y in cluster if x == col_max[y]]

    while all(in_range(x + height, y) and a[x + height][y] == '.' for x, y in cluster):
        height += 1

    return height - 1


def drop(cluster, height):
    for x, y in cluster:
        a[x][y] = '.'

    for x, y in cluster:
        a[x + height][y] = 'x'


def throw(x, k):
    x, y = target(x, k)

    if x == -1 or y == -1:
        return

    a[x][y] = '.'

    for d in range(4):
        nx, ny = x + dx[d], y + dy[d]
        if in_range(nx, ny) and a[nx][ny] == 'x':
            c = cluster(nx, ny)
            h = height(c)

            if h:
                drop(c, h)
                return


for i, x in enumerate(b):
    throw(n - x, i % 2)

for row in a:
    print(*row, sep="")
