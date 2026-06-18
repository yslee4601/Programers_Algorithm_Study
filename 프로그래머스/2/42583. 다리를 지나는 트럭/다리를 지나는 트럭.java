// 순서대로 다리를 건넌다
// 먼저 오른 트럭이 먼저 내려간다 -> FIFO 큐
// 다리 자체를 길이 bridge_length짜리 큐로 

import java.util.*;

class Solution {
    public int solution(int bridgeLength, int weight, int[] truckWeights) {
        Deque<Integer> bridge = new ArrayDeque<>();
        for (int i = 0; i < bridgeLength; i++) {
            bridge.offer(0);            // 다리를 빈 칸으로 가득 채워 시작
        }

        int time = 0;
        int curWeight = 0;
        int idx = 0;                    // 다음에 진입할 트럭

        while (idx < truckWeights.length) {
            time++;
            curWeight -= bridge.poll(); // 맨 앞 칸이 한 칸 전진(빠져나감)

            if (curWeight + truckWeights[idx] <= weight) {
                curWeight += truckWeights[idx];
                bridge.offer(truckWeights[idx]);  // 트럭 진입
                idx++;
            } else {
                bridge.offer(0);        // 자리 없음 → 빈 칸으로 전진
            }
        }

        return time + bridgeLength;     // 마지막 트럭이 끝까지 건널 시간
    }
}