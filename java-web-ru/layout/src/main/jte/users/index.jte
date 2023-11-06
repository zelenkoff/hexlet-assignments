@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
content = @`
    <a href="/users/build">New User</a>
    @for(var user : page.getUsers())
        <div>${user.getFirstName()} ${user.getLastName()}</div>
    @endfor
`
)