package top.booksms.facelogin.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.booksms.facelogin.Service.LoginService;
import top.booksms.facelogin.Utils.GsonUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
@Controller
@SessionAttributes(value = "useinf")
public class LoginController {
    @Autowired
    LoginService loginService=null;
    @RequestMapping("/login/index")
    public String getface(){
        return "getface";
    }
    @RequestMapping("/login/searchface")
    @ResponseBody
    public   String searchface(@RequestBody @RequestParam(name = "imagebast64") StringBuffer imagebast64, Model model,HttpServletRequest request) throws IOException {
        Map<String, Object> searchface = loginService.searchface(imagebast64);
        System.out.println("SEARCHFACE"+searchface);
        if(searchface==null||searchface.get("user_id")==null){
            System.out.println("我进来了");
            String flag="fail";
            String s = GsonUtils.toJson(flag);
            return s;
        }
            String user_id = searchface.get("user_id").toString();
            String score=searchface.get("score").toString().substring(0,2);
            int i = Integer.parseInt(score);
            if(i>80){
                model.addAttribute("userinf",user_id);
                HttpSession session = request.getSession();
                session.setAttribute("userinf",user_id);
                System.out.println("存入session");
            }
        System.out.println(searchface);
        String s = GsonUtils.toJson(searchface);
        return s;
    }
    @RequestMapping("/login/facesucceed")
    public String Faceloginsucceed(){
        return "succeed";
    }
    @RequestMapping("/index")
    public String loginIndex(){
        return "getface";
    }

}
