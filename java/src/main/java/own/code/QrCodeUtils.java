package own.code;

/**
 * @author CaptainWang
 * @since 2024/6/12
 */

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class QrCodeUtils {

    /**
     * 默认是黑色
     */
    private static final int QR_COLOR = 0xFF000000;

    /**
     * 背景颜色
     */
    private static final int BG_WHITE = 0xFFFFFFFF;


    public static void creatQRCode(String qrCodeContent) throws WriterException {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bm;
        bm = multiFormatWriter.encode(qrCodeContent, BarcodeFormat.QR_CODE, 400, 400,
                qrCodeEncodeHintTypeMap());
        int w = bm.getWidth();
        int h = bm.getHeight();
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        try {
            // 开始利用二维码数据创建Bitmap图片，分别设为黑（0xFFFFFFFF）白（0xFF000000）两色
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    image.setRGB(x, y, bm.get(x, y) ? QR_COLOR : BG_WHITE);
                }
            }
            ImageIO.write(image, "png", new File("qrcode-13.png"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            image.flush();
        }
    }


    //用于设置QR二维码参数
    public static Map<EncodeHintType, Object> qrCodeEncodeHintTypeMap() {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        // 设置QR二维码的纠错级别（H为最高级别）具体级别信息
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        // 设置编码方式
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 设置间距
        hints.put(EncodeHintType.MARGIN, 0);
        return hints;
    }


}
