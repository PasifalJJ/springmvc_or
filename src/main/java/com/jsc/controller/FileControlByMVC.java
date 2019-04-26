package com.jsc.controller;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service("/fileControlByMVC")
@RequestMapping("/file")
public class FileControlByMVC {
    @RequestMapping(path = "/mvcUpload",method= RequestMethod.POST)
    public String upload(MultipartFile file, HttpServletRequest req,String fileDercribe) throws IOException {
        String originalFilename = file.getOriginalFilename();
        InputStream is = file.getInputStream();
        ServletContext servletContext = req.getServletContext();
        String realPath = servletContext.getRealPath("/img");
        File filePath=new File(realPath);
        if (!filePath.exists()){
            filePath.mkdirs();
        }
        FileOutputStream fos=new FileOutputStream(filePath+"/"+originalFilename);
        byte[] bytes=new byte[1024];
        int len=0;
        while ((len=is.read(bytes))!=-1){
            fos.write(bytes,0,len);
        }
        System.out.println("fileDercribe = " + fileDercribe);
        String name = file.getName();
        System.out.println("name = " + name);
        is.close();
        fos.close();
        return "redirect:/file/goUpload";
    }
}
