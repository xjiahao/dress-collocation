package com.dress.collocation.util;

import com.dress.collocation.vo.VerificationCodeImg;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Description:验证码生成器
 * Created by xuejiahao on 2016/10/23.
 */
public class VerificationCodeUtils {

    private static final Font mFont = new Font("Times New Roman", Font.PLAIN, 17);

    /**
     * 构造6位数字验证码
     *
     * @return
     */
    public static String buildVerificationCode() {
        Random random = new Random();
        StringBuilder verificationCode = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            verificationCode.append(random.nextInt(10));
        }
        return verificationCode.toString();
    }


    private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 生成图片验证码
     *
     * @return
     */
    public static VerificationCodeImg buildImg() {
        VerificationCodeImg verificationCodeImg = new VerificationCodeImg();
        int width = 100, height = 18;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(getRandColor(200, 250));
        g.fillRect(1, 1, width - 1, height - 1);
        g.setColor(new Color(102, 102, 102));
        g.drawRect(0, 0, width - 1, height - 1);
        g.setFont(mFont);
        g.setColor(getRandColor(160, 200));
        //画随机线
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            g.drawLine(x, y, x + xl, y + yl);
        }
        //从另一方向画随机线
        for (int i = 0; i < 70; i++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int xl = random.nextInt(12) + 1;
            int yl = random.nextInt(6) + 1;
            g.drawLine(x, y, x - xl, y - yl);
        }
        //生成随机数,并将随机数字转换为字母
        StringBuilder verificationCode = new StringBuilder("");
        for (int i = 0; i < 6; i++) {
            int itmp = random.nextInt(26) + 65;
            char ctmp = (char) itmp;
            verificationCode.append(String.valueOf(ctmp));
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(String.valueOf(ctmp), 15 * i + 10, 16);
        }
        g.dispose();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "JPEG", bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        verificationCodeImg.setVerificationCode(verificationCode.toString());
        verificationCodeImg.setBase64(Base64Utils.encode(bytes.toByteArray()));
        return verificationCodeImg;
    }
}
