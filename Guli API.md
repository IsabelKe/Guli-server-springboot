# API Document

# Content

```
1). Login
2). Get all users
3). Get all roles
4). Add a new role
5). Set the role permission
6). Get the main cagetories or the subcategories
7). Add a category
8). Update a category
9). Get all files
10). Add a new file
```

## Login

### Request URL：

	/api/v1/users/login

### Request Method：

	POST

### Parameters

	|Parameters	 |Is required 	|Data Type     |Description
	|username    |Y       	  	|string   	   |username
	|password    |Y           	|string        |password

### Response Data

```
Success:
{
    "result": "SUCCESS",
    "message": null,
    "data": {
        "username": "isabel",
        "password": null,
        "roleNames": [
            "test",
            "manager",
            "isabel",
            "dfsdf"
        ]
    }
}
User name is wrong
{
    "result": "FAILED",
    "message": "User name is wrong",
    "data": null
}
Password is wrong
{
    "result": "FAILED",
    "message": "The password is wrong",
    "data": null
}
```

## Get all users

### Request URL：

	/api/v1/users

### Request Method：

	GET

### Response Data

```
{
    "result": "SUCCESS",
    "message": null,
    "data": [
        {
            "id": 2,
            "loginAccount": "isabel",
            "userName": "isabel",
            "email": "isabel@gmail.com",
            "createTime": "2021-01-05",
            "roleNames": [
                "test",
                "manager",
                "isabel",
                "dfsdf"
            ]
        }
    ]
}
```

## Get all roles

### Request URL：

	/api/v1/roles

### Request Method：

	GET

### Response Data

```
{
    "result": "SUCCESS",
    "message": null,
    "data": [
        {
            "roleId": 1,
            "roleName": "test",
            "createTime": "2021-01-26",
            "authTime": "2021-01-28 09:53:42",
            "authName": "Isabel",
            "menus": [
                "/home",
                "/products"
            ]
        }
	]

}
```

## Add a new role

### Request URL：

	/api/v1/roles

### Request Method：

	POST

### Body data

```
|Parameters	 |Is required |Data Type   |Description
|roleName    |Y           |string      |role name
```

### Response Data

A list of roles will be returned , including the new role

```
{
    "result": "SUCCESS",
    "message": null,
    "data": [
        {
            "roleId": 1,
            "roleName": "test",
            "createTime": "2021-01-26",
            "authTime": "2021-01-28 09:53:42",
            "authName": "Isabel",
            "menus": [
                "/home",
                "/products"
            ]
        },
        {
            "roleId": 9,
            "roleName": "kitty",
            "createTime": "2021-02-08 19:09:29",
            "authTime": null,
            "authName": null,
            "menus": []
        }
    ]
}
```

## Set the role permission

### Request URL：

	/api/v1/roles

### Request Method：

	PUT

### Body data

```
|Parameters	 |Is required |Data Type   |Description
|roleId      |Y           |Integer     |role id
|authName    |Y           |string      |person who sets the permission
|menus       |Y           |string array|a list of permissions
```

### Response Data

A list of roles will be returned , including the new role permission

```
{
    "result": "SUCCESS",
    "message": null,
    "data": [
        {
            "roleId": 1,
            "roleName": "test",
            "createTime": "2021-01-26",
            "authTime": "2021-01-28 09:53:42",
            "authName": "Isabel",
            "menus": [
                "/home",
                "/products"
            ]
        },
        {
            "roleId": 9,
            "roleName": "kitty",
            "createTime": "2021-02-08 19:09:29",
            "authTime": "2021-02-08 19:21:00",
            "authName": "kitty",
            "menus": [
                "/products"
            ]
        }
    ]
}
```



## Get the main categories or the subcategories

### Request URL

	/api/v1/categories

### Request method

	GET

### Query Parameters

```
|Parameters	 |Is required |Data Type   |Description
|parentId    |Y           |Integer      |Main category id. 0 means get main categories, otherwise sub-categories.
```

### Response Data

