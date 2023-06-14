//client

//client

package exercise_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {

		try {
			// Connect to the server at localhost, port 4228
			Socket socket = new Socket(InetAddress.getLocalHost(), 4220);

			// Create a scanner to read user input
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter text: ");
			String text = scanner.nextLine();

			// Create output stream
			DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

			// Send text to the server
			outputStream.writeUTF(text);

			// Create input stream
			DataInputStream inputStream = new DataInputStream(socket.getInputStream());

			// Read the word count from the network
			int wordCount = inputStream.readInt();
			System.out.println("Number of words: " + wordCount);

			// Close everything
			inputStream.close();
			outputStream.close();
			socket.close();
			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
