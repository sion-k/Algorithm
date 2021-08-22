cnt = 0
for i in range(8):
	line = input()
	for j in range(8):
		if (i + j) % 2 == 0 and line[j] == 'F':
			cnt += 1
print(cnt)