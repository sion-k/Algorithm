T = int(input())
for tc in range(T):
    H, W, N = map(int, input().split())
    Y = (N - 1) % H + 1
    X = (N - 1) // H + 1
    print(("%d" + "%02d") % (Y, X))
