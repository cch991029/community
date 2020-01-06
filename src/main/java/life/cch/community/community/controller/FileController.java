package life.cch.community.community.controller;

import life.cch.community.community.dto.FileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by codedrinker on 2020-01-06 17:32:57
 */
@Controller
public class FileController {

    @ResponseBody
    @RequestMapping("/file/upload")
    public FileDTO upload(){
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/image/bg.jpg");
        return fileDTO;
    }
}
