package com.test.image;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author wujc
 * @ClassName ImageUtil
 * @Description: TODO
 * @create 2018-09-29 16:28
 */
public class ImageUtil {
    public static BufferedImage getBufferedImg(BufferedImage src, String url, int width, int height, int ro) {
        int angle = (int) (90 * ro);
        int type = src.getColorModel().getTransparency();
        int wid = width;
        int hei = height;
        if (ro % 2 != 0) {
            int temp = width;
            width = height;
            height = temp;
        }
        Rectangle re = new Rectangle(new Dimension(width, height));
        BufferedImage BfImg = null;
        BfImg = new BufferedImage(re.width, re.height, type);
        Graphics2D g2 = BfImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        /*g2.translate((re.width-width)/2, (re.height-height)/2);*/
        g2.rotate(Math.toRadians(angle), re.width / 2, re.height / 2);
        g2.drawImage(src, (re.width - wid) / 2, (re.height - hei) / 2, null);
        g2.dispose();
        return BfImg;
    }


}
