package com.common.baselib.utils.time;

import android.widget.TextView;

import com.common.baselib.utils.ValidationUtils;

import java.util.LinkedList;
import java.util.Random;

/**
 * 数字跳动动画 工具类
 * Created by Sudroid on 2017/8/30.
 */

public class NumAnim {

    //每秒刷新多少次
    private static final int COUNTPERS = 100;


    public static void startAnim(TextView textV, double num) {
        startAnim(textV, num, 500, false);
    }

    public static void startAnim(TextView textV, double num, long time, boolean isInteger) {
        if (num <= 1) {
            textV.setText(NumberFormat(num, 2));
            return;
        }


        Double[] nums = splitnum(num, (int) ((time / 1000f) * COUNTPERS));
        Counter counter = null;
        if (isInteger) {
            counter = new Counter(textV, nums, time, isInteger);
        } else {
            counter = new Counter(textV, nums, time, isInteger);
        }

        textV.removeCallbacks(counter);
        textV.post(counter);
    }

    private static Double[] splitnum(double num, int count) {
        Random random = new Random();
        double numtemp = num;
        double sum = 0;
        LinkedList<Double> nums = new LinkedList<Double>();
        nums.add(0d);
        while (true) {
            double nextFloat = NumberFormatFloat(
                    (random.nextDouble() * num * 2f) / (double) count,
                    2);
            System.out.println("next:" + nextFloat);
            if (numtemp - nextFloat >= 0) {
                sum = NumberFormatFloat(sum + nextFloat, 2);
                nums.add(sum);
                numtemp -= nextFloat;
            } else {
                nums.add(num);
                return nums.toArray(new Double[0]);
            }
        }
    }


    private static class Counter implements Runnable {

        private final TextView view;
        private Double[] nums;
        private long pertime;
        private boolean isInteger;

        private int i = 0;

        Counter(TextView view, Double[] nums, long time, boolean isInteger) {
            this.view = view;
            this.nums = nums;
            this.isInteger = isInteger;
            this.pertime = time / nums.length;
        }

        @Override
        public void run() {
            if (i > nums.length - 1) {
                view.removeCallbacks(Counter.this);
                return;
            }
            if (isInteger) {
                view.setText(ValidationUtils.parseMoney(NumberFormat(nums[i++], 0)));
            } else {
                view.setText(ValidationUtils.parseMoney(NumberFormat(nums[i++], 2)));
            }
            view.removeCallbacks(Counter.this);
            view.postDelayed(Counter.this, pertime);
        }
    }

    private static String NumberFormat(double f, int m) {
        return String.format("%." + m + "f", f);
    }

    private static double NumberFormatFloat(double f, int m) {
        String strfloat = NumberFormat(f, m);
        return Double.parseDouble(strfloat);
    }

}