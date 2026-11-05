//WHEN TO USE STACKS?
//When we need to reverse something
//When we need to keep track of the order of things
//When we need to keep track of the most recent item
//WHEN IMPLEMENTING DFS ITERATIVELY

//CHECKING FOR BALANCED PARENTHESES
function isParenthesesBalanced(str){
    let stack = [];

    for(let char of str){
        //Checking if the character is an opening or closing parenthesis - we then push it to the top of the stack if it is
        if(char == '(' || char == '{' || char == '['){
            stack.push(char);
        }
        //Otherwise we check if its a closing parenthesis - if it is we check if the stack is empty, then if the last element in the stack is the corresponding opening parenthesis - if it is we pop it off the stack, otherwise we return false
        else if(char == ')' || char == '}' || char == ']'){
            if(stack.length == 0){
                return false;
            }
            //Checking if the last character in stack matches the corresponding opening parenthesis for the current closing parenthesis - if it does we pop it off the stack, otherwise we return false
            let last = stack.pop();
            if((char == ')' && last != '(') ||
                (char == '}' && last != '{') || 
                (char == ']' && last != '[')){
                return false;
            }
        }
    }

    return stack.length == 0;
}

//DFS
//WHEN TO USE DFS?
//When we need to explore all possible paths
//When we need to find a path to a specific node
//When we need to find the shortest path in an unweighted graph
function dfsRec(adj, visited, s, res){
    visited[s] = true;
    res.push(s);

    for(let neighbor of adj[s]){
        if(!visited[neighbor]){
            dfsRec(adj, visited, neighbor, res);
        }
    }
}

function dfs(adj){
    const visited = new Array(adj.length).fill(false);
    const res = [];
    dfsRec(adj, visited, 0, res);
    return res;
}