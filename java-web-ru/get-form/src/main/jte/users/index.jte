@import exercise.dto.users.UsersPage
@param UsersPage page

<!-- BEGIN -->
<form action="/users" method="get">
    <input type="search" required name="firstName" value="${page.getFullName()}">
    <input type="submit" value="Искать">
</form>

<ul>
    @for(var user : page.getUsers())
        <li>${user.getFirstName()} ${user.getLastName()}</li>
    @endfor
</ul>
<!-- END -->
