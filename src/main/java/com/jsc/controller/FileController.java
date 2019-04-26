package com.jsc.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

@Controller("fileController")
@RequestMapping("/file")
public class FileController {

    @RequestMapping("/goUpload")
    public String goUpload(){
        return "/upload";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upLoad(HttpServletRequest req,HttpServletRequest resp)throws Exception{
        //req.setCharacterEncoding("utf-8");
        //创建磁盘文件工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //创建核心的解析类，解析req
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解析req请求，获取请求行，请求头和请求体
        List<FileItem> fileItems = upload.parseRequest(req);
        //创建文件保存位置
        ServletContext servletContext = req.getServletContext();
        String realPath = servletContext.getRealPath("/img");
        File file=new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }

        //遍历fileItems,判断文件上传是普通项还是文件上传项
        for (FileItem fileItem : fileItems) {
            //如果是普通项
            if (fileItem.isFormField()){
                //获取普通项的name
                String fieldName = fileItem.getFieldName();
                //如果是获取普通项的值
                String value = fileItem.getString("utf-8");
                System.out.println(fieldName+"-->"+value);
            }else {
                //判断时文件上传项目
                String name = fileItem.getName();
                InputStream is = fileItem.getInputStream();
                FileOutputStream fos=new FileOutputStream(file+"/"+name);
                byte[] bytes=new byte[1024];
                int len=0;
                while ((len=is.read(bytes))!=-1){
                    fos.write(bytes,0,len);
                }
                is.close();
                fos.close();
            }
        }
        return "redirect:/file/goUpload";
    }
}
