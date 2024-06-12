package code;

/**
 * @author CaptainWang
 * @since 2024/6/12
 */

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.IOException;

public class QRCodeGenerator {
    public static void main(String[] args) {
        String content = "https://m.piaoxingqiu.com/content/642560d172c91700014e24e3?channelId=6267a80f4c98da6cf99bd224";
        int size = 400; // 设置二维码的大小

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, size, size, QrCodeUtils.qrCodeEncodeHintTypeMap());

            // 将二维码保存为文件
            MatrixToImageWriter.writeToFile(bitMatrix, "PNG", new File("qrcode-12.png"));
            System.out.println("二维码已生成并保存到文件中。");
            QrCodeUtils.creatQRCode(content);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}