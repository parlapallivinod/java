package in.rgukt.r081247.java.algorithm.search;

public class BinarySearchCircularArray {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        int[] arr = {8,9,1,2,3,4,5,6,7};
        int element = 1;
        System.out.println(element);
        int res = findr(arr, element, 0, arr.length-1);
        System.out.println(res);
    }

    public static int find(int[] arr, int element,  int start, int end) {
        System.out.printf("find(%d, %d, %d)%n", element, start, end);
        if(start > end)
            return -1;

        int mid = start + (end - start) / 2;

        if(element == arr[mid]) {
            return mid;
        } else if(element < arr[mid]) {
            if(arr[start] < arr[mid]) {
                if(element >= arr[start])
                    return find(arr,element, start, mid-1);
                else {
                    return find(arr, element, mid+1, end);
                }
            } else {
                int i = start;
                while(i < mid) {
                    if(arr[i] > arr[i+1])
                        break;
                    i++;
                }
                if(i != mid)
                    return find(arr, element, i+1, mid-1);
                else
                    return find(arr, element, mid+1, end);
            }
        } else {
            if(arr[mid] < arr[end]) {
                if(element <= arr[end])
                    return find(arr,element, mid+1, end);
                else {
                    return find(arr, element, start, mid-1);
                }
            } else {
                int i = end;
                while(i > mid) {
                    if(arr[i] < arr[i-1])
                        break;
                    i--;
                }

                if(i != mid)
                    return find(arr, element, mid+1, i-1);
                else
                    return find(arr, element, start, mid-1);
            }
        }
    }

    public static int findr(int[] arr, int element,  int start, int end) {
        System.out.printf("find(%d, %d, %d)%n", element, start, end);
        if(start > end)
            return -1;

        int mid = start + (end - start) / 2;

        if(element == arr[mid]) {
            return mid;
        } else if(element < arr[mid]) {
            if(arr[start] < arr[mid]) {
                if(element >= arr[start])
                    return findr(arr,element, start, mid-1);
                else {
                    return findr(arr, element, mid+1, end);
                }
            } else {
                    return findr(arr, element, start, mid-1);
            }
        } else {
            if(arr[mid] < arr[end]) {
                if(element <= arr[end])
                    return findr(arr,element, mid+1, end);
                else {
                    return findr(arr, element, start, mid-1);
                }
            } else {

                    return findr(arr, element, mid+1, end);

            }
        }
    }
}