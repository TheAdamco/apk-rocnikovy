import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;


class MainActivity  extends Activity implements SensorEventListener {
    TextView textView;
    StringBuilder builder = new StringBuilder();

    float [] history = new float[3];
    String [] direction = {"NONE","NONE","NONE"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        setContentView(textView);

        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
        manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);

    }

    String mooves="";
    int previous=0;




    String checkcode(){
        int pom=mooves.length()-previous+1;
        Integer[][] arrays = new Integer[10][pom];
        for (int i=0;i<10;i++){
            arrays[i][0]=i;
        }
        int k=0;
        for (int i=previous+1; i<mooves.length();i++){
            for (int j = 0; j < 10; j++) {
                //if
                switch (mooves.charAt(i)) {
                    case 'l':
                        switch (j) {
                            case 2:
                                arrays[j][k] = 1;
                                break;
                            case 3:
                                arrays[j][k] = 2;
                                break;
                            case 5:
                                arrays[j][k] = 4;
                                break;
                            case 6:
                                arrays[j][k] = 5;
                                break;
                            case 8:
                                arrays[j][k] = 7;
                                break;
                            case 9:
                                arrays[j][k] = 8;
                                break;
                            default:
                                arrays[j][k] = -1;
                        }
                        break;
                    case 'r':
                        switch (j) {
                            case 1:
                                arrays[j][k] = 2;
                                break;
                            case 2:
                                arrays[j][k] = 3;
                                break;
                            case 4:
                                arrays[j][k] = 5;
                                break;
                            case 5:
                                arrays[j][k] = 6;
                                break;
                            case 7:
                                arrays[j][k] = 8;
                                break;
                            case 8:
                                arrays[j][k] = 9;
                                break;
                            default:
                                arrays[j][k] = -1;
                        }
                        break;
                    case 'd':
                        switch (j) {
                            case 4:
                                arrays[j][k] = 1;
                                break;
                            case 5:
                                arrays[j][k] = 2;
                                break;
                            case 6:
                                arrays[j][k] = 3;
                                break;
                            case 7:
                                arrays[j][k] = 4;
                                break;
                            case 8:
                                arrays[j][k] = 5;
                                break;
                            case 9:
                                arrays[j][k] = 6;
                                break;
                            default:
                                arrays[j][k] = -1;
                        }
                        break;
                    case 'u':
                        switch (j) {
                            case 0:
                                arrays[j][k] = 2;
                                break;
                            case 1:
                                arrays[j][k] = 4;
                                break;
                            case 2:
                                arrays[j][k] = 5;
                                break;
                            case 3:
                                arrays[j][k] = 6;
                                break;
                            case 4:
                                arrays[j][k] = 7;
                                break;
                            case 5:
                                arrays[j][k] = 8;
                                break;
                            case 6:
                                arrays[j][k] = 9;
                                break;
                            default:
                                arrays[j][k] = -1;
                        }
                        break;
                    default:
                        arrays[j][k] = -1;
                }

            }
            k++;

        }
        String ret="";
        for (int i=0;i<10;i++){
            int p=0;
            if (arrays[i][pom-2]!=-1) {
                p=arrays[i][pom-2];
                ret=ret+(char)p;
            }
        }
        return ret;
    }
    @Override
    public void onSensorChanged(SensorEvent event) {

        float xChange = history[0] - event.values[0];
        float yChange = history[1] - event.values[1];
        float zChange = history[2] - event.values[2];

        history[0] = event.values[0];
        history[1] = event.values[1];
        history[2] = event.values[2];


        int check=0;
        String ret;
        if (xChange > 5){
            direction[0] = "LEFT";
            System.out.println(event.values[0]+"  "+event.values[1]+"  "+event.values[2]);
            mooves+="l";
            check=1;
            builder.append(",L ");
        }
        else if (xChange < -5){
            direction[0] = "RIGHT";
            System.out.println(event.values[0]+"  "+event.values[1]+"  "+event.values[2]);
            mooves+="r";
            check=1;
            //builder.append(",R ");
        }
        if (yChange > 5){
            direction[1] = "DOWN";
            System.out.println(event.values[0]+"  "+event.values[1]+"  "+event.values[2]);
            mooves+="d";
            check=1;
            //builder.append(",D ");
        }
        else if (yChange < -5){
            System.out.println(event.values[0]+"  "+event.values[1]+"  "+event.values[2]);
            direction[1] = "UP";
            mooves+="u";
            check=1;
            //builder.append(",U ");
        }
        if (zChange > 5){
            direction[2] = "BACKWARD";
            System.out.println(event.values[0]+"  "+event.values[1]+"  "+event.values[2]);
            mooves+="b";
            check=1;
            //builder.append(",B ");
        }
        else if (zChange < -5){
            direction[2] = "FORWARD";
            System.out.println(event.values[0]+"  "+event.values[1]+"  "+event.values[2]);
            check=1;
            mooves+="f";
            //builder.append(",F ");
            //ret=checkcode();
            //builder.append(ret);
        }

        //System.out.println("X: "+event.values[0]+"X: "+event.values[0]+" Y: "+event.values[1]+" Z: "+event.values[2]);


        //if (check==1) textView.setText(builder.toString());

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // nothing to do here
    }

}