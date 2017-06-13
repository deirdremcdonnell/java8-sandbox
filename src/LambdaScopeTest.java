/**
 * Copyright 2017 Dell Inc. or its subsidiaries. All Rights Reserved.
 */

import java.util.function.Consumer;

/**
 * LambdaScopeTest
 */
public class LambdaScopeTest {
    public int x = 0;
    private boolean isTrue = false;

    class FirstLevel {

        public int x = 1;
        private boolean isTrue = true;
        void methodInFirstLevel(int x) {
             boolean localVar = true;

            // The following statement causes the compiler to generate
            // the error "local variables referenced from a lambda expression
            // must be final or effectively final" in statement A:
            //
         //   x = 99;

            Consumer<Integer> myConsumer = y ->
            {
                boolean isTrue = false;
                System.out.println("x = " + x); // Statement A
                System.out.println("y = " + y);
                System.out.println("this.x = " + this.x);
                System.out.println("this.isTrue = " + LambdaScopeTest.this.isTrue);
                System.out.println("LambdaScopeTest.this.x = " +
                        LambdaScopeTest.this.x);
                System.out.println("localVar = " + localVar);
            };

            myConsumer.accept(x);

        }
    }

    public static void main(String... args) {
        LambdaScopeTest scopeTest = new LambdaScopeTest();
        LambdaScopeTest.FirstLevel fl = scopeTest.new FirstLevel();
        fl.methodInFirstLevel(23);
    }
}