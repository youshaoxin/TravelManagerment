package you.shaoxin.Utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 功能:密码加密工具类
 * 创建时间: 2019-08-14 12:47 --游菜花
 */
public class PasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    /*
     * 功能:测试
    **/
    public static void main(String[] args) {
        String password = "123";
        String pwd = encodePassword(password);

        System.out.println(pwd);
    }

}
