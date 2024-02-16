import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* BOJ 2991. 사나운 개 */
public class Problem2991 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/boj/Input2991.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] biteTimesStr = br.readLine().split(" ");
        int[] biteTimes = new int[biteTimesStr.length];

        for (int i = 0; i < biteTimes.length; i++) {
            biteTimes[i] = Integer.parseInt(biteTimesStr[i]);
        }

        String[] visitTimesStr = br.readLine().split(" ");
        int[] visitTimes = new int[visitTimesStr.length];

        for (int i = 0; i < visitTimes.length; i++) {
            visitTimes[i] = Integer.parseInt((visitTimesStr[i]));
        }

        int[] biteCount = new int[3];

        for (int i = 0; i < biteCount.length; i++) {
            biteCount[i] += isBite(visitTimes[i], biteTimes[0], biteTimes[1]);
            biteCount[i] += isBite(visitTimes[i], biteTimes[2], biteTimes[3]);
        }

        for (int i : biteCount) {
            System.out.println(i);
        }

        br.close();
    }

    public static int isBite(int job, int biteTime, int unBiteTime) {
        int currentTime = job % (biteTime + unBiteTime);

        if (currentTime != 0 && currentTime <= biteTime) {
            return 1;
        }

        return 0;
    }
}
