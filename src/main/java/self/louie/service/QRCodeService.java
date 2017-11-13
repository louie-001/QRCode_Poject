package self.louie.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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
     * @param imgPath 二维码图片存放路径
     */
    public void createQRCode(String content,int width,int height,int margin,String imgPath) throws WriterException, IOException {
        Hashtable<EncodeHintType,Object> hintTypes = new Hashtable<EncodeHintType, Object>();
        hintTypes.put(EncodeHintType.CHARACTER_SET,CharacterSetECI.UTF8);
        hintTypes.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hintTypes.put(EncodeHintType.MARGIN,margin);

        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width,height,hintTypes);
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
     * 获取二维码图片字节数组
     * @param content 二维码内容
     * @param width 宽
     * @param height 高
     * @param margin 外边距
     * @return 二维码对应的字节数组
     * @throws Exception
     */
    public byte[] getQRCodeBytes(String content,int width,int height,int margin) throws Exception{
        Hashtable<EncodeHintType,Object> hintTypes = new Hashtable<EncodeHintType, Object>();
        ByteArrayOutputStream outputStream = null;

        hintTypes.put(EncodeHintType.CHARACTER_SET,CharacterSetECI.UTF8);//设置编码
        hintTypes.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);//设置容错级别
        hintTypes.put(EncodeHintType.MARGIN,margin);//设置外边距

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width,height,hintTypes);//获取二维码

            //将二维码写入图片
            BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    bufferedImage.setRGB(i,j,bitMatrix.get(i,j)? Color.RED.getRGB():Color.WHITE.getRGB());
                }
            }

            //将图片转为字节数组
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage,"png",outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (outputStream != null){
                outputStream.close();
            }
        }
    }

}
