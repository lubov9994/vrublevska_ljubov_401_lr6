package javaee.diststudy.beans;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Stateful
@StatefulTimeout(value = 20, unit = TimeUnit.SECONDS)
public class LogBean {
    private ArrayList<String> logList = new ArrayList<>();

    public void logMethod(String name) {
        Date d = new Date();
        logList.add(name + " time " + (new SimpleDateFormat("hh:mm").format(d)));
    }

    public void toConsole() {
        for(int i = 0; i < logList.size();i++) {
            System.out.println(logList.get(i));
        }
    }

    @Remove
    public void clearLog() {
        logList.clear();
    }
}
