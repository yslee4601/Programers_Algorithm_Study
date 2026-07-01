/*
 * 예제로 규칙 발견
 *   - n=3, [[1,1,0],[1,1,0],[0,0,1]] -> 0-1 연결, 2는 홀로 -> 네트워크 2개.
 *   - computers[i][j] == 1 이면 i와 j가 직접 연결(간선)이라는 뜻. (i==j는 자기 자신이라 무시해도 됨)
 *
 * 의사코드
 *   count = 0
 *   for i in 0..n-1:
 *       if !visited[i]:
 *           DFS(i)      // i가 속한 네트워크 전체를 방문표시
 *           count++
 *   return count
 *   DFS(cur): visited[cur]=true; for j in 0..n-1: if computers[cur][j]==1 && !visited[j]: DFS(j)
*/

class Solution {
    int n;
    int[][] computers;
    boolean[] visited;

    public int solution(int n, int[][] computers) {
        // 1) DFS에서 공용으로 참조할 값 저장
        this.n = n;
        this.computers = computers;
        this.visited = new boolean[n];

        int count = 0;

        // 2) 모든 컴퓨터를 순회하며 "새 네트워크의 시작점" 찾기
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i);   // 3) i가 속한 네트워크를 통째로 방문표시
                count++;  // 4) 새 네트워크 하나 발견
            }
        }
        return count;
    }

    // 5) DFS: cur와 직접 연결된(값 1) 미방문 컴퓨터로 계속 퍼져나간다
    private void dfs(int cur) {
        visited[cur] = true;
        for (int j = 0; j < n; j++) {
            // 6) 인접 행렬이므로 한 줄(cur 행)을 훑어 연결 여부 확인
            if (computers[cur][j] == 1 && !visited[j]) {
                dfs(j);
            }
        }
    }
}