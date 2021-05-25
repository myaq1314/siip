package com.pay.siip.util;

import com.pay.siip.validate.EmptyValidate;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author : czh
 * description :
 * date : 2021-05-20
 * email 916419307@qq.com
 */
public class StreamUtil {

    public static void close(Closeable... closeables) {
        if (EmptyValidate.isEmpty(closeables)) {
            return;
        }

        for (Closeable closeable : closeables) {
            try {
                if (closeable != null) {
                    closeable.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
