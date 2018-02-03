package org.falace.geocache

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.geo.Point
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class PlaceService {

  @Autowired
  private lateinit var template: RedisTemplate<Any?, Any?>

  fun get(key: String): Mono<Place> {
    val place = template.opsForHash<String, Place>().get("Place", key)
    return Mono.just(place?: Place("nowhere"))
  }

  // x,y are lng,lat
  fun put(key: String, place: Place): Long {
    template.opsForHash<String, Place>().put("Place", key, place)

    // just a random place to try... 
    val memberMap: Map<Any?, Point> = mutableMapOf(place to Point(52.459861, 13.370086))
    return template.opsForGeo().add(key, memberMap)
  }


}