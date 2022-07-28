package helper;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("Duplicates")
public class DateHelper {
    public static final ZoneId BEIJING_ZONE_ID = ZoneId.of("+8");

    public final static DateFormat DF = DateFormat.getDateInstance(DateFormat.FULL, Locale.CHINA);

    public static Date fromLong(Long in) {
        return fromLong(in, null);
    }

    public static Date fromLong(Long in, Date defaultValue) {
        return in == null ? defaultValue : new Date(in);
    }

    public static Date fromLongString(String string) {
        return fromLongString(string, null);
    }

    public static Date fromLongString(String string, Date defaultValue) {
        try {
            return new Date(Long.parseLong(StringUtils.trimToNull(string)));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static String fromDateToString(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取输入时间的前一个午夜
     */
    public static Date getBeforeMidnight(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取输入日期的下一个午夜
     */
    public static Date getAfterMidnight(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取输入日期
     */
    public static Date getPeriod(Integer year, Integer month) {
        if (year == null || month == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 年度的最后一刻
     */
    public static Date lastTimeOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR) + 1;
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR, -1);
        calendar.roll(Calendar.SECOND, -1);
        calendar.roll(Calendar.MINUTE, -1);
        return calendar.getTime();
    }

    /**
     * 年度的最后一刻
     */
    public static Date lastTimeOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.HOUR, -1);
        calendar.roll(Calendar.SECOND, -1);
        calendar.roll(Calendar.MINUTE, -1);
        return calendar.getTime();
    }

    /**
     * 取当前月的第一天
     */
    public static Date beginOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 取当前月的最后一天
     */
    public static Date lastOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 判断两个时间段是否有重叠
     * 当两个时间段中任意一个的开始时间，处在另一个时间段中，则两个时间段有重叠
     * 不允许端点重合
     */
    public static boolean isRepeated(Date leftStart, Date leftEnd, Date rightStart, Date rightEnd) {
        return leftStart.getTime() >= rightStart.getTime() && leftStart.getTime() <= rightEnd.getTime()
                || rightStart.getTime() >= leftStart.getTime() && rightStart.getTime() <= leftEnd.getTime();
    }

    /**
     * 判断两个时间段是否有重叠
     * 当两个时间段中任意一个的开始时间，处在另一个时间段中，则两个时间段有重叠
     * 允许端点重合
     */
    public static boolean isRepeatedAllow(Date leftStart, Date leftEnd, Date rightStart, Date rightEnd) {
        return leftStart.getTime() >= rightStart.getTime() && leftStart.getTime() < rightEnd.getTime()
                || rightStart.getTime() >= leftStart.getTime() && rightStart.getTime() < leftEnd.getTime();
    }

    public static int getYear(Date date) {
        return date.toInstant().atZone(BEIJING_ZONE_ID).getYear();
    }

    public static int getMonth(Date date) {
        return date.toInstant().atZone(BEIJING_ZONE_ID).getMonthValue();
    }

    public static String transDurationToCN(long mills, TimeUnit timeUnit) {
        long leftSeconds = mills / 1000;
        long days = leftSeconds / 86400;
        if (timeUnit == TimeUnit.DAYS)
            return days + "天";

        leftSeconds %= 86400;
        long hours = leftSeconds / 3600;
        if (timeUnit == TimeUnit.HOURS)
            return days + "天" + hours + "时";

        leftSeconds %= 3600;
        long minutes = leftSeconds / 60;
        if (timeUnit == TimeUnit.MINUTES)
            return days + "天" + hours + "时" + minutes + "分";

        leftSeconds %= 60;
        if (timeUnit == TimeUnit.SECONDS)
            return days + "天" + hours + "时" + minutes + "分" + leftSeconds + "秒";

        throw new RuntimeException("不支持的时间单位，仅支持（天，时，分，秒）");
    }

    public static boolean isOneDay(Date date1, Date date2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = simpleDateFormat.format(date1);
        String format2 = simpleDateFormat.format(date2);
        return format.equals(format2);
    }

    public static Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekday = calendar.get(Calendar.DAY_OF_WEEK); //获取时间的星期数
        int minus = weekday == 1 ? 6 : weekday - 2;
        if (minus > 0) {
            calendar.add(Calendar.DATE, -minus);//减n天
        }
        return calendar.getTime();
    }

    public static Date getLastDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //如果为周日则直接返回
        if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
            return date;
        }
        calendar.add(Calendar.DATE, 7 - calendar.get(Calendar.DAY_OF_WEEK) + 1);
        return calendar.getTime();
    }

    /**
     * 获取当前月份最后一天
     *
     * @param date
     * @return
     */
    public static Date getMaxMonthDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }
    /**
     * 获取当前月份最后一天
     *
     * @param date
     * @return
     */
    public static Date getFirstMonthDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取下一个月的最后一天.
     *
     * @return
     */
    public static Date getPerMonthMaxDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }
    /**
     * 获取下一个月的第一天.
     *
     * @return
     */
    public static Date getPerMonthFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取到当前年初
     * @param date
     * @return
     */
    public static Date getYearFirst(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.clear(Calendar.MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        calendar.set(Calendar.MINUTE, 0);
        //将秒至0
        calendar.set(Calendar.SECOND, 0);
        //将毫秒至0
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
