package org.falace.geocache

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import javax.annotation.PreDestroy


@Configuration
@EnableRedisRepositories
class AppConfig {

  @Autowired
  lateinit var factory: RedisConnectionFactory

  @Bean
  @Primary
  fun redisConnectionFactory(): LettuceConnectionFactory {
    return LettuceConnectionFactory()
  }

  @Bean
  fun redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<Any, Any> {
    val redisTemplate = RedisTemplate<Any, Any>()
    redisTemplate.connectionFactory = connectionFactory
    return redisTemplate
  }

  @Bean
  fun stringRedisTemplate(connectionFactory: RedisConnectionFactory) = StringRedisTemplate(connectionFactory)

  @PreDestroy
  fun flushTestDb() = factory.connection.flushDb()

}