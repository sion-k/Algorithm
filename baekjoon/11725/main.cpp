#include <iostream>
#include <vector>
using namespace std;

vector<int> parent;
vector<vector<int>> adj;

void dfs(int here, int prev) {
	for (int there : adj[here]) if (there != prev) {
		parent[there] = here;
		dfs(there, here);
	}
}

int main() {
	cin.tie(0);
	ios_base::sync_with_stdio(0);
	int n;
	cin >> n;
	parent = vector<int>(n + 1);
	for (int i = 0; i <= n; i++) {
		adj.push_back(vector<int>());
	}
	for (int i = 0; i < n - 1; i++) {
		int u, v;
		cin >> u >> v;
		adj[u].push_back(v);
		adj[v].push_back(u);
	}
	dfs(1, 1);
	for (int i = 2; i <= n; i++) {
		cout << parent[i] << "\n";
	}
}