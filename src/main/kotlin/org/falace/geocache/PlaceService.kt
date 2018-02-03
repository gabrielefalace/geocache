package org.falace.geocache

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class PlaceService {

  @Autowired
  private lateinit var template: RedisTemplate<Any?, Any?>

  fun get(key: String): Mono<String> {
    val place = template.opsForHash<String, Place>().get("Place", key)
    return Mono.just(place?.toString() ?: "damn")
  }

  fun put(key: String, value: Place) {
    template.opsForHash<String, Place>().put("Place", key, value)
  }


}