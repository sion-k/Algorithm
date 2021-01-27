# 팰린드롬?
import sys
input = sys.stdin.readline

N = int(input())
number = list(map(int, input().split()))
M = int(input())
cnt = [[-1 for j in range(N)] for i in range(N)]
cnt[0] = [1]*N

def dp(start, end):
    global cnt
    l = end-start
    if cnt[l][start-1]!=-1:
        return cnt[l][start-1]
    have_to_compare = (True if dp(start, start+l//2-(l+1)%2)==dp(end-l//2+(l+1)%2, end) else False)
    if have_to_compare:
        result = 1
        for i in range((l+1)//2):
            if number[start+i-1]!=number[end-i-1]:
                result = 0
                break
        cnt[l][start-1] = result
        return cnt[l][start-1]
    cnt[l][start-1] = 0
    return cnt[l][start-1]
for t in range(M):
    (s, e) = list(map(int, input().split()))
    print(dp(s, e))