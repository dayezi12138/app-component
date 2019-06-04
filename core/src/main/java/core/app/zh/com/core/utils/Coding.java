package core.app.zh.com.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/** 字节转换类 Unicode **/
public class Coding {
	/**
	 * 读取单字节
	 * 
	 * @param list
	 * @param startIndex
	 * @return
	 */
	public static int read1Byte(byte[] list, int startIndex) {

		return list[startIndex] & 0xff;
	}

	/**
	 * 读取2字节
	 * 
	 * @param list
	 * @param startIndex
	 * @return
	 */
	public static int read2Byte(byte[] list, int startIndex) {
		/*byte[] dataAddArray2 = new byte[2];
		for (int i = 0; i < 2; i++) {
			dataAddArray2[1-i] = list[startIndex + i];
		}*/
		return readFrom2Byte(list);
	}

	/**
	 * 读取4字节
	 * 
	 * @param list
	 * @param startIndex
	 * @return
	 */
	public static int read4Byte(byte[] list, int startIndex) {
		byte[] dataAddArray4 = new byte[4];
		for (int i = 0; i < 4; i++) {
			dataAddArray4[3-i] = list[startIndex + i];
		}
		return readFrom4Byte(dataAddArray4);
	}

	/**
	 * 读取8字节
	 * 
	 * @param bb
	 * @param index
	 * @return
	 */
	public static long read8Byte(byte[] bb, int index) {
/*		return ((long) (bb[index + 7] & 0xff)) << 56
				| ((long) (bb[index + 6] & 0xff)) << 48
				| ((long) (bb[index + 5] & 0xff)) << 40
				| ((long) (bb[index + 4] & 0xff)) << 32
				| ((long) (bb[index + 3] & 0xff)) << 24
				| ((long) (bb[index + 2] & 0xff)) << 16
				| ((long) (bb[index + 1] & 0xff)) << 8
				| ((long) (bb[index + 0] & 0xff)) << 0;*/


        return ((long) (bb[index + 0] & 0xff)) << 56
                | ((long) (bb[index + 1] & 0xff)) << 48
                | ((long) (bb[index + 2] & 0xff)) << 40
                | ((long) (bb[index + 3] & 0xff)) << 32
                | ((long) (bb[index + 4] & 0xff)) << 24
                | ((long) (bb[index + 5] & 0xff)) << 16
                | ((long) (bb[index + 6] & 0xff)) << 8
                | ((long) (bb[index + 7] & 0xff)) << 0;
	}

	/**
	 * 通过byte数组取得float
	 * 
	 * @param b
	 * @param index
	 * @return
	 */
	public static double readDouble(byte[] b, int index) {
		long l;
		l = b[0];
		l &= 0xff;
		l |= ((long) b[index+1] << 8);
		l &= 0xffff;
		l |= ((long) b[index+2] << 16);
		l &= 0xffffff;	
		l |= ((long) b[index+3] << 24);
		l &= 0xffffffffl;
		l |= ((long) b[index+4] << 32);
		l &= 0xffffffffffl;
		l |= ((long) b[index+5] << 40);
		l &= 0xffffffffffffl;
		l |= ((long) b[index+6] << 48);
		l &= 0xffffffffffffffl;
		l |= ((long) b[index+7] << 56);
//		return Double.parseDouble(Util.getFormatDouble(Double.longBitsToDouble(l),"#.##"));
		return Double.longBitsToDouble(l);  
	}	
	
	/**
	 * 写入2字节	
	 * 
	 * @param list
	 * @param content
	 * @param startIndex
	 */
	public static void write2byte(byte[] list, int content, int startIndex) {
		byte[] dataAddArray2 = new byte[2];
		dataAddArray2 = build2ByteArray(content);
		for (int i = 0; i < 2; i++) {
			list[startIndex + i] = dataAddArray2[1 - i];
			//list[startIndex + i] = dataAddArray2[i];
		}
	}

	/**
	 * 写入4字节
	 * 
	 * @param list
	 * @param content
	 * @param startIndex
	 */
	public static void write4byte(byte[] list, long content, int startIndex) {
		byte[] dataAddArray4 = new byte[4];
		dataAddArray4 = build4ByteArray(content);
		for (int i = 0; i < 4; i++) {
			list[startIndex + i] = dataAddArray4[3 - i];
		}
	}

	/**
	 * 写入8字节 整形
	 * 
	 * @param list
	 * @param content
	 * @param startIndex
	 */
	public static void write8byte(byte[] list, long content, int startIndex) {
		byte[] dataAddArray8 = new byte[8];
		dataAddArray8 = build8ByteArray(content);
		for (int i = 0; i < 8; i++) {
			list[startIndex + i] = dataAddArray8[7 - i];
		}
	}
	
//	/**
//	 * 写入8字节 浮点型
//	 * 
//	 * @param list
//	 * @param content
//	 * @param startIndex
//	 */
//	public static void writeDouble8byte(byte[] list, double d, int startIndex) {
//		byte[] dataAddArray8 = new byte[8];
////		dataAddArray8 = build8ByteArray(content);
//		long content = Double.doubleToLongBits(d);
//		dataAddArray8 = build8ByteArray(content);
//		for (int i = 0; i < 8; i++) {
//			list[startIndex + i] = dataAddArray8[7 - i];
//		}	
//	}

	/**
	 * 读两字节
	 * 
	 * @param getData
	 * @return
	 */
	public static int readFrom2Byte(byte[] getData) {
		return (getData[0] & 0xff) << 8 | getData[1] & 0xff;
	}

