import java.util.*;

public class bellman {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of nodes : ");
        int n = sc.nextInt();
        int dist[][] = new int[n][n]; // minimum cost
        int dmat[][] = new int[n][n]; // graph
        int via[][] = new int[n][n]; // through a node
        System.out.println("Enter the graph adjacency matrix : ");
        for(int i = 0; i < n ; i++) {
            for(int j = 0; j < n; j++) {
                dmat[i][j] = sc.nextInt();
                dmat[i][i] = 0;
                dist[i][j] = dmat[i][j];
                via[i][j] = j;
            }
        }
        int count = 0;
        do {
            count = 0;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    for(int k = 0; k < n; k++) {
                        if(dist[i][j] > dist[i][k] + dist[k][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                            via[i][j] = k;
                            count++;
                        }
                    }
                }
            }
        }while(count != 0);
        for(int i = 0; i < n; i++) {
            System.out.println("Router " + i + " value is : ");
            for(int j = 0; j < n; j++) {
                System.out.println("To " + j + "via " + via[i][j] + " distance is " + dist[i][j]);
            }
        }
    }
}
