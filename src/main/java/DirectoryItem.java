import java.io.File;

public class DirectoryItem extends File{

    public DirectoryItem(String pathname) {
        super(pathname);
    }

    @Override
    public String toString() {
        return getName();
    }
}
