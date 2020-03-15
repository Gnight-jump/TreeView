package sample;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.IntBuffer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritablePixelFormat;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;


public class FileDate {

    public static Canvas getFileIconToNode(File file) {
        //获取图标
        Image image = (Image) ((ImageIcon) FileSystemView.getFileSystemView().getSystemIcon(file)).getImage();
        BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.BITMASK);
        //把图片画到图片缓冲区
        bi.getGraphics().drawImage(image, 0, 0, null);
        //将图片缓冲区的数据转换成int型数组
        int[] data = ((DataBufferInt) bi.getData().getDataBuffer()).getData();
        WritablePixelFormat<IntBuffer> pixelFormat = PixelFormat.getIntArgbInstance();
        //建立画布
        Canvas canvas = new Canvas(bi.getWidth() + 2, bi.getHeight() + 2);
        PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
        pixelWriter.setPixels(1, 1, bi.getWidth(), bi.getHeight(), pixelFormat, data, 0, bi.getWidth());
        return canvas;
    }

    public static String getFileName(File file) {
        return FileSystemView.getFileSystemView().getSystemDisplayName(file);
    }
}
