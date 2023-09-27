package com.bvt.moreinfo;

import com.bvt.moreinfo.service.VulkanService;
import com.bvt.moreinfo.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @projectName: MoreInfo
 * @package: com.bvt.moreinfo
 * @className: VulkanController
 * @author: Tom
 * @description: TODO
 */
@RestController
@RequestMapping("/employee")
public class VulkanController {

    @Autowired
    private VulkanService vulkanService;

    @Value("${file.upload.url}")
    private String uploadFilePath;

    @RequestMapping("/upload")
    public Result httpUpload(@RequestParam("files") MultipartFile[] files) {
        for(int i=0;i<files.length;i++){
            String fileFullName = files[i].getOriginalFilename();  // 文件名
            String fileName = fileFullName.substring(0, fileFullName.lastIndexOf("."));
            File dest = new File(uploadFilePath +'/'+ fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            try {
                files[i].transferTo(dest);
            } catch (Exception e) {
                return Result.error("解析出错");
            }

            String suffixName = fileFullName.substring(fileFullName.lastIndexOf(".") + 1);  //  获取后缀名


            long currentTime=System.currentTimeMillis();
            String destinationFileName = String.format("%d", currentTime);
//            Boolean encodeFlag = vulkanService.encodeFile(new File(uploadFilePath), dest, destinationFileName);
//            if (!encodeFlag) {
//                return Result.error("加密/重命名出错");
//            }
//            dest.delete();

            return Result.ok("已接收");
    }
}
