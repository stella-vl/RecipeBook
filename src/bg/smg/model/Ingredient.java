package bg.smg.model;

public class Ingredient {
    private String name;
    private String metric;
    private double weight;
    
    public Ingredient() {
        this.name = "";
        this.metric = "";
        this.weight = 0;
    }

    public Ingredient(String name, String metric, double weight) {
        this.name = name;
        this.metric = metric;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", metric='" + metric + '\'' +
                ", weight=" + weight +
                '}';
    }
}
