package core.app.zh.com.core.utils;

/**
 * Created by jjx on 2016/11/16.
 */
public class HelperUtil {

    public static String byteToHexString(byte[] buffer) {

        String hex = "";
        for (int i = 0; i < buffer.length; i++) {
            String temp = Integer.toHexString(buffer[i] & 0xFF);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            hex = hex + " " + temp;
        }


        return hex;

    }

    public static void arrayCopy(byte[] src, byte[] des, int start, int length) {
        for (int i = start; i < start + length; i++) {
            des[i - start] = src[i];
        }
    }

    public static byte[] HexStringToByteArray(String s) throws IllegalArgumentException {
        int len = s.length();
        if (len % 2 == 1) {
            throw new IllegalArgumentException("Hex string must have even number of characters");
        }
        byte[] data = new byte[len / 2]; // Allocate 1 byte per 2 hex characters
        for (int i = 0; i < len; i += 2) {
            // Convert each character into a integer (base-16), then bit-shift into place
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static byte[] getCertificationData() {
        return ("ZH:831557" + '\r' + '\n').getBytes();
    }

    public static byte[] getCheckData() {
        return ("ZH:readstate" + '\r' + '\n').getBytes();
    }

    public static byte[] getStopData() {
        return ("ZH:stop" + '\r' + '\n').getBytes();
    }

    public static byte[] getUpdata() {
        return ("ZH:goup" + '\r' + '\n').getBytes();
    }

    public static byte[] getDowndata() {
        return ("ZH:getd" + '\r' + '\n').getBytes();
    }

    public static byte[] getOpenFan() {
        return ("ZH:kqfs" + '\r' + '\n').getBytes();
    }

    public static byte[] getCloseFan() {
        return ("ZH:gbfs" + '\r' + '\n').getBytes();
    }

    public static byte[] getSMartdata() {
        return ("ZH:znms" + '\r' + '\n').getBytes();
    }

    public static byte[] getHumanControldata() {
        return ("ZH:sdms" + '\r' + '\n').getBytes();
    }

    public static byte[] getOpenUVLight() {
        return ("ZH:dkuv" + '\r' + '\n').getBytes();
    }

    public static byte[] getCloseUVLight() {
        return ("ZH:gbuv" + '\r' + '\n').getBytes();
    }

    public static byte[] getOpenLedData(String value) {
        return ("ZH:dkled" + value + '\r' + '\n').getBytes();
    }

    public static byte[] getCloseLedData() {
        return ("ZH:gbd" + '\r' + '\n').getBytes();
    }

    public static byte[] getPTCData(String value) {
        return ("ZH:dkptc" + value + '\r' + '\n').getBytes();
    }

    public static byte[] getClosePTCData() {
        return ("ZH:gbptc" + '\r' + '\n').getBytes();

    }
}
