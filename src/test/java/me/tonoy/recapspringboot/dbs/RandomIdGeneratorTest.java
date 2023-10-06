package me.tonoy.recapspringboot.dbs;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.hibernate.type.Type;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;


class RandomIdGeneratorTest {
    private RandomIdGenerator generator;
    private Type type;
    private ServiceRegistry serviceRegistry;

    @BeforeEach
    public void setup() {
        generator = new RandomIdGenerator();
        type = mock(Type.class);
        serviceRegistry = mock(ServiceRegistry.class);
    }

    @Test
    public void configureSetsDefaultValuesWhenNoParametersProvided() {
        Properties parameters = new Properties();

        generator.configure(type, parameters, serviceRegistry);

        assertEquals("", generator.getIdPrefix());
        assertEquals(16, generator.getIdLength());
    }

    @Test
    public void configureSetsValuesFromParameters() {
        Properties parameters = new Properties();

        parameters.setProperty(RandomIdGenerator.ID_PREFIX_PARAMETER, "prefix");
        parameters.setProperty(RandomIdGenerator.ID_LENGTH_PARAMETER, "10");

        generator.configure(type, parameters, serviceRegistry);

        assertEquals("prefix", generator.getIdPrefix());
        assertEquals(10, generator.getIdLength());
    }

    @Test
    public void generateReturnsRandomString() {
        SharedSessionContractImplementor session = mock(SharedSessionContractImplementor.class);

        Object object = new Object();
        Properties parameters = new Properties();

        parameters.setProperty(RandomIdGenerator.ID_PREFIX_PARAMETER, "prefix");
        parameters.setProperty(RandomIdGenerator.ID_LENGTH_PARAMETER, "10");

        generator.configure(type, parameters, serviceRegistry);

        String generatedId = (String) generator.generate(session, object);

        // You can't predict the exact value, but you can check if it's a string and has the expected length.
        assertThat(17).isEqualTo(generatedId.length());
        assertTrue(generatedId.startsWith("prefix"));
    }
}