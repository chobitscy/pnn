package com.pn.support;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Nullable;

public class Condition {

    public Condition() {

    }

    public static <T> IPage<T> getPage(Query query) {
        long current = query.getCurrent() == null ? 1 : query.getCurrent();
        long size = query.getSize() == null ? 10 : query.getSize();
        Page<T> page = new Page<T>(current, size);
        String[] ascArr = query.getAscs().split(",");
        String[] descArr = ascArr;
        int var4 = ascArr.length;

        int var5;
        for (var5 = 0; var5 < var4; ++var5) {
            String asc = descArr[var5];
            page.addOrder(OrderItem.asc(cleanIdentifier(asc)));
        }

        descArr = query.getDescs().split(",");
        String[] var8 = descArr;
        var5 = descArr.length;

        for (int var9 = 0; var9 < var5; ++var9) {
            String desc = var8[var9];
            page.addOrder(OrderItem.desc(cleanIdentifier(desc)));
        }

        return page;
    }

    public static String cleanIdentifier(@Nullable String param) {
        if (param == null) {
            return null;
        } else {
            StringBuilder paramBuilder = new StringBuilder();

            for (int i = 0; i < param.length(); ++i) {
                char c = param.charAt(i);
                if (Character.isJavaIdentifierPart(c)) {
                    paramBuilder.append(c);
                }
            }

            return paramBuilder.toString();
        }
    }
}
