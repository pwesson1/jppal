/*
    jppal, a Paypal button generator in Java
    Copyright (C) 2011  Maurice Perry <maurice@perry.ch>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.tastefuljava.pay;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
    private static final Logger LOG = Logger.getLogger(Util.class.getName());
    private static final MessageDigest DIGEST;
    private static final char HEX[] = "0123456789abcdef".toCharArray();

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String DATETIME_FORMAT = "dd/MM/yyyy HH:mm";

    public static <T> boolean eq(T a, T b) {
        if (a == b) {
            return true;
        } else if (a == null || b == null) {
            return false;
        } else {
            return a.equals(b);
        }
    }

    public static boolean isBlank(String s) {
        return s == null || s.trim().length() == 0;
    }

    public static String blank2null(String s) {
        if (s == null) {
            return null;
        }
        s = s.trim();
        return s.length() == 0 ? null : s;
    }

    public static String null2blank(String s) {
        return s == null ? "" : s.trim();
    }

    public static String hex(byte data[]) {
        char[] chars = new char[2*data.length];
        for (int i=0; i < data.length; i++) {
            int low = data[i] & 0x0f;
            int high = (data[i] & 0xf0) >> 4;
            chars[i*2] = HEX[high];
            chars[i*2 + 1] = HEX[low];
        }
        return new String(chars);
    }

    public static byte[] hash(byte data[]) {
        synchronized (DIGEST) {
            DIGEST.reset();
            return DIGEST.digest(data);
        }
    }

    public static String hash(String value) {
        try {
            byte data[] = hash(value.getBytes("UTF-8"));
            return hex(data);
        } catch (UnsupportedEncodingException e) {
            // Cannot happen: UTF-8 support is required by Java spec
            LOG.log(Level.SEVERE, "Failed to generate digest", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Date parseDateTime(String value, String pattern) {
        if (isBlank(value)) {
            return null;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.parse(value);
        } catch (ParseException e) {
            LOG.log(Level.SEVERE, "Error parsing date", e);
            throw new NumberFormatException(e.getMessage());
        }
    }

    public static Date parseDate(String value) {
        return parseDateTime(value, DATE_FORMAT);
    }

    public static Date parseDateTime(String value) {
        return parseDateTime(value, DATETIME_FORMAT);
    }

    public static String formatDateTime(Date value, String pattern) {
        if (value == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(value);
    }

    public static String formatDate(Date date) {
        return formatDateTime(date, DATE_FORMAT);
    }

    public static String formatDateTime(Date date) {
        return formatDateTime(date, DATETIME_FORMAT);
    }

    public static BigDecimal parseDecimal(String s) {
        if (isBlank(s)) {
            return null;
        }
        try {
            DecimalFormat format = getDecimalFormat("0.00");
            return (BigDecimal)format.parse(s);
        } catch (ParseException e) {
            LOG.log(Level.SEVERE, "Error parsing decimal", e);
            throw new NumberFormatException(e.getMessage());
        }
    }

    public static String formatDecimal(BigDecimal value) {
        if (value == null) {
            value = BigDecimal.ZERO;
        }
        DecimalFormat format = getDecimalFormat("0.00");
        return format.format(value);
    }

    private static DecimalFormat getDecimalFormat(String pattern) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        DecimalFormat format = new DecimalFormat(pattern);
        format.setParseBigDecimal(true);
        format.setDecimalFormatSymbols(symbols);
        return format;
    }

    static {
        try {
            DIGEST = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            LOG.log(Level.SEVERE, "Failed to create digest", e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
