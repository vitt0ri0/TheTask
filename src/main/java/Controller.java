import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TreeView<File> browser;

    @FXML
    private Button button;

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn columnName;
    @FXML
    private TableColumn columnType;

    public Controller() {
    }

    @Override
    public void initialize(URL fxmlFileColation, ResourceBundle resources) {

        //        String homeDir = System.getProperty("user.home");
        String homeDir = "C:\\Users\\Vitt0ri0\\Downloads";
        File home = new File(homeDir);


        TreeItem<File> root = new TreeItem<>(home);
        root.setExpanded(true);
        root = loadDirs(root);

        browser.setRoot(root);

        ObservableList<FileItem> data = FXCollections.observableArrayList();
        data.add(new FileItem("file1", ".jar"));
        data.add(new FileItem("file2", ".txt"));

        data = getDirectoryItems(home);

        // определяем фабрику для столбца с привязкой к свойству name
        columnName.setCellValueFactory(new PropertyValueFactory<FileItem, String>("name"));

        columnType.setCellValueFactory(new PropertyValueFactory<FileItem, String>("type"));

        tableView.setItems(data);
//        tableView.getColumns().addAll(new TableColumn("Hello"), new TableColumn("World"));

    }

    public TreeItem<File> loadDirs(TreeItem<File> root) {
        File file = root.getValue();
        File[] files = file.listFiles();
        if (files != null)
            for (File f : files) {
                TreeItem<File> node = loadDirs(new TreeItem<File>(f));
                root.getChildren().add(node);
            }
        return root;
    }

    public void showDirectoryItems() {

    }

    public ObservableList<FileItem> getDirectoryItems(File directory) {
        ObservableList<FileItem>  filesList = FXCollections.observableArrayList();
        File[] files = directory.listFiles();
        for (File f : files) {
            String filename = f.getName();
            int filetypeIndex = filename.lastIndexOf('.');

            String name;
            String type = "";

            if (f.isDirectory()) {
                name = filename;
                type = "<dir>";
            }
            else if (filetypeIndex == -1) {
                name = filename;
            } else {
                name = filename.substring(0, filetypeIndex);
                type = filename.substring(filetypeIndex);
            }

            filesList.add(new FileItem(name, type));
        }
        return filesList;
    }
}
