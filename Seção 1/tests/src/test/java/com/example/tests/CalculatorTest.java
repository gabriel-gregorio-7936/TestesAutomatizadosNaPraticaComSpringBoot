package com.example.tests;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {
    @Test
    public void testSum() {
        Calculator calculator = new Calculator();
        assertThat(calculator.sum(1, 1)).isEqualTo(2);
    }
}
