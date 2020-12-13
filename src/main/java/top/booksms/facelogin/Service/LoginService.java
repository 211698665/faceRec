package top.booksms.facelogin.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.booksms.facelogin.Face.BaiduAIFace;
import top.booksms.facelogin.SetingModel.Setingmodel;

import java.io.IOException;
import java.util.Map;

@Service
public class LoginService {
    @Autowired
    BaiduAIFace faceapi;
    @Autowired
    Setingmodel setingmodel;
    public Map<String,Object> searchface(StringBuffer imagebase64) throws IOException {
        String substring = imagebase64.substring(imagebase64.indexOf(",")+1, imagebase64.length());
        setingmodel.setImgpath(substring);
        setingmodel.setGroupID("syw");  //需要在百度智能云上创建人脸识别库
        System.out.println(substring);
        Map map = faceapi.FaceSearch(setingmodel);
        return map;
    }
}
