@import exercise.dto.users.UserPage
@param UserPage userPage

<div>
    <h2>${userPage.getUser().getFirstName()} ${userPage.getUser().getLastName()}</h2>
    <p>User id: ${userPage.getUser().getId()}</p>
</div>
