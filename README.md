# SPACenter
SPA center app

For best user experience use google chrome.

Simple spring application demonstrates basic functionalities as login/logout user ,validating data , adding and removing items to cart and so on.

Run on http://localhost:8080

After download and start it creates initial database with some products and the following users:

user@com.com with default password: 123 and role: "USER"

moderator@com.com with default password: 123 and role: "MODERATOR"

admin@com.com with default password: 123 and role: "ADMIN"

User with role "USER" can view and buy products but can't add new products. It can only change his username and password.

User with role "MODERATOR" can view and buy products as well as add new products. It can change his username and password.

User with role "ADMIN" can view and buy products as well as add new products. It can change his username and password as well as roles of rest of the users.
