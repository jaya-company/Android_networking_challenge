package com.example.android.networkconnect.util;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UseCaseThreadPoolScheduler {

    private static final int POOL_SIZE = 2;
    private static final int MAX_POOL_SIZE = 4;
    private static final int TIMEOUT = 30;
    private static UseCaseThreadPoolScheduler INSTANCE;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private ThreadPoolExecutor mThreadPoolExecutor;

    private UseCaseThreadPoolScheduler() {
        mThreadPoolExecutor = new ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, TIMEOUT,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(POOL_SIZE));
    }

    public static UseCaseThreadPoolScheduler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UseCaseThreadPoolScheduler();
        }
        return INSTANCE;
    }

    public void execute(Runnable runnable) {
        mThreadPoolExecutor.execute(runnable);
    }

    public void runOnUIThread(Runnable runnable) {
        runOnUIThread(runnable, 0);
    }

    public void runOnUIThread(Runnable runnable, long delay) {
        if (delay == 0) {
            mHandler.post(runnable);
        } else {
            mHandler.postDelayed(runnable, delay);
        }
    }
}