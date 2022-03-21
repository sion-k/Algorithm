#include <iostream>
#include <vector>
#include <tuple>
#include <algorithm>
#define ALL(x) (x).begin(), (x).end()
using namespace std;

vector<tuple<int, int, int>> s;
vector<vector<int>> adj;

bool compare(tuple<int, int, int>& u, tuple<int, int, int> &v) {
	return get<2>(u) > get<2>(v);
}

// u가 v를 포함하는지 여부
bool includes(tuple<int, int, int>& u, tuple<int, int, int>& v) {
	long long x1 = get<0>(u), y1 = get<1>(u), r1 = get<2>(u);
	long long x2 = get<0>(v), y2 = get<1>(v), r2 = get<2>(v);
	return r1 > r2 && (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) < (r1 + r2) * (r1 + r2);
}

// root번째 노드를 루트로 하는 서브트리에 node번째 원을 삽입
void insert(int root, int node, int prev) {
	// root번째 노드의 자식중에 포함되는 자식이 있는지 확인
	for (int there : adj[root]) if (there != prev) {
		if (includes(s[there], s[node])) {
			insert(there, node, root);
			return;
		}
	}
	adj[root].push_back(node);
	adj[node].push_back(root);
}

// here정점에서 가장 먼 정점까지의 거리와, 그 정점 번호
pair<int, int> dfs(int here, int prev) {
	pair<int, int> ret;
	ret.second = here;
	for (int there : adj[here]) if (there != prev) {
		pair<int, int> cand = dfs(there, here);
		cand.first++;
		if (ret < cand) {
			ret = cand;
		}
	}
	return ret;
}

int main() {
	cin.tie(0);
	ios_base::sync_with_stdio(0);
	int n;
	cin >> n;
	s.emplace_back(0, 0, (int)1e9);
	for (int i = 0; i < n; i++) {
		int x, y, r;
		cin >> x >> y >> r;
		s.emplace_back(x, y, r);
	}
	sort(ALL(s), compare);
	// 0번 정점은 좌표 평면
	adj = vector<vector<int>>(n + 1);
	for (int here = 1; here <= n; here++) {
		insert(0, here, 0);
	}
	pair<int, int> p = dfs(0, 0);
	pair<int, int> q = dfs(p.second, p.second);
	cout << q.first << "\n";
}
