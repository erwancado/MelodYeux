package converter;
//Importation des packages dont on va se servir
import java.io.*;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileManager {

    /**
     * décompresse le fichier zip dans le répertoire donné
     * @param zipfile le fichier zip à décompresser
     * @param folder le répertoire où les fichiers seront extraits
     * @throws FileNotFoundException
     * @throws IOException
     * @return
     */
    public static File unzip(File zipfile, File folder) throws FileNotFoundException, IOException{

        // création de la ZipInputStream qui va servir à lire les données du fichier zip
        ZipInputStream zis = new ZipInputStream(
                new BufferedInputStream(
                        new FileInputStream(zipfile.getCanonicalFile())));

        // extractions des entrées du fichiers zip (i.e. le contenu du zip)
        ZipEntry ze = null;
        try {
            while((ze = zis.getNextEntry()) != null){

                // Pour chaque entrée, on crée un fichier
                // dans le répertoire de sortie "folder"
                File f = new File(folder.getCanonicalPath(), ze.getName());

                // Si l'entrée est un répertoire,
                // on le crée dans le répertoire de sortie
                // et on passe à l'entrée suivante (continue)
                if (ze.isDirectory()) {
                    f.mkdirs();
                    continue;
                }

                // L'entrée est un fichier, on crée une OutputStream
                // pour écrire le contenu du nouveau fichier
                f.getParentFile().mkdirs();
                OutputStream fos = new BufferedOutputStream(
                        new FileOutputStream(f));

                // On écrit le contenu du nouveau fichier
                // qu'on lit à partir de la ZipInputStream
                // au moyen d'un buffer (byte[])
                try {
                    try {
                        final byte[] buf = new byte[8192];
                        int bytesRead;
                        while (-1 != (bytesRead = zis.read(buf)))
                            fos.write(buf, 0, bytesRead);
                    }
                    finally {
                        fos.close();
                    }
                }
                catch (final IOException ioe) {
                    // en cas d'erreur on efface le fichier
                    f.delete();
                    throw ioe;
                }
            }
        }
        finally {
            // fermeture de la ZipInputStream
            zis.close();
        }
        File file = Objects.requireNonNull(folder.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".xml");
            }
        }))[0];
        return file;
    }
    public static boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }
}
