package Project1.Project;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProjectController{

    @GetMapping("project") //Web Application에서 /project를 호출하면 이 메소드가 출력이 된다
    //쉽게 말하면 @GetMapping(이름)으로 설정하고  localhost8080/이름으로 실행하면 밑에 있는 메소드가 실행된다.
    public String project(Model model){
        model.addAttribute("data","hello!!" );
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }
}