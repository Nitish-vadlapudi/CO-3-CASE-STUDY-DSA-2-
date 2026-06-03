import java.util.*;

public class KruskalMST {

    static class Edge {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    static class UnionFind {

        int[] parent;
        int[] rank;

        UnionFind(int n) {

            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        int find(int x) {

            if (parent[x] != x)
                parent[x] = find(parent[x]);

            return parent[x];
        }

        boolean union(int a, int b) {

            int pa = find(a);
            int pb = find(b);

            if (pa == pb)
                return false;

            if (rank[pa] < rank[pb])
                parent[pa] = pb;
            else if (rank[pa] > rank[pb])
                parent[pb] = pa;
            else {
                parent[pb] = pa;
                rank[pa]++;
            }

            return true;
        }
    }

    public static void main(String[] args) {

        String[] stations = {
                "M","K","W","S","E","Y","H"
        };

        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge(4,3,4));
        edges.add(new Edge(6,4,5));
        edges.add(new Edge(0,3,7));
        edges.add(new Edge(0,1,8));
        edges.add(new Edge(3,2,9));
        edges.add(new Edge(0,5,9));
        edges.add(new Edge(0,2,10));
        edges.add(new Edge(1,2,11));
        edges.add(new Edge(2,5,12));
        edges.add(new Edge(0,6,14));

        edges.sort(Comparator.comparingInt(e -> e.w));

        UnionFind uf = new UnionFind(7);

        int total = 0;

        System.out.println("MST Edges:");

        for (Edge e : edges) {

            if (uf.union(e.u, e.v)) {

                total += e.w;

                System.out.println(
                        stations[e.u] + " -- " +
                        stations[e.v] +
                        " : " + e.w);
            }
        }

        System.out.println(
                "\nTotal Cost = " + total + " Crores");
    }
}