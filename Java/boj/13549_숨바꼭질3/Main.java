import java.io.*;
import java.util.*;

public class Main {
    private static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if (n >= k) {
            System.out.println(n - k);
            return;
        }

        int res = bfs();
        System.out.println(res);
    }

    private static int bfs() {
        int[] dist = new int[100001];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[n] = 0;

        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { n, 0 });

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[1] > dist[cur[0]]) {
                continue;
            }

            // 찾으면 종료
            if (cur[0] == k) {
                return dist[cur[0]];
            }

            int next = cur[0] * 2;
            int time = cur[1];

            if (0 <= next && next <= 100000 && time < dist[next]) {
                dist[next] = time;
                queue.offerFirst(new int[] { next, time });
            }

            next = cur[0] - 1;
            time = cur[1] + 1;
            if (0 <= next && next <= 100000 && time < dist[next]) {
                dist[next] = time;
                queue.offerLast(new int[] {next, time});
            }

            next = cur[0] + 1;
            time = cur[1] + 1;
            if (0 <= next && next <= 100000 && time < dist[next]) {
                dist[next] = time;
                queue.offerLast(new int[] {next, time});
            }
        }

        return dist[k];
    }
}
