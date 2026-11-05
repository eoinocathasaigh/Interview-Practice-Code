//WHEN TO USE QUEUES
//When we need to process things in order
//When we need to keep track of the order of things
//When we need to keep track of the oldest item
//WHEN IMPLEMENTING BFS
//When we want to find the shortest path

//SHORTEST PATH IN A GRAPH - BFS
function bfs(adj, src){
    const visited = new Array(adj.length).fill(false);
    const res = [];
    const queue = [];

    visited[src] = true;
    queue.push(src);

    while(queue.length > 0){
        let curr = queue.shift();
        res.push(curr);

        for(let neighbor of adj[curr]){
            if(!visited[neighbor]){
                visited[neighbor] = true;
                queue.push(neighbor);
            }
        }
    }

    return res;
}