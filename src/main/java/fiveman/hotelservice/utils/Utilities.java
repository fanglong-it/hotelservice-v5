package fiveman.hotelservice.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Utilities {

      public static boolean isEmptyString(String result) {
            if (result == null || result.trim().isEmpty() || result.isEmpty()) {
                  return true;
            }
            return false;
      }

      public static final String ALGORITHM = "HmacSHA256";

      public static String calculateHMac(String data) throws Exception {
            Mac sha256_HMAC = Mac.getInstance(ALGORITHM);
            String key = Common.SECRET_KEY;
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), ALGORITHM);
            sha256_HMAC.init(secret_key);

            return byteArrayToHex(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
      }

      public static String byteArrayToHex(byte[] a) {
            StringBuilder sb = new StringBuilder(a.length * 2);
            for (byte b : a)
                  sb.append(String.format("%02x", b));
            return sb.toString();
      }

      // public static boolean isLong(String s) {
      // try {
      // Long tmp = Long.parseLong(s);
      // } catch(NumberFormatException e) {
      // return false;
      // } catch(NullPointerException e) {
      // return false;
      // }
      // return true;
      // }

}