	/**
	 * 读四字节
	 * 
	 * @param getData
	 * @return
	 */
	public static int readFrom4Byte(byte[] getData) {
/*		return (getData[3] & 0xff) << 24 | (getData[2] & 0xff) << 16
				| (getData[1] & 0xff) << 8 | getData[0] & 0xff;*/


		return (getData[0] & 0xff) << 24 | (getData[1] & 0xff) << 16
				| (getData[2] & 0xff) << 8 | getData[3] & 0xff;
	}

	/**
	 * 读取8字节
	 * 
	 * @param b
	 * @return
	 */
	public static long read8Byte(byte[] b) {
		long temp = 0;
		long res = 0;
		for (int i = 0; i < 8; i++) {
			res <<= 8;
			temp = b[i] & 0xff;
			res |= temp;
		}
		return res;
	}

	/**
	 * 添加两字节入发包
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] build2ByteArray(int data) {
		byte[] dataOne = new byte[2];
		dataOne[0] = (byte) (data >> 8 & 0xff);
		dataOne[1] = (byte) (data & 0xff);
		return dataOne;
	}

	/**
	 * 添加四字节入发包
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] build4ByteArray(long data) {
		byte[] dataOne = new byte[4];
		dataOne[0] = (byte) (data >> 24 & 0xff);
		dataOne[1] = (byte) (data >> 16 & 0xff);
		dataOne[2] = (byte) (data >> 8 & 0xff);
		dataOne[3] = (byte) (data & 0xff);
		return dataOne;
	}

	/**
	 * 添加八字节入发包
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] build8ByteArray(long data) {
		byte[] dataOne = new byte[8];
		dataOne[0] = (byte) (data >> 56 & 0xff);
		dataOne[1] = (byte) (data >> 48 & 0xff);
		dataOne[2] = (byte) (data >> 40 & 0xff);
		dataOne[3] = (byte) (data >> 32 & 0xff);
		dataOne[4] = (byte) (data >> 24 & 0xff);
		dataOne[5] = (byte) (data >> 16 & 0xff);
		dataOne[6] = (byte) (data >> 8 & 0xff);
		dataOne[7] = (byte) (data & 0xff);
		return dataOne;
	}

	/**
	 * @param value
	 * @param arraySize
	 * @return
	 */
	public static byte[] stringToWcharUnicodeBytes(String value, int arraySize) {
		char[] valueChars = value.toCharArray();
		byte[] data = new byte[valueChars.length * 2];

		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) 0xFE;
		}

		for (int i = 0; i < valueChars.length && i < arraySize; i++) {
			data[i * 2] = (byte) valueChars[i];
			data[i * 2 + 1] = (byte) (valueChars[i] >> 8);
		}

		return data;
	}

	/**
	 * 将java字符串转换为wchar数组
	 * 
	 * @param value
	 * @param data
	 * @param pos
	 */
	public static void stringToWcharUnicodeBytes(String value, byte[] data,
			int pos) {
		if(value == null || value.equals(""))
			return ;
		char[] valueChars = value.toCharArray();

		for (int i = pos; i < pos + valueChars.length * 2; i++) {
			data[i] = (byte) 0xFE;
		}

		for (int i = 0; i < valueChars.length; i++) {
			data[i * 2 + pos] = (byte) valueChars[i];
			data[i * 2 + 1 + pos] = (byte) (valueChars[i] >> 8);
		}
	}

	/**
	 * 将wchar数组 转换为java字符串
	 * 
	 * @param data
	 * @return
	 */
	public static String wcharUnicodeBytesToString(byte[] data, int pos,
			int lenght) {
		StringBuffer builder = new StringBuffer("");
		if (lenght == 0) {
			lenght = data.length - pos;
		}
		if (lenght % 2 != 0) {
			lenght -= 1;
		}
		for (int i = pos; i < pos + lenght; i += 2) {
			if (data[i] == 0xfffffffe) {
				break;
			}
			if (data[i] == 0 && data[i + 1] == 0) {
				break;
			}
			int[] temp = new int[2];
			temp[0] = data[i] & 0xFF;
			temp[1] = data[i + 1] & 0xFF;

			builder.append(wcharUnicodeBytesToChar(temp));
		}

		return builder.toString();
	}

	/**
	 * 将wchar 转换为java字符
	 * 
	 * @param data
	 * @return
	 */
	private static char wcharUnicodeBytesToChar(int[] data) {

		char c = (char) ((data[1] << 8) + data[0]);

		return c;
	}

	public final static String changeToMD5(String str)
			throws NoSuchAlgorithmException {
		if (str != null && !str.equals("")) {
			return toMd5(str.getBytes()).toUpperCase();
		}
		return "";
	}

	private static String toMd5(byte[] bytes) throws NoSuchAlgorithmException {

		MessageDigest algorithm = MessageDigest.getInstance("MD5");
		algorithm.reset();
		algorithm.update(bytes);
		return toHexString(algorithm.digest(), "0");

	}

	private static String toHexString(byte[] bytes, String separator) {
		StringBuffer ret = new StringBuffer("");
		for (byte buf : bytes) {
			String str = Integer.toHexString(buf);
			if (str.length() < 2) {
				ret.append(separator + str);
			} else {
				ret.append(str.substring(str.length() - 2));
			}
		}
		return new String(ret);
	}
}
