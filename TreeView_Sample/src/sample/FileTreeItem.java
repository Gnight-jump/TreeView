package sample;

import sample.FileDate;
import static sample.control.ROOT_FILE;

import java.awt.event.ItemEvent;
import java.io.File;
import java.util.function.Function;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;


public class FileTreeItem extends TreeItem<String> {

    private final File file;
    private final Function<File,File[]> func;
    //判断树节点是否被初始化
    private boolean notInitialized = true;

    public FileTreeItem(File file) {
        super(FileDate.getFileName(file), FileDate.getFileIconToNode(file));
        this.file = file;
        func = (File f) -> {
            if (((FileTreeItem) this.getParent()).getFile() == ROOT_FILE) {
                String name = FileDate.getFileName(f);
                if (name.equals("网络")) {
                    return new File[0];
                }
            }
            return f.listFiles();
        };
    }

    public FileTreeItem(File file, Function<File,File[]> func) {
        super(FileDate.getFileName(file),FileDate.getFileIconToNode(file));
        this.file=file;
        this.func=func;
        //this.addEventHandler();
    }



    //重写getchildren，加载子目录
    @Override
    public ObservableList<TreeItem<String>> getChildren() {
        ObservableList<TreeItem<String>> children = super.getChildren();
        if (this.notInitialized && this.isExpanded()) {
            //修改初始化
            this.notInitialized = false;
            //若为目录则添加到子结点中
            if (this.getFile().isDirectory()) {
                for (File f : func.apply(this.getFile())) {
                    //如果文件是目录，则把它加到树节点上
                   if (f.isDirectory()||isImage(f)) {
                        children.add(new FileTreeItem(f));
                    }
                }
            }
        }
        return children;
    }
    //重写叶子方法,不为目录则是叶子
    @Override
    public boolean isLeaf() {
        return !file.isDirectory();
    }
    public File getFile() {
        return file;
    }



    //判断是否为图片
    private boolean isImage(File f){
        if(!f.isDirectory()){
            String name=f.getPath();
            String[] strArray = name.split("\\.");
            int Index = strArray.length -1;
            if(strArray[Index].equals("jpg")||strArray[Index].equals("jpeg")||strArray[Index].equals("gif")||
                    strArray[Index].equals("bmp")||strArray[Index].equals("png")||strArray[Index].equals("JPG")||
                    strArray[Index].equals("JPEG")||strArray[Index].equals("GIF")||
                    strArray[Index].equals("BMP")||strArray[Index].equals("PNG")){
                System.out.println(strArray[Index]);
                return true;
            }
        }
        return false;
    }

    //获取名字
    public String getString(File file) {
        return FileDate.getFileName(file) == null ? "" : FileDate.getFileName(file).toString();
    }
}
