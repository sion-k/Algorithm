#include <cstdio>

int a, b;

int main() {
	scanf("%d%d", &a, &b);
	while (a && b) {
		puts((a > b) ? "Yes" : "No");
		scanf("%d%d", &a, &b);
	}
	return 0;
}