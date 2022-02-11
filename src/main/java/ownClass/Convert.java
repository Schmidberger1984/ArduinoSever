package ownClass;


public class Convert {

    double datatemp;
    double datahumid;
    int[] resistance={510,562,617,674,736,802,872,946,985,1024,1106,1190,1280,1373,1469,1570,1674,1782,1894,2010,2096,2201};
    int[] grad={-50,-40,-30,-20,-10,0,10,20,25,30,40,50,60,70,80,90,100,110,120,130,140,150};

    public Convert(int datatemp,int datahumid) {
        this.datatemp = datatemp;
        this.datahumid = datahumid;
    }
    public Convert(int datatemp) {
        this.datatemp = datatemp;
    }

    /***
     * convert raw data (bit value) to temperature
     * @return temperature value
     */

    public double toGrad() {
        try {
            double calculate;
            calculate = (datatemp+250) * (3.3 / 4096.0);  //voltage from the seonsor  --- 3,3V ref and 10 Bit AD-Converter
            calculate = calculate / ((3.3 - calculate) / 1000);  // calculate the Resistance of the Sensor --- Pullupresistor with 1k
            int posRes;
            for (posRes = 0; posRes < resistance.length; posRes++) {
                if (resistance[posRes] > calculate) break;
            }
            double diffRes;
            double diffGrad;
            double diffCalc;
            double mul;
            diffRes = resistance[posRes] - resistance[posRes - 1];
            diffGrad = grad[posRes] - grad[posRes - 1];
            diffCalc = calculate - resistance[posRes - 1];
            mul = diffRes / diffCalc;
            return grad[posRes - 1] + diffGrad / mul;
        }catch ( Exception e) {
            return -99999.0;
        }
    }

    /**
     * generate from adc-value a percent value
     * @return humidity value
     */

    public double toHumidity() {
        return datahumid/41;
    }
}
