package org.example.backend.app;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/image")
public class ImageApp {
    private static final Logger logger = Logger.getLogger(ImageApp.class.getName());

    @Value("${image-server.path}")
    String basePath;

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws Exception{
        File dir = new File(basePath);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new Exception("Failed to create directory");
            }
        }
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = UUID.randomUUID() + suffix;
        Path path = Paths.get(basePath, filename);
        logger.info("Image saved, url: " + path);
        file.transferTo(path.toAbsolutePath());
        return filename;
    }

    @GetMapping("/download")
    public void downloadImage(@RequestParam("url") String fileURL, HttpServletResponse response) throws Exception {
        File file = Paths.get(basePath, fileURL).toFile();
        //输入流，通过输入流读取文件内容
        FileInputStream fileInputStream = new FileInputStream(file);
        //输出流，通过输出流将文件写回浏览器，在浏览器展示图片
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("image/" + fileURL.substring(fileURL.lastIndexOf(".") + 1));

        int len;
        byte[] bytes = new byte[1024];
        while((len = fileInputStream.read(bytes))!= -1){
            outputStream.write(bytes,0,len);
            outputStream.flush();
        }
        //关闭资源
        outputStream.close();
        fileInputStream.close();
    }

    @GetMapping("{url}")
    public void getImage(@PathVariable String url, HttpServletResponse response) throws Exception {
        downloadImage(url, response);
    }

}
