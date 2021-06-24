T = int(input())
for tc in range(T):
    H, W, L = map(int, input().split())
    Y = (L - 1) % H + 1
    X = (L - 1) // H + 1
    print(("%d" + "%02d") % (Y, X))
