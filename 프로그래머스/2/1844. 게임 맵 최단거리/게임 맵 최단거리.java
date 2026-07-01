import java.util.ArrayDeque;
import java.util.Queue;

/*
 * 예제로 규칙 발견
 *   - 1은 길, 0은 벽. 좌상단(0,0)에서 출발해 우하단(n-1,m-1)까지.
 *   - BFS는 시작점에서 가까운 칸부터 퍼지므로, 도착칸에 처음 닿았을 때의 거리(칸 수)가 최솟값.
 *   - 예제1 -> 11, 예제2(벽으로 막힘) -> -1.
 *
 * 의사코드
 *   dist[0][0] = 1
 *   push (0,0)
 *   while queue:
 *       (x,y) = pop
 *       for 4방향 (nx,ny):
 *           if 범위 안 && maps==1 && dist==0:   // 길이고 아직 미방문
 *               dist[nx][ny] = dist[x][y] + 1
 *               push (nx,ny)
 *   return dist[n-1][m-1] == 0 ? -1 : dist[n-1][m-1]
*/

class Solution {
    // 상하좌우 4방향
    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};

    public int solution(int[][] maps) {
        int n = maps.length;     // 행 개수
        int m = maps[0].length;  // 열 개수

        // 1) 거리 배열(방문표시 겸용). 0 = 미방문
        int[][] dist = new int[n][m];

        // 2) BFS 준비: 시작칸 (0,0) 큐에 넣고 거리 1
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        dist[0][0] = 1;

        // 3) BFS 본체
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + DX[d];
                int ny = y + DY[d];

                // 4) 범위 밖 / 벽(0) / 이미 방문한 칸은 건너뜀
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (maps[nx][ny] == 0) continue;
                if (dist[nx][ny] != 0) continue;

                // 5) 한 칸 더 왔으니 거리 +1, 방문 표시 후 큐에 넣기
                dist[nx][ny] = dist[x][y] + 1;
                queue.offer(new int[]{nx, ny});
            }
        }

        // 6) 도착칸을 방문했으면 그 거리가 답, 못 갔으면(0) -1
        int answer = dist[n - 1][m - 1];
        return answer == 0 ? -1 : answer;
    }
}