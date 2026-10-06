n, m = map(int, input().split())
a = [list(input()) for _ in range(n)]
b = [False] * (ord('Z') + 1)

dx, dy = [0, 0, 1, -1], [1, -1, 0, 0]


def dfs(x, y):
    def in_range(x, y):
        return 0 <= x < n and 0 <= y < m

    ret = 0
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if in_range(nx, ny) and not b[ord(a[nx][ny])]:
            b[ord(a[nx][ny])] = True

            cand = 1 + dfs(nx, ny)
            if ret < cand:
                ret = cand

            b[ord(a[nx][ny])] = False

    return ret


b[ord(a[0][0])] = True
print(1 + dfs(0, 0))
