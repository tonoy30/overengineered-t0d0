package me.tonoy.recapspringboot.dbs;

import lombok.Getter;
import me.tonoy.recapspringboot.common.RandomString;
import org.apache.commons.lang3.math.NumberUtils;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.util.Properties;

@Getter
public class RandomIdGenerator implements IdentifierGenerator {
    public static final String ID_GENERATOR_NAME = "randomIdGenerator";

    public static final String ID_PREFIX_PARAMETER = "idPrefix";
    private static final String DEFAULT_ID_PREFIX = "";
    private String idPrefix;

    public static final String ID_LENGTH_PARAMETER = "idLength";
    private static final String DEFAULT_ID_LENGTH = "16";
    private String idLength;

    @Override
    public void configure(Type type, Properties parameters, ServiceRegistry serviceRegistry) {
        idPrefix = ConfigurationHelper.getString(ID_PREFIX_PARAMETER, parameters, DEFAULT_ID_PREFIX);
        idLength = ConfigurationHelper.getString(ID_LENGTH_PARAMETER, parameters, DEFAULT_ID_LENGTH);
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        return RandomString.next(idPrefix, NumberUtils.toInt(idLength));
    }
}
