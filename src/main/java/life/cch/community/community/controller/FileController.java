package life.cch.community.community.controller;

import life.cch.community.community.dto.FileDTO;
import life.cch.community.community.provider.TCloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by codedrinker on 2020-01-06 17:32:57
 */
@Controller
public class FileController {

    @Autowired
    private TCloudProvider tCloudProvider;

    @ResponseBody
    @RequestMapping("/file/upload")
    public FileDTO upload(HttpServletRequest request){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("editormd-image-file");
        //文件名字
        String fileName = multipartFile.getOriginalFilename();
        //文件类型,后缀
        String contentType = multipartFile.getContentType();
        InputStream inputStream = null;
        Integer fileSize = null;
        try {
            inputStream = multipartFile.getInputStream();
            fileSize = inputStream.available();
            String url = tCloudProvider.upload(fileName, inputStream, contentType, fileSize);
            System.out.println(url);
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(1);
            fileDTO.setUrl(url);
            return fileDTO;
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/image/bg.jpg");
        return fileDTO;
    }
}
