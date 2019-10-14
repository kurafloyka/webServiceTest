package util;

import io.restassured.RestAssured;
import org.junit.Before;
import org.apache.log4j.Logger;
import test.Post;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseAPITest {



    public static final Logger log = Logger.getLogger(Post.class.getName());




    @Before
    public void setup() {
        RestAssured.baseURI = "http://example.com/";





    }


}





