S = [int(input()) for i in range(7)]
sum = 0
m = 100
for x in S:
	if x % 2 == 1:
		sum += x
		m = min(m, x)
if sum == 0:
	print(-1)
else:
	print(sum)
	print(m)
