//server


package exercise_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {

		try {
			// Bind ServerSocket to a port
			int portNo = 4220;
			ServerSocket serverSocket = new ServerSocket(portNo);

			while (true) {

				// Accept client request for connection
				Socket clientSocket = serverSocket.accept();

				// Create input stream
				DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());

				// Read the text from the client
				String input = dataInputStream.readUTF();

				// Get the value for the total words using the countWords() method
				int totalcountWords = countWords(input);

				// Create stream to write data on the network
				DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

				// Send the total word count back to the client
				outputStream.writeInt(totalcountWords);
				outputStream.flush();

				// Close the socket and streams
				outputStream.close();
				dataInputStream.close();
				clientSocket.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int countWords(String text) {
		// Split the text into words based on whitespace
		String[] words = text.split("\\s+");
		// Return the number of words
		return words.length;
	}
}