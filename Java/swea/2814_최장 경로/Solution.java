import java.io.*;
import java.util.*;

class Solution {
    private static boolean[][] graph;
    private static int n, max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            graph = new boolean[n + 1][n + 1];
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                graph[x][y] = true;
                graph[y][x] = true;
            }

            max = 1;
            for (int i = 1; i <= n; i++) {
                boolean[] visited = new boolean[n + 1];
                visited[i] = true;
                dfs(i, visited, 1);
            }

            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }

        System.out.print(sb);
    }

    private static void dfs(int start, boolean[] visited, int cnt) {
        for (int i = 1; i <= n; i++) {
            if (graph[start][i] && !visited[i] && start != i) {
                visited[i] = true;
                dfs(i, visited, cnt + 1);
                visited[i] = false;
            }

            max = Math.max(max, cnt);
        }
    }
}
