import com.google.zxing.WriterException;
import org.junit.Test;
import self.louie.service.QRCodeService;

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


}
