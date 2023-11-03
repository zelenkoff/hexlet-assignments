@import exercise.dto.users.UsersPage
@param UsersPage page

<ul>
    @for(var user : page.getUsers())
        <li>
            <h2><a href="/user/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></h2>

            <p>Email: ${user.getEmail()}</p>
        </li>
    @endfor
</ul>