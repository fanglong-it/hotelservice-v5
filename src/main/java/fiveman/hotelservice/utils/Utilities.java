package fiveman.hotelservice.utils;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.stream.IntStream;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import fiveman.hotelservice.entities.OrderDetail;
import fiveman.hotelservice.entities.RoomPrice;

public class Utilities {
      public static boolean isEmptyString(String result) {
            if (result == null || result.trim().isEmpty() || result.isEmpty()) {
                  return true;
            }
            return false;
      }

      public static String calculateHMac(String data, String algorithm, String key) throws Exception {
            Mac Hmac = Mac.getInstance(algorithm);
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), algorithm);
            Hmac.init(secret_key);

            return byteArrayToHex(Hmac.doFinal(data.getBytes("UTF-8")));
      }

      public static String byteArrayToHex(byte[] a) {
            StringBuilder sb = new StringBuilder(a.length * 2);
            for (byte b : a)
                  sb.append(String.format("%02x", b));
            return sb.toString();
      }

      public static String getCurrentDateByFormat(String format) {
            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));

            SimpleDateFormat formatter = new SimpleDateFormat(format);
            String vnp_CreateDate = formatter.format(cld.getTime());

            return vnp_CreateDate;
      }

      public static String getExpireDate(String format) {
            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            cld.add(Calendar.MINUTE, 15);
            return formatter.format(cld.getTime());
      }

      public static String hmacSHA512(final String key, final String data) {
            try {

                  if (key == null || data == null) {
                        throw new NullPointerException();
                  }
                  final Mac hmac512 = Mac.getInstance("HmacSHA512");
                  byte[] hmacKeyBytes = key.getBytes();
                  final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
                  hmac512.init(secretKey);
                  byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
                  byte[] result = hmac512.doFinal(dataBytes);
                  StringBuilder sb = new StringBuilder(2 * result.length);
                  for (byte b : result) {
                        sb.append(String.format("%02x", b & 0xff));
                  }
                  return sb.toString();

            } catch (Exception ex) {
                  return "";
            }
      }

      public static boolean isDateValid(String date) {
            try {
                  DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                  df.setLenient(false);
                  df.parse(date);
                  return true;
            } catch (ParseException e) {
                  return false;
            }
      }

      public static String getCurrentDate() {
            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String vnp_CreateDate = formatter.format(cld.getTime());
            return vnp_CreateDate;
      }

      public static boolean compareTwoDateString(String str1, String str2) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                  Date date1 = formatter.parse(str1);
                  Date date2 = formatter.parse(str2);
                  if (date1 == date2) {
                        return true;
                  }
            } catch (ParseException e) {
                  throw new RuntimeException(e);
            }
            return false;
      }

      public static String parseDoubleToVND(double price) {
            String COUNTRY = "VN";
            String LANGUAGE = "vi";
            String str = NumberFormat.getCurrencyInstance(new Locale(LANGUAGE, COUNTRY)).format(price);
            return str;
      }

      public static double calculateTotalAmount(List<OrderDetail> orderDetails) {
            double totalAmount = 0;
            for (OrderDetail orderDetail : orderDetails) {
                  totalAmount += (orderDetail.getQuantity() * orderDetail.getAmount());
            }
            return totalAmount;
      }

      public static List<String> getStringDateBetweenArrivalAndDeparture(String startDate,
                  String endDate) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime startDateLDT = LocalDateTime.parse(startDate,
                        dtf);
            LocalDateTime endDateLDT = LocalDateTime.parse(endDate, dtf);
            List<String> dates = new ArrayList<>();
            for (LocalDateTime date = startDateLDT; date.isBefore(endDateLDT); date = date.plusDays(1)) {
                  String dateString = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(date);
                  dates.add(dateString);
            }
            return dates;
      }

      public static int findIndex(List<RoomPrice> list, String date) {
            int len = list.size();
            return IntStream.range(0, len)
                    .filter(i -> date.equals(list.get(i).getDate()))
                    .findFirst() // first occurrence
                    .orElse(-1); // No element found
      }

}
