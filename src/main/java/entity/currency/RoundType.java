package entity.currency;

public enum RoundType {
        CEIL {
        protected double roundMethod (double value) {
                return Math.ceil(value);
            }
        },
        FLOOR {
        protected double roundMethod (double value) {
                return Math.floor(value);
            }
        },
        ROUND {
        protected double roundMethod(double value) {
                return Math.round(value);
            }
        };

    protected abstract double roundMethod(double value);

    public int round (double roundedValue, int accuracy){
        int rate = pow10(accuracy);
        return (int)(this.roundMethod(roundedValue/rate) * rate);
    }

    private static int pow10(int a) {
        if (a > 0) {
            a--;
            return 10 * pow10(a);
        }
        return 1;
    }
}
