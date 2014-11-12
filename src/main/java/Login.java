import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@EnableAutoConfiguration
public class Login implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "Hello World!!!!!!!!!";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "name", defaultValue = "Test") String name){
        return "This is the name: " + name;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public User getUsers(){
        return new User("firstName", "lastName");
    }

    @RequestMapping(value = PATH)
    public String errorPage(){
        return "404: Page Not Found!";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Login.class, args);
//
//        String[] beanNames = context.getBeanDefinitionNames();
//        Arrays.sort(beanNames);
//
//        System.out.println("###############################################################################");
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }
//        System.out.println("###############################################################################");
    }

}
