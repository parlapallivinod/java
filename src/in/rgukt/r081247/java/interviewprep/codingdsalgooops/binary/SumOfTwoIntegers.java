package in.rgukt.r081247.java.interviewprep.codingdsalgooops.binary;

class SumOfTwoIntegers {
    public int getSum(int a, int b) {
        int temp;
        while (b != 0) {
            temp = (a & b) << 1;
            a = a ^ b;
            b = temp;
        }
        return a;
    }
}
