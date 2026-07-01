class Solution {
    int target;
    int[] numbers;
    
    public int solution(int[] numbers, int target) {
        // 1) DFS에서 공용으로 참조할 값들을 필드에 저장
        this.numbers = numbers;
        this.target = target;

        // 2) 0번째 숫자부터, 지금까지 합 0으로 시작
        return dfs(0, 0);
    }

    // 3) index번째 숫자를 +/- 두 갈래로 시도하는 DFS
    private int dfs(int index, int sum) {
        // 4) 모든 숫자를 다 썼다면(잎 노드): 합이 target과 같으면 성공 1가지
        if (index == numbers.length) {
            return sum == target ? 1 : 0;
        }
        // 5) 더하는 경우 + 빼는 경우의 성공 수를 합산
        return dfs(index + 1, sum + numbers[index])
             + dfs(index + 1, sum - numbers[index]);
    }
}