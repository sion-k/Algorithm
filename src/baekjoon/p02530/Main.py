A, B, C = map(int, input().split())
D = int(input())
S = (3600 * A + 60 * B + C + D) % 86400
print(S // 3600, (S % 3600) // 60, S % 60)

