
public class DemoDownloadURL {
	public void main(String[] args) {
		
		 // 1. pedimos una URL por l�nea de comandos
        System.out.println("Escriba la URL");
        Scanner scanner = new Scanner(System.in);
        String url = scanner.nextLine();

        // 2. creamos el objeto URL
        try {
            URL myURL = new URL("http://" + url);

            // 3. Obtenemos un objeto HttpURLConnection. openConnection 
            HttpURLConnection conexion = (HttpURLConnection) myURL.openConnection();

            // 4. configuramos la conexión al método GET. setRequestMethod
            conexion.setRequestMethod("GET");

            // 5. Nos conectamos. connect
            conexion.connect();

            // 6. Obtenemos y imprimimos el código de respuesta. getResponseCode
            System.out.println("c�digo de respuesta= " + conexion.getResponseCode());

            // 7. Obtenemos y imprimimos el tamaño del recurso. ContentLength
            int fileLength = conexion.getContentLength();
            System.out.println("tama�o del archivo= " + fileLength);

            // 8. Guardamos el stream a un fichero con el nombre del recurso
            //    en caso de que el código sea correcto.
            //(BufferedInputStream -> FileOutputStream)
            InputStream input = conexion.getInputStream();
            byte[] buffer = new byte[4096];
            int n = - 1;

            File file = new File("archivo");
            OutputStream output = new FileOutputStream(file);
            while ((n = input.read(buffer)) != -1) {
                output.write(buffer, 0, n);
            }

            output.close();

            // 9. Damos un error en caso de que el código sea incorrecto
        } catch (IOException io) {
            System.out.println("la url no es correcta");
        }

    }
}