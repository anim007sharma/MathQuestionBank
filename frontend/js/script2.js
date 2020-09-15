document.getElementById("addProblem").addEventListener("submit", addProblem);
function addProblem(e){
    e.preventDefault();
    let title = document.getElementById('title').value;
    let content = document.getElementById('content').value;
    const url = "https://jsonplaceholder.typicode.com/posts/";
    fetch(url, {
            method:'POST',
            headers: {'Accept': 'application/json, text/plain, */*',
                     'Content-type': 'application/json'},
            body: JSON.stringify({ title:title, content:content})
    })
    .then((res) => res.json())
    .then((data) => console.log(data))
}
