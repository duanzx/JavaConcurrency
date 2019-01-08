package fileTest;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.*;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class CompressTest {
    /*http://www.xwood.net/_site_domain_/_root/5870/5874/t_c272126.html*/

    /**
     * 遍历所有的文件，全部压缩，并设置密码为99RE4
     */

    public void testCompressFile(String filePath, File sourceFile) throws Exception {
        File tarFile = Paths.get(filePath, UUID.randomUUID().toString().replace("-", "").toUpperCase() + ".zip").toFile();
        tarFile.createNewFile();
        ArchiveOutputStream archiveOutputStream = null;
        InputStream inputStream = null;
        try {
            archiveOutputStream = new ZipArchiveOutputStream(new FileOutputStream(tarFile));
            ZipFile zipFile = new ZipFile(tarFile.toPath().toString());
            ArchiveEntry entry = archiveOutputStream.createArchiveEntry(sourceFile, sourceFile.getName());
            archiveOutputStream.putArchiveEntry(entry);
            inputStream = Files.newInputStream(sourceFile.toPath());
            IOUtils.copy(inputStream, archiveOutputStream);
            archiveOutputStream.closeArchiveEntry();
        } catch (Exception e) {
            throw e;
        } finally {
            IOUtils.closeQuietly(archiveOutputStream);
            IOUtils.closeQuietly(inputStream);
        }
    }

    @Test
    public void test() throws Exception {
        Path paths = Paths.get("D:\\docatm", "aa.txt");
        File sourceFile = paths.toFile();
        if (!sourceFile.exists()) {
            sourceFile.createNewFile();
        }
        testCompressFile("D:\\docatm", sourceFile);

//        for(File parentFile : paths.toFile().listFiles()){
//            File[] subFiles = parentFile.listFiles();
//            for(File subFile : subFiles){
//                System.out.println(subFile.getName());
//            }
//        }
    }
}
