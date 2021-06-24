A, B = map(int, input().split())
C = int(input())
t = 60 * A + B + C
print((t // 60) % 24, t % 60)

