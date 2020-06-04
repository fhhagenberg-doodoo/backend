package org.fhooe.fhhagenberg.mcm.ci.backend.configurations

import io.r2dbc.pool.PoolingConnectionFactoryProvider.MAX_SIZE
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions.*
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.connectionfactory.init.CompositeDatabasePopulator
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator
import org.springframework.transaction.annotation.EnableTransactionManagement

@Profile("dev")
@Configuration
class DBConfiguration: AbstractR2dbcConfiguration() {

    @Value("\${spring.r2dbc.username}")
    private lateinit var username: String

    @Value("\${spring.r2dbc.host}")
    private lateinit var host: String

    @Value("\${spring.r2dbc.driver}")
    private lateinit var driver: String

    @Value("\${spring.r2dbc.database}")
    private lateinit var database: String

    @Value("\${spring.r2dbc.password}")
    private lateinit var password: String

    @Bean(name=["postgreConnectionFactory"])
    override fun connectionFactory(): ConnectionFactory {
        return ConnectionFactories.get(
                builder()
                        .option(DRIVER, this.driver)
                        .option(HOST, this.host)
                        .option(PORT, 5432)
                        .option(USER, this.username)
                        .option(PASSWORD, this.password)
                        .option(DATABASE, this.database)
                        .option(MAX_SIZE, 40)
                        .build()
        )
    }

    @Bean
    fun initializer(
            @Qualifier("postgreConnectionFactory") connectionFactory: ConnectionFactory?
    ): ConnectionFactoryInitializer? {
        val initializer = ConnectionFactoryInitializer()
        initializer.setConnectionFactory(connectionFactory!!)
        val populator = CompositeDatabasePopulator()
        populator.addPopulators(ResourceDatabasePopulator(ClassPathResource("schema.sql")))
        populator.addPopulators(ResourceDatabasePopulator(ClassPathResource("data.sql")))
        initializer.setDatabasePopulator(populator)
        return initializer
    }
}