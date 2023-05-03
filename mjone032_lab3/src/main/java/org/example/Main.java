package edu.ucr.cs.cs167.mjone032;


import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;



public class Main {

    static class IsEven implements Function<Integer, Boolean> {
        @Override
        public Boolean apply(Integer x) {
            return x % 2 == 0;
        }
    }

    static class IsDivisibleByThree implements Function<Integer, Boolean> {
        @Override
        public Boolean apply(Integer x) {
            return x % 3 == 0;
        }
    }

    public static Function<Integer, Boolean> combineWithAnd(Function<Integer, Boolean> ... filters) {


        Function<Integer, Boolean> result = new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer integer) {
                return Arrays.stream(filters).allMatch(i -> i.apply(integer));
            }
        };


        return result;

    }
    public static Function<Integer, Boolean> combineWithOr(Function<Integer, Boolean> ... filters) {

        Function<Integer, Boolean> result = new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer integer) {
                return Arrays.stream(filters).anyMatch(i->i.apply(integer));
            }
        };


        return result;

    }

    public static void main(String[] args) {

        Function<Integer, Boolean> divisibleByFive = new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer x) {
                return x % 5 == 0;
            }
        };

        Function<Integer, Boolean> divisibleByTen = x -> x % 10 == 0;


        if(args.length<3){
            System.out.printf("Error: At least three parameters expected, from, to, and base.\n");
        }
        else {

            int arg1 = Integer.parseInt(args[0]);
            int arg2 = Integer.parseInt(args[1]);


            Boolean and;

            if(args[2].contains(",")){
                and = true;
            }
            else{
                and = false;
            }

            String list[] = null;

            if(and) {
                list = args[2].split(",");
            }
            else {
                list = args[2].split("v");
            }




            Function<Integer, Boolean>[] filters = new Function[list.length];
            for(int i = 0; i < filters.length; i++){

                int v = Integer.valueOf(list[i]);
                filters[i] = new Function<Integer, Boolean>(){
                    @Override
                    public Boolean apply(Integer integer) {
                        return integer % v == 0;
                    }
                };

            }

            Function<Integer, Boolean> result;
            if(and) {
                result = combineWithAnd(filters);
            }
            else{
                result = combineWithOr(filters);
            }

            printNumbers(arg1, arg2, result);





            /*
            if (filter == 2) {
                filterC = new IsEven();
            } else if (filter == 3) {
                filterC = new IsDivisibleByThree();
            } else if (filter == 5){
                filterC = divisibleByFive;
            } else if(filter == 10){
                filterC = divisibleByTen;
            }

             */

            /*
            Function<Integer, Boolean> divisibleByBase = x -> x % filter == 0;
            filterC = divisibleByBase;
            printNumbers(arg1, arg2, filterC);
             */


            //Q1) new Main.IsEven().apply(arg1);
            //Q2) After adding 'filter = 0' at the end of the function it does not compile anymore.
            //Q3) local variables referenced from a lambda expression must be final or effectively final

        }

    }



    public static void printNumbers(int from, int to, Function<Integer, Boolean> filter){


        for(Integer i = from; i <= to; i+=1){

            Boolean read = filter.apply(i);
            if(read){
                System.out.printf("Acceptable value: %d\n", i);
            }
        }

        /*
        if(Objects.equals(filter.getClass(), IsEven.class)){
            printEvenNumbers(from,to);
        }
        else if(Objects.equals(filter.getClass(), IsDivisibleByThree.class)){
            printNumbersDivisibleByThree(from,to);
        }
        else{

            System.out.printf("Error: unexpected object found.\n");
        }
        */


    }

    public static void printEvenNumbers(int from, int to){

        System.out.printf("Printing numbers in the range [%d,%d]\n", from, to);

        if(from%2 != 0) {
            ++from;
        }

        for(int i = from; i <= to; i+=2){

            System.out.println(i);
        }

        System.out.printf("End of even numbers\n");

    }

    public static void printNumbersDivisibleByThree(int from, int to) {

        System.out.printf("Printing numbers in the range [%d,%d]\n", from, to);

        for(int i = from; i <= to; i+=1){

            if(i%3 == 0){
                System.out.println(i);
            }

        }

        System.out.printf("End of divisible by 3 numbers\n");

    }



}




