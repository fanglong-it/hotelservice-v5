package fiveman.hotelservice.utils;

public class Utilities {

	public static boolean isEmptyString(String result) {
		if(result == null || result.trim().isEmpty() || result.isEmpty()) {
			return true;
		}
		return false;
	}
	
	// public static boolean isLong(String s) {
	//     try { 
	//        Long tmp = Long.parseLong(s); 
	//     } catch(NumberFormatException e) { 
	//         return false; 
	//     } catch(NullPointerException e) {
	//         return false;
	//     }
	//     return true;
	// }
	
}
