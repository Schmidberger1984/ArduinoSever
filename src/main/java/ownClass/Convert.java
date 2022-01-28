package ownClass;


public class Convert {

    double data;
    int[] resistance={485,510,562,617,677,740,807,877,951,990,1029,1111,1196,1286,1376,1475,1575,1679,1786,1896,1950,2003,2103,2189};
    int[] grad={-55,-50,-40,-30,-20,-10,0,10,20,25,30,40,50,60,70,80,90,100,110,120,125,130,140,150};

    public Convert(int data) {
        this.data = data;
    }

    public double toGrad() {
        try {
            double calculate;
            calculate = data * (5.0 / 4096.0);  //voltage from the seonsor  --- 5V ref and 10 Bit AD-Converter
            calculate = calculate / ((5 - calculate) / 1000);  // calculate the Resistance of the Sensor --- Pullupresistor with 1k
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
    public double toHumidity() {
        return 33.3;
    }
}
