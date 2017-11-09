package self.louie.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.CharacterSetECI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * 二维码服务类
 * Created by louie on 2017-11-9.
 */
public class QRCodeService {

    /**
     * 生成二维码
     * @param content 二维码内容
     * @param width 宽
     * @param height 高
     * @param imgPath 二维码图片存放路径
     */
    public void createQRCode(String content,int width,int height,String imgPath) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width,height);
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                bufferedImage.setRGB(i,j,bitMatrix.get(i,j)? Color.BLACK.getRGB():Color.WHITE.getRGB());
            }
        }

        File imageFile = new File(imgPath+"myQRCode.png");
        if (!imageFile.getParentFile().exists()){
            imageFile.getParentFile().mkdirs();
        }
        ImageIO.write(bufferedImage,"png",imageFile);
    }

    /**
     * 生成二维码
     * @param content 二维码内容
     * @param width 宽
     * @param height 高
     * @param margin 二维码外边距，0到4
     * @param errorCorrectionLevel 容错级别，0到3
     * @param imgPath 二维码图片存放路径
     */
    public void createQRCode(String content,int width,int height,int margin,int errorCorrectionLevel,String imgPath){}

}
