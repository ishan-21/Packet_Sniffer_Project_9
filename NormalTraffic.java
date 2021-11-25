import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/*
 * PROJECT 9: PACKET SNIFFER
 * Submitted By;-
 * ISHAN RAI, 2019B3A70504P
 * SHREY BANSAL, 2019B3A70504P
 * Submitted to:-
 * Shail Saharan Ma'am on 24 November 2021
 */

public class NormalTraffic extends Traffic {

	public static final int NUMBER_OF_PACKETS = 5;
	
	public static final int PORT_GENERATING_NORMAL_TRAFFIC = 9999;

	public static DatagramSocket datagramSocket;

	public static InetAddress ipAddress;

	public static byte byteBuffer[];

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		// Here, we Create the socket object for
		// carrying the data.
		datagramSocket = new DatagramSocket();

		ipAddress = InetAddress.getLocalHost();
		byteBuffer = null;

		// loops while user not enters "bye" or the counterVariable reaches the
		// NUMBER_OF_PACKETS
		int counterVariable = 0;
		while (counterVariable < NUMBER_OF_PACKETS) {
			System.out.println("Please enter the input string for the request: ");
			String stringInput = sc.nextLine();

			// converting the String input into the byte array.
			byteBuffer = stringInput.getBytes();

			// here, we create the datagramPacket for sending
			// the data.
			DatagramPacket datagramPacketUsedToSendData = new DatagramPacket(byteBuffer, byteBuffer.length, ipAddress, PORT_GENERATING_NORMAL_TRAFFIC );

			// Now, we invoke the send call to actually send
			// the data.
			datagramSocket.send(datagramPacketUsedToSendData);

			// break the loop if client enters "bye"
			if (stringInput.equals("bye")) {
				break;
			}
			counterVariable++;

		}

		datagramSocket.close();
		sc.close();
	}

}
