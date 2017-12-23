package com.wzh.shop.util;

import org.hibernate.dialect.MySQL57InnoDBDialect;

public class CustomMysqlDialect extends MySQL57InnoDBDialect {

    @Override
    public String getTableTypeString() {
        return "  ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
