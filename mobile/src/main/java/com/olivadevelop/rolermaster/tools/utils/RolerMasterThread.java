package com.olivadevelop.rolermaster.tools.utils;

import android.content.Context;
import android.widget.ProgressBar;

import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.intefraces.ActionRolerMaster;

import static com.google.android.gms.internal.zzahn.runOnUiThread;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 15/01/2018.
 */

public class RolerMasterThread {
    private static final RolerMasterThread ourInstance = new RolerMasterThread();
    private Context context;

    private RolerMasterThread() {
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static RolerMasterThread getInstance() {
        return ourInstance;
    }

    /**
     * Llama a @newThread pero sin progressbar
     *
     * @param time   tiempo máximo del hilo
     * @param action acción que va a realizar al concluir
     */
    public void newThread(int time, ActionThread action) {
        newThread(time, action, null);
    }

    /**
     * Crea un hilo secundario para demorar una acción. Se admite un progresbar
     *
     * @param time        tiempo de demora máximo
     * @param action      la acción que va a realizar el hilo al concluir el tiempo
     * @param progressBar la barra de progreso que irá incrementando
     */
    public void newThread(final int time, final ActionThread action, final ProgressBar progressBar) {
        new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (waited < time) {
                        sleep(100);
                        if (progressBar != null) {
                            progressBar.incrementProgressBy(100);
                        }
                        waited += 100;
                    }
                } catch (InterruptedException e) {
                    Tools.Logger(context, e);
                } finally {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            action.run();
                        }
                    });
                }
            }
        }.start();
    }

    public static class ActionThread implements ActionRolerMaster {
        @Override
        public void run() {

        }
    }
}
