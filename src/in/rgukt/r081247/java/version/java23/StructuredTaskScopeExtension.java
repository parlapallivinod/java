package in.rgukt.r081247.java.version.java23;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.*;

import static java.util.concurrent.Future.State.RUNNING;

public class StructuredTaskScopeExtension {
    public static void main(String[] args) {
        Quotation bestQuotation = getBestQuotation();
        System.out.println(bestQuotation);
    }

    public static Quotation getBestQuotation() {
        try(QuotationScope scope = new QuotationScope()) {
            scope.fork(()->callQuotation());
            scope.fork(()->callQuotation());
            scope.fork(()->callQuotation());
            scope.fork(()->callQuotation());
            scope.fork(()->callQuotation());

            try {
                scope.joinUntil(Instant.now().plusMillis(300));
            } catch (TimeoutException e) {
                System.out.println(e.toString());
            }

            return scope.getBestQuotation();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Quotation callQuotation() {
        Random r = new Random();
        Long waitTime = r.nextLong(10, 1000);
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {

        }
        if(r.nextBoolean())
            throw new RuntimeException("Failed");
        return new Quotation(r.nextInt(10, 100), "Apple");
    }

    public static class QuotationScope extends StructuredTaskScope<Quotation> {
        private Collection<Quotation> quotations = new ConcurrentLinkedDeque<>();
        private Collection<Throwable> exceptions = new ConcurrentLinkedDeque<>();

        @Override
        protected void handleComplete(StructuredTaskScope.Subtask<? extends Quotation> subtask) {
            switch(subtask.state()) {
                case Subtask.State.SUCCESS -> {
                    System.out.println("Success: "+ subtask.get());
                    this.quotations.add(subtask.get());
                }
                case Subtask.State.FAILED -> {
                    System.out.println("Failed: " + subtask.exception().toString());
                    this.exceptions.add(subtask.exception());
                }
                case Subtask.State.UNAVAILABLE -> {}
            }
        }

        public QuotationException exception() {
            QuotationException exception = new QuotationException("Got No Quotations");
            this.exceptions.forEach(exception::addSuppressed);
            return exception;
        }

        public Quotation getBestQuotation() {
            return quotations.stream()
                    .min(Comparator.comparing(Quotation::price))
                    .orElseThrow(this::exception);
        }

    }
    public static record Quotation(int price, String name) {
    }

    public static class QuotationException extends RuntimeException {
        public QuotationException(String message) {
            super(message);
        }
    }
}


