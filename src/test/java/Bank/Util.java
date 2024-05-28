package Bank;
/*
 * Declare some common parameters for scripts
 * You can change them to adapt your environment
 *
 */

public class Util {

    public static final int WAIT_TIME = 30; // Delay time to wait the website
					    // launch completely
    public static final String BASE_URL = "http://www.demo.guru99.com/";

    // Valid account for login
    public static final String USER_NAME = "mngr1336";
    public static final String PASSWD = "dAnavUq";


    // Expected output
    public static final String EXPECT_TITLE = "Guru99 Bank Manager HomePage";
    public static final String EXPECT_ERROR = "User or Password is not valid";

    public static final String PATTERN = ":";
    public static final String FIRST_PATTERN = "mngr";
    public static final String SECOND_PATTERN = "[0-9]+";


//     public static final String FIREFOX_PATH =
//     "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";

}
