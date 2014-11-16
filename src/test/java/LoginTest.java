import org.junit.Test;
import org.mockito.Mock;
import wiremock.org.apache.http.HttpResponse;
import wiremock.org.apache.http.client.HttpClient;
import wiremock.org.apache.http.client.methods.HttpGet;
import wiremock.org.apache.http.impl.client.DefaultHttpClient;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LoginTest {
    @Mock
    private HttpClient defaultHttpClient = new DefaultHttpClient();
    private HttpGet request = new HttpGet("/index");

//    @Test
//    public void testShouldKnowThatHomeReturnsHello(){
//        Login login = new Login();
//        String expectedGreeting = "Hello World!!!!!!!!!";
//
//        assertEquals(login.index(), expectedGreeting);
//    }

//    @Test
//    public void testShouldRunSpringApplicationWhenMainIsInvoked(){
//        SpringApplication mockApplication = mock(SpringApplication.class);
//        String[] args = {};
//
//        try {
//            Login.main(args);
//            verify(mockApplication.run(), atLeastOnce());
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//            assertTrue(false);
//        }
//    }

    @Test
    public void testShouldHaveTheErrorPageMessageOfNotFound(){
        Login login = new Login();
        String expectedErrorMessage = "404: Page Not Found!";

        assertEquals(login.errorPage(), expectedErrorMessage);
    }

    @Test
    public void testShouldOverrideTheErrorPathMethod(){
        Login login = new Login();
        String expectedPath = "/error";

        assertEquals(login.getErrorPath(), expectedPath);
    }

    @Test
    public void testUserRedirectShouldRetrieveNewUserWithDummyDetails(){
        Login login = new Login();
        User expectedUser = new User("firstName", "lastName");

        assertThat(login.getUsers(), is(equalTo(expectedUser)));
    }

    @Test
    public void testShouldKnowLoginRedirectsWithMessage()
    {

        try {
            HttpResponse response = defaultHttpClient.execute(request);
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println(response);
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        }
        catch (Exception e){
            System.out.println("#################################################################");
            System.out.println(e.getMessage());
            System.out.println("#################################################################");
        }
    }
}
