package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class control implements Initializable {

    public static File ROOT_FILE = FileSystemView.getFileSystemView().getRoots()[0];

    @FXML
    private TreeView<String> tree_map;
    @FXML
    private Button btn_close;



    public void eventClose(){
        System.out.println(ROOT_FILE);
    }

    public void initialize(URL location, ResourceBundle resources){
        initTreeView();
    }
    public void initTreeView(){
        FileTreeItem fileTreeItem = new FileTreeItem(ROOT_FILE, f -> {
            File[] all = f.listFiles();
            File[] director = f.listFiles(File::isDirectory);
            List<File> list = new ArrayList<>(Arrays.asList(all));
            list.removeAll(Arrays.asList(director));
            return list.toArray(new File[list.size()]);
        });
        tree_map.setRoot(fileTreeItem);
        tree_map.setShowRoot(false);

    }

}
