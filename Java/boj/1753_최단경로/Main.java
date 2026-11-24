import java.io.*;
import java.util.*;

public class Main {
    private static List<List<int[]>> map;
    private static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine()) - 1;

        dist = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);

        map = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            map.get(u - 1).add(new int[] { o - 1, w });
        }

        dist[k] = 0;
        bfs(k);

        for (int i = 0; i < v; i++) {
            System.out.println(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]);
        }
    }

    private static void bfs(int start) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        queue.offer(new int[] { start, 0 });

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[1] > dist[cur[0]]) {
                continue;
            }

            for (int[] next : map.get(cur[0])) {
                if (dist[next[0]] <= dist[cur[0]] + next[1]) {
                    continue;
                }

                dist[next[0]] = dist[cur[0]] + next[1];
                queue.offer(new int[] { next[0], dist[next[0]] });
            }
        }
    }
}
