package model;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimerModel {
    private int minutes;
    private int seconds;
    private Timer timer;
    private TimerTask timerTask;

    private boolean isPaused;
    private boolean isTimeOut = false;

    public CountdownTimerModel(int minutes) {
        this.minutes = minutes;
        this.seconds = minutes * 60;
        this.isPaused = false;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void startTimer(JLabel label) {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (!isPaused) {
                    if (seconds > 0) {
                        seconds--;
                        setTimeText(label);
                    } else {
                        stopTimer();
                        timeOut();
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
    }

    public void stopTimer() {
        if (timerTask != null) {
            timerTask.cancel();
        }
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

    public void pauseTimer() {
        isPaused = true;
    }

    public void resumeTimer() {
        isPaused = false;
    }

    private String formatTime() {
        int displayMinutes = seconds / 60;
        int displaySeconds = seconds % 60;
        return String.format("%02d:%02d", displayMinutes, displaySeconds);
    }

    public void setTimeText(JLabel label) {
        label.setText(formatTime());
    }

    public void timeOut() {
        isTimeOut = true;
    }

    public boolean isTimeOut() {
        return  isTimeOut;
    }
}
