<!DOCTYPE html>
<html lang="en">
<head>
    <title>WishList</title>
</head>
<body>
<h1>WishList</h1> <hr>
<form action="/" method="get">
    Поиск по названию объявления: <input type="text" name="title"><br>
    <input type="submit" value="Поиск"/>
</form>
<#list products as product >
    <div>
        <p><b>${product.title}</b> ${product.description} | <a href="/product/${product.id}">Подробнее...</a></p>
    </div>
<#else>
    <h3>Товаров нет</h3>
</#list>
<hr>
<h3>Создать новую задачу</h3>
<form action= "/product/create" method="post" enctype="multipart/form-data">
    Название: <input type="text" name="title"/><br><br>
    Описание: <input type="text" name="description"/><br><br>
    Цена: <input type="number" name="price"/><br><br>
    Имя: <input type="text" name="author"/><br><br>
    Первая фотография: <input type="file" name="file1"/><br><br>
    Вторая фотография: <input type="file" name="file2"/><br><br>
    Третья фотография: <input type="file" name="file3"/><br><br>
    <input type="submit" value="Добавить задачу"/>
</form>


</body>
</html>