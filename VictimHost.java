import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

/*
 * PROJECT 9: PACKET SNIFFER
 * Submitted By;-
 * ISHAN RAI, 2019B3A70504P
 * SHREY BANSAL, 2019B3A70504P
 * Submitted to:-
 * Shail Saharan Ma'am on 24 November 2021
 */

public class VictimHost {
	static ArrayList<DatagramPacket> listOfPackets = new ArrayList<>();
	// ArrayList of Datagram Packets

	public static final int NORMAL_THRESHOLD_PACKETS = 1000;
	// number of packets after which it is classified as an attack

	public static final int SIZE_OF_BYTE_ARRAY = 65535;
	// The constant which defines the size of the byte array

	public static final int PORT_AT_LOCAL_HOST_MACHINE = 9999;

	public static DatagramSocket datagramSocket;

	public static byte[] dataAsByteArray;

	public static DatagramPacket datagramPacketUsedToReceiveData;

	public static int numberOfPacketsReceived;

	public static void main(String[] args) throws IOException {
		// Here, we create a socket to listen at port stationed at local host machine
		datagramSocket = new DatagramSocket(PORT_AT_LOCAL_HOST_MACHINE);
		dataAsByteArray = new byte[SIZE_OF_BYTE_ARRAY];

		datagramPacketUsedToReceiveData = null;

		numberOfPacketsReceived = 0;
		// set initially to zero

		while (true) {

			// Now, we create a DatgramPacket to receive the data.
			datagramPacketUsedToReceiveData = new DatagramPacket(dataAsByteArray, dataAsByteArray.length);

			// Now, the socket receives the data in the byte buffer.
			datagramSocket.receive(datagramPacketUsedToReceiveData);
			listOfPackets.add(datagramPacketUsedToReceiveData);

			numberOfPacketsReceived++;

			System.out.println("The Request sent by the Client: " + VictimHost.convertByteToString(dataAsByteArray));
			VictimHost.scanPort(datagramPacketUsedToReceiveData);

			// Exit the server if the client sends "bye"
			// note that this is intended just for the NormalTraffic Class as in the attack
			// traffic class we have automated the process of String
			// generation -> we are not taking input from the user in the case of Attack
			// Traffic as entering 1050 strings
			// by hand is close to impossible

			if (convertByteToString(dataAsByteArray).toString().equals("bye")) {
				System.out.println("Client sent bye.....EXITING");
				break;
			}

			// Clear the buffer after every message.
			dataAsByteArray = new byte[65535];

			if (numberOfPacketsReceived > NORMAL_THRESHOLD_PACKETS) {
				System.out.println("An attack is happening".toUpperCase());
			}
		}

		datagramSocket.close();
	}

	// A utility method to convert the byte array
	// data into a string representation.

	public static String convertByteToString(byte[] a) {
		if (a == null)
			return null;
		StringBuilder instanceOfStringBuilder = new StringBuilder();
		int i = 0;
		while (a[i] != 0) {
			instanceOfStringBuilder.append((char) a[i]);
			i++;
		}
		return instanceOfStringBuilder.toString();
		// returns the byte as a string
	}

	/*
	 * This method is used to scan the port and ultimately print the required output
	 * on the screen
	 */
	public static void scanPort(DatagramPacket datagramPacket) {

		// for a system operating on a server, the same system sends and receives the
		// request
		// therefore, the Source IP Address and Destination IP Address would be same

		System.out.println("The Source IP Address is: " + datagramPacket.getAddress());
		System.out.println("The Destination IP Address is: " + datagramPacket.getAddress());

		// The same goes for the source port and the destination port

		System.out.println("The Source Port is: " + datagramPacket.getPort());
		System.out.println("The Destination Port is: " + datagramPacket.getPort());

	}
}