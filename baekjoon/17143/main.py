n, m, k = map(int, input().split())
a = [list(map(int, input().split())) for _ in range(k)]

dx, dy, dd = [0, -1, 1, 0, 0], [0, 0, 0, 1, -1], [0, 2, 1, 4, 3]


def move(x, y, s, d):
    def step(x, y, d):
        def in_range(x, y):
            return 1 <= x <= n and 1 <= y <= m

        nx, ny = x + dx[d], y + dy[d]
        if not in_range(nx, ny):
            d = dd[d]
            nx, ny = x + dx[d], y + dy[d]

        return nx, ny, d

    for _ in range(s):
        x, y, d = step(x, y, d)

    return x, y, d


def boom():
    global a

    cand = [[(0, -1)] * (m + 1) for _ in range(n + 1)]

    for i, (x, y, _, _, z) in enumerate(a):
        cand[x][y] = max(cand[x][y], (z, i))

    a = [[x, y, s, d, z]
         for i, (x, y, s, d, z) in enumerate(a) if cand[x][y][1] == i]


def fish(f_y):
    global a

    cand = (n + 1, -1, 0)
    for i, (x, y, _, _, z) in enumerate(a):
        if y == f_y:
            cand = min(cand, (x, i, z))

    a = [[x, y, s, d, z]
         for i, (x, y, s, d, z) in enumerate(a) if i != cand[1]]

    return cand[2] if cand[1] != -1 else 0


ret = 0

for f_y in range(1, m + 1):
    ret += fish(f_y)

    na = []
    for x, y, s, d, z in a:
        x, y, d = move(x, y, s, d)
        na.append([x, y, s, d, z])
    a = na

    boom()

print(ret)
