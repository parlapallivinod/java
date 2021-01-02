package in.rgukt.r081247.java.util.practice;

public class Sample {
    private String field1;
    private String field2;

    private Sample() {
    }

    public String getFiled1() {
        return field1;
    }

    private void setField1(String filed1) {
        this.field1 = filed1;
    }

    public String getField2() {
        return field2;
    }

    private void setField2(String field2) {
        this.field2 = field2;
    }

    public static class Builder {
        private String field1;
        private String field2;

        public Builder() {

        }

        public Builder field1(String field1) {
            this.field1 = field1;
            return this;
        }

        public Builder field2(String field2) {
            this.field2 = field2;
            return this;
        }

        public Sample build() {
            Sample sample = new Sample();
            if (this.field1 != null)
                sample.setField1(this.field1);
            if (this.field2 != null)
                sample.setField2(this.field2);
            return sample;
        }
    }

    @Override
    public String toString() {
        return "Sample{" +
                "field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                '}';
    }
}
