package com.pantesting.andromidi;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.widget.Toast;

import java.io.*;

public class FileManager {
    public static byte[] readFileFromInternalStorage(String filename, Context my_context) {
        try {
            // Chemin complet vers le fichier copié
            String filePath = my_context.getFilesDir() + "/Andromidi/" + filename;
            File file = new File(filePath);

            // Vérifiez si le fichier existe
            if (!file.exists()) {
                throw new FileNotFoundException("Fichier non trouvé : " + filePath);
            }

            // Lire le fichier
            InputStream is = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()]; // Utilisez la taille du fichier
            is.read(buffer);
            is.close();

            return buffer; // Retourne le contenu du fichier
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Retourne null en cas d'erreur
        }
    }

    public static byte[] readFileFromAsset(String filename, Context my_context) throws IOException {
        StringBuilder content = new StringBuilder();
        AssetManager assetManager = my_context.getAssets();
        InputStream inputStream = null;

        // Ouvrir le fichier dans les assets
        inputStream = assetManager.open(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        // Lire le contenu du fichier ligne par ligne
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();

        return content.toString().getBytes(); // Retourne le contenu du fichier
    }

    /**
     * installs preset files into Andromidi.
     * N'oubliez pas que lors de l'installation de l'APK, vous ne pouvez pas directement
     * écrire dans le système de fichiers, mais vous pouvez préparer les fichiers
     * dans le répertoire de l'application une fois qu'elle est lancée.
     */
    public  static void copyAssets(Context my_context) {
        AssetManager assetManager = my_context.getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
            int nb_effects = 0;
            for (String filename : files) {
                InputStream in = assetManager.open(filename);
                String outFileName = my_context.getFilesDir() + "/Andromidi/" + filename; // ou un autre chemin
                OutputStream out = new FileOutputStream(outFileName);
                byte[] buffer = new byte[1024];
                int read;
                while ((read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                }
                nb_effects += 1;
                in.close();
                out.flush();
                out.close();
                Toast.makeText(my_context, "" + nb_effects + " effets copiés", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void listCopiedFiles_internal(Context my_context) {
        String dir_path = my_context.getFilesDir() + "/Andromidi/";
        File dir_handler = new File(dir_path);

        // Vérifiez si le répertoire existe
        if (dir_handler.exists() && dir_handler.isDirectory()) {
            // Récupérez la liste des fichiers
            File[] files = dir_handler.listFiles();
            if (files != null) {
                for (File file : files) {
                    // Affichez le nom de chaque fichier
                    Log.d("CopiedFile", file.getName());
                }
            } else {
                Log.d("CopiedFile", "Aucun fichier trouvé dans le répertoire.");
            }
        } else {
            Log.d("CopiedFile", "Le répertoire n'existe pas.");
        }
    }
}
