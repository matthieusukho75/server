<html>
<head>
    <title>${title}</title>
</head>
<body>
    <div>
         <h1>${title}</h1>
         <p>${text}</p>
    </div>
    <div>
       <#list comments as comment>
                   <div>
                     <p>${comment.text}</p>
                   </div>
       </#list>
    </div>
 <div>
  <form action="" method="post">
   <div>
       <label for="comment">Add comment</label>
       <textarea id="comment" name="comment" rows="3"></textarea>
   </div>
       <button type="submit">Add</button>
  </form>
<div>
</body>
</html>