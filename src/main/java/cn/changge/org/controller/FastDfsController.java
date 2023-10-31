package cn.changge.org.controller;

import cn.changge.base.utils.AxiosResult;

import cn.changge.org.service.IFastDfsService;
import org.apache.velocity.shaded.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/common/fastDfs")
public class FastDfsController {

    @Autowired
    private IFastDfsService fastDfsService;
    @PostMapping
    public AxiosResult upload(MultipartFile file){

        try {
            byte[] bytes = file.getBytes(); //文件的内容
            String originalFilename = file.getOriginalFilename();

            String extName = FilenameUtils.getExtension(originalFilename);
            String path = fastDfsService.upload(bytes,extName); //上传成功返回地址

            return AxiosResult.me().setData(path); //地址返回前端
        } catch (Exception e) {
            e.printStackTrace();
            return AxiosResult.me().setSuccess(false).setMessage("上传失败！"+e.getMessage());
        }

    }
    @DeleteMapping
    public AxiosResult delete(String path){
        try {
            fastDfsService.delete(path);
            return AxiosResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AxiosResult.me().setSuccess(false).setMessage("删除文件失败"+e.getMessage());
        }

    }

}