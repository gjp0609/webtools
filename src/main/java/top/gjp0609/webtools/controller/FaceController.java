package top.gjp0609.webtools.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;
import top.gjp0609.webtools.controller.dto.FaceArgs;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/face")
public class FaceController {


    @PostMapping("/stream")
    public Map<String, Object> stream(@RequestBody FaceArgs args) {
        System.err.println(System.currentTimeMillis());
        System.out.println(args);
        saveImage(args.getImage());
        String[] faces = args.getFaces();
        for (String face : faces) {
            saveImage(face);
        }
        HashMap<String, Object> map = new HashMap<>(4);
        map.put("code", 200);
        map.put("msg", "OK");
        return map;
    }

    /**
     * base64字符串转化成图片
     * 对字节数组字符串进行Base64解码并生成图片
     */
    private static void saveImage(String imgStr) {
        if (StringUtils.isBlank(imgStr)) {
            return;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                //调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            //生成图片
            String imgFilePath = "C:/Files/Faces/" + System.currentTimeMillis() + ".jpg";
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
        } catch (Exception ignored) {
        }
    }

}
