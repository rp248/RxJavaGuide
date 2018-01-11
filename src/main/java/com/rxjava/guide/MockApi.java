package com.rxjava.guide;

public class MockApi {

    public void getMockData(MockApiListener mockApiListener, int noOfItems, int pauseTime) {
        final int itemsToSend = noOfItems;
        final int sleepTime = pauseTime;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i <= itemsToSend) {
                    mockApiListener.listen(i);
                    i++;
                    try {
                        Thread.sleep(sleepTime);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                mockApiListener.listen(-1);
            }
        });
        thread.start();
    }
}
