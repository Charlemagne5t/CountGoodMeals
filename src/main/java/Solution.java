import java.util.HashMap;
import java.util.Map;

public class Solution {
    private static int[] powerOfTwo;
    public int countPairs(int[] deliciousness) {
        initPowerOfTwo();
        Map<Integer, Integer> numsAndCount = getNumsAndCount(deliciousness);
        int pairs = 0;
        for(Map.Entry<Integer, Integer> entry : numsAndCount.entrySet()) {
            pairs = (pairs + countPairsFor(entry.getKey(), entry.getValue(), numsAndCount)) % 1_000_000_007;
        }
        return pairs;
    }

    private int countPairsFor(int num, int count, Map<Integer, Integer> numsAndCount) {
        int diff, sum = 0;
        for (int i = powerOfTwo.length - 1; i >= 0 && powerOfTwo[i] >= num; i--) {
            diff = powerOfTwo[i] - num;
            if (diff > num) {
                sum = (sum + (int)((long)count * numsAndCount.getOrDefault(diff, 0) % 1_000_000_007)) % 1_000_000_007;
            } else if (diff == 0 || diff == num) {
                sum = (sum + (int)(((long)count * (count - 1) / 2) % 1_000_000_007)) % 1_000_000_007;
                break;
            } else {
                break;
            }
        }
        return sum;
    }

    private Map<Integer, Integer> getNumsAndCount(int[] deliciousness) {
        Map<Integer, Integer> numsAndCount = new HashMap<>();
        for (int value : deliciousness) {
            numsAndCount.merge(value, 1, Integer::sum);
        }
        return numsAndCount;
    }

    private void initPowerOfTwo() {
        if (powerOfTwo == null) {
            powerOfTwo = new int[21];
            powerOfTwo[0] = 1;
            for (int i = 1; i < powerOfTwo.length; i++) {
                powerOfTwo[i] = 2 * powerOfTwo[i-1];
            }
        }
    }
}
