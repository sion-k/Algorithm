import sys
input = sys.stdin.readline
print = sys.stdout.write

def solve(n):
	ret = 0
	for i in range(len(n) - 1, -1, -1):
		ret += int(n[0:i + 1])
	return ret
	
TC = int(input())
for tc in range(TC):
	l, r = input().split()
	print(str(solve(r) - solve(l)))
	print("\n")
	