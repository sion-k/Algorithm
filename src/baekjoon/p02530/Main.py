A, B, C = map(int, input().split())
D = int(input())
S = 60 * 60 * A + 60 * B + C + D
print((S // 60 * 60) % 24, (S // 60) % 60, S % 60)
