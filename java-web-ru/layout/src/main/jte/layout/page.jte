@import gg.jte.Content
@param Content content

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hexlet Javalin Layout</title>
</head>
<body>
<header>
    <a href="/">Main</a>
    <a href="/users">Users</a>
</header>
${content}
<footer>Hello there!</footer>
</body>
</html>