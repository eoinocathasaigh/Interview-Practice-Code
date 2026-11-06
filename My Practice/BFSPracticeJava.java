//BFS as explained is a very important algo which speads out to all unexplored closest vertices before going to any others
//Used for situations like finding the shortest path in an unweighted graph, finding connected components, and many more"
//Also used to detect cycles in a graph and to find the shortest path
//The main steps of the algorithm are as follows:
/*
Starting from 0 (root) the traversal proceeds as follows
1. Visit the starting node
2. Enqueue all unvisited neighbors of the current node
3. Dequeue a node from the queue and visit it
4. Repeat steps 2 and 3 until the queue is empty
*/

//Another way to achieve this is through using method 2 - we are only given a list of adjacent vertices and we can use a queue to keep track of the vertices to be explored next.
class BFSJava{
    public static void main(String[] args){

    }

    static void bfs(int[][] graph, int start){
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> queue = new LinkedList<>();

        //Mark the starting node as visited and enqueue it
        visited[start] = true;
        queue.add(start);

        while(!queue.isEmpty()){
            //Dequeue a vertex from the queue and print it
            int current = queue.poll();
            System.out.print(current + " ");

            //Get all adjacent vertices of the dequeued vertex current
            for(int i = 0; i < graph[current].length; i++){
                int neighbor = graph[current][i];
                if(!visited[neighbor]){
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    //BFS for a single connected component of a graph
    static ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj){
        int V = adj.size();
        boolean[] visited = new boolean[V];
        ArrayList<Integer> results = new ArrayList<>();

        //Confirm we are starting at index/src 0
        int src = 0;
        Queue<Integer> q = new LinkedList<>();
        //State that we have visited the source node
        visited[src] = true;
        q.add(src);

        //Beginning the loop through the rest of the graph
        while(!q.isEmpty()){
            int curr = q.poll();
            res.add(curr);

            //Visit all the unvisited neighbors of the current node
            for(int x: adj.get(curr)){
                if(!visited[x]){
                    visited[x] = true;
                    q.add(x);
                }
            }
        }

        return results;
    }
}