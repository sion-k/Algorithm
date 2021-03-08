import sys
# x[i, j]가 팰린드롬인지 반환
def isPal(x, i, j) :
    if i >= j : return True
    if x[i] == x[j] : return isPal(x, i + 1, j - 1)
    else : return False

L = sys.stdin.readline().rstrip()
while L != "0" :
    sys.stdout.write("yes" if isPal(L, 0, len(L) - 1) else "no")
    sys.stdout.write("\n")
    L = sys.stdin.readline().rstrip()
