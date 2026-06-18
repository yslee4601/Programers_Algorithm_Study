// 먼저 배포되어야 하는 순서대로
// 앞 작업이 끝나야 뒤 작업이 따라 나간다 -> 큐

import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> days = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            // 정수 올림: ceil(a/b) = (a + b - 1) / b
            int need = (100 - progresses[i] + speeds[i] - 1) / speeds[i];
            days.offer(need);
        }

        List<Integer> answer = new ArrayList<>();
        while (!days.isEmpty()) {
            int leader = days.poll();   // 맨 앞이 새 배포의 기준
            int count = 1;
            while (!days.isEmpty() && days.peek() <= leader) {
                days.poll();            // 기준 이하면 함께 배포
                count++;
            }
            answer.add(count);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
