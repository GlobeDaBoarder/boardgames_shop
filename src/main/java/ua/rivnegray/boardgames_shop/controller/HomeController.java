package ua.rivnegray.boardgames_shop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "<h1>Welcome!</h1>" +
                "<h3>This is a temporary Rivnegray home page, which provides access to API.<h3>"+
                "<h3>API can be tested and accessed via Swagger UI or just byt making requests manually to any URL links provided in the documentation </h3>" +
                "<h3> The default admin account should have permissions to access every endpoint of API" +
                "<p><a href='/api/ui'>Swagger API UI</a></p>" +
                "<p><a href='/api/docs'>API Docs</a></p>";
    }
}
