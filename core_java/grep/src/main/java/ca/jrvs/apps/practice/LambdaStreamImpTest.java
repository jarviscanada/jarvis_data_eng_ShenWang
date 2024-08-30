package ca.jrvs.apps.practice;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamImpTest {

    public static void main(String[] args) {
        LambdaStreamExcImp lse = new LambdaStreamExcImp();

        testCreateStrStream(lse);
        testToUpperCase(lse);
        testFilter(lse);
        testCreateIntStreamFromArray(lse);
        testCreateIntStreamRange(lse);
        testSquareRootIntStream(lse);
        testGetOdd(lse);
        testGetLambdaPrinter(lse);
        testPrintMessages(lse);
        testPrintOdd(lse);
        testFlatNestedInt(lse);
    }

    private static void testCreateStrStream(LambdaStreamExcImp lse) {
        Stream<String> stream = lse.createStrStream("a", "b", "c");
        List<String> result = stream.collect(Collectors.toList());
        System.out.println("testCreateStrStream: " + result + " | Expected: [a, b, c]");
    }

    private static void testToUpperCase(LambdaStreamExcImp lse) {
        Stream<String> stream = lse.toUpperCase("a", "b", "c");
        List<String> result = stream.collect(Collectors.toList());
        System.out.println("testToUpperCase: " + result + " | Expected: [A, B, C]");
    }

    private static void testFilter(LambdaStreamExcImp lse) {
        Stream<String> stream = lse.createStrStream("apple", "banana", "cherry");
        Stream<String> filteredStream = lse.filter(stream, "a");
        List<String> result = filteredStream.collect(Collectors.toList());
        System.out.println("testFilter: " + result + " | Expected: [cherry]");
    }

    private static void testCreateIntStreamFromArray(LambdaStreamExcImp lse) {
        int[] arr = {1, 2, 3, 4, 5};
        IntStream intStream = lse.createIntStream(arr);
        List<Integer> result = lse.toList(intStream);
        System.out.println("testCreateIntStreamFromArray: " + result + " | Expected: [1, 2, 3, 4, 5]");
    }

    private static void testCreateIntStreamRange(LambdaStreamExcImp lse) {
        IntStream intStream = lse.createIntStream(1, 5);
        List<Integer> result = lse.toList(intStream);
        System.out.println("testCreateIntStreamRange: " + result + " | Expected: [1, 2, 3, 4, 5]");
    }

    private static void testSquareRootIntStream(LambdaStreamExcImp lse) {
        int[] arr = {1, 4, 9, 16};
        IntStream intStream = lse.createIntStream(arr);
        DoubleStream doubleStream = lse.squareRootIntStream(intStream);
        List<Double> result = doubleStream.boxed().collect(Collectors.toList());
        System.out.println("testSquareRootIntStream: " + result + " | Expected: [1.0, 2.0, 3.0, 4.0]");
    }

    private static void testGetOdd(LambdaStreamExcImp lse) {
        IntStream intStream = lse.createIntStream(1, 10);
        IntStream oddStream = lse.getOdd(intStream);
        List<Integer> result = lse.toList(oddStream);
        System.out.println("testGetOdd: " + result + " | Expected: [1, 3, 5, 7, 9]");
    }

    private static void testGetLambdaPrinter(LambdaStreamExcImp lse) {
        Consumer<String> printer = lse.getLambdaPrinter("start>", "<end");
        System.out.print("testGetLambdaPrinter: ");
        printer.accept("Message body");
        // Expected output: start>Message body<end
    }

    private static void testPrintMessages(LambdaStreamExcImp lse) {
        Consumer<String> printer = lse.getLambdaPrinter("msg:", "!");
        String[] messages = {"a", "b", "c"};
        System.out.println("testPrintMessages:");
        lse.printMessages(messages, printer);
        // Expected output:
        // msg:a!
        // msg:b!
        // msg:c!
    }

    private static void testPrintOdd(LambdaStreamExcImp lse) {
        Consumer<String> printer = lse.getLambdaPrinter("odd number:", "!");
        IntStream intStream = lse.createIntStream(0, 5);
        System.out.println("testPrintOdd:");
        lse.printOdd(intStream, printer);
        // Expected output:
        // odd number:1!
        // odd number:3!
        // odd number:5!
    }

    private static void testFlatNestedInt(LambdaStreamExcImp lse) {
        Stream<List<Integer>> nestedInts = Stream.of(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );
        Stream<Integer> squaredInts = lse.flatNestedInt(nestedInts);
        List<Integer> result = squaredInts.collect(Collectors.toList());
        System.out.println("testFlatNestedInt: " + result + " | Expected: [1, 4, 9, 16, 25, 36, 49, 64, 81]");
    }
}
