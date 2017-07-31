package com.interview.services;

import com.interview.model.MessageModel;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by robert.j.ssemmanda on 29/07/2017.
 * this class will broadcast all save changes made via the api to
 * all ui sessions active for the users to view data
 *
 */
public class BroadCaster implements Serializable {

    /*
    * Variable */
    static ExecutorService executorService = Executors.newSingleThreadExecutor();

    //interface to be implemented by our listeners
    public interface BroadcastListener {
        void receiveBroadcast(Object messageModel);
    }

    //list to store all our listeners
    private static LinkedList<BroadcastListener> listeners = new LinkedList<>();

    //method for our uis to register
    public static synchronized void register(BroadcastListener listener) {
        listeners.add(listener);
    }

    //method for ui to unregister
    public static synchronized void unregister(BroadcastListener listener) {
        listeners.remove(listener);
    }

    //inform our listeners we have data to look into
    public static synchronized void broadcast(final Object message) {
        for (final BroadcastListener listener: listeners)
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    listener.receiveBroadcast(message);
                }
            });
    }

}
