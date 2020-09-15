document.getElementById("allProblems").addEventListener("click", listAllProblem);
document.getElementById("addProblem").addEventListener("click", displayProblem);
document.getElementById("editProblem").addEventListener("click", displayEditProblem);

function listAllProblem(){
    document.getElementById("cnt-form-add").style.display="none";
    document.getElementById("output").style.display="block";
    document.getElementById("cnt-form-edit").style.display="none";
    const url = "https://jsonplaceholder.typicode.com/todos/1";
    //const proxyurl = "https://cors-anywhere.herokuapp.com/";
    //const url = "http://192.168.1.5:8080/problems/1";
    fetch(url)
    .then((resp) => resp.json())
    .then((problems) => {
        let output = `
        <table class="table table-light table-hover" id="problemsList">
            <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Content</th>
            </tr>
            </thead>
            <tbody>
        `;
        // problems.forEach(function(data){
        //     output+=`
        //     <tr>
        //         <td>${data.id}</td>
        //         <td>${data.userId}</td>
        //         <td>${data.title}</td> 
        //     </tr>
        //     `;  
        // });
        // problems.forEach(function(data){
        //     output+=`
        //     <tr>
        //         <td>${data.id}</td>
        //         <td>${data.userId}</td>
        //         <td>${data.title}</td> 
        //     </tr>
        //     `;  
        // });
        output+='<tr class="clickable-row"><td>'+problems.id+'</td><td>'+problems.content+'</td><td>'+problems.title+'</tr>';
        // output+=`
        // <tr class="clickable-row">
        //     <td>${problems.id}</td>
        //     <td>${problems.content}</td>
        //     <td>${problems.title}</td> 
        // </tr>
        // `;
        output+=`
        </tbody>
        </table>
        `;
        document.getElementById('output').innerHTML = output;

        var item;
        $(document).ready(function(){
            $('#problemsList tbody tr').click(function() {
                $(this).addClass('bg-success').siblings().removeClass('bg-success'); 
            });
            $("#deleteProblem").on('click',function(){
                $('.bg-success').closest('tr').remove();
            });
            item = $(this).find('td:first').html();
        });

        document.getElementById("deleteProblem").addEventListener("click", deleteProblem);
        function deleteProblem(){
            const url = "https://jsonplaceholder.typicode.com/posts";
            fetch(url+'/'+item, {
                method: 'DELETE'
            }).then((resp) => resp.json())
            .then((data) => console.log(data))
        }
    })
    .catch(() => 
    console.log("Can't Access"));
}


function displayProblem(){
    document.getElementById("output").style.display="none";
    document.getElementById("cnt-form-add").style.display="block";
    document.getElementById("cnt-form-edit").style.display="none";
    document.getElementById("addProblem_form").addEventListener("submit", addProblem);
    function addProblem(){
        e.preventDefault();
        let title_addProblem = document.getElementById('title_addProblem').value;
        let content_addProblem = document.getElementById('content_addProblem').value;
        const url = "https://jsonplaceholder.typicode.com/posts/";
        fetch(url, {
                method:'POST',
                headers: {'Accept': 'application/json, text/plain, */*',
                        'Content-type': 'application/json'},
                body: JSON.stringify({ title_addProblem:title_addProblem, content_addProblem:content_addProblem})
        })
        .then((res) => res.json())
        .then((data) => console.log(data))
    }
}

function displayEditProblem(){
    document.getElementById("output").style.display="none";
    document.getElementById("cnt-form-add").style.display="none";
    document.getElementById("cnt-form-edit").style.display="block";
    document.getElementById("editProblem_form").addEventListener("submit", editProblem);
    function editProblem(){

    }
}
