import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

/*
 * PROJECT 9: PACKET SNIFFER
 * Submitted By;-
 * ISHAN RAI, 2019B3A70504P
 * SHREY BANSAL, 2019B3A70504P
 * Submitted to:-
 * Shail Saharan Ma'am on 23 November 2021
 */

public class AttackTraffic extends Traffic {

	public static final int NUMBER_OF_PACKETS = 1050;

	public static final int LOWER_LIMIT_OF_MESSAGE_LENGTH = 5;

	public static final int UPPER_LIMIT_OF_MESSAGE_LENGTH = 10;
	
	public static final int PORT_GENERATING_ATTACK_TRAFFIC = 9999;

	public static DatagramSocket datagramSocket;

	public static InetAddress ipAddress;

	public static byte byteBuffer[];

	public static void main(String[] args) throws IOException {

		// Here, we create the socket object for
		// carrying the data.
		datagramSocket = new DatagramSocket();

		ipAddress = InetAddress.getLocalHost();
		byteBuffer = null;

		int counterVariable = 0;
		while (counterVariable < NUMBER_OF_PACKETS) {

			String stringInput = AttackTraffic.generateRandomString();

			// convert the String input into the byte array.
			byteBuffer = stringInput.getBytes();

			// Now we Create the datagramPacket for sending
			// the data.
			DatagramPacket datagramPacketUsedToSendData = new DatagramPacket(byteBuffer, byteBuffer.length, ipAddress,
					PORT_GENERATING_ATTACK_TRAFFIC);

			// invoking the send call to actually send
			// the data.
			datagramSocket.send(datagramPacketUsedToSendData);

			counterVariable++;

		}

		datagramSocket.close();

	}

	private static String generateRandomString() {

		String ALLOWEDCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		// this is the set of allowed chars in the string
		// We have kept all the alphabets and numerics

		StringBuilder randomStringBuilder = new StringBuilder();
		// initiating an instance of the StringBuilder class

		Random rand = new Random();
		// this instance of Random class is used to set the length of the string to be
		// generated
		// It also helps in the generation of the random string itself
		
		int numberOfCharactersInTheString = LOWER_LIMIT_OF_MESSAGE_LENGTH
				+ rand.nextInt(UPPER_LIMIT_OF_MESSAGE_LENGTH - LOWER_LIMIT_OF_MESSAGE_LENGTH + 1);
		// length of the string to be generated -> a random value of length between 5 to
		// 10

		while (randomStringBuilder.length() < numberOfCharactersInTheString) {
			int index = (int) (rand.nextFloat() * ALLOWEDCHARS.length());
			randomStringBuilder.append(ALLOWEDCHARS.charAt(index));
		}

		String randomStringToBeReturned = randomStringBuilder.toString();
		// The instance of the StringBuilder class is converted to an instance of the
		// String class before the method returns

		return randomStringToBeReturned;
		// The String is returned

	}
}
