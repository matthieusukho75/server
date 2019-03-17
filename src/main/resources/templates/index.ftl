<#-- @ftlvariable name="" type="fr.iim.iwm.a5.matthieu.sukho.IndexData" -->
<html>
<head>
    <title>Welcome!</title>
    <link rel="stylesheet" href="/static/styles.css">
</head>
<body>

<#list articles as article>
    <a href="/article/${article.id}">${article.title}</a>!
</#list>

</body>
</html>