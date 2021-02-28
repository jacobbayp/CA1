/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. fuck
 */


   // const URL = "http://localhost:8080/jpareststarter/api/members/all" 
     const URL = "https://test.madebay.dk/CA1/api/members/all"
                document.getElementById("all").onclick = getAll;
               
       
                
                 
                 
            
            
            
            function getAll(){
              const data = fetch(URL)
                        .then((res) => res.json()) 
                        .then(members => {
                            const tableRows = members.map(memb => {
                                const row =  `<tr> <td>${memb.id}</td> <td>${memb.name}</td> <td>${memb.studentID}</td> <td>${memb.favouriteColor}</td> </tr>`                      
                          return row;
            })
                    const rowsAsStr = tableRows.join("");
                    document.getElementById("table-rows").innerHTML = rowsAsStr
                });
         
            }
