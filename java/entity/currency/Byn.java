package entity.currency;

public class Byn implements Comparable<Byn>{
    private final int value;

    public Byn(int value) {
        this.value = value;
    }
    
    public Byn(int rubs, int copecks) {
    	this.value = rubs * 100 + copecks;
    }

    public Byn(Byn object) {
        this(object.value);
    }
    
    public Byn (String str) {
    	String[] rubArr = str.split("\\.");
		int rubs = Integer.parseInt(rubArr[0]);
		int copecks = Integer.parseInt(rubArr[1]);
		value = rubs*100 + copecks;
    }
    
    public int getIntValue() {
    	return value;
    }

    public Byn multiply(int i) {
        return new Byn(value*i);
    }

    public Byn multiply(double i, RoundType type, int acc){
        return new Byn(type.round(value*i, acc));
    }

    public Byn devide(int i, RoundType type, int acc) {
        return new Byn(type.round((double)(value/i), acc));
    }

    public Byn devide(double i, RoundType type, int acc) {
        return multiply(1.0/i, type, acc);
    }

    public Byn add(Byn obj) {
        return new Byn(value + obj.value);
    }

    public Byn substract(Byn obj) {
        return new Byn(value - obj.value);
    }

    public Byn round(RoundType type, int accuracy) {
        return new Byn(type.round(value, accuracy));
    }

    @Override
    public int compareTo(Byn object) {
        return value - object.value;
    }

    @Override
    public String toString() {
        return value / 100 + "." + value % 100 / 10 + value % 10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Byn byn = (Byn) o;

        return value == byn.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
