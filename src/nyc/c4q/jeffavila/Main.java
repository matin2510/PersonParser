package nyc.c4q.jeffavila;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class Main {

    private static final String FILE_TO = "d:\\desktop\\PersonParser.txt";
    private static final String COMMA_URI = "https://s3.amazonaws.com/def-method/code-test/comma.txt";
    private static final String SPACE_URI = "https://s3.amazonaws.com/def-method/code-test/space.txt";
    private static final String PIPE_URI = "https://s3.amazonaws.com/def-method/code-test/pipe.txt";
    private static  String result = "";

    public static void main(String[] args) {
        // write your code here
        String[] URIS = {COMMA_URI,SPACE_URI,PIPE_URI};
        for (String uris:URIS) {
            URI u = URI.create(uris);
            try (InputStream inputStream = u.toURL().openStream()) {
                //File file = new File(FILE_TO);
                result =  convertInputStreamToString(inputStream);

            } catch (Exception e){
                System.out.println(e.getLocalizedMessage());
            }

            System.out.println(result);

        }

    }// InputStream -> File

    private static void copyInputStreamToFile(InputStream inputStream, File file)
            throws IOException {

        try (FileOutputStream outputStream = new FileOutputStream(file)) {

            int read;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            // commons-io
            //IOUtils.copy(inputStream, outputStream);

        }

    }
    private static String convertInputStreamToString(InputStream inputStream)
            throws IOException {

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }

        inputStream.close();

        return result.toString(StandardCharsets.UTF_8.name());

    }

}