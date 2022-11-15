package com.assignment.utilities;

import org.mindrot.jbcrypt.BCrypt;

public class HashUtil {

    public static String hash(String plain) {
        // chuỗi các kí tự ngẫu nhiên
        String salt = BCrypt.gensalt();
        // truyền vào pass gốc và trộn với salt
        return BCrypt.hashpw(plain, salt);
    }

    public static boolean verify(String plain, String hashed) {
        return BCrypt.checkpw(plain, hashed);
    }

}