```
{
    "result": "SUCCESS",
    "message": null,
    "data": [
        {
            "id": 1,
            "parentId": 2,
            "name": "Book",
            "subCategories": []
        },
        {
            "id": 2,
            "parentId": 2,
            "name": "Children's Book",
            "subCategories": []
        }
    ]
}
```

## Add a category

### Request URL

    /api/v1/categories

### Request method

    POST

### Body data

```
|Parameters	   |Is required    |Data Type    |Description
|parentId      |Y              |Integer      |Main category id
|name          |Y              |string       |Category name
```

### Response Data

Returns a list of categories



## Update a category

### Request URL：

    /api/v1/categories

### Request method

    PUT

### Body data

    |Parameters	   |Is required    |Data Type    |Description
    |parentId      |Y              |Integer      |Main category id
    |id            |N              |Integer      |Sub-category id
    |name          |Y              |string       |Category name or the sub-category

### Response Data

Returns a list of categories



## Get all files

### Request URL：

    /api/v1/files

### Request method

    GET

### Response Data

```
{
    "result": "SUCCESS",
    "message": null,
    "data": [
        {
            "pictureId": "0c37e100-7f0f-426c-937f-420cf391959b",
            "file": null,
            "url": "http://localhost:8001/uploadfile/c-handbook.pdf"
        },
        {
            "pictureId": "831deef2-45a9-4dc1-af82-d1d305765a06",
            "file": null,
            "url": "http://localhost:8001/uploadfile/The_React_Cheatsheet_for_2021_(_Real-World_Examples).pdf"
        },
        {
            "pictureId": "e787312b-44b2-4fd8-9ad2-08c635bcba72",
            "file": null,
            "url": "http://localhost:8001/uploadfile/javascript-beginner-handbook.pdf"
        },
        {
            "pictureId": "f2461bb2-9f1c-4884-8901-ecece7c89263",
            "file": null,
            "url": "http://localhost:8001/uploadfile/1.jpg"
        }
    ]
}
```

## Add a new file

### Request URL：

    /api/v1/files

### Request method

    POST

### Body data

```
|Parameters	   |Is required    |Data Type    |Description
|userId        |Y              |Integer      |id of the user who uploaded the file
|file          |Y              |File         |file that will be uploaded
```

### Response Data

Returns the accessing URL

```
{
    "result": "SUCCESS",
    "message": null,
    "data": "http://localhost:8001/uploadFile/1.jpg"
}


```



















### 5. Request URL：

    /api/v1/products

### Request method

    GET

### Parameters

    |Parameters	   |Is required    |Data Type    |Description
    |pageNum       |Y              |Number       |page number
    |pageSize      |Y              |Number       |size on each page

### Response Data

```
{
    "result": "SUCCESS",
    "message": null,
    "data": {
        "pageNum": 1,
        "pageSize": 5,
        "total": 5,
        "pages": 1,
        "categoryList": [
            {
                "productId": 1,
                "productName": "children book 1",
                "imgs": null,
                "desc": "fiction book",
                "price": 12,
                "detail": "detail",
                "status": 1,
                "categoryId": 1,
                "subId": 2
            },
            {
                "productId": 2,
                "productName": "children book 2",
                "imgs": null,
                "desc": "fiction book",
                "price": 13,
                "detail": "detail",
                "status": 1,
                "categoryId": 1,
                "subId": 2
            },
            {
                "productId": 3,
                "productName": "children book 3",
                "imgs": null,
                "desc": "fiction book",
                "price": 13,
                "detail": "detail",
                "status": 1,
                "categoryId": 1,
                "subId": 2
            },
            {
                "productId": 4,
                "productName": "children book 4",
                "imgs": null,
                "desc": "fiction book",
                "price": 13,
                "detail": "detail",
                "status": 1,
                "categoryId": 1,
                "subId": 2
            },
            {
                "productId": 5,
                "productName": "children book 5",
                "imgs": null,
                "desc": "fiction book",
                "price": 13,
                "detail": "detail",
                "status": 1,
                "categoryId": 1,
                "subId": 2
            }
        ]
    }
}
```

