import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@EnableAutoConfiguration
public class Login implements ErrorController {

//    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
//
//            "classpath:/META-INF/resources/", "classpath:/resources/",
//
//            "classpath:/resources/static/", "classpath:/public/" };

    private static final String PATH = "/error";

//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public String index() {
//        return "Hello World!!!!!!!!!";
//    }

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

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");

                container.addErrorPages(error401Page, error404Page, error500Page);
            }
        };
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
