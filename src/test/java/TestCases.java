import com.google.zxing.WriterException;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import self.louie.service.QRCodeService;

import java.io.File;
import java.io.IOException;

/**
 * Created by louie on 2017-11-9.
 */
public class TestCases {

    @Test
    public void testCreateDefaultQRCode() throws IOException, WriterException {
        QRCodeService service = new QRCodeService();
        service.createQRCode("hello world",300,300,"./");
    }

    @Test
    public void testGetQRCodeBytes() throws Exception {
        QRCodeService service = new QRCodeService();
        byte[] bytes = service.getQRCodeBytes("https://www.runoob.com",500,500,1);

        FileUtils.writeByteArrayToFile(new File("我的二维码.png"),bytes,true);
    }


}
