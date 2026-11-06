//Same as the other sister file, this one will just cover the implementation of the dfs algorithm in java this time

class dfs{

    public static void main(String[] args){

    }

    //DFS
    static void dfsRec(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int s, ArrayList<Integer> res){
        //Setting up the list of what we have visited already
        visited[s] = true;
        //Adding the starting node to the results list
        res.add(s);

        //Recursively visit all adjacent vertices that arent visited yet
        for(int i: adj.get(s)){
            //If we havent visited the node already - we call dfs again and pass this node to continue searching this path
            if(!visited[i]){
                dfsRec(adj, visited, i, res)
            }
        }
    }

    static ArrayList<Integer>dfs(ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[adj.size()];
        ArrayList<Integer> res = new ArrayList<>();
        dfsRec(adj, visited, 0, res);
        return res;
    }


    //DFS ON A DISCONNECTED GRAPH
    //In a disconnected graph some vertices may not be reachable from a single source
    //To ensure all vertices are visited in DFS traversal, we iterate through each vertex, and if a vertex is unvisited, we perform a DFS starting from that vertex being the source
    //This way, DFS explores every connected component of the graph

    private static void dfsRecDisconnect(ArrayList<ArrayList<Integer> > adj, boolean[] visited, int s, ArrayList<Integer> res) {
        visited[s] = true;
        res.add(s);

        // Recursively visit all adjacent vertices that are not visited yet
        for (int i : adj.get(s)) {
            if (!visited[i]) {
                dfsRecDisconnect(adj, visited, i, res);
            }
        }
    }

    public static ArrayList<Integer> dfsDisconnect(ArrayList<ArrayList<Integer> > adj) {
        boolean[] visited = new boolean[adj.size()];
        ArrayList<Integer> res = new ArrayList<>();

        // Loop through all vertices 
        // to handle disconnected graphs
        for (int i = 0; i < adj.size(); i++) {
            if (!visited[i]) {
                dfsRecDisconnect(adj, visited, i, res);
            }
        }

        return res;
    }
}