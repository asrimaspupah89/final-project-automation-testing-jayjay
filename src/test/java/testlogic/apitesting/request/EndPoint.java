package testlogic.apitesting.request;

public class EndPoint {

    // domain url
    public static final String BASE_URL = "https://dummyapi.io/data/v1/";

    // End Point User Management
    // get list users >> GET: /user
    public static final String GET_LIST_USERS = BASE_URL + "user";

    // get profile user by id >> GET: /user/:id
    public static final String GET_PROFILE_USER = BASE_URL + "user/";

    // post create new user >> POST: /user/create
    public static final String CREATE_NEW_USER = BASE_URL + "user/create";

    // update user by id >> PUT: /user/:id
    public static final String UPDATE_USER = BASE_URL + "user/";

    // delete user by id >> DELETE: /user/:id
    public static final String DELETE_USER = BASE_URL + "user/";

    // Endpoint Tag Management
    // get list users >> GET: /tag
    public static final String GET_LIST_TAGS = BASE_URL + "tag";
}
