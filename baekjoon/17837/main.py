n, k = map(int, input().split())
color = [list(map(int, input().split())) for _ in range(n)]

piece = []
for _ in range(k):
    x, y, d = map(int, input().split())
    piece.append([x - 1, y - 1, 0, d - 1])

board = [[[] for _ in range(n)] for _ in range(n)]

for i, (x, y, _, _) in enumerate(piece):
    board[x][y].append(i)

dx, dy, dd = [0, 0, -1, 1], [1, -1, 0, 0], [1, 0, 3, 2]


def move(k):
    x, y, z, d = piece[k]
    nx, ny = x + dx[d], y + dy[d]

    def in_range(x, y):
        return 0 <= x < n and 0 <= y < n

    if not in_range(nx, ny) or color[nx][ny] == 2:
        d = dd[d]
        piece[board[x][y][z]][-1] = d

        nx, ny = x + dx[d], y + dy[d]

    if in_range(nx, ny) and color[nx][ny] != 2:
        s = board[x][y][z:] if color[nx][ny] == 0 else board[x][y][z:][::-1]
        for i in s:
            board[x][y].pop()
            piece[i] = [nx, ny, len(board[nx][ny]), piece[i][-1]]
            board[nx][ny].append(i)


def turn():
    for i in range(k):
        move(i)
        for _, _, z, _ in piece:
            if z >= 3:
                return False

    return True


for t in range(1, 1002):
    if not turn():
        break

print(-1 if t == 1001 else t)
