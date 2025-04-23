package com.example.practicejava.file;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

@RestController
@RequestMapping("/demo")
public class FileController {

    @RequestMapping("/fileview/{id}")
    public void fileView(@PathVariable("id") Long id, @RequestParam(required = false) Map map, HttpServletResponse response) throws Exception {

        System.out.println("id:" + id);

        System.out.println(map.toString());
//        return AjaxResult.success("成功",fileService.fileView(id, response));
//        return AjaxResult.success("成功",fileService.liindatafileview(id, response));
    }

    @PostMapping(path = {"/upload"})
    public void getMs(@RequestPart("file") MultipartFile[] files) throws IOException {
        for (MultipartFile file : files){
            String fileName = file.getOriginalFilename();
            File dest = new File("/Users/cyq/Downloads/" + fileName);
            file.transferTo(dest);
        }
    }

    @GetMapping("/download")
    public void download(String fileName, HttpServletResponse response) throws IOException {

        String _u = "/Users/cyq/Downloads/";
        String filePath = _u + fileName + ".xlsx";
        File file = new File(filePath);

        response.setContentType("application/octet-stream");
        response.addHeader("Content-Length", "" + file.length());
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(file.getName(), "UTF-8"));

        FileInputStream inputStream = new FileInputStream(file);
        ServletOutputStream outputStream = response.getOutputStream();
        try (inputStream; outputStream){
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > 0){
                outputStream.write(buffer, 0, len);
            }
        }
    }
}


