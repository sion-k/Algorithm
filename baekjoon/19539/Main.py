N = int(input())
S = map(int, input().split())
sum = 0; cnt = 0
for x in S:
	sum += x
	cnt += x // 2
print("YES" if sum % 3 == 0 and cnt >= sum // 3 else "NO")
